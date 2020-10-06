<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
	<c:forEach items="${data}" var="item">
        <div class="f-item">
            <div class="img">
                <img src="/resources/img/rest/${item.i_rest}/rec_menu/${item.menuList[0].menu_pic}" alt="">
            </div>
	        <div class="ctnt">
				<div class="top">
					<div class="f-title">
						<h2 class="nm NotoSans-500">${item.rest_nm}</h2>
						<span class="addr">${item.rest_addr}</span>
					</div>
				</div>
				<div class="bottom">
					<c:forEach items="${item.menuList}" var="item2">
						<div class="recMenuItem" id="recMenuItem_${item2.seq}">
							<div class="pic">
								<img src="/resources/img/rest/${item.i_rest}/rec_menu/${item2.menu_pic}">
							</div>
							<div class="info">
								<div class="nm">${item2.menu_nm}</div>
								<div class="price"><fmt:formatNumber type="number" value="${item2.menu_price}"/>원</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
        </div>
    </c:forEach>
</div>