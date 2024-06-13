<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FarmFarm</title>
    <link rel="stylesheet" href="${path}/resources/css/bbsStyle.css">
    <style>
        body {
            background-color: white;
            font-family: Arial, sans-serif;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #ffcccc;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #ffcccc;
        }
        .form-control {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #ffcccc;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #ffcccc;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>게시글 수정</h2>
        <form:form method="POST" action="${pageContext.request.contextPath}/board/edit?bbsId=${board.bbsId}" modelAttribute="board" enctype="multipart/form-data">
            <div class="form-group">
                <label for="bbsTtl">제목:</label>
                <form:input path="bbsTtl" id="bbsTtl" required="true" type="text" class="form-control" />
            </div>
            <div class="form-group">
                <label for="bbsCn">내용:</label>
                <form:textarea path="bbsCn" id="bbsCn" required="true" rows="5" class="form-control"></form:textarea>
            </div>
            <div class="form-group">
                <label for="ctgryFld">카테고리:</label>
                <form:select path="ctgryFld" id="ctgryFld" class="form-control">
                    <option value="">전체 카테고리</option>
                    <option value="0" ${board.ctgryFld == '0' ? 'selected' : ''}>패션의류/잡화</option>
                    <option value="1" ${board.ctgryFld == '1' ? 'selected' : ''}>뷰티</option>
                    <option value="2" ${board.ctgryFld == '2' ? 'selected' : ''}>출산/유아동</option>
                    <option value="3" ${board.ctgryFld == '3' ? 'selected' : ''}>식품</option>
                    <option value="4" ${board.ctgryFld == '4' ? 'selected' : ''}>주방용품</option>
                    <option value="5" ${board.ctgryFld == '5' ? 'selected' : ''}>생활용품</option>
                    <option value="6" ${board.ctgryFld == '6' ? 'selected' : ''}>홈인테리어</option>
                    <option value="7" ${board.ctgryFld == '7' ? 'selected' : ''}>가전디지털</option>
                    <option value="8" ${board.ctgryFld == '8' ? 'selected' : ''}>스포츠/레저</option>
                    <option value="9" ${board.ctgryFld == '9' ? 'selected' : ''}>자동차용품</option>
                    <option value="10" ${board.ctgryFld == '10' ? 'selected' : ''}>도서/음반/DVD</option>
                    <option value="11" ${board.ctgryFld == '11' ? 'selected' : ''}>완구/취미</option>
                    <option value="12" ${board.ctgryFld == '12' ? 'selected' : ''}>문구/오피스</option>
                    <option value="13" ${board.ctgryFld == '13' ? 'selected' : ''}>반려동물용품</option>
                    <option value="14" ${board.ctgryFld == '14' ? 'selected' : ''}>헬스/건강식품</option>
                </form:select>
            </div>
            <div class="form-group">
                <label for="file">상품 이미지:</label>
                <input type="file" id="file" name="file" multiple="multiple" accept="image/*" />
            </div>
            <div class="form-group">
                <label for="slePrc">가격:</label>
                <form:input path="slePrc" id="slePrc" required="true" type="number" class="form-control" />
            </div>	
            <button type="submit">수정하기</button>
        </form:form>
    </div>
</body>
</html>