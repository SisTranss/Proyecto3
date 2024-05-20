package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;


import lombok.ToString;

import java.util.Date;


@ToString
public class Cuenta {
    private int numero_cuenta;
    private Double saldo;

    private Date ultima_transaccion;

    private String estado;

    private String tipo;

    private List<OperacionCuenta> operaciones_cuentas;

    private int num_doc_cliente;

    


    public Cuenta(int numero_cuenta, Double saldo, Date ultima_transaccion, String estado, String tipo, List<OperacionCuenta> operaciones_cuentas, int num_doc_cliente) {
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.ultima_transaccion = ultima_transaccion;
        this.estado = estado;
        this.operaciones_cuentas = operaciones_cuentas;
        this.tipo = tipo;
        this.num_doc_cliente = num_doc_cliente;
    }

    public Cuenta() {
    }

    public int getNum_doc_cliente() {
        return num_doc_cliente;
    }

    public void setNum_doc_cliente(int num_doc_cliente) {
        this.num_doc_cliente = num_doc_cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getNumero_cuenta() {
        return numero_cuenta;
    }
    public void setNumero_cuenta(int numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Double setSaldo(Double saldo) {
        return this.saldo = saldo;
    }

    public Date getUltima_transaccion() {
        return ultima_transaccion;
    }

    public void setUltima_transaccion(Date ultima_transaccion) {
        this.ultima_transaccion = ultima_transaccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<OperacionCuenta> getOperaciones_cuentas() {
        return operaciones_cuentas;
    }

    public void setOperaciones_cuentas(List<OperacionCuenta> operaciones_cuentas) {
        this.operaciones_cuentas = operaciones_cuentas;
    }


    

}
   