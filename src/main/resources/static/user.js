



getAuthenticatedUser().then(function (value) {

    let roles = value.roles;
    let rolesValue = '';

    for (let i = 0; i < roles.length; i++) {
        rolesValue += roles[i].authority;
        if (i != (roles.length-1)) {
            rolesValue += ' ';
        }
    }

    let bar = document.querySelector("#barEmail");
    let barHtml = `<span>${value.email}</span>`;
    bar.innerHTML = barHtml;

    let barRoles = document.querySelector("#barRoles");
    let barRolesHtml = `<span>${rolesValue}</span>`;
    barRoles.innerHTML = barRolesHtml;

    let tbodyUserTable = document.querySelector("#tableUser tbody");

    let html = `<tr>
                        <th>${value.id}</th>
                        <th>${value.firstname}</th>
                        <th>${value.lastname}</th>
                        <th>${value.age}</th>
                        <th>${value.email}</th>
                        <th><span class="ml-2">${rolesValue}</span></th>
                     </tr>`;

    tbodyUserTable.innerHTML = html;

});


async function getAuthenticatedUser() {
    let response = await fetch('http://localhost:8080/authenticatedUser');
    if (response.ok) {
        let data = await response.json();
        return data;
    } else {
        alert('error', response.status);
    }
}