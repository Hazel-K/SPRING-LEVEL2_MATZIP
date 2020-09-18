<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div>
		<div class="msg">${msg}</div>
		<form action="/user/login" name="frm" class="loginFrm" method="post">
			<h1>MatZip</h1>
		<!-- action에 / 붙이면 localhost:3036 다음부터 시작(처음부터), 안붙이면 해당 주소 다음부터 이어서나감 -->
			<div>
				<input type="text" name="user_id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="user_pw" placeholder="비밀번호">
			</div>
			<div>
				<input type="submit" value="로그인">
			</div>
		</form>
		<a href="/user/join">아이디가 없으신가요?</a>
	</div>
</div>