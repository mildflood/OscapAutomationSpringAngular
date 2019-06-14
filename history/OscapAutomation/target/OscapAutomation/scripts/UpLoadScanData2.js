function submitForm(){
        $.ajax({
            url : 'ScheduleCtrlServlet2',
            type: 'Post',
            data : $('#scanhostForm').serialize(), 
            success : function(responseText) {
                $('#confirmDataUpload').text(responseText);
            }
        });
     return false;   
}
  