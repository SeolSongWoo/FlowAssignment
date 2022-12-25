function logincheck() {
    const id = $('#username').val();
    const password = $('#password').val();

    if(id == ""  || password == "") {
        alert("아이디 또는 비밀번호를 입력해주세요.");
        return;
    }

    const account = {
        'id' : id,
        'password' : password,
    };

    $.ajax({
        type : "POST"
        , url : "/login/check"
        , dataType : "json"
        , data : account
        , success : function(data, textStatus, request){
            if(data.rt === 'ok') {
                location.href="/main";
            }else {
                alert(data.rt);
            }
        }
        , beforeSend: function() {
        }
        , complete: function() {
        }
        , error: function(request,status,error) {
        }
    });
}