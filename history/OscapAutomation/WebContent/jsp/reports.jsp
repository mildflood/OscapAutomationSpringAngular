<!-- 
Author Jonas Okwara
Date 05-28-2018
main Reports page for 
all  users
-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Openscap  Automation Tool</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"	src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript" src="scripts/upLoadHostData.js"></script>
<script type="text/javascript" src="scripts/scanHost.js"></script>
<script type="text/javascript" src="scripts/deleteData.js"></script>
<script type="text/javascript" src="scripts/deleteRowData.js"></script>
<script type="text/javascript" src="scripts/saveData.js"></script> 
<script type="text/javascript" src="scripts/upLoadHostData.js"></script>  
<script type="text/javascript" src="scripts/scanScheduler.js"></script>
<script type="text/javascript" src="scripts/dateFilter.js"></script>

<!-- My Own Cascading Style Sheets -->
<Link href="css/defaultLayout.css" rel="stylesheet" type="text/css" />
<Link href="css/defaultFont.css" rel="stylesheet" type="text/css" />
</head>




<body>
<div id="wrapper">
<div id="banner"> <h6>OpenScap Automation Tool - Scan Reports</h6> </div>
<nav class="navbar navbar-inverse">
<div class="container-fluid"> 

<ul class="nav navbar-nav navbar-left">
<li><a href="../initial.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
<li><a href="schedulerGui.jsp"><span class="glyphicon glyphicon-log-in"></span>Scheduler</a></li> 
<li><a href="realTimeScan.jsp"><span class="glyphicon glyphicon-user"></span>Real Time Scan</a></li>
<li><a href="scanActivity.jsp"><span class="glyphicon glyphicon-user"></span>Scan Activity Details</a></li>
</ul>

<ul class="nav navbar-nav navbar-right logout">
<li><a href="../initial.jsp"><h1>${sessionMessage}  ${userObj.firstname} ${userObj.lastname}<span style="margin-left: 14px"> LogOut</span></h1></a></li>
</ul>
</div>
</nav>
<!-- End of Banner and Headers --> 

<!-- Beginning OF Main Content -->
<div class="panel panel-info content-panel">
<div class="panel panel-heading"><div id="confirmDataUpload"> </div> </div>
<div class="panel panel-body">
<div id="mainContentWrapper">
<div class="row firstRow">  <!--Start Of First Row Column -->

<div class="col-lg-3 leftColumn">
<div class="panel panel-info leftPanel">
<div class="panel panel-heading">Scan Hosts</div>
<div class="panel panel-body">  
<div id="reportContainer">
<ul id="reportDiv" class="reportList list-unstyled">
<li><a href="ReportCtrlServlet?input=sben1a003"><span class="glyphicon glyphicon-tag"></span>Sben1a003</a></li> 
<li><a href="ReportCtrlServlet?input=sben2a004"><span class="glyphicon glyphicon-tag"></span>Sben2a004</a></li> 

<li><a href="ReportCtrlServlet?input=owl"><span class="glyphicon glyphicon-tag"></span>Owl</a></li> 
<li><a href="ReportCtrlServlet?input=hawk"><span class="glyphicon glyphicon-tag"></span>Hawk</a></li> 
<li><a href="ReportCtrlServlet?input=falcon"><span class="glyphicon glyphicon-tag"></span>Falcon</a></li> 
<li><a href="ReportCtrlServlet?input=osprey"><span class="glyphicon glyphicon-tag"></span>Osprey</a></li> 
<li><a href="ReportCtrlServlet?input=lion"><span class="glyphicon glyphicon-tag"></span>Lion</a></li> 

</ul>
</div>
</div>
</div>
</div> 

<!-- Right Column Begins -->
<div class="col-lg-6 midColumn"> 
<div class="panel panel-info rightReportPanel"> 
<div class="panel panel-heading headerSize">Generated Report List for: ${formattedNameString}</div>
<div class="panel panel-body">
<!-- <strong>These Are Reports from Server: ${scanSessionObj.getServerName()}</strong> --> 

<div id="#reportTable">
<table id="reportsDataTable" class="table reportsTable scrollable table-striped table-bordered table-hover table-responsive table-condensed"> 
<thead>
<tr>
<th>Scan Report</th>	
<th>Read Report</th>	 
<th>Name Filter</th>	 
<th>Date Filter</th> 
<th>Save</th>
<th>Delete</th>		 					
</tr>
</thead>	
							
<tbody>
<c:forEach var="item" items="${returnedFileList}" varStatus="iter">
<tr class="success">													
<td>${item}</td>
<td><a href="http://localhost:8080/generated/fileDump/${item}"><button type="button" class="btn btn-default btn-md"><span class="glyphicon glyphicon-open"></span></button></a></td> 	
<td><button type="button" class="btn btn-default btn-md"><span class="glyphicon glyphicon-filter"></span></button></td>
<td><button type="button" class="btn btn-default btn-md"><span class="glyphicon glyphicon-filter"></span></button></td>
<td><a href="SaveDataCtrlServlet?value=${item}"><button type="button" id="saveItem" onclick="saveData()" class="btn btn-default btn-md"><span class="glyphicon glyphicon-save"></span></button></a></td> 
<td><a href="DeleteCtrlServlet?value=${item}"><button type="button" id="deleteItem" class="btn btn-default btn-md"><span class="glyphicon glyphicon-trash"></span></button></a></td>		
</tr>
</c:forEach>
</tbody>					
</table>
</div>

</div>
</div>

</div> 

<div class="col-lg-3 rightColumn"> 
<div class="panel panel-info">  
<div class="panel panel-heading">The read panel </div>
<div class="panel panel-body">
<div id="SuccessContainer"> 

</div>
</div>
</div>

</div>

</div>
</div>
</div>
</div>
<!-- End of Main Content -->

</div>
</body>
</html>