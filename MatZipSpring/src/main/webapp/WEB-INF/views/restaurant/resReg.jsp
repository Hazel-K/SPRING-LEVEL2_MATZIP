<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sectionContainerCenter">
	<div id="resRegContainer">
		<h1 id="regTitle">등록하기</h1>
		<form id="frm" class="regGrid" action="/restaurant/resRegProc" method="post" onsubmit="return chkFrm()">
			<input id="regGrid1" type="text" name="nm" placeholder="가게명">
			<input id="regGrid2" type="text" name="addr" placeholder="주소" onchange="changeAddr()">
			<button id="regGrid3" onclick="getLatLng()">좌표가져오기</button>
			<div id="regGrid6"></div>
			<input type="hidden" name="lat" value="0">
			<input type="hidden" name="lng" value="0">
			<input type="hidden" name="i_user" value="${loginUser.i_user}">
			<div id="regGrid4">
				<span>카테고리:</span>
				<select name="cd_category" id="">
					<c:forEach items="${categoryList}" var="item">
						<option value="${item.cd}">${item.val}</option>
					</c:forEach>
				</select>
			</div>
			<div id="regGrid5"><input type="submit" value="등록"></div>
		</form>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6bb62151ddeddee6978f9dd897043e8e&libraries=services"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script type="text/javascript">
		// 현재 사용 안함
		function ajaxSubmit() {
			event.preventDefault()
			axios.get('/restaurant/ajaxResReg', {
				params: {
					nm : frm.nm.value,
					addr : frm.addr.value,
					lat : frm.lat.value,
					lng : frm.lng.value,
					cd_category : frm.cd_category.value
				}
			}).then(function(res) {
				if(res == '1') {
					alert('가게를 등록했습니다.')
					frm.submit()
				} else {
					alert('등록에 실패했습니다. 다시 시도해주세요')
				}
				location.href='/restaurant/resReg'
			})
		}
		// 현재 사용 안함
	
		// 다 입력했는지 체크하는 기능 구현
		function chkFrm() {
			if(frm.nm.value.length == 0) {
				alert('가게명을 입력해주세요')
				frm.nm.focus()
				return false
			}
			if(frm.addr.value.length < 9) {
				alert('주소를 확인해주세요')
				frm.addr.focus()
				return false
			}
			if(frm.lat.value == '0' || frm.lng.value == '0') {
				alert('좌표가져오기를 실행해주세요.')
				frm.addr.focus()
				return false
			}
			if(frm.cd_category.value == '0') {
				alert('카테고리를 선택해주세요.')
				frm.cd_category.focus()
				return false
			}
			return true
		}
		// 다 입력했는지 체크하는 기능 구현
	
		// 주소 좌표 찍은 후 변경 시 값 초기화 기능 구현
		function changeAddr() {
			if(frm.lat.value != '0' && frm.lng.value != '0') {
				regGrid6.innerText = '좌표를 다시 검색해주세요.'
			}
			frm.lat.value = '0'
			frm.lng.value = '0'
		}
		// 주소 좌표 찍은 후 변경 시 값 초기화 기능 구현
	
		// 검색된 주소 좌표 찍기 구현
		const geocoder = new kakao.maps.services.Geocoder()
	
		function getLatLng() {
			event.preventDefault()
			const addrStr = frm.addr.value
			
			if(addrStr.length < 9) {
				alert('주소를 확인해주세요')
				frm.addr.focus()
				return
			}
			
			geocoder.addressSearch(addrStr, function(result, status) {
				if(status === kakao.maps.services.Status.OK) {
//					console.log(result)
					frm.lat.value = result[0].y
					frm.lng.value = result[0].x
					
					if(frm.lat.value != '0' && frm.lng.value != '0') {
						regGrid6.innerText = '등록할 수 있는 주소입니다.'
					} else {
						regGrid6.innerText = '올바르지 않은 주소입니다.'
					}
				}
			})
		}
		// 검색된 주소 좌표 찍기 구현
	</script>
</div>