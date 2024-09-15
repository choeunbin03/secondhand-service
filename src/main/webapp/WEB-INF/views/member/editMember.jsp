<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Change Info</title>

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
                    <td><input type="text" name="mbrNm" id="mbrNm" value="${editMember.mbrNm}" ></td>
                </tr>
                
                <tr>
                    <th>ID</th>
                    <td><input type="text" name="mbrId" id="mbrId" value="${editMember.mbrId}" readonly></td>
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
                    <td><input type="text" name="rgn" id="rgn" value="${editMember.rgn}" ></td>
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
</body>
</html>