<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
</head>
<body>
	<!-- Default로 보여주는 공통 부분 -->
	<div id="container">
		<!-- view로 보여주는 가변 부분, 컨테이너 안에서 표시 -->
		<jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
	</div>
</body>
</html>