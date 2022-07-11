package servicio;

import datos.ProductoDAO;
import datos.VendedorDAO;
import datos.VentaDAO;
import domain.Producto;
import domain.Vendedor;
import domain.Venta;

import java.util.ArrayList;
import java.util.List;

public class Operaciones {
    private final VendedorDAO datosVendedor;
    private final ProductoDAO datosProducto;
    private final VentaDAO datosVenta;

    public Operaciones() {
        datosVendedor = new VendedorDAO();
        datosProducto = new ProductoDAO();
        datosVenta = new VentaDAO();
    }

    public void registrarNuevaVenta(int codVendedor, int codProducto){
        Venta nuevaVenta = new Venta(codVendedor, codProducto);
        this.datosVenta.insertar(nuevaVenta);
        System.out.println("Se ha registrado la venta");
    }

    public void registrarNuevoProducto(String nombre, Double precio, String categoria){
        Producto nuevoProducto = new Producto(nombre, precio, categoria);
        this.datosProducto.insertar(nuevoProducto);
        System.out.println("Se ha registrado el producto");
    }

    public Double calcularComisionVendedor(int codVendedor){
        List<Venta> ventasPorVendedor = datosVenta.buscarPorVendedor(codVendedor);
        List<Producto> productosVendidos = new ArrayList<>();
        for(Venta venta: ventasPorVendedor){
            productosVendidos.add(datosProducto.buscarProductoPorCodigo(venta.getCodigoProducto()));
        }
        int cantVentas = ventasPorVendedor.size();
        Double comision = Vendedor.calcularComision(cantVentas, productosVendidos);
        return comision;
    }

    public Producto buscarProductoPorNombre(String nombre){
        Producto producto = datosProducto.buscarProductoPorNombre(nombre);
        return producto;
    }
    public List<Producto> buscarProductoPorCategoria(String categoria){
        List<Producto> productos = datosProducto.buscarProductosPorCategoria(categoria);
        return productos;
    }
    public Producto buscarProductoPorCodigo(int codigo){
        Producto producto = datosProducto.buscarProductoPorCodigo(codigo);
        return producto;
    }
}
