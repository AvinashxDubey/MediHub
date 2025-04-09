// Wait for the entire HTML document to be fully loaded before executing this script
document.addEventListener('DOMContentLoaded', function() {

    // Attach a submit event listener to the admission form
    document.getElementById('admissionForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission (which would reload the page)

        // Retrieve input values from the form fields
        const name = document.getElementById('patientName').value;
        const age = document.getElementById('patientAge').value;
        const gender = document.getElementById('patientGender').value;
        const department = document.getElementById('departmentSelect').value;

        // Check if all fields are filled
        if (name && age && gender && department) {
            // Create a patient object to send to the backend
            const patientData = {
                name,
                age,
                gender,
                department
            };

            // Send a POST request to the backend to admit the patient
            fetch('http://localhost:8080/api/patients', {
                method: 'POST', // HTTP method
                headers: {
                    'Content-Type': 'application/json' // Tells server we're sending JSON data
                },
                body: JSON.stringify(patientData) // Convert JS object to JSON string for sending
            })
                .then(response => {
                    // Check if the response was successful (status code 200–299)
                    if (!response.ok) {
                        throw new Error("Failed to admit patient.");
                    }
                    return response.json(); // Parse the response JSON
                })
                .then(data => {
                    // If success, alert user and reset the form
                    alert('Patient admitted successfully.');
                    document.getElementById('admissionForm').reset();
                })
                .catch(error => {
                    // If an error occurred (e.g., network or server), log and notify
                    console.error('Error:', error);
                    alert('Something went wrong while admitting the patient.');
                });
        } else {
            // Warn user if any field is empty
            alert('All fields are required.');
        }
    });
});
