function followByLink(url) {
    return executeRequest(url, "GET");
}

function deleteEntity(url) {
    return executeRequest(url, "DELETE");
}

function findAll(url) {
    return executeRequest(url, "GET");
}

function findById(url) {
    return executeRequest(url, "GET");
}

function executeRequest(url, method) {
    $.ajax({
        url: url,
        type: method,
        success: function (response) {
            document.getElementById('frame').innerHTML = response;
        }
    });
    return false;
}

function sendForm(formId, url, method) {
    $.ajax({
        url: url,
        type: method,
        data: $("#"+formId).serialize(),
        success: function (response) {
            console.log(response);
            document.getElementById('frame').innerHTML = response;
        }
    });
    return false;
}
