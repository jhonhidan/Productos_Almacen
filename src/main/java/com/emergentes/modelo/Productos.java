
package com.emergentes.modelo;

/**
 *
 * @author Jhonny
 */
public class Productos {
private int id;
    private String producto;
    private Double precio;
    private int cantidad;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Productos{" + "id=" + id + ", producto=" + producto + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }
    
}
