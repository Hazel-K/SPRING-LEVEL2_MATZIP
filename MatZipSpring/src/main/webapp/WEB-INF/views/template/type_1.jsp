<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css">
<c:if test="${css != null}">
    <c:forEach items="${css}" var="item">
        <link rel="stylesheet" href="/resources/css/${item}.css">
    </c:forEach>
</c:if>
</head>
<body>
    <!-- Default로 보여주는 공통 부분 -->
    <div id="bgTag"></div>
    <main class="container">
        <header class="hWrapper">
            <div>그림</div>
            <div id="containerPImg">
                <div>
                <c:choose>
                    <c:when test="${loginUser.profile_img != null}">
                        <img class="loginUserImg" src="/resources/img/user/${loginUser.i_user}/${loginUser.profile_img}" class="pImg">
                    </c:when>
                    <c:otherwise>
                        <img class="loginUserImg" src="/resources/img/default_profile.jpg" class="pImg">
                    </c:otherwise>
                </c:choose>
                </div>
                <div id="printUserMsg">
                    ${loginUser.nm}님, 환영합니다.
                </div>
                <div>
                    <span class="pointer" onclick="location.href='/user/logout'">로그아웃</span>
                    <span class="pointer">회원정보수정</span>
                </div>
            </div>
            <div>
            	<ul id="header_menus">
            		<li onclick="location.href='/restaurant/resMap'">홈</li>
            		<li onclick="location.href='/restaurant/resReg'">등록</li>
            		<li onclick="location.href='/user/resFavorite'">찜</li>
            	</ul>
            </div>
        </header>
        <section class="sWrapper">
            <article class="arWrapper">
                <!-- view로 보여주는 가변 부분, 컨테이너 안에서 표시 -->
	            <jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
            </article>
            <aside class="asWrapper">
                광고용
            </aside>
        </section>
        <footer class="fWrapper">
        	<div id="footerTitle">BACKEND PROJECT HEZEL_K Co., Ltd.</div>
            <div id="footerAddr">무슨광역시 무슨로 044-01</div>
            <div id="footerCopy">ⓒCOPYLEFT Reserved Everyone</div>
        </footer>
    </main>
</body>
</html>