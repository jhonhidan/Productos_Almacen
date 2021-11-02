<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Productos"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Productos> lista = (List<Productos>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Productos</h1>
        <p>
            <a href="MainController?op=nuevo">Nuevo</a>
        </p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Productos</th>
                <th>Precio</th>
                <th>Cantidad</th>

            </tr>
            
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.producto}</td><!-- comment -->
                    <td>${item.precio}</td><!-- comment -->
                    <td>${item.cantidad}</td><!-- comment -->
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Esta seguro ???'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
