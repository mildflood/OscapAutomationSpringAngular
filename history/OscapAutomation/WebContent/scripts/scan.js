$(document).ready(function(){ 		
   $('#scanlist').on('click', function(){ 		 
	$.ajax({
        url : 'FileServlet',
        type: 'Post',
        data : {
          value: $('#scanlist').val()
        },
        dataType: "json",
        success : function(jsonData) {           	
        	var hostCount =  jsonData.length;
        	for( var i = 0; i< hostCount; i++){
        	var data = jsonData[i];	
        	console.log(data);
              $('#myDataTable').append(data+  "<br/>");  
        	

        	  }
        	
        }     
            
        
      });
	
    });
	
		
 });
		
	
