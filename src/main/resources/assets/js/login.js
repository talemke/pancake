
/* Login */
function login() {
	// Get elements and values
	const usernameElement = document.getElementById('username');
	const passwordElement = document.getElementById('password');
	const username = usernameElement.value.trim();
	const password = passwordElement.value.trim();


	// Validation
	let error = false;
	error |= checkValid(usernameElement, username !== '');
	error |= checkValid(passwordElement, password !== '');

	if (error) return;


	// Disable form
	usernameElement.disabled = true;
	passwordElement.disabled = true;


	// Launch AJAX
	launchAJAX('api/v0/auth/login', {}, function(res, status, text) {
		return false;
	});
}
/* Login */
