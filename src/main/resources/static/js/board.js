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
        $("#btn-reply-save").on("click", ()=>{    //function(){},  ()=>{} this를 바인딩하기 위해서!!
            this.replySave();
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
    },
    replySave: function(){
        let data = {
            userId:$("#userId").val(),
            boardId:$("#boardId").val(),
            content: $("#reply-content").val()
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글작성완료!!");
            // console.log(resp);
            location.href=`/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    replyDelete: function(boardId, replyId){
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function(resp){
            alert("댓글삭제완료!!");
            // console.log(resp);
            location.href=`/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();