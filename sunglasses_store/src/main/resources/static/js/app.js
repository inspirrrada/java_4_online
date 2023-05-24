// function myFunction(imgs) {
//     // Get the expanded image
//     var expandImg = document.getElementById("expandedImg");
//     console.log(expandImg);
//     // Get the image text
//     //var imgText = document.getElementById("imgtext");
//     // Use the same src in the expanded image as the image being clicked on from the grid
//     expandImg.parentElement.style.display = "none";
//     expandImg.src = imgs.src;
//     console.log(expandImg.src);
//     console.log(imgs.src);
//     // Use the value of the alt attribute of the clickable image as text inside the expanded image
//     //imgText.innerHTML = imgs.alt;
//     // Show the container element (hidden with CSS)
//     //expandImg.parentElement.style.display = "block";
//     expandImg.parentElement.style.display = "flex";
// }

function myFunction() {
    var x = document.getElementById("address-info");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }

    $('.collapse').collapse();
}