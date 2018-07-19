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
  <a href="/index.html" class="active">Home</a>
  <a href="/salePeople/salePeopleList">Sale's people list</a>
  <a href="/customer/customersList">Customers list</a>
  <a href="/order/ordersList">Orders list </a>
</div>

	
	<div class="container">
	<h2>Fill form to create new Customer</h2>
	<form:form action="/customer/createNewCustomer" method="post"  modelAttribute="customer">
					<label for="name">Customer name</label><br>
					<form:input type="text" name="name" path="name"/>
					<form:errors path="name" cssClass="error"/><br> 
					
					<label for="city">City</label><br>
					 <form:input type="text" name="city" path="city" />
					 <form:errors path="city" cssClass="error"/><br>
					 
					 <label for="rating"> Rating</label><br>
					 <form:input type="number"  name="rating" path="rating"/>
					 <form:errors path="rating" cssClass="error"/><br> 
					 
					 <label for="salePersonId">Sale Person id</label><br>
					 					 
					  <select name="salePersonId" >
					  <option value="${customer.salePersonId}">${customer.salePersonId}</option>
					 
					  <option value=""></option>
					 
					  <c:forEach items="${salePersonIds}" var="salePersonId" varStatus="status">
					  <option value="${salePersonId}">${salePersonId}</option>
					  </c:forEach>
					 
					  </select> 
					  <form:errors path="salePersonId" cssClass="error"/><br> 
					  
   <br> 
  <input type="submit"  formaction="/customer/create" value ="Create" >
 
</form:form>
</div>
</body>

</html>
