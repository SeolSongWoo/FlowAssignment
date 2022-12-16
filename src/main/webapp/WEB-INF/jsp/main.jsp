<!-- 템플릿 출처 : https://startbootstrap.com/template/sb-admin MIT 라이센스 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>파일 확장자설정</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="resources/css/styles.css" rel="stylesheet" />
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <style>
        #loading {
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            position: fixed;
            display: block;
            background: #ededed;
            opacity: 0.7;
            z-index: 100001;
            text-align: center;
        }
        #loading > #loading_bar {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 100002;
            transform: translate(-50%,-50%);
        }
    </style>
    <script>
        $(function (){
            $("input[name='BasicExtensionN']").click(function () {
                const BasicValue = {
                    Value : $(this).val()
                };
                if(BasicValue.Value == null || BasicValue.Value == '') {
                    alert("잘못된 경로로 접근하였습니다.");
                    return;
                }
               if($(this).is(":checked")) {
                   BasicExtensionADD(BasicValue);
               }else {
                   BasicExtensionDEL(BasicValue);
               }
            });

            $('#ExtensionAdd').on('click',function() {
                let CustomValue = $.trim($('#ExtensionText').val());
                if(CustomValue.length > 20) {
                    alert("최대 입력 길이는 20글자입니다.");
                    return;
                }
                $("input[name='BasicExtensionN']").each(function() {
                    if(this.value == CustomValue) {
                        alert("고정 확장자로 등록되어있는 확장자입니다.");
                        return;
                    }
                });
                CustomValue = {Value : CustomValue};
                CustomExtensionADD(CustomValue);

            });
        });

        function BasicExtensionADD (BasicValue) {
            $.ajax({
                type : "POST"
                , url : "/extensionadd"
                , dataType : "json"
                , data : BasicValue
                , success : function(res){
                    if(res.rt == "ok"){
                        alert("확장자 등록이 완료되었습니다.");
                    }else if(res.rt == "Null") {
                        alert("정상적인 경로로 접근해주십시오.");
                    }else {
                        alert(res.rt);
                        return;
                    }
                }
                , beforeSend: function() {
                    $('#loading').show();
                }
                , complete: function() {
                    $('#loading').hide();
                }
                , error: function(request,status,error) {
                    alert(`ERROR CODE:"\${request.status}\n 서버와의 통신에 실패하였습니다.`);
                }
            });
        }

        function BasicExtensionDEL(BasicValue) {
            $.ajax({
                type : "POST"
                , url : "/extensiondel"
                , dataType : "json"
                , data : BasicValue
                , success : function(res){
                    if(res.rt == "ok"){
                        alert("정상적으로 삭제되었습니다.");
                    }else if(res.rt == "Null") {
                        alert("정상적인 경로로 접근해주십시오.");
                    }else {
                        alert(res.rt);
                        return;
                    }
                }
                , beforeSend: function() {
                    $('#loading').show();
                }
                , complete: function() {
                    $('#loading').hide();
                }
                , error: function(request,status,error) {
                    alert(`ERROR CODE:"\${request.status}\n 서버와의 통신에 실패하였습니다.`);
                }
            });
        }

        function CustomExtensionADD(CustomValue) {
            $.ajax({
                type : "POST"
                , url : "/extensionadd"
                , dataType : "json"
                , data : CustomValue
                , success : function(res){
                    if(res.rt == "ok"){
                        alert("확장자 등록이 완료되었습니다.");
                    }else if(res.rt == "Null") {
                        alert("정상적인 경로로 접근해주십시오.");
                    }
                    else {
                        alert(res.rt);
                        return;
                    }
                }
                , beforeSend: function() {
                    $('#loading').show();
                }
                , complete: function() {
                    $('#loading').hide();
                }
                , error: function(request,status,error) {
                    alert(`ERROR CODE:"\${request.status}\n 서버와의 통신에 실패하였습니다.`);
                }
            });
        }

        function CustomExtensionDEL(CustomValue) {

        }

    </script>

    <body class="sb-nav-fixed">
    <div id="loading" style="display: none;">
        <div id="loading_bar">
            <img src="resources/img/spinloading.gif">
            <p style="font-size: x-large; font-weight: bold;">로딩 중</p>
        </div>
    </div>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">파일 확장자설정</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                파일 확장자 설정
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">파일 확장자 설정</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">파일 확장자 설정</li>
                        </ol>
                    </div>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            파일 확장자 차단
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div style="margin-bottom: 20px;">
                                    <li style="display: inline-block; padding-right: inherit;">고정 확장자</li>
                                    <label style="padding-right: inherit" for="ex_bat"><input type="checkbox" id="ex_bat" name="BasicExtensionN" style="padding-right: inherit" value="bat"/>bat</label>
                                    <label style="padding-right: inherit" for="ex_cmd"><input type="checkbox" id="ex_cmd" name="BasicExtensionN" style="padding-right: inherit" value="cmd"/>cmd</label>
                                    <label style="padding-right: inherit" for="ex_com"><input type="checkbox" id="ex_com" name="BasicExtensionN" style="padding-right: inherit" value="com"/>com</label>
                                    <label style="padding-right: inherit" for="ex_cpl"><input type="checkbox" id="ex_cpl" name="BasicExtensionN" style="padding-right: inherit" value="cpl"/>cpl</label>
                                    <label style="padding-right: inherit" for="ex_exe"><input type="checkbox" id="ex_exe" name="BasicExtensionN" style="padding-right: inherit" value="exe"/>exe</label>
                                    <label style="padding-right: inherit" for="ex_scr"><input type="checkbox" id="ex_scr" name="BasicExtensionN" style="padding-right: inherit" value="scr"/>scr</label>
                                    <label style="padding-right: inherit" for="ex_js"><input type="checkbox" id="ex_js" name="BasicExtensionN" style="padding-right: inherit" value="js"/>js</label>
                                </div>
                                <div style="margin-bottom: 10px;">
                                    <li style="display: inline-block; padding-right: inherit;">커스텀 확장자</li>
                                    <input id="CustomExtension"type="text" placeholder="확장자 입력" id="ExtensionText"/>
                                    <button class="btn btn-secondary" id="ExtensionAdd">+추가</button>
                                </div>
                                <div style="margin-left: 130px; border: 1px solid gray; width:50%; height: 300px; border-radius:10px;">

                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="resources/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="resources/assets/demo/chart-area-demo.js"></script>
        <script src="resources/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="resources/js/datatables-simple-demo.js"></script>
    </body>
</html>
