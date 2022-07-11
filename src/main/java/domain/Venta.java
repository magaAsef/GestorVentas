package domain;

public class Venta {
    private int codigo;
    private int codigoVendedor;
    private int codigoProducto;


    public Venta() {
    }

    public Venta(int codigo) {
        this.codigo = codigo;
    }

    public Venta(int codigoVendedor, int codigoProducto) {
        this.codigoVendedor = codigoVendedor;
        this.codigoProducto = codigoProducto;

    }

    public Venta(int codigo, int codigoVendedor, int codigoProducto) {
        this.codigo = codigo;
        this.codigoVendedor = codigoVendedor;
        this.codigoProducto = codigoProducto;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "codigo=" + codigo +
                ", codigoVendedor=" + codigoVendedor +
                ", codigoProducto=" + codigoProducto +

                '}';
    }
}
