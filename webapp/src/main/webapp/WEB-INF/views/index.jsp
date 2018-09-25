
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
</head>
<body>
	<div class="container-group">
		<div class="container-fluid">
			<div class="page-header">
				<h3>Main view</h3>
			</div>
			<div class="navbar navbar-default navbar-fixed">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Brand</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">Search</a></li>
					<li><a href="#">More</a></li>
					<li><a href="#">Options</a></li>
				</ul>
			</div>
			<ol class="breadcrumb">
				<li><a href="/home">Home</a></li>
				<li class="active">Region</li>
			</ol>
			<div class="container">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="footer">
				<div class="container">
					<p class="text-muted credit">
						Example courtesy <a href="http://martinbean.co.uk">Martin Bean</a>
						and <a href="http://ryanfait.com/sticky-footer/">Ryan Fait</a>.
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>