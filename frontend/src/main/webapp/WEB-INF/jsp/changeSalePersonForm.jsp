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
			href="/customer/customersList">Customers list</a> <a
			href="/order/ordersList">Orders list </a>
	</div>

	
	<div class="container">
	<h2>Change Sale person</h2><br>
	<div class="alert">
			<span class="closebtn"
				onclick="this.parentElement.style.display='none';">&times;</span> <strong>Warning!</strong>
			When you change SalePerson for Customer, Orders between Customer and Old Sale Person will
			be deleted.
		</div>
		<br>
		
			<form action="/customer/changeSalePersonForm" method="post">
			<label for="customerId">Customer id:</label><br>
		
		 	<input type="number" name="customerId"	value=${customer.id } readOnly="readonly"><br>
		  
		  	<label >Customer name</label><br>
		   	<input type="text" value=${customer.name } readOnly="readonly"><br>

		 	<label >City</label><br>
			<input type="text" value=${customer.city } readOnly="readonly"><br>

			<label >Rating</label><br>
		 	<input type="number" step="0.01" value=${customer.rating} readOnly="readonly"><br>
		 	
		 	<label for="salePersonId" >SalePerson id</label><br>
			
			<select name="salePersonId" >
			  <option value="${customer.salePersonId}">${customer.salePersonId}</option>
					 
			  <option value=""></option>
				 
			  <c:forEach items="${salePersonIds}" var="salePersonId" varStatus="status">
			  <option value="${salePersonId}">${salePersonId}</option>
			  </c:forEach>
					 
			 </select> 
				  	
		<br>


		
		<input type="submit" formaction="/customer/changeSalePerson" value="Change Sale Person">
		<br> 

	</form>
</div>
</body>

</html>
