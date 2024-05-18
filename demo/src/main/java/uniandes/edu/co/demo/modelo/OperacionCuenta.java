package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;


import lombok.ToString;

import java.util.Date;


@ToString
public class OperacionCuenta {

    @Id
    private Integer id;

    private String tipoOperacion;

    private Date fechaOperacion;

    private Float montoPago;

    private int cuentaDestino;

    public OperacionCuenta(Integer id, String tipoOperacion, Date fechaOperacion, Float montoPago, int cuentaDestino) {
        this.id = id;
        this.tipoOperacion = tipoOperacion;
        this.fechaOperacion = fechaOperacion;
        this.montoPago = montoPago;
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Float getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Float montoPago) {
        this.montoPago = montoPago;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }




    
}
