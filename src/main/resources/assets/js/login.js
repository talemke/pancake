
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
		return false;
	});
}
/* Login */
