<!-- 
Author Jonas Okwara
Date 02-17-19
View page for populating drop down list
accepting selections and getting responses 
from servlet controller
 -->

<%@ include file="/includes/header.jsp"%> 
<body>
<div id="wrapper">
<div id="banner"> <h6>OpenScap Automation Scan Reporter</h6> </div>
<nav class="navbar navbar-inverse">
<div class="container-fluid"> 

<ul class="nav navbar-nav navbar-left">
<li><a href="homeGui.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li> 
<li><a href="multiScanGui.jsp"><span class="glyphicon glyphicon-user"></span>Scheduler</a></li>
</ul>
<ul class="nav navbar-nav navbar-left reportForm">
<li>
<form  class="form-inline" action="PopulateDropDownServlet" method="Post"> 
    <div class="form-group">
    <select class="form-control" id="dropDown"  name="eachItem"> 
      <option value=" " selected disabled>Filter By Node Name</option>
      <c:forEach items="${nodeList}" var="item">
      <option value="${item.hostname}">${item.hostname}</option>
      </c:forEach>
      </select>       
    </div> 
    
    <button type="submit"    id="loadButton"    class="btn btn-info">Load</button>
    <button type="submit"    id="submitButton"  class="btn btn-info">Submit</button>
   <button type="reset"      id="clearButton"   class="btn btn-info">Clear</button>    
</form>
</li>
</ul>

<ul class="nav navbar-nav navbar-right logout">
<li><a href="initial.jsp"><h1>${sessionMessage} ${userObj.firstname} ${userObj.lastname}<span style="margin-left: 14px"> LogOut</span></h1></a></li>
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
<br>
<div>
<strong style="color:green">${successMessage}</strong>
</div>

</div>
<div class="panel panel-body">

<div id="reportsDataTable">
<table id="reportsDataTable" class="reportsTable scrollable table-striped table-bordered table-hover table-responsive table-condensed"> 
<thead>
<tr>
<th>Generated Report</th>	
<th>Read Report</th>	 	 
<th>Save</th>	
</tr>				

</thead>	
							
<tbody>
<c:forEach var="item" items="${returnedFileList}" varStatus="iter">
<tr class="success">													
<td>${item}</td>
<td><a href="${initParam.fileURL}/${item}"><button type="button" class="btn btn-default btn-md"><span class="glyphicon glyphicon-open"></span></button></a></td> 	
<td><a href="SaveDataCtrlServlet?url=${initParam.fileURL}&value=${item}"><button type="button" id="saveItem" onclick="saveData()" class="btn btn-default btn-md"><span class="glyphicon glyphicon-save"></span></button></a></td> 		
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