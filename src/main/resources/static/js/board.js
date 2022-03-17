let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{    //function(){},  ()=>{} this를 바인딩하기 위해서!!
            this.save();
        });
        $("#btn-delete").on("click", ()=>{    //function(){},  ()=>{} this를 바인딩하기 위해서!!
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{    //function(){},  ()=>{} this를 바인딩하기 위해서!!
            this.update();
        });
    },
    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("글쓰기완료!!");
           // console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    deleteById: function() {
        var id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function (resp) {
            alert("삭제완료!!");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function(){
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("글수정완료!!");
            // console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();