const modalEdit = document.getElementById('modal_edit');
const modalDelete = document.getElementById('modal_delete');
const addNewUserButton = document.getElementById("add_new_user_button");
const buttonEdit = modalEdit.querySelector('#edit_button');
const buttonDelete = modalDelete.querySelector('#delete_button');


modalEdit.addEventListener('show.bs.modal', setModalData);
modalDelete.addEventListener('show.bs.modal', setModalData);

addNewUserButton.onclick = function () {
    saveUser(document.getElementById('add_new_user_form')).then(
        function (result){
            if (result) {
                updateAllUserTable();
            }else{
                alert("Что-то пошло не так")
            }
        }
    )
}

buttonEdit.onclick = function () {
    saveUser(modalEdit).then(
        function (result) {
            if (result){
                bootstrap.Modal.getInstance(modalEdit).hide();
                updateAllUserTable();
            } else {
                alert("Что-то пошло не так")
            }
        })
};

buttonDelete.onclick = function () {
    deleteUser(modalDelete).then(
        function (result) {
            if (result) {
                bootstrap.Modal.getInstance(modalDelete).hide();
                updateAllUserTable();
            } else {
                alert("Что-то пошло не так");
            }
        }
    )
}

function setModalData(event) {
    clearModalData(this);
    const button = event.relatedTarget;
    fetch("admin/api/users/" + button.getAttribute("data-bs-id"))
        .then(response => response.json())
        .then(user => {
            this.querySelector('#user_id').value = user.id;
            this.querySelector('#name').value = user.firstName;
            this.querySelector('#last_name').value = user.lastName;
            this.querySelector('#age').value = user.age;
            this.querySelector('#login').value = user.username;
            const select = this.querySelector('#role_select');
            for (let i = 0; i < user.authorities.length; i++) {
                let selector = '[value="' + user.authorities[i].id + '"]';
                let option = select.querySelector(selector);
                option.selected = true;
            }
        });
}

function clearModalData(modal) {
    modal.querySelector('#user_id').value = "";
    modal.querySelector('#name').value = "";
    modal.querySelector('#last_name').value = "";
    modal.querySelector('#age').value = "";
    modal.querySelector('#login').value = "";
    modal.querySelector('#password').value = "";
    const options = modal.querySelector('#role_select').options;
    for (let i = 0; i < options.length; i++) {
        options[i].selected = false;
    }
}

async function saveUser(form) {
    const options = form.querySelector('#role_select').options;
    const authorities = [];
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            const role = new Role(
                options[i].value,
                options[i].text
            );
            authorities.push(role);
        }
    }
    const user = new User(
        form.querySelector('#user_id').value,
        form.querySelector('#name').value,
        form.querySelector('#last_name').value,
        form.querySelector('#age').value,
        form.querySelector('#login').value,
        form.querySelector('#password').value,
        authorities
    );
    const response = await fetch('admin/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    });
    const responseUser = await response.json();
    return user.username === responseUser.username;
}

async function deleteUser(form) {
    let id = form.querySelector("#user_id").value;
    const response = await fetch('admin/api/users', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(id)
    });
    return response.ok;
}