function submitSingleSchedForm(){
        $.ajax({
            url : 'ScheduleCtrlServlet',
            type: 'Post',
            data : $('#singleScanForm').serialize(), 
            success : function(responseText) {
                $('#confirmDataUpload').text(responseText);
            }
        });
    return false;
}
  