<!-- 
Author Jonas Okwara
Date 05-28-2018
main Reports page for 
all  users
-->

<%@ include file="/includes/schedHeader.jsp"%> 

<body>
<div id="wrapper">
<div id="banner"> <h6>OpenScap Automation Scan Scheduler</h6> </div>
<nav class="navbar navbar-inverse">
<div class="container-fluid"> 

<ul class="nav navbar-nav navbar-left">
<li><a href="homeGui.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>  
<li><a href="reportGui.jsp" id="callServlet"><span class="glyphicon glyphicon-user"></span>Scan Reports</a></li> 
<li><a data-toggle="modal" href="#scheduleModal"><span class="glyphicon glyphicon-user"></span> Schedule a Scan</a></li>
</ul>


<ul class="nav navbar-nav navbar-right logout">
<li><a href="initial.jsp">${sessionMessage} ${userObj.firstname} ${userObj.lastname}<span style="margin-left: 14px"> LogOut</span></a></li>
</ul>
</div>
</nav><!-- End of Banner and Headers --> 

<!-- Schedule Modal --> 
<div id="scheduleModal" class="modal fade" role="dialog">
			<div class=" modal-dialog  modal-sm">

				<!-- Modal content-->
				<div class="modal-content">
					
					<div class="modal-body"> 
	
<form id="multiScanForm">
<div class="form-group">
  <label for="comment">Your Scan Hosts:</label><br>
  <textarea class="form-textBox" rows="2" name="nodename" id="nodename"> </textarea>
</div>

<div class="form-group"> <!-- Date input -->
  <label  for="date">Scheduled Date</label><br>
  <input class="form-control dateControl" id="dateOfScan" name="dateOfScan" placeholder="MM/DD/YYYY" type="date"/>
</div>  

<div class="form-group"> <!-- Time input -->
    <label  for="time">Scheduled Time</label><br>
    <input class="form-control  timeControl" id="timeOfScan" name="timeOfScan" placeholder="HH/MM/AM_PM" type="time"/>
</div>


<div class="form-group">
      <label for="group">Task Group</label><br>
      
    <select class="form-control input-md col-sm-24 selectControl" name="group" id="group">
        <option value=" " selected disabled>Group</option>
        <option value="group1">Group 1</option>
        <option value="group2">Group 2</option>
        <option value="group3">Group 3</option>
        <option value="group4">Group 4</option>
        <option value="group5">Group 5</option> 
     </select>
</div>  

<div class="form-group"> <!-- Email input -->
  <label  for="email">Administrator Email</label><br>
  <input class="form-control emailControl"   id="email" name="email" placeholder="Email Address" type="email"/>
</div>

  
<button onClick="submitForm()" id="closeModalButton"  type="submit" class="btn btn-info btn-md">Submit</button>
<button type="reset"  class="btn btn-info btn-md">Clear</button>		
<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>	  
</form>
</div><!-- End of Modal Boday -->

</div>
</div>
</div> 
<!-- End of schedule  Modal Box -->

<div class="panel panel-info   mainContentWrapper">
<div class="panel panel-heading"> </div>
<div class="panel panel-body">
<div class="row secondRow">  

<div class="col-md-12 firstColumn">


<div class="row secondRow">  
<div class="col-md-12 secondColumn">
<div class="panel panel-info dataTablePanel">

<div class="panel panel-heading"><div id="returnedData"> </div> </div>
<div class="panel panel-body">


<div id="scheduleTableContent">
<table id="multiScheduleDataTable" class="scheduletable scrollable table-hover table-striped table-bordered  table-responsive table-condensed"> 
<thead>
<tr>
<th>NodeName</th>
<th>Administrator Firstname</th>
<th>Administrator Lastname</th>
<th>Scheduled Date</th>	
<th>Scheduled Time</th>	
<th>Status</th>			
</tr>			
</thead>	
							

<tfoot>
<tr>
<th>NodeName</th>
<th>Administrator Firstname</th>
<th>Administrator Lastname</th>
<th>Scheduled Date</th>	
<th>Scheduled Time</th>	
<th>Status</th>		
</tr>			
</tfoot> 
					
</table> 
</div>  


</div>
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
