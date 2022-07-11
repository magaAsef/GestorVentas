import datos.Conexion;
import datos.ProductoDAO;
import datos.VendedorDAO;
import datos.VentaDAO;
import domain.Producto;
import domain.Vendedor;
import domain.Venta;
import servicio.Operaciones;

import java.sql.*;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int opcion = - 1;
        Scanner scanner = new Scanner(System.in);
        Operaciones operaciones = new Operaciones();

        while(opcion != 0){
            System.out.println("Elija una opcion: \n" +
                    "1. Registrar nueva venta. \n" +
                    "2. Registrar nuevo producto. \n" +
                    "3. Calcular la comision que recibira un empleado. \n" +
                    "4. Buscar producto por nombre. \n" +
                    "5. Buscar producto por codigo. \n" +
                    "6. Buscar productos por categoria. \n" +
                    "0. Salir. \n");

            opcion = Integer.parseInt(scanner.nextLine());

            switch(opcion){
                case 1:
                    System.out.println("Ingrese el codigo del vendedor que realizo la venta: ");
                    int codVendedor = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese el codigo del producto vendido");
                    int codProducto = Integer.parseInt(scanner.nextLine());

                    operaciones.registrarNuevaVenta(codVendedor, codProducto);
                    break;

                case 2:
                    System.out.println("Ingrese el nombre del producto ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el precio: ");
                    Double precio = Double.parseDouble(scanner.nextLine());
                    System.out.println("Ingrese la categoria a la que pertenece el producto: ");
                    String categoria = scanner.nextLine();

                    operaciones.registrarNuevoProducto(nombre, precio, categoria);
                    break;
                    
                case 3:
                    System.out.println("Ingrese el codigo del vendedor que desea calcular la comision: ");
                    codVendedor = Integer.parseInt(scanner.nextLine());
                    Double comision = operaciones.calcularComisionVendedor(codVendedor);
                    System.out.println("comision = " + comision);
                    break;

                case 4:
                    System.out.println("Ingrese el nombre del producto: ");
                    nombre = scanner.nextLine();
                    Producto resultado = operaciones.buscarProductoPorNombre(nombre);
                    System.out.println("resultado = " + resultado);
                    break;

                case 5:
                    System.out.println("Ingrese el codigo del producto: ");
                    int codigo = Integer.parseInt(scanner.nextLine());
                    resultado = operaciones.buscarProductoPorCodigo(codigo);
                    
                case 6:
                    System.out.println("Ingrese la categoria de productos: ");
                    categoria = scanner.nextLine();
                    List<Producto> productos = operaciones.buscarProductoPorCategoria(categoria);
                    for (Producto producto: productos){
                        System.out.println("producto = " + producto);
                    }
                    break;

                case 0:
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opcion no reconocida");
            }
        }
    }
}
