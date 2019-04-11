function submitForm(){
        $.ajax({
            url : 'MultiScheduleCtrlServlet',
            type: 'Post',
            data : $('#multiScanForm').serialize(), 
            success : function(responseText) {
                $('#confirmDataUpload').text(responseText);
            }
        });
     return false;   
}
  