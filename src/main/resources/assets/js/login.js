
/* Login */
function login() {
	// Get elements and values
	const usernameElement = document.getElementById('username');
	const passwordElement = document.getElementById('password');
	const submit = document.getElementById('submit');
	const username = usernameElement.value.trim();
	const password = passwordElement.value.trim();


	// Validation
	let error = false;
	error |= checkValid(usernameElement, username !== '');
	error |= checkValid(passwordElement, password !== '');

	if (error) return;


	// Disable form
	submit.disabled = true;


	// Format data
	const data = {};
	data.username = username;
	data.password = password;


	// Launch AJAX
	launchAJAX('/api/v0/auth/login', data, function(res, status, text) {
		submit.disabled = false;
		if (status != 200) return false;

		if (res.error) {
			showAlert('danger', null, res.error, true, 'sm');
			return true;
		}

		setCookie('PancakeSessionID', res.token);
		window.location.href = '/';
		return true;
	});
}
/* Login */
