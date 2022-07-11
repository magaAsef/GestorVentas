package domain;

import java.util.List;

public class Vendedor {
    private int codigo;
    private String nombre;
    private Double sueldo;


    public Vendedor() {
    }

    public Vendedor(int codigo) {
        this.codigo = codigo;
    }

    public Vendedor(String nombre, Double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public Vendedor(int codigo, String nombre, Double sueldo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sueldo = sueldo;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public static Double calcularComision(int cantidadVentas, List<Producto> productosVendidos){
        Double suma = 0.0;
        Double porcentaje  = 0.0;
        Double comision = 0.0;
        if(cantidadVentas == 2){
            porcentaje = 5.0;
        }
        else if (cantidadVentas > 2) {
            porcentaje = 10.0;
        }
        for(Producto producto: productosVendidos){
            suma = suma + producto.getPrecio();
        }
        comision = comision + (suma * porcentaje)/100;
        return comision;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
