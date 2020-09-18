<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div id="joinContainer">
	<form class="joinFrm" id="frm" name="Frm" action="/user/joinProc" method="post" onsubmit="return chkJoin()">
	<!-- action에 / 붙이면 localhost:3036 다음부터 시작(처음부터), 안붙이면 해당 주소 다음부터 이어서나감 -->
		<input id="grid0" type="text" name="user_id" placeholder="아이디"><span id="chkId" onclick="chkId()">중복확인</span>
		<div id="grid2">
			<input type="hidden" name="checkedId" value="1">
			<div id="idChkResult"></div>
		</div>
		<input id="grid3" type="password" name="user_pw" placeholder="비밀번호">
		<input id="grid4" type="password" name="user_pwre" placeholder="비밀번호 확인">
		<input id="grid5" type="text" name="nm" placeholder="이름">
		<div id="grid6">
			<input type="submit" value="회원가입">
		</div>
	</form>
	<div>
		<a href="/user/login">로그인으로</a>
	</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		function chkJoin() {
			const user_id = frm.user_id.value
			const checkedId = frm.checkedId.value
			const user_pw = frm.user_pw.value
			const pwre = frm.user_pwre.value
			const nm = frm.nm.value
			
//			console.log(typeof(checkedId) + ":" + checkedId)
			
			if(user_id == '') {
				alert('아이디를 입력해주세요')
				frm.user_id.focus()
				return false
			}
			if (checkedId != 0) {
				switch(checkedId) {
				case '1':
					alert('아이디 중복검사를 먼저 진행해주세요.')
					frm.user_id.focus()
					return false
				case '2':
					alert('중복된 아이디로는 가입하실 수 없습니다.')
					frm.user_id.focus()
					return false
				}
			}
			if (user_pw == '') {
				alert('비밀번호를 입력해주세요')
				frm.user_pw.focus()
				return false
			}
			if (pwre == '') {
				alert('비밀번호를 다시 입력해주세요')
				frm.user_pwre.focus()
				return false
			}
			if(user_pw != pwre) {
				alert('비밀번호가 같지 않습니다. 다시 확인해주세요.')
				frm.user_pwre.focus()
				return false
			}
			if (nm == '') {
				alert('이름을 입력해주세요')
				frm.nm.focus()
				return false
			}
			
			return true
		}
	
		function chkId() {
			const user_id = frm.user_id.value
			
			if(user_id == '') {
				alert('아이디를 입력해주세요')
				frm.user_id.focus()
				return
			}
			
			axios.get('/user/ajaxIdChk', {
				params: {
					user_id
				}
			}).then(function(res) {
				// console.log(res)
				if(res.data.result == 2) {
					idChkResult.innerText = '사용할 수 있는 아이디입니다.'
					frm.checkedId.value = 0
				} else if (res.data.result == 3) {
					idChkResult.innerText = '이미 사용중인 아이디입니다.'
					frm.checkedId.value = 2
				}
//				console.log(frm.checkedId.value)
			})
		}
	</script>
</div>