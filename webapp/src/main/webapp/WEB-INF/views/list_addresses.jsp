
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
<jsp:useBean id="requestList" scope="request" type="java.util.List" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="baseUrl" value="/addresses" />
</head>
<body>
	<div class="container-group">
		<div class="container-fluid">
			<div class="page-header">
				<h3>list_addresses</h3>
				<p>The context path is: ${context}.</p>
				<p>list_addresses.jsp</p>
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
					<li><a href="${context}/region/list">Regiony</a></li>
					<li><a href="${context}/country/list">Country</a></li>
					<li><a href="${context}/city/list">Cities</a></li>
					<li class="active">Addresses</li>
					<li><a href="${context}/address/create"><button
								class="btn-small">create address</button></a></li>
				</ol>

				<div class="panel panel-default">
					<table class="table table-borderd">
						<thead>
							<tr>
								<th>ID</th>
								<th>Street Name</th>
								<th>Street Number</th>
								<th>Entrance</th>
								<th>Description</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestList}" var="request">
								<c:url value="${baseUrl}/expand" var="openUrl">
									<c:param name="id" value="${request.addressId}" />
								</c:url>
								<c:url value="${baseUrl}/edit" var="editUrl">
									<c:param name="id" value="${request.addressId}" />
								</c:url>
								<c:url value="${baseUrl}/delete" var="deleteUrl">
									<c:param name="id" value="${request.addressId}" />
								</c:url>
								<tr>
									<td><a href="${openUrl}">${request.addressId}</a></td>
									<td>${request.streetName}</td>
									<td>${request.streetNumber}</td>
									<td>${request.entrance}</td>
									<td>${request.description}</td>
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
		</div>
	</div>
</body>
</html>