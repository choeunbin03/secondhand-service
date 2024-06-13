<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Begin Wrapper -->
	<div class="ctgryBar-wrapper">		
		<div class="ctgry-menu-control">
			<button type="button" class="ctgry-menu-btn btn" style="position:fixed; left:150px;">
				<i class="xi-bars xi-2x"></i>
			</button>
			<div class="ctgry-content-container">
				<h4>전체 카테고리</h4>
				<ul class="nav-v1">
					<li data-value="0" class="menu-v1"><a>패션의류/잡화</a>
					<li data-value="1" class="menu-v1"><a>뷰티</a>
					<li data-value="2" class="menu-v1"><a>출산/유아동</a>
					<li data-value="3" class="menu-v1"><a>식품</a>
					<li data-value="4" class="menu-v1"><a>주방용품</a>
					<li data-value="5" class="menu-v1"><a>생활용품</a>
					<li data-value="6" class="menu-v1"><a>홈인테리어</a>
					<li data-value="7" class="menu-v1"><a>가전디지털</a>
					<li data-value="8" class="menu-v1"><a>스포츠/레저</a>
					<li data-value="9" class="menu-v1"><a>자동차용품</a>
					<li data-value="10" class="menu-v1"><a>도서/음반/DVD</a>
					<li data-value="11" class="menu-v1"><a>완구/취미</a>
					<li data-value="12" class="menu-v1"><a>문구/오피스</a>
					<li data-value="13" class="menu-v1"><a>반려동물용품</a>
					<li data-value="14" class="menu-v1"><a>헬스/건강식품</a>
				</ul>
			</div><!-- ctgry-content -->
		</div><!-- ctgry-menu-control -->
	 
	</div> <!-- ctgryBar-wrapper -->
	 <!-- End Header -->
	 
<script>
	$(document).ready(function(){		
	  	$('.menu-v1').click(function(){
	  		var ctgryFld = $(this).data('value');  
	  		location.href = "/board/bbsList?ctgryFld="+ctgryFld;
	  	});
	});
</script>	
