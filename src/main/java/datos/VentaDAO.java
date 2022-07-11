package datos;

import domain.Venta;

import java.sql.*;
import java.util.*;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class VentaDAO {
    private static final String SQL_SELECT = "SELECT codigo, codigo_vendedor, codigo_producto FROM venta";
    private static  final String SQL_INSERT ="INSERT INTO venta(codigo_vendedor, codigo_producto) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE venta SET codigo_vendedor = ?, codigo_producto = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM venta WHERE codigo = ?";
    private static final String SQL_SELECT_VENDEDOR = "SELECT codigo, codigo_vendedor, codigo_producto FROM venta WHERE codigo_vendedor = ?";


    public List<Venta> seleccionar() {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT);
            rs = stmnt.executeQuery();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                int codigoVendedor = rs.getInt("codigo_vendedor");
                int codigoProducto = rs.getInt("codigo_producto");

                venta = new Venta(codigo, codigoVendedor, codigoProducto);
                ventas.add(venta);
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

        return ventas;
    }

    public List<Venta> buscarPorVendedor(int codV) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT_VENDEDOR);
            stmnt.setInt(1, codV);
            rs = stmnt.executeQuery();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                int codigoVendedor = rs.getInt("codigo_vendedor");
                int codigoProducto = rs.getInt("codigo_producto");

                venta = new Venta(codigo, codigoVendedor, codigoProducto);
                ventas.add(venta);
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

        return ventas;
    }

    public int insertar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_INSERT);
            stmnt.setInt(1, venta.getCodigoVendedor());
            stmnt.setInt(2, venta.getCodigoProducto());
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

    public int actualizar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_UPDATE);
            stmnt.setInt(1, venta.getCodigoVendedor());
            stmnt.setInt(2, venta.getCodigoProducto());
            stmnt.setInt(3, venta.getCodigo());
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

    public int eliminar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_DELETE);
            stmnt.setInt(1, venta.getCodigo());
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
