<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
   <title>Change Info</title>
=======
   <title>FarmFarm</title>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0

   <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 50%; margin: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }
        .btn { padding: 5px 10px; cursor: pointer; background-color: #f06292; color: white; border: none; }
        .btn:disabled { background-color: #ccc; cursor: not-allowed; }
       h1 { color: #ffccd5; }
    </style>
    
</head>
<body>

   <div class="container">
        <h1>Change Info</h1>
        <form method="post" action="/member/editMember">
           
            <table>
            
                <tr>
                    <th>이름</th>
<<<<<<< HEAD
                    <td><input type="text" name="mbrNm" id="mbrNm" value="${editMember.mbrNm}" ></td>
=======
                    <td><input type="text" name="mbrNm" id="mbrNm" value="${editMember.mbrNm}" readonly></td>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
                </tr>
                
                <tr>
                    <th>ID</th>
<<<<<<< HEAD
                    <td><input type="text" name="mbrId" id="mbrId" value="${editMember.mbrId}" readonly></td>
=======
                    <td><input type="text" name="mbrId" id="mbrId" value="${editMember.mbrId}" ></td>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
                </tr>
                
                <tr>
                    <th>PW</th>
                    <td><input type="password" name="mbrPwd" id="mbrPwd" value="${editMember.mbrPwd }" ></td>
                </tr>
                
                <tr>
                    <th>Email</th>
                    <td><input type="email" name="mbrEmail" id="mbrEmail" value="${editMember.mbrEmail}" ></td>
                
                <tr>
                    <th>주소</th>
<<<<<<< HEAD
                    <td><input type="text" name="rgn" id="rgn" value="${editMember.rgn}" ></td>
=======
                    <td><input type="button" value="동네 입력" onclick="goPopup()"class="register-input" style="width: 80px;">
	        		<input type="text" name="rgn" id="rgn" class="register-input" value="${editMember.rgn}" readonly="readonly"></td>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
                </tr>
            </table>
            
            <button type="submit" class="btn">변경사항을 확정하기</button>
            
           <input type="hidden" id="csrfParameterName" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
    <c:if test="${not empty invalidChange}">
        <script type="text/javascript">
            alert('회원 가입 제약 조건이 맞지 않습니다.');
        </script>
    </c:if>
<<<<<<< HEAD
=======

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
</script>
>>>>>>> 67c7351913d3d7b685702f20c8a57c2edc988ea0
</body>
</html>