function getPlaceInformation() {
    var json;
    if (XMLHttpRequest.readyState==4 && XMLHttpRequest.status == 200) {
        json=JSON.parse(XMLHttpRequest.responseText);
        console.log(this);
        console.log(this.responseText);
        alert("SUCCESS");
    }
    else {
        alert("FAIL");
        json= "No JSON Complete";
    }
    return json;
}