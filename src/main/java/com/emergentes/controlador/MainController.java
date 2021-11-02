package com.emergentes.controlador;

import com.emergentes.modelo.Productos;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhonny
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        int id;
        String op;
        ArrayList<Productos> lista = new ArrayList<Productos>();

        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        if (op.equals("list")) {
            //operaciones listar
            String sql = "select * from productos";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Productos pro = new Productos();
                    pro.setId(rs.getInt("id"));
                    pro.setProducto(rs.getString("Producto"));
                    pro.setPrecio(rs.getDouble("Precio"));
                    pro.setCantidad(rs.getInt("cantidad"));
                    lista.add(pro);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (op.equals("nuevo")) {
            //operacion para desplegar formulario
            Productos p = new Productos();
            request.setAttribute("pro", p);
            request.getRequestDispatcher("editar.jsp").forward(request, response);

        }
        if (op.equals("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            try {
                ps = conn.prepareStatement("delete from productos where id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (op.equals("editar")) {
            id = Integer.parseInt(request.getParameter("id"));

            try {
                Productos pro1 = new Productos();
                ps = conn.prepareStatement("select * from productos where id=?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    pro1.setId(rs.getInt("id"));
                    pro1.setProducto(rs.getString("Producto"));
                    pro1.setPrecio(rs.getDouble("Precio"));
                    pro1.setCantidad(rs.getInt("cantidad"));

                }
                request.setAttribute("pro", pro1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String producto = request.getParameter("producto");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Productos pro = new Productos();
        pro.setId(id);
        pro.setProducto(producto);
        pro.setPrecio(precio);
        pro.setCantidad(cantidad);

        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (id == 0) {
            String sql = "insert into productos (Producto,Precio,cantidad)values(?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, pro.getProducto());
                ps.setDouble(2, pro.getPrecio());
                ps.setInt(3, pro.getCantidad());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);

            }
        } else {
            //modificar tabla productos
            String sql1 = "update productos set Producto=?,Precio=?,cantidad=? where id=?";
            try {
                ps = conn.prepareStatement(sql1);
                ps.setString(1, pro.getProducto());
                ps.setDouble(2, pro.getPrecio());
                ps.setInt(3, pro.getCantidad());
                ps.setInt(4,pro.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        response.sendRedirect("MainController");

    }

}
