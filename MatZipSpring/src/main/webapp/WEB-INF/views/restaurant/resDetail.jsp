<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div>
	<div class="recMenuContainer">
		<c:forEach items="${recMenuList}" var="item">
			<div class="recMenuItem" id="recMenuItem_${item.seq}">
				<div class="pic">
					<c:if test="${item.menu_pic != null and item.menu_pic != ''}">
						<img
							src="/resources/img/rest/${data.i_rest}/rec_menu/${item.menu_pic}">
					</c:if>
				</div>
				<div class="info">
					<div class="nm">${item.menu_nm}</div>
					<div class="price">
						<fmt:formatNumber type="number" value="${item.menu_price}" />
						원
					</div>
				</div>
				<c:if test="${loginUser.i_user == data.i_user}">
					<div class="delIconContainer" onclick="delRecMenu(${item.seq})">
						<span class="material-icons">clear</span>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div id="sectionContainerCenter">
		<div>
			<c:if test="${loginUser.i_user == data.i_user}">
				<button onclick="isDel()">가게 삭제</button>

				<h2>- 추천 메뉴 -</h2>
				<div>
					<div>
						<button type="button" onclick="addRecMenu()">추천 메뉴 추가</button>
					</div>
					<form id="recFrm" action="/restaurant/recMenus"
						enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<div id="recItem"></div>
						<div>
							<input type="submit" value="등록">
						</div>
					</form>
				</div>

				<h2>- 메뉴 -</h2>
				<div>
					<form id="menuFrm" action="/restaurant/menus"
						enctype="multipart/form-data" method="post">
						<input type="hidden" name="i_rest" value="${data.i_rest}">
						<input type="file" name="menu_pic" multiple>
						<div>
							<input type="submit" value="등록">
						</div>
					</form>
				</div>
			</c:if>

			<div class="restaurant-detail">
				<div id="detail-header">
					<div class="restaurant_title_wrap">
						<span class="title">
							<h1 class="restaurant_name">${data.nm}</h1>
						</span>
					</div>
					<div class="status branch_none">
						<span class="cnt hit">${data.hits}</span> <span
							class="cnt favorite">${data.cnt_favorite}</span>
					</div>
				</div>
				<div>
					<table>
						<caption>레스토랑 상세 정보</caption>
						<tbody>
							<tr>
								<th>주소</th>
								<td>${data.addr}</td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>${data.cd_category_nm}</td>
							</tr>
							<tr>
								<th>메뉴</th>
								<td>
									<div class="menuList" id="conMenuList">
										<!-- 
									<c:if test="${fn:length(menuList) > 0}">
										<c:forEach var="i" begin="0" end="${fn:length(menuList) > 3 ? 2 : fn:length(menuList) - 1}">
											<div class="menuItem">
												<img src="/resources/img/rest/${data.i_rest}/menu/${menuList[i].menu_pic}">
												<c:if test="${loginUser.i_user == data.i_user}">
													<div class="delIconContainer pointer" onclick="delMenu(${menuList[i].seq})">
														<span class="material-icons">clear</span>
													</div>
												</c:if>
											</div>
										</c:forEach>
									</c:if>
										<c:if test="${fn:length(menuList) > 3}">
											<div class="menuItem bg_black">
												<div class="moreCnt">
													+${fn:length(menuList) - 3}
												</div>
											</div>
										</c:if>
										 -->
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="carouselContainer">
	<div id="imgContainer">
		<div class="swiper-container">
			<div id="swiper-wrapper" class="swiper-wrapper">
			</div>
			<!-- Add Arrows -->
			<div class="swiper-button-next"></div>
			<div class="swiper-button-prev"></div>
		</div>
	</div>
	<div>
		<span class="material-icons" onclick="closeCarousel()">clear</span>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script>

	var isMe = ${loginUser.i_user == data.i_user}
	var menuList = []
	
	function ajaxSelMenuList() {
		axios.get('/restaurant/ajaxSelMenuList', {
			params: {
				i_rest: ${data.i_rest}
			}
		}).then(function(res) {
			menuList = res.data
			refreshMenu()
		})
	}

	function refreshMenu() {
		conMenuList.innerHTML = ''
		menuList.forEach(function(item, idx) {
			makeMenuItem(item, idx)
		})
	}

	function makeMenuItem(item, idx) {
		const div = document.createElement('div')
		div.className = 'menuItem'	
		const img = document.createElement('img')
		img.src = `/resources/img/rest/${data.i_rest}/menu/\${item.menu_pic}`
		img.style.cursor = 'pointer'
		img.addEventListener('click', openCarousel)
		
		div.append(img)
		
		const swiperDiv = document.createElement('div')
		swiperDiv.className = 'swiper-slide'
		const swiperImg = document.createElement('img')
		swiperImg.src = `/resources/img/rest/${data.i_rest}/menu/\${item.menu_pic}`
		
		swiperDiv.append(swiperImg)
	
		<c:if test="${loginUser.i_user == data.i_user}">
			const delDiv = document.createElement('div')
			delDiv.className = 'delIconContainer'
			delDiv.addEventListener('click', function() {
				if(idx > -1) {
					// 서버 삭제 요청
					axios.get('/restaurant/ajaxDelMenu', {
						params: {
							i_rest : ${data.i_rest},
							seq: item.seq,
							menu_pic: item.menu_pic
						}
					}).then(function(res) {
						if(res.data == 1) {
							menuList.splice(idx, 1)
							swiper.removeSlide(idx)
							refreshMenu()
						} else {
							alert('메뉴를 삭제할 수 없습니다.')
						}
					})
					
				}
			})
		
			const span = document.createElement('span')
			span.className = 'material-icons'
			span.innerText = 'clear'
		
			delDiv.append(span)
			div.append(delDiv)
		</c:if>
		
		conMenuList.append(div)
		swiper.addSlide(idx, swiperDiv)
	}

	function delRecMenu(seq) {
		if(!confirm('삭제하시겠습니까?')) {
			return
		}
		console.log('seq : ' + seq)
		
		axios.get('/restaurant/ajaxDelRecMenu', {
			params: {
				i_rest: ${data.i_rest},
				seq: seq
			}
		}).then(function(res) {
			console.log(res)
			if(res.data == 1) {
				//엘리먼트 삭제
				let ele = document.querySelector('#recMenuItem_' + seq)
				ele.remove()
			}
		})
	}
	<c:if test="${loginUser.i_user == data.i_user}">
	var idx = 0;
	function addRecMenu() {
		var div = document.createElement('div')
		div.setAttribute('id', 'recMenu_' + idx++)
		
		var inputNm = document.createElement('input')
		inputNm.setAttribute('type', 'text')
		inputNm.setAttribute('name', 'menu_nm')
		var inputPrice = document.createElement('input')
		inputPrice.setAttribute('type', 'number')
		inputPrice.setAttribute('name', 'menu_price')
		var inputPic = document.createElement('input')
		inputPic.setAttribute('type', 'file')
		inputPic.setAttribute('name', 'menu_pic')
		var delBtn = document.createElement('button')
		delBtn.innerText='X'
		delBtn.addEventListener('click', function() {
			div.remove()
		})
		
		div.append('메뉴: ')
		div.append(inputNm)
		div.append(' 가격: ')
		div.append(inputPrice)
		div.append(' 사진: ')
		div.append(inputPic)
		div.append(delBtn)
		
		recItem.append(div)
	}
	addRecMenu()
	function isDel() {
		if(confirm('삭제 하시겠습니까?')) {
			location.href = '/restaurant/del?i_rest=${data.i_rest}'
		}
	}
	</c:if>
	
	ajaxSelMenuList()
	
	var swiper = new Swiper('.swiper-container', {
	  loop: true,
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    });
	
	function closeCarousel() {
		carouselContainer.style.opacity = 0
		carouselContainer.style.zIndex = -10
	}
	
	function openCarousel() {
		carouselContainer.style.opacity = 1
		carouselContainer.style.zIndex = 40
	}
</script>
