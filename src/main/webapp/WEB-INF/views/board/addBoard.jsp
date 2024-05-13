<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 등록</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">

</head>

<body>
    <div class="form-container">
        <h2>게시글 등록</h2>
        <form:form method="POST" action="${pageContext.request.contextPath}/board/add" modelAttribute="board" enctype="multipart/form-data">
            <div class="form-group">
                <label for="bbsTtl">제목:</label>
                <form:input path="bbsTtl" id="bbsTtl" required="true" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="bbsCn">내용:</label>
                <form:textarea path="bbsCn" id="bbsCn" required="true" rows="5" class="form-control" />
            </div>
            <div class="form-group">
                <label for="ctgryFld">카테고리:</label>
                <form:select path="ctgryFld" id="ctgryFld" class="form-control">
                   <option value="">전체 카테고리</option>
        		<option value="0" ${param.ctgryFld == '0' ? 'selected' : ''}>패션의류/잡화</option>
        		<option value="1" ${param.ctgryFld == '1' ? 'selected' : ''}>뷰티</option>
        		<option value="2" ${param.ctgryFld == '2' ? 'selected' : ''}>출산/유아동</option>
        		<option value="3" ${param.ctgryFld == '3' ? 'selected' : ''}>식품</option>
        		<option value="4" ${param.ctgryFld == '4' ? 'selected' : ''}>주방용품</option>
        		<option value="5" ${param.ctgryFld == '5' ? 'selected' : ''}>생활용품</option>
        		<option value="6" ${param.ctgryFld == '6' ? 'selected' : ''}>홈인테리어</option>
        		<option value="7" ${param.ctgryFld == '7' ? 'selected' : ''}>가전디지털</option>
        		<option value="8" ${param.ctgryFld == '8' ? 'selected' : ''}>스포츠/레저</option>
        		<option value="9" ${param.ctgryFld == '9' ? 'selected' : ''}>자동차용품</option>
        		<option value="10" ${param.ctgryFld == '10' ? 'selected' : ''}>도서/음반/DVD</option>
        		<option value="11" ${param.ctgryFld == '11' ? 'selected' : ''}>완구/취미</option>
        		<option value="12" ${param.ctgryFld == '12' ? 'selected' : ''}>문구/오피스</option>
        		<option value="13" ${param.ctgryFld == '13' ? 'selected' : ''}>반려동물용품</option>
        		<option value="14" ${param.ctgryFld == '14' ? 'selected' : ''}>헬스/건강식품</option>
                </form:select>
            </div>
            <div class="form-group">
                <label for="file">상품 이미지:</label> 
                <input type="file" id="file" name="file" accept="image/*" />
            </div>
            <div class="form-group">
                <label for="slePrc">가격:</label>
                <form:input path="slePrc" id="slePrc" required="true" type="number" class="form-control" />
            </div>
            <button type="submit">등록하기</button>
        </form:form>
    </div>
</body>
</html>
