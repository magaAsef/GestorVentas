package datos;

import domain.Producto;

import java.sql.*;
import java.util.*;
import static datos.Conexion.*;

public class ProductoDAO {
    private static final String SQL_SELECT = "SELECT codigo, nombre, precio, categoria FROM producto";
    private static  final String SQL_INSERT ="INSERT INTO producto(nombre, precio, categoria) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre = ?, precio = ?, categoria = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE codigo = ?";
    private static final String SQL_SELECT_CODIGO = "SELECT codigo, nombre, precio, categoria FROM producto WHERE codigo = ?";
    private static final String SQL_SELECT_NOMBRE = "SELECT codigo, nombre, precio, categoria FROM producto WHERE nombre = ?";
    private static final String SQL_SELECT_CATEGORIA  = "SELECT codigo, nombre, precio, categoria FROM producto WHERE categoria = ?";

    public List<Producto> seleccionar()  {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT);
            rs = stmnt.executeQuery();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                String categoria = rs.getString("categoria");

                producto = new Producto(codigo, nombre, precio, categoria);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmnt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return productos;
    }

    public Producto buscarProductoPorCodigo(int cod) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Producto producto = null;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT_CODIGO);
            stmnt.setInt(1, cod);
            rs = stmnt.executeQuery();
            rs.next();


            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            Double precio = rs.getDouble("precio");
            String categoria = rs.getString("categoria");

            producto = new Producto(codigo, nombre, precio, categoria);


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmnt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return producto;
    }

    public Producto buscarProductoPorNombre(String nom) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Producto producto = null;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT_NOMBRE);
            stmnt.setString(1, nom);
            rs = stmnt.executeQuery();
            rs.next();

            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            Double precio = rs.getDouble("precio");
            String categoria = rs.getString("categoria");

            producto = new Producto(codigo, nombre, precio, categoria);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmnt);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return producto;
    }

    public List<Producto> buscarProductosPorCategoria(String cat) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT_CATEGORIA);
            stmnt.setString(1, cat);
            rs = stmnt.executeQuery();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                String categoria = rs.getString("categoria");

                producto = new Producto(codigo, nombre, precio, categoria);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmnt);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return productos;
    }

    public int insertar(Producto producto)  {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_INSERT);
            stmnt.setString(1, producto.getNombre());
            stmnt.setDouble(2, producto.getPrecio());
            stmnt.setString(3, producto.getCategoria());
            registros = stmnt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmnt);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int actualizar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_UPDATE);
            stmnt.setString(1, producto.getNombre());
            stmnt.setDouble(2, producto.getPrecio());
            stmnt.setString(3, producto.getCategoria());
            stmnt.setInt(4, producto.getCodigo());
            registros = stmnt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmnt);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_DELETE);
            stmnt.setInt(1, producto.getCodigo());
            registros = stmnt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmnt);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

}
