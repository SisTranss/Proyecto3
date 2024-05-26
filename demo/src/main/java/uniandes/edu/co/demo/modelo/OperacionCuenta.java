package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.ToString;

import java.util.Date;


@ToString
public class OperacionCuenta {

    private String tipo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_operacion;

    private int monto_pago;

    private int cuenta_destino;

    public OperacionCuenta( String tipo, Date fecha_operacion, int monto_pago, int cuenta_destino) {

        this.tipo = tipo;
        this.fecha_operacion = fecha_operacion;
        this.monto_pago = monto_pago;
        this.cuenta_destino = cuenta_destino;
    }

    public OperacionCuenta() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public int getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(int monto_pago) {
        this.monto_pago = monto_pago;
    }

    public int getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(int cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }




    
}
