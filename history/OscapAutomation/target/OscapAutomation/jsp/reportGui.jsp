<!-- 
Author Jonas Okwara
Date 05-28-2018
main Reports page for 
all  users
-->

<%@ include file="../includes/header.jsp"%> 

<body>
<div id="wrapper">
<div id="banner"> <h6>OpenScap Automation Scan Reporter</h6> </div>
<nav class="navbar navbar-inverse">
<div class="container-fluid"> 

<ul class="nav navbar-nav navbar-left">
<li><a href="homeGui.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li> 
<li><a href="scanGui.jsp"><span class="glyphicon glyphicon-user"></span>Real Time Scan</a></li>
<li><a href="schedulerGui.jsp"><span class="glyphicon glyphicon-user"></span>Scheduler</a></li>
</ul>

<ul class="nav navbar-nav navbar-left reportForm">
<li>
<form  class="form-inline" action="ReportCtrlServlet" method="Post">
    <div class="form-group">
      <select class="form-control" id="hostreport" name="hostreport">
        <option value=" " selected disabled>Filter By Node Name</option>
        <option value="sben1a003">Sben1a003</option>
        <option value="sben2a004">Sben2a004</option>
        <option value="sben3a005">Sben3a005</option>
        <option value="sben4a006">Sben4a006</option>
      </select> 
    </div> 
    
   
   <div class="form-group">
      <select class="form-control" id="hostreport" name="hostreport">
        <option value=" " selected disabled>Filter By ADO</option>
        <option value="sben1a003">Sben1a003</option>
        <option value="sben2a004">Sben2a004</option>
        <option value="sben3a005">Sben3a005</option>
        <option value="sben4a006">Sben4a006</option>
      </select> 
    </div> 
   
   
    <button type="submit" class="btn btn-info">Submit</button>
   <button type="reset" class="btn btn-info">Clear</button>
  </form>
</li>
</ul>

<ul class="nav navbar-nav navbar-right logout">
<li><a href="../initial.jsp"><h1>${sessionMessage} ${userObj.firstname} ${userObj.lastname}<span style="margin-left: 14px"> LogOut</span></h1></a></li>
</ul>
</div>
</nav>
<!-- End of Banner and Headers --> 

<div class="panel panel-info   mainContentWrapper">
<div class="panel panel-heading"> </div>
<div class="panel panel-body">

<div class="row firstRow">  
<div class="col-md-12 1stReportColumn">

</div>

<div class="col-md-12">
<div class="panel panel-info 2ndReportColumn">
<div class="panel panel-heading">
Generated Report List for: ${formattedNameString}



</div>
<div class="panel panel-body">

<div id="#reportTable">
<table id="reportsDataTable" class="reportsTable scrollable table-striped table-bordered table-hover table-responsive table-condensed"> 
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
<td><a href="http://10.137.54.33:8080/generated/fileDump/${item}"><button type="button" class="btn btn-default btn-md"><span class="glyphicon glyphicon-open"></span></button></a></td> 	
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
</div>
</div>
</div>
</div>
</body>
</html>