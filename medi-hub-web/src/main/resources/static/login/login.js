document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('loginForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const loginData = {
            username: username,
            password: password
        };

        fetch('http://localhost:8080/api/staff/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Invalid credentials');
                }
                return response.text(); // You can also use `.json()` if server sends JSON
            })
            .then(data => {
                alert('Login successful');
                // Redirect to status.html where we get all patients data
                window.location.href = 'status.html';
            })
            .catch(error => {
                console.error('Login error:', error);
                alert('Login failed: ' + error.message);
            });
    });
});
