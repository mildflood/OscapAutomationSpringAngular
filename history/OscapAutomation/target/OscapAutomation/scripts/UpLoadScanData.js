function submitForm(){
        $.ajax({
            url : 'ScheduleCtrlServlet',
            type: 'Post',
            data : $('#scanhostForm').serialize(), 
            success : function(responseText) {
                $('#confirmDataUpload').text(responseText);
            }
        });
     return false;   
}
  