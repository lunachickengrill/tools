
<!DOCTYPE html>
<html lang="en">
<head>
<title>Addressmanagement</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="baseUrl" value="/home"/>
</head>
<body>
	<div class="container-group">
		<div class="container-fluid">
			<div class="page-header">
				<h3>Addressmanagement</h3>
				<p>The context path is: ${context}</p>
				<p>home.jsp</p>
			</div>
			<div class="navbar navbar-default navbar-fixed">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">myCompany</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${context}${baseUrl}">Home</a></li>
					<li><a href="search">Advanced Search</a></li>
				</ul>
			</div>
			<div class="container">
				<ol class="breadcrumb">
					<li><a href="${context}/home">Home</a></li>
					<li class="active">Home</li>
				</ol>

				<h2>Addressmanagement Application</h2>

				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-body">
							<a href="${context}/region">Regions</a>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body">
							<a href="${context}/country/list">Countries</a>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body">
							<a href="${context}/city/list">Cities</a>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-body">
							<a href="${context}/address/list">Addresses</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">
			<div class="container">
				<p class="text-muted credit">
					footer example <a href="/">superTolleFirma</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>