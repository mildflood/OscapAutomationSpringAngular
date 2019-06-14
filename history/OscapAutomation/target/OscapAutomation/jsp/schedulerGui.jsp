<!-- 
Author Jonas Okwara
Date 05-28-2018
main Reports page for 
all  users
-->

<%@ include file="../includes/schedHeader.jsp"%> 

<body>
<div id="wrapper">
<div id="banner"> <h6>OpenScap Automation Scan Scheduler</h6> </div>
<nav class="navbar navbar-inverse">
<div class="container-fluid"> 

<ul class="nav navbar-nav navbar-left">
<li><a href="homeGui.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>  
<li><a href="scanGui.jsp"><span class="glyphicon glyphicon-user"></span>Real Time Scan</a></li>
<li><a href="reportGui.jsp"><span class="glyphicon glyphicon-user"></span>Scan Reports</a></li> 
<li><a href="multiScanGui.jsp"><span class="glyphicon glyphicon-user"></span>Multi Scan</a></li>
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


<form class="form-inline"  id="singleScanForm" onsubmit="return submitSingleSchedForm();">

<!-- <form class="form-inline"  action="ScheduleCtrlServlet" method="Post"> -->
<fieldset class="scheduler-border">
<legend class="scheduler-border">Flexible Schedule</legend>
<div class="form-group">
      <label for="hostname">Nodes</label><br>
    <select multiple class="form-control input-md col-lg-24"  name="nodename" id="nodename">
        <option value="sben1a003">Sben1a003</option>
        <option value="sben2a004">Sben2a004</option>
        <option value="sben3a005">Sben3a005</option>
        <option value="sben4a006">Sben4a006</option>
        <option value="sben5a007">Sben5a007</option> 
     </select>
</div> 


<div class="form-group">
      <label for="group">Task Group</label><br>
    <select class="form-control input-md col-lg-24"  name="group" id="group">
        <option value=" " selected disabled>Group</option>
        <option value="group1">Group 1</option>
        <option value="group2">Group 2</option>
        <option value="group3">Group 3</option>
        <option value="group4">Group 4</option>
        <option value="group5">Group 5</option> 
     </select>
</div> 


<div class="form-group"> <!-- Date input -->
        <label class="control-label" for="date">Date</label><br>
        <input class="form-control" id="dateOfScan" name="dateOfScan" placeholder="MM/DD/YYYY" type="date"/>
</div>  


<div class="form-group"> <!-- Time input -->
        <label class="control-label" for="time">Time</label><br>
        <input class="form-control" id="timeOfScan" name="timeOfScan" placeholder="HH/MM/AM_PM" type="time"/>
</div>  


<div class="form-group"> <!-- Email input -->
        <label class="control-label" for="email">Email</label><br>
        <input class="form-control" id="email" name="email" placeholder="email address" type="email"/>
</div>  

<button type="submit" class="btn btn-info">Submit</button>
<button type="reset" class="btn btn-info">Clear</button>

</fieldset> 

<fieldset class="scheduler-border">
<legend class="scheduler-border">Fixed Schedule</legend>
<div class="radio sm-3">
<label class="radio-inline"><input type="radio" value="">OnMondays</label>
<label class="radio-inline"><input type="radio" value="">OnTuesdays</label>
<label class="radio-inline"><input type="radio" value="">OnWednesdays</label>
<label class="radio-inline"><input type="radio" value="">OnThursdays</label>
<label class="radio-inline"><input type="radio" value="">OnFridays</label>
<label class="radio-inline"><input type="radio" value="">OnSaturdays</label>
<label class="radio-inline"><input type="radio" value="">OnSundays</label>

</div>
</fieldset> 
</form>

</div><!-- End Of First Column -->

</div>

<div class="row secondRow">  
<div class="col-md-12 secondColumn">
<div class="panel panel-info dataTablePanel">

<div class="panel panel-heading"><div id="returnedData"> </div> </div>
<div class="panel panel-body">


<div id="scheduleTableContent">
<table id="scheduleDataTable" class="scheduletable scrollable table-hover table-striped table-bordered  table-responsive table-condensed"> 
<thead>
<tr>
<th>Nodename</th>
<th>Administrator Firstname</th>
<th>Administrator Lastname</th>
<th>Scheduled Date</th>	
<th>Scheduled Time</th>	
<th>Status</th>	
<th>Group</th>				
</tr>			
</thead>	
							

<tfoot>
<tr>
<th>Nodename</th>
<th>Administrator Firstname</th>
<th>Administrator Lastname</th>
<th>Scheduled Date</th>	
<th>Scheduled Run Time</th>	
<th>Status</th>	
<th>Group</th>
					
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


</body>
</html>


