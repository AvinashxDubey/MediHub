document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/api/patients/status')
        .then(response => response.json())
        .then(data => {
            document.getElementById('statusContent').innerHTML = data.map(patient =>
                `<p>Name: ${patient.name}, Department: ${patient.department}</p>`
            ).join('');
        })
        .catch(error => {
            console.error('Failed to load patient status:', error);
        });
});
