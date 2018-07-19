<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<h2>Update Order</h2>
	<form:form action="/order/updateOrder" method="post" modelAttribute="order">
	
		<label for="id">Order id</label><br>	
		<form:input type="number" name="id" path="id" readOnly="readonly"/><br>
		
		<label for="amount">Order amount</label><br>	
		<form:input type="number" name="amount" step="0.01" path="amount" />
		<form:errors path="amount" cssClass="error"/><br>
		
			
		<label for="orderDate">Order date</label><br>	
		<form:input type="date" name="orderDate"  path="orderDate" />
		<form:errors path="orderDate" cssClass="error"/><br><br>
		
		
		<label for="customerId">Customer id</label><br>	
		<form:input type="number" name="customerId" path="customerId" readOnly="readonly"/>
		<form:errors path="customerId" cssClass="error"/><br>
		
		
		
		<label for="salePersonId">Sale Person id</label><br>	
		<form:input type="number" name="salePersonId" path="salePersonId" readOnly="readonly"/>
		<form:errors path="salePersonId" cssClass="error"/><br>
		
		<input type="submit" formaction="/order/update" value="Update">

		<br>

	</form:form>
</div>
</body>

</html>
