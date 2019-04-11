$(document).ready(function(){
	
	$("#cancelSched").click(function(){
		$.ajax({
            url : 'CancelSchedServlet',
            type: 'Post',
            data : {
                value: $('#cancelSched').val()
               
              }, 
            success : function(responseText) {
                $('#returnedData').text(responseText);
            }
        });
		
	});

	
});


