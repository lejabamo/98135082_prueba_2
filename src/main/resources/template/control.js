function allowDrop(ev) {
    ev.preventDefault();
  }
  
  function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
    var m = $("#"+ev.target.id).data("estado");
    console.log(m);
    ev.dataTransfer.setData("estado", $(ev.target).data("estado"));
  }
  
  function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var estadoDrag = ev.dataTransfer.getData("estado");
    estadoDrag = parseInt(estadoDrag);
    //************************/
    var id = $(ev.target).attr("id");
    id = id.replace("div","");
    id = parseInt(id);
    //************************/
    //alert((estadoDrag+1)+"-"+id+"-"+data);
    if((estadoDrag+1)===id){
        $("#"+data).data("estado",(estadoDrag+1));
        ev.target.appendChild(document.getElementById(data));
        ///
    }else{
        alert("No se puede");
    }
  }

function load() {
    $(div1).html("");
    $.ajax({
        url: "/mascotas",
        method: "GET",
        dataType: "json",
        type: "json",
        success: function (r) {
            if (r.status == 200) {
                for (var i = 0; i < r.data.length; i++) {
                    var html = "<div data-estado='"+r.data[i].estado+"' id='id"+i+"' class='drag' draggable='true' ondragstart='drag(event)'>" + r.data[i].nombre + "</div>";
                    $("#div"+r.data[i].estado).append(html);
                }
            } else {
                alert(r.message);
            }
        },
        error: function (e, err) {
            console.log(e);
            console.log(err);
        }
    });
}

function save(data) {
    var json = JSON.stringify(data);
    $.ajax({
        url: "/mascotas",
        method: "POST",
        dataType: "json",
        type: "POST",
        data:json,
        success: function (r) {
            if (r.status == 200) {
                load(); 
            } else {
                alert(r.message);
            }
        },
        error: function (e, err) {
            console.log(e);
            console.log(err);
        }
    });
}

$(function () {
    load();
    $("#crear").submit(function(){
        var data = {
            destarea : destarea.value,
        };
        save(data);
        return false;
    });
});
