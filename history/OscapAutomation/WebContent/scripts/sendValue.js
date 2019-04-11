function submitValue(){
 var	 nodename = $(this).val();	
        $.ajax({
            url : 'FileServlet',
            type: 'Post',
            data : nodename, 
            success : function(responseText) {
                $('#reportTable').text(responseText);
            }
        });
      
}
  