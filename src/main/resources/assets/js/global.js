
/* AJAX */
function launchAJAX(script, data, callback) {
	const http = new XMLHttpRequest();

	http.onreadystatechange = function() {
		if (http.readyState !== 4) return;
		let res = http.responseText;
		if (http.responseText.startsWith('{')) res = JSON.parse (http.responseText);
		if (!callback(res, http.status, http.statusText)) {
			showAlertDanger('AJAX Error', 'Unexpected <code>' + http.status + ' - ' + http.statusText + '</code> @ <code>' + script + '</code> | Please report this error.');
		}
	};

	http.open('POST', script);
	http.setRequestHeader('Content-Type', 'application/json');
	http.send(JSON.stringify(data));
}
/* AJAX */





/* Show Alert */
function showAlertDanger(title, description) {
	let html = '';
	html += '<div class="alert alert-danger alert-dismissible fade show" role="alert">'
	html += '<strong class="mr-2">' + title + '</strong>';
	html += description;
	html += '<button class="close" data-dismiss="alert">&times;</button>';
	html += '</div>';
	document.getElementById('error_container').innerHTML += html;
}
/* Show Alert */





/* Remove Error Border */
function removeErrorBorder(e) {
	e.classList.remove('border-danger');
}
/* Remove Error Border */





/* Utility */
function isEmail(str) {
	const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(str);
}

function checkValid(element, valid) {
	if (valid) {
		element.classList.remove('border-danger');
		return false;
	} else {
		element.classList.add('border-danger');
		return true;
	}
}
/* Utility */





/* Cookies */
function setCookie(name, value) {
	const d = new Date();
	d.setTime(d.getTime() + (30 * 24 * 60 * 60 * 1000));
	const expires = 'expires=' + d.toUTCString();
	document.cookie = name + '=' + value + ';' + expires + ';path=/';
}

function getCookie(name) {
	name += '=';
	const ca = document.cookie.split(';');
	for(let i = 0; i < ca.length; i++) {
		let c = ca[i];
		while (c.charAt(0) === ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) === 0) {
			return c.substring(name.length, c.length);
		}
	}
	return '';
}

function unsetCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
}
/* Cookies */





/* Clipboard */
function copyToClipboard(text) {
	const input = document.createElement('INPUT');
	input.type = 'text';
	input.value = text;
	document.body.append(input);

	input.select();
	input.setSelectionRange(0, 99999);

	document.execCommand('copy');

	input.remove();
}
/* Clipboard */





/* Relative Time */
function relativeTime(timestamp) {
	const now = Date.now();
	let diff = timestamp - now;
	if (diff < 0) diff = -diff;
	if (diff < 1000) return 'just now';

	let days = 0;
	let hours = 0;
	let minutes = 0;
	let seconds = 0;

	if (diff >= 1000*60*60*24) {
		days = Math.floor(diff / (1000*60*60*24));
		diff = diff % (1000*60*60*24);
	}
	if (diff >= 1000*60*60) {
		hours = Math.floor(diff / (1000*60*60));
		diff = diff % (1000*60*60);
	}
	if (diff >= 1000*60) {
		minutes = Math.floor(diff / (1000*60));
		diff = diff % (1000*60);
	}
	if (diff >= 1000) {
		seconds = Math.floor(diff / (1000));
	}

	let str = '';
	let str2;
	let count = 0;
	const limit = 2;
	const concat = function (str, amount, count, limit, singleString, multiString) {
		if (amount === 0 || count >= limit) return null;
		if (count !== 0) str += ', ';
		str += amount + ' ' + (amount === 1 ? singleString : multiString);
		return str;
	};

	str2 = concat(str, days, count, limit, 'day', 'days');
	if (str2) { str = str2; count++; }

	str2 = concat(str, hours, count, limit, 'hour', 'hours');
	if (str2) { str = str2; count++; }

	str2 = concat(str, minutes, count, limit, 'minute', 'minutes');
	if (str2) { str = str2; count++; }

	str2 = concat(str, seconds, count, limit, 'second', 'seconds');
	if (str2) { str = str2; count++; }

	const i = str.lastIndexOf(', ');
	if (i !== -1) str = str.substring(0, i) + ' and ' + str.substring(i + 2);

	if (now > timestamp) str = str + ' ago';
	else str = 'in ' + str;
	return str;
}
/* Relative Time */





/* Update Timestamps */
function updateTimestamps() {
	document.querySelectorAll('.timestamped').forEach(function(e) {
		if (!e.getAttribute('data-timestamp')) {
			e.innerHTML = 'ERROR';
			e.classList.remove('timestamped');
			return;
		}
		e.innerHTML = relativeTime(e.getAttribute('data-timestamp'));
	});
}

setInterval(function() {
	updateTimestamps();
}, 1000);

window.addEventListener('load', function() {
	updateTimestamps();
});
/* Update Timestamps */
