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
			href="/customer/customersList">Customers list</a> <a href="/order/ordersList">Orders list </a>
	</div>
	<div class="container">
	<h2>Update Sale person</h2>

	<form:form action="/salePeople/updateSalePerson" method="post" modelAttribute="salePerson">
	
		<label for="id">Sale Person id</label><br>	
		<form:input type="number" name="id" path="id" readOnly="readonly"/><br>
		
		
		<label for="name">Sale person name</label><br>
		<form:input type="text" name="name" path="name"/>
		<form:errors path="name" cssClass="error"/><br> 
		
		<label for="city">City</label><br>		  
  		<form:input type="text" name="city" path="city"/>
  		<form:errors path="city" cssClass="error"/><br> 
  		
  		<label for="commissions">Commissions</label><br>
  		<form:input type="number" step="0.01" name="commissions" path="commissions"/>
  		<form:errors path="commissions" cssClass="error"/><br> 

		<input type="submit" formaction="/salePeople/update" value="Update">
		<br>

	</form:form>	
</div>
</body>

</html>
