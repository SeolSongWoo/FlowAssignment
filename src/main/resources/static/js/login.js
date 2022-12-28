function logincheck() {
    const id = $('#username').val();
    const password = $('#password').val();
    const alphaDigit= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    if(id == ""  || password == "") {
        alert("아이디 또는 비밀번호를 입력해주세요.");
        return;
    }
    if (id.indexOf(" ") >= 0) {
        alert("아이디에는 공백이 들어갈 수 없습니다.");
        id.focus();
        return;
    }
    for (let i=0; i<id.length; i++) {
        if (alphaDigit.indexOf(id.substring(i, i+1)) == -1) {
            alert("아이디는 영문과 숫자의 조합만 사용할 수 있습니다.");
            id.focus();
            return;
        }
    }
    if (password.indexOf(" ") >= 0) {
        alert("비밀번호에는 공백이 들어갈 수 없습니다.");
        password.focus();
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