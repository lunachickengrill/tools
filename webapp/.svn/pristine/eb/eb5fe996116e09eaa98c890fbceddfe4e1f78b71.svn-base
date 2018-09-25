
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

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="baseUrl" value="/region" />
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h3>region/{id}</h3>
			<p>The context path is: ${context}.</p>
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

		<ol class="breadcrumb">
			<li><a href="${context}/home">Home</a></li>
			<li class="active">Region</li>
			<li><a href="${context}/region/${dto.id}">${dto.name}</a></li>
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">${dto.name}</div>
			<div class="panel-body fixed-panel">
				<table class="table table-borderd">

					<tbody>
						<tr>
							<td>ID</td>
							<td>${dto.id}</td>
						</tr>
						<tr>
							<td>Name</td>
							<td>${dto.name}</td>
						</tr>
						<tr>
							<td>RegionCode</td>
							<td>${dto.regionCode}</td>
						</tr>
						<tr>
							<td>Description</td>
							<td>${dto.description}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="panel panel-default">
				<table class="table table-borderd">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dto.countries}" var="country">
							<spring:url value="/country/${country.id}" var="expandUrl" />
							<spring:url value="${baseUrl}/edit/${country.id}" var="editUrl" />
							<spring:url value="${baseUrl}/delete${country.id}"
								var="deleteUrl" />


							<tr>
								<td><a href="${expandUrl}">${country.id}</a></td>
								<td>${country.countryCode}</td>
								<td>${country.name}</td>
								<td>${country.description}</td>
								<td><a href="${editUrl}">edit</a></td>
								<td><a href="${deleteUrl}">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	<div id="footer">
		<div class="container">
			<p class="text-muted credit">
				footer example <a href="/">superTolleFirma</a>
			</p>
		</div>
	</div>
</body>
</html>