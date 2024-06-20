document.addEventListener("DOMContentLoaded", function() {
    fetch('/test/simple')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Simple test data isis:', data);
        })
        .catch(error => console.error('Error fetching simple test data:', error));
});
