
/* Register */
function registerAccount() {
	// Get elements and values
	const usernameElement = document.getElementById('username');
	const passwordElement = document.getElementById('password');
	const password2Element = document.getElementById('password2');
	const submit = document.getElementById('submit');
	const username = usernameElement.value.trim();
	const password = passwordElement.value.trim();
	const password2 = password2Element.value.trim();


	// Validation
	let error = false;
	error |= checkValid(usernameElement, username !== '');
	error |= checkValid(passwordElement, password !== '');
	error |= checkValid(password2Element, password === password2);

	if (error) return;


	// Disable form
	submit.disabled = true;


	// Format data
	const data = {};
	data.username = username;
	data.password = password;


	// Launch AJAX
	launchAJAX('/api/v0/auth/register', data, function(res, status, text) {
		submit.disabled = false;
		if (status !== 200) return false;

		if (res.error) {
			showAlert('danger', null, res.error, true, 'sm');
			return true;
		}

		window.location.href = '/auth/login';
		return true;
	});
}
/* Register */
