package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;


import lombok.ToString;

import java.util.Date;


@ToString
public class Cuenta {
    @Id
    private int id;
    private Double saldo;

    private Date ultimaTransaccion;

    private String estado;

    private List<OperacionCuenta> operacionesCuentas;


    public Cuenta(int id, Double saldo, Date ultimaTransaccion, String estado, List<OperacionCuenta> operacionesCuentas) {
        this.id = id;
        this.saldo = saldo;
        this.ultimaTransaccion = ultimaTransaccion;
        this.estado = estado;
        this.operacionesCuentas = operacionesCuentas;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Double setSaldo(Double saldo) {
        return this.saldo = saldo;
    }

    public Date getUltimaTransaccion() {
        return ultimaTransaccion;
    }

    public void setUltimaTransaccion(Date ultimaTransaccion) {
        this.ultimaTransaccion = ultimaTransaccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<OperacionCuenta> getOperacionesCuentas() {
        return operacionesCuentas;
    }

    public void setOperacionesCuentas(List<OperacionCuenta> operacionesCuentas) {
        this.operacionesCuentas = operacionesCuentas;
    }


    

}
   