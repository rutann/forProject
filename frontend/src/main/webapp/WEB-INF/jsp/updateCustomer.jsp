<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html lang="en">
<head>
<link href="<c:url value="/css/main.css" />" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<h1>Business connections</h1>
	<div class="topnav">
		<a href="/index.html" class="active">Home</a> <a
			href="/salePeople/salePeopleList">Sale's people list</a> <a
			href="/customer/customersList">Customers list</a> <a href="/order/ordersList">Orders list  </a>
	</div>

	
<div class="container">
<h2>Update Customer</h2>
	<form:form action="/customer/updateCustomer" method="post" modelAttribute="customer">
	
		<label for="id">Customer id</label><br>	
		<form:input type="number" name="id" path="id" readOnly="readonly"/><br>
		
		<label for="name">Customer name</label><br>	
		<form:input type="text" name="name" path="name" />
		<form:errors path="name" cssClass="error"/>
		<br>
		
		<label for="city">City</label><br>	
		<form:input type="text" name="city" path="city" />
		<form:errors path="city" cssClass="error"/>
		<br>
		
		<label for="rating">Rating</label><br>	
		<form:input type="number" step="0.01" name="rating"	path="rating" />
		<form:errors path="rating" cssClass="error"/>
		<br>
		
		<label for="salePersonId">SalePerson id</label><br>	
		<form:input type="number" name="salePersonId" path="salePersonId" readOnly="readonly"/>
		<form:errors path="salePersonId" cssClass="error"/>
		<br>

		<input type="submit" formaction="/customer/update" value="Update">
		 <input type="submit" formaction="/customer/changeSalePersonForm" value="Change Sale Person">
		<br>

	</form:form>
</div>
</body>

</html>
