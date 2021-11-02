<%@page import="com.emergentes.modelo.Productos"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : editar
    Created on : 2 nov. 2021, 11:03:17
    Author     : Jhonny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Productos pro = (Productos) request.getAttribute("pro");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${pro.id==0}">
                Nuevo
            </c:if>
             <c:if test="${pro.id !=0}">
                 Editar
            </c:if>
        </h1>
        
        <form action="MainController" method="POST">
            <input type="hidden" name="id" value="${pro.id}" />
            <table >

                <tr>
                    <th></th>
                    <th></th>
                </tr>

                <tr>
                    <td>Producto</td>
                    <td>
                        <input type="text" name="producto" value="${pro.producto}" />
                    </td>

                </tr>
                <tr>
                    <td>Precio</td>
                    <td>
                        <input type="text" name="precio" value="${pro.precio}" />
                    </td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td>
                        <input type="text" name="cantidad" value="${pro.cantidad}" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Enviar" />
                    </td>
                </tr>

            </table>

        </form>

    </body>
</html>
