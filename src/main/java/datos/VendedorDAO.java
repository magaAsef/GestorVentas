package datos;

import domain.Vendedor;

import java.sql.*;
import java.util.*;

import static datos.Conexion.*;

public class VendedorDAO {
    private static final String SQL_SELECT = "SELECT * FROM vendedor";
    private static final String SQL_INSERT = "INSERT INTO vendedor(nombre, sueldo) VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE vendedor SET nombre = ?, sueldo = ? WHERE codigo = ?";

    private static final  String SQL_DELETE = "DELETE FROM vendedor WHERE codigo = ?";

    public List<Vendedor> seleccionar()  {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Vendedor vendedor = null;
        List<Vendedor> vendedores = new ArrayList<>();

        try {
            conn =  getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT);
            rs = stmnt.executeQuery();
            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                Double sueldo = rs.getDouble("sueldo");

                vendedor = new Vendedor(codigo, nombre, sueldo);
                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally{
            try {
                close(rs);
                close(stmnt);
                close(conn);

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

        return vendedores;
    }

    public int insertar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_INSERT);
            stmnt.setString(1, vendedor.getNombre());
            stmnt.setDouble(2, vendedor.getSueldo());
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

    public int actualizar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_UPDATE);
            stmnt.setString(1, vendedor.getNombre());
            stmnt.setDouble(2, vendedor.getSueldo());
            stmnt.setInt(3, vendedor.getCodigo());
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

    public int eliminar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmnt = conn.prepareStatement(SQL_DELETE);
            stmnt.setInt(1, vendedor.getCodigo());
            registros = stmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
