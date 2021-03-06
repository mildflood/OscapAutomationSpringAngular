<%@ include file="/includes/loginPageHeader.jsp"%> 
<body>
	<div id="bodyContainer">

		<div id="middleContent">
			<!-- Beginning of Outer Panel -->
			<div class="panel panel-primary outerPanel">
				<div class="panel panel-heading">OpenScap Extention Tool</div>

				<div class="panel panel-body">

					<div id="imageContainer">
						<div class="row">

							<div class="col-lg-4">
								<img
									style="width: 70em; height: 240px; margin-top: 20px; margin-bottom: 20px; border: 2px solid #9CC"
									src="images/digits2.jpeg" class="img-rounded"
									alt="Cinque Terre">
							</div>


						</div>
					</div>
					<div class="panel panel-default login-panel">

						<!-- Beginning of Inner Panel -->
						<div class="panel panel-heading">User Login  
						
						<%! String error = ""; %>
					    <% error  = (String) session.getAttribute("errorMessage");%>
					    <% if(error != null){ %>
					    <strong style="color:red">${errorMessage}</strong>
					    <% } %>
						
						</div>
						<div class="panel panel-body">


							<form role="form" class="form-inline" action="LoginCtrlServlet"
								method="Post">
								<div class="form-group">
									<label for="username"><span
										class="glyphicon glyphicon-user"></span> <strong>UserName</strong></label><br>
									<input class="form-control" name="username" required
										autocomplete="off" placeholder="Username" />
								</div>
								<div class="form-group">
									<label for="password"><span
										class="glyphicon glyphicon-eye-open"></span> <strong>Password:</strong></label><br>
									<input type="password" name="password" autocomplete="off"
										required class="form-control" />
									<!--  pattern="[a-zA-Z0-9]{8,}" required class="form-control" /> -->
								</div>

								<br>
								<br>
							
								<div id="buttons">
									<button type="submit" class="btn btn-primary">
										<span class="glyphicon glyphicon-log-in"></span> Login
									</button>
									<button type="reset" class="btn btn-primary">
										<span class="glyphicon glyphicon-edit"></span> Reset
									</button>

								</div>
							</form>

						</div>
					</div>
					<!-- End of Inner panel -->
				</div>

			</div>
			<!-- End of Outer Panel -->
		</div>

	</div>
	<!-- End of bodyContainer -->
</body>
</html>