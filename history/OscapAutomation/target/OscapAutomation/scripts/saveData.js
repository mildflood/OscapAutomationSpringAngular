 function saveData(){	
	
		
		$.ajax({
            url : 'FileServlet',
            type: 'Post',
            data : {
              dataID: theData
            },
            dataType: "json",
            success : function(jsonData) { 
            var jsonObj = JSON.parse(jsonData); 
            
              
              document.getElementById("reportTable").innerHTML = jsonObj[i];
            }     
                
            
        });
 }
       


 