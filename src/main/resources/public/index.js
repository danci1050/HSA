window.onload = function(){
    document.querySelector("#ujra").addEventListener("click", scrollUp);
    document.querySelector("#sim-button").addEventListener("click", function(){
        document.querySelector("#form-container").scrollIntoView();
    });
}

function scrollUp(){
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}