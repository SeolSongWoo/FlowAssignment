<!-- 템플릿 출처 : https://startbootstrap.com/template/sb-admin MIT 라이센스 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <link href="resources/bootstrap/css/styles.css" rel="stylesheet" />
        <link href="resources/customcss/customcss.css" rel="stylesheet" />
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">>
    <div id="loading" style="display: none;">
        <div id="loading_bar">
            <img src="resources/bootstrap/img/spinloading.gif">
            <p style="font-size: x-large; font-weight: bold;">로딩 중</p>
        </div>
    </div>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/main">파일 확장자설정</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
        </nav>
        <div id="layoutSidenav" id="CustomListDiv">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="/main">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                파일 확장자 설정
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
                            <div class="row" id="reloaddiv">
                                <div class="padding20">
                                    <li class="listyle">고정 확장자</li>
                                    <label class="pad-inher" for="ex_bat"><input type="checkbox" id="ex_bat" name="BasicExtensionN" class="pad-inher" value="bat"<c:if test="${fn:contains(ExBasicList,'bat')}" >checked</c:if>/>bat</label>
                                    <label class="pad-inher" for="ex_cmd"><input type="checkbox" id="ex_cmd" name="BasicExtensionN" class="pad-inher" value="cmd"<c:if test="${fn:contains(ExBasicList,'cmd')}" >checked</c:if>/>cmd</label>
                                    <label class="pad-inher" for="ex_com"><input type="checkbox" id="ex_com" name="BasicExtensionN" class="pad-inher" value="com"<c:if test="${fn:contains(ExBasicList,'com')}" >checked</c:if>/>com</label>
                                    <label class="pad-inher" for="ex_cpl"><input type="checkbox" id="ex_cpl" name="BasicExtensionN" class="pad-inher" value="cpl"<c:if test="${fn:contains(ExBasicList,'cpl')}" >checked</c:if>/>cpl</label>
                                    <label class="pad-inher" for="ex_exe"><input type="checkbox" id="ex_exe" name="BasicExtensionN" class="pad-inher" value="exe"<c:if test="${fn:contains(ExBasicList,'exe')}" >checked</c:if>/>exe</label>
                                    <label class="pad-inher" for="ex_scr"><input type="checkbox" id="ex_scr" name="BasicExtensionN" class="pad-inher" value="scr"<c:if test="${fn:contains(ExBasicList,'scr')}" >checked</c:if>/>scr</label>
                                    <label class="pad-inher" for="ex_js"><input type="checkbox" id="ex_js" name="BasicExtensionN" class="pad-inher" value="js"<c:if test="${fn:contains(ExBasicList,'js')}" >checked</c:if>/>js</label>
                                </div>
                                <div class="padding10">
                                    <li class="listyle">커스텀 확장자</li>
                                    <input id="CustomExtensionText"type="text" placeholder="확장자 입력"/>
                                    <button class="btn btn-secondary " id="CustomExtensionAdd">+추가</button>
                                </div>
                                <div class="basic-view">
                                    <input type="hidden" value="${excount}" id="CustomExCount" />
                                    <p>${excount}/300</p>
                                    <c:forEach var="datalist" items="${ExCustomList}">
                                        <button type="button" id="btn_${datalist.ex_name}" name="CustomDeleteBtn" value="${datalist.ex_name}" class="btn btn-danger btn-xs">${datalist.ex_name}  <span class="glyphicon glyphicon-remove"></span></button>
                                    </c:forEach>
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
        <script src="resources/bootstrap/js/scripts.js"></script>
        <script src="resources/js/extension.js"></script>
    </body>
</html>
