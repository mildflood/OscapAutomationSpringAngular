$(document).ready(function(){ 
$.getJSON("LoadDataCtrlServlet", function(jsonData){  	
	var scan_data = '';	
	$.each(jsonData, function(key, value){	 
	  scan_data += '<tr>';
	  scan_data += '<td>'+value.nodename+'</td>';
	  scan_data += '<td>'+value.scanAdministrator.firstname+'</td>';
	  scan_data += '<td>'+value.scanAdministrator.lastname+'</td>';
	  scan_data += '<td>'+value.dateOfScan+'</td>';
	  scan_data += '<td>'+value.timeOfScan+'</td>';
	  scan_data += '<td>'+value.status+'</td>';
	  scan_data += '<td>'+value.group+'</td>';
	  scan_data += '</tr>';

}); 

	$('#scheduleDataTable').append(scan_data);
	$('#scheduleDataTable').dataTable( {
		
	});
});
});

