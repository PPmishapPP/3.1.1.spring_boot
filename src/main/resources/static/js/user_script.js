window.onload = updateUserTable;

function updateUserTable() {
    const tbody = document.getElementById('user_tbody');
    fetch('/user/api')
        .then(response => response.json())
        .then(user => tbody.appendChild(getUserRow(user, false)))
}