$(document).ready(function(){ 
$.getJSON("MultiScanLoadServlet", function(jsonMultiData){  	
	var multiScan_data = '';	
	$.each(jsonMultiData, function(key, value){	 
		multiScan_data += '<tr>';
		multiScan_data += '<td>'+value.nodename+'</td>';
		multiScan_data += '<td>'+value.scanAdministrator.firstname+'</td>';
		multiScan_data += '<td>'+value.scanAdministrator.lastname+'</td>';
		multiScan_data += '<td>'+value.dateOfScan+'</td>';
		multiScan_data += '<td>'+value.timeOfScan+'</td>';
		multiScan_data += '<td>'+value.status+'</td>';
		multiScan_data += '</tr>';

}); 

	$('#multiScheduleDataTable').append(multiScan_data);
	$('#multiScheduleDataTable').dataTable( {
		
	});
});
});
