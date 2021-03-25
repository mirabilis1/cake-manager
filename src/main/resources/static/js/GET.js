const baseUrl = "http://localhost:8080/cakes";

function loadCakes() {
    fetch(baseUrl)
        .then(response => response.json())
        .then(cakes => {

            let tableHeader = `<thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Title</th>
                                            <th>Description</th>
                                            <th>Image</th>
                                        </tr>
                                        </thead>`

            let tableBody = "<tbody>";
            for (let index = 0; index < cakes.length; index++) {
            	tableBody += "<tr><td>" + cakes[index].cakeId + "</td><td>"
                    + cakes[index].title + "</td><td>" + cakes[index].desc + "</td><td>"
                    + "<img src='" + cakes[index].image + "' width='300' height='272'/></td></tr>";
            }
            tableBody += "</tbody>"
            
            document.getElementById("cakeInfo").innerHTML = `<table>` + tableHeader + tableBody + `</table>`;
        });
}

window.onload = function () {
    loadCakes();
}