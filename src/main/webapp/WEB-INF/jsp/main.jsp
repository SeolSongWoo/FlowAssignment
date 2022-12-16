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

    <script>
        $(function (){
            $("input[name='BasicExtensionN']").click(function () {
                const BasicValue = {
                    BasicValue : $(this).val()
                };
                if(BasicValue.BasicValue == null || BasicValue.BasicValue == '') {
                    alert("잘못된 경로로 접근하였습니다.");
                    return;
                }
               if($(this).is(":checked")) {
                   $.ajax({
                       type : "POST"
                       , url : "/extensionadd"
                       , async : false                      //동기 처리
                       , dataType : "json"
                       , data : BasicValue
                       , success : function(res){
                           if(res.rt == "ok"){
                                alert("확장자 등록이 완료되었습니다.");
                           }else {
                               alert(res.rt);
                               return;
                           }
                       }
                   });
               }else {
                   console.log($(this).val());
               }
            });
        });

    </script>

    <body class="sb-nav-fixed">
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
                                    <input id="CustomExtension"type="text" placeholder="확장자 입력"/>
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
