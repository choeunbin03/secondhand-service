<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FarmFarm</title>
    <style>
        /* CSS 스타일링은 선택사항입니다. */
        .register-container {
          max-width: 400px;
          margin: 0 auto;
          padding: 20px;
          border: 1px solid #ccc;
          border-radius: 5px;
          text-align: center;
        }
        .register-input {
          width: calc(100% - 20px);
          padding: 10px;
          margin-bottom: 10px;
          border: 1px solid #ccc;
          border-radius: 5px;
        }
        .register-btn {
          background-color: #007bff;
          color: white;
          padding: 10px 20px;
          border: none;
          border-radius: 5px;
          cursor: pointer;
          margin-bottom: 20px;
        }
        .error-message {
          color: red;
          margin-top: 5px; /* 오류 메시지 위쪽에 여백 추가 */
          text-align: left; /* 오류 메시지 텍스트를 왼쪽 정렬 */
        }
    </style>
</head>
<body>
<<<<<<< HEAD
<div class="register-container">
    <h2>회원 가입</h2>
    <form action="/member/signin" method="post">
        <input type="text" id="name" name="mbrNm" class="register-input" placeholder="이름" required>
        <br>
        <input type="text" id="username" name="mbrId" class="register-input" placeholder="사용자 아이디(6글자 이상으로 된 영어)" required>
        <br>
        <c:if test="${mbrIdErrorMessage eq 'mbrIdError'}">
            <a> 사용자 이름이 중복되었습니다.</a>
        </c:if>
        <c:if test="${not empty errors and errors.hasFieldErrors('mbrId')}">
            <div class="error-message">${errors.getFieldError('mbrId').defaultMessage}</div>
        </c:if>
        <br>
        <input type="password" id="password" name="mbrPwd" class="register-input" placeholder="비밀번호(8글자 이상이고 숫자와 특수 기호를 포함한 영어)" required>
        <br>
        <c:if test="${not empty errors and errors.hasFieldErrors('mbrPwd')}">
            <div class="error-message">${errors.getFieldError('mbrPwd').defaultMessage}</div>
        </c:if>
        <br>
        <input type="password" id="confirm_password" name="mbrPwdConfirm" class="register-input" placeholder="비밀번호 확인" required>
        <br>
        <c:if test="${not empty errors and errors.hasFieldErrors('mbrPwdConfirm')}">
            <div class="error-message">${errors.getFieldError('mbrPwdConfirm').defaultMessage}</div>
        </c:if>
        <br>
        <input type="email" id="email" name="email" class="register-input" placeholder="이메일" required>
        <br>
        <input type="button" name="mbrRoadAddr" id="mbrRoadAddr" value="동네 입력" onclick="goPopup()"class="register-input" style="width: calc(100% - 20px);">
        <br>
        <button type="submit" class="register-btn" style="width: calc(100% - 20px);">가입하기</button>
    </form>
</div>
<script>
   function goPopup(){
      // 주소검색을 수행할 팝업 페이지를 호출합니다.
      // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
      
      var pop = window.open("/member/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
      
      // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
       //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
   }
   
   
   function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
         // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
         
         document.getElementById("mbrRoadAddr").value = roadAddrPart1;
         //document.getElementById("mbrAddrDetail").value = addrDetail;
         //document.getElementById("mbrZipNo").value = zipNo;
   
         
   }
=======
	<div class="register-container">
	    <h2>회원 가입</h2>
	    <form action="/member/signin" method="post">
	        <input type="text" id="name" name="mbrNm" class="register-input" placeholder="이름" required>
	        <br>
	        <input type="text" id="username" name="mbrId" class="register-input" placeholder="사용자 아이디(6글자 이상으로 된 영어)" required>
	        <br>
	        <c:if test="${mbrIdErrorMessage eq 'mbrIdError'}">
	            <a> 사용자 이름이 중복되었습니다.</a>
	        </c:if>
	        <c:if test="${not empty errors and errors.hasFieldErrors('mbrId')}">
	            <div class="error-message">${errors.getFieldError('mbrId').defaultMessage}</div>
	        </c:if>
	        <br>
	        <input type="password" id="password" name="mbrPwd" class="register-input" placeholder="비밀번호(8글자 이상이고 숫자와 특수 기호를 포함한 영어)" required>
	        <br>
	        <c:if test="${not empty errors and errors.hasFieldErrors('mbrPwd')}">
	            <div class="error-message">${errors.getFieldError('mbrPwd').defaultMessage}</div>
	        </c:if>
	        <br>
	        <input type="password" id="confirm_password" name="mbrPwdConfirm" class="register-input" placeholder="비밀번호 확인" required>
	        <br>
	        <c:if test="${not empty errors and errors.hasFieldErrors('mbrPwdConfirm')}">
	            <div class="error-message">${errors.getFieldError('mbrPwdConfirm').defaultMessage}</div>
	        </c:if>
	        <br>
	        <input type="email" id="email" name="email" class="register-input" placeholder="이메일" required>
	        <br>
	        <input type="button" value="동네 입력" onclick="goPopup()"class="register-input" style="width: calc(100% - 20px);">
	        <input type="text" name="rgn" id="rgn" class="register-input" value="" readonly="readonly">
	        <br>
	        <button type="submit" class="register-btn" style="width: calc(100% - 20px);">가입하기</button>
	    </form>
	</div>
	
<script>
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		
		var pop = window.open("/member/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	
	
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			
			document.getElementById("rgn").value = roadAddrPart1;
			//document.getElementById("mbrAddrDetail").value = addrDetail;
			//document.getElementById("mbrZipNo").value = zipNo;
	
			
	}
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
</script>

</body>
</html>