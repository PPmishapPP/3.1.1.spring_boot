window.onload = updateAllUserTable;


function updateAllUserTable() {
    fetch('admin/api/users')
        .then(response => response.json())
        .then(users => {
            const tbody = document.getElementById('all_users_tbody');
            tbody.innerHTML = '';
            for(let i = 0; i < users.length; i++){
                tbody.appendChild(getUserRow(users[i]));
            }
        });
}

function getUserRow(user) {
    let row = document.createElement("tr");
    let rowHTML = getUserTD(user.id);
    rowHTML += getUserTD(user.firstName);
    rowHTML += getUserTD(user.lastName);
    rowHTML += getUserTD(user.age);
    rowHTML += getUserTD(user.username);
    let authorities = '';
    for (let i = 0; i < user.authorities.length; i++){
        authorities += user.authorities[i].authority + " ";
    }
    rowHTML += getUserTD(authorities);
    rowHTML += getUserTD("<button type='button' class='btn btn-info text-white' data-bs-toggle='modal' " +
        "data-bs-target='#modal_edit' data-bs-id='"+ user.id+"'>Edit</button>");
    rowHTML += getUserTD("<button type='button' class='btn btn-danger' data-bs-toggle='modal' "+
        "data-bs-target='#modal_delete' data-bs-id='"+ user.id+"'>Delete</button>")

    row.innerHTML = rowHTML;
    return row;
}
function getUserTD(val){
    return "<td>" + val + "</td>";
}