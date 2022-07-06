var url = window.location.host;
window.onload = function(){
    
    document.get    


   
    var images = new XMLHttpRequest();
    images.open('GET', 'http://'+url+'/api/v1/images', true);
    images.send();
    images.onload = function(){
        var data = JSON.parse(images.responseText);

        var image_list = document.querySelector(".image-list");
        for(var i = 0; i < data.length; i++){
            var html = `<tr>
            <th class="align-middle" scope="row">${i+1}</th>
            <td class="align-middle"><img class="table-img" src="images/${data[i]}" alt=""></td>
            <td class="align-middle">${data[i]}</td>
            <td class="new">
                <div class="record-img-container">
                  <img class="table-img" id="newImg" src="">
                  <div class="record-img-overlay center-vertically center-horizontally"> <i class="gg-software-upload"></i></div>
                  <input type="file"  accept="image/jpg" class="table-upload-button" id="newImageUpload">
                </div>
              </td>
                    
            <td class="align-middle"><button type="button" onclick="save(this)" data-img="${data[i]}" class="btn bg-green save">Mentés</button></td>
          </tr>`
            image_list.insertAdjacentHTML("beforeend", html);
        }
        document.querySelectorAll("#newImageUpload").forEach(function(element){
            element.addEventListener("change", function(e){
                const [file] = e.target.files;
            var tr = e.target.closest("tr");
            if(file){
                tr.querySelector("#newImg").src = URL.createObjectURL(file);
            }});
        });
        
    }


    

    
}

function save(save_button){
    var img_name = save_button.getAttribute("data-img");
    var tr = save_button.closest("tr");
    if(tr.querySelector("#newImageUpload").files.length > 0){
    const formData = new FormData();
    formData.append("image", tr.querySelector("#newImageUpload").files[0]);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://'+url+'/api/v1/images/'+img_name, true);
    xhr.send(formData);
    xhr.onload = function(){
        window.location.reload();
    }
}
}


