document.addEventListener('DOMContentLoaded', function () {
    function fetchDashboardData() {
        fetch('http://localhost:8080/api/dashboard')
            .then(response => response.json())
            .then(data => {
                const availableBeds = data.availableBeds || 0;
                const totalPatients = data.totalPatients || 0;
                const opdQueue = data.opdQueueSize || 0;

                const occupiedBeds = totalPatients - opdQueue;
                const totalBeds = availableBeds + occupiedBeds;

                // Update Beds
                document.getElementById('bedsContent').innerHTML = `
                    <p>Available Beds: ${availableBeds}</p>
                    <p>Occupied Beds: ${occupiedBeds}</p>
                    <p>Total Beds: ${totalBeds}</p>
                `;

                // Update Patients
                document.getElementById('patientsContent').innerHTML = `
                    <p>Total Patients: ${totalPatients}</p>
                    <p>Patients in OPD: ${opdQueue}</p>
                `;

                // Update OPD Queue
                document.getElementById('opdQueueStatus').textContent = opdQueue;
            })
            .catch(error => {
                console.error('Failed to fetch dashboard data:', error);
            });
    }

    fetchDashboardData(); // Initial fetch
    setInterval(fetchDashboardData, 10000); // Auto-refresh every 10 sec (optional)
});
