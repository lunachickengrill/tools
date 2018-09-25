
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
</head>
<body>
	<div class="container-group">
		<div class="container-fluid">
			<div class="page-header">
				<h3>list_regions</h3>
				<p>The context path is: ${context}.</p>
				<p>list_regions.jsp</p>
			</div>
			<div class="navbar navbar-default navbar-fixed">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">myCompany</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${context}/home">Home</a></li>
					<li><a href="search">Advanced Search</a></li>
				</ul>
			</div>
			<div class="container">
				<ol class="breadcrumb">
					<li><a href="${context}/home">Home</a></li>
					<li class="active">Region</li>
					<li><a href="${context}/region/create"><button
								class="btn-small">create region</button></a></li>
				</ol>


				<form:form method="POST" commandName="dto">
					<div class="form-group">
						<label for="title">Name</label>
						<form:input path="name" />
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<form:input path="description" />
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form:form>
			</div>

			<div id="footer">
				<div class="container">
					<p class="text-muted credit">
						footer example <a href="/">superTolleFirma</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>