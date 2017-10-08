<%-- 
    Document   : Checkout
    Created on : Mar 20, 2014, 10:33:06 PM
    Author     : javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, com.tecster.tests1.shopping.CD" %>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music Without Borders Checkout</title>
</head>
<body bgcolor="#33CCFF">
 <font face="Times New Roman,Times" size=+3>
  Music Without Borders Checkout
 </font>
 <hr><p>
 <center>
 <table border="0" cellpadding="0" width="100%" bgcolor="#FFFFFF">
 <tr>
 <td><b>ALBUM</b></td>
 <td><b>ARTIST</b></td>
 <td><b>COUNTRY</b></td>
 <td><b>PRICE</b></td>
 <td><b>QUANTITY</b></td>
 <td></td>
 </tr>
 <%
  List<Object> buylist = (List<Object>) session.getAttribute("shopping.shoppingcart");
  String amount = (String) request.getAttribute("amount");
  for (int i=0; i < buylist.size();i++) {
   CD anOrder = (CD) buylist.get(i);
 %>
 <tr>
 <td><b><%= anOrder.getAlbum() %></b></td>
 <td><b><%= anOrder.getArtist() %></b></td>
 <td><b><%= anOrder.getCountry() %></b></td>
 <td><b><%= anOrder.getPrice() %></b></td>
 <td><b><%= anOrder.getQuantity() %></b></td>
 </tr>
 <%
  }
  session.invalidate();
 %>
 <tr>
 <td>     </td>
 <td>     </td>
 <td><b>TOTAL</b></td>
 <td><b>$<%= amount %></b></td>
 <td>     </td>
 </tr>
 </table>
 <p>
 <%-- a href="/examples/jsp/shopping/EShop.jsp">Shop some more!</a --%>
 <a href="/TestMvcModel2/EShop.jsp">Shop some more!</a>
 </center>
</body>
</html>
