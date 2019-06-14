<%@ include file="../includes/scanHeader.jsp"%> 
<%@ page session="true" %>
<body>
<div id="wrapper">
<div id="banner"> <h6>OpenScap Real Time  Scan Monitor</h6> </div>
<nav class="navbar navbar-inverse">
<div class="container-fluid"> 

<ul class="nav navbar-nav navbar-left">
<li><a href="homeGui.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
<li><a href="schedulerGui.jsp"><span class="glyphicon glyphicon-log-in"></span>Scheduler</a></li> 
<li><a href="reportGui.jsp"><span class="glyphicon glyphicon-user"></span>Scan Reports</a></li>
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

<div class="col-md-12 firstColumn">

<div class="formContainer">
  <form class="form-inline" id="scanForm" action="ScanServlet" method="Post">  
  <fieldset class="scheduler-border">
<legend class="scheduler-border">Multiple Select</legend> 

<!-- <form id="scanForm" onsubmit="return getScanResults();"> --> 
    <div class="form-group">  
      <label for="nodes">Group 1 </label><br>
      <select multiple class="form-control" id="nodes" name="nodes">
        <option value="sben1a003-mgt">Sben1a003</option>
        <option value="sben2a004-mgt">Sben2a004</option>
        <option value="sben3a005-mgt">Sben3a005</option>
        <option value="sben4a006-mgt">Sben4a006</option>
      </select>    
    </div> 
    
    
    <div class="form-group">  
      <label for="nodes">Group 2 </label><br>
      <select multiple class="form-control" id="nodes" name="node">
        <option value="sben1a003-mgt">Sben1a003</option>
        <option value="sben2a004-mgt">Sben2a004</option>
        <option value="sben3a005-mgt">Sben3a005</option>
        <option value="sben4a006-mgt">Sben4a006</option>
      </select>    
    </div> 
    
    
    <div class="form-group">  
      <label for="nodes">Group 3 </label><br>
      <select multiple class="form-control" id="nodes" name="node">
        <option value="sben1a003-mgt">Sben1a003</option>
        <option value="sben2a004-mgt">Sben2a004</option>
        <option value="sben3a005-mgt">Sben3a005</option>
        <option value="sben4a006-mgt">Sben4a006</option>
        <option value="sben3a007-mgt">Sben3a007</option>
        <option value="sben4a008-mgt">Sben4a008</option>
      </select>    
    </div> 
    
    <button type="submit" class="btn btn-info">Submit</button>
    <button type="reset" class="btn btn-info">Clear</button>
    </fieldset>
  </form>
</div> 
</div>


<div class="col-md-12 secondColumn">
<div class="panel panel-info">
<div class="panel panel-heading">Status of Real Time Scan</div>
<div class="panel panel-body"> 
<div id="#tableContent">
<table id="realScanDataTable" class="realtimeTable scrollable table-striped table-bordered table-hover table-responsive table-condensed"> 

<thead>                      
<tr>

<th>Initiator First Name </th>
<th>Initiator Last Name </th>
<th>Status On Node</th>
<th>Scan Date</th>
<th>Scan Time</th>	
<th>Actions</th>		 					
</tr>
</thead>	
							
<tbody>
<c:forEach var="item" items="${returnedList}" varStatus="iter">
<tr class="success">													
<td>${scanSessionObj.getLoginUser().getFirstname()}</td>
<td>${scanSessionObj.getLoginUser().getLastname()}</td>
<td>${item}</td>
<td>${scanSessionObj.getScanDate()}</td>
<td>${scanSessionObj.getScanTime()}</td>	
<td><button type="button" class="btn btn-info btn-xs" onclick="deleteRow(this)">Delete</button></td>						
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

</div><!-- End of First Row -->
</body>
<html>
