/*
function fn_chatView(chatSpceId)
{
	$.ajax({
		type: "GET",
		url: "/chat/chatView",
		data: {chatSpceId: chatSpceId},
		success:function(data){
			//부분 업데이트
			$(".chatContent").load(location.href + " .chatContent");
			alert('ddd');
		},
		error: function(data){
			alert("실패");
			console.log(data);
		}
	});
}

function fn_regiChat()
{

	var params = $("#chatFrm").serializeArray();
	var referrer = window.location.href;

	$.ajax({
		type: "POST",
		url: "/chat/chatRegi",
		data: params,
		success:function(data){
			//부분 업데이트
			//$(".chatContent").load(location.href + " .chatContent");
			console.log("성공");
			//location.reload();
			location.href = referrer;
		},
		error: function(data){
			alert("실패");
			console.log(data);
		}
	});
}
*/

