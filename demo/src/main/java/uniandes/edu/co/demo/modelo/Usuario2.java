package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "usuarios2")
@ToString

public class Usuario2 {
    @Id
    private int id;

    private String nombre;
    private String tipo_doc;
    @Indexed(unique = true)
    private int num_doc;
    private String email;
    private int telefono;
    private String nacionalidad;
    private String direccion;
    private String ciudad;
    private String departamento;
    private int tipo_usuario; // 1: empleado, 2:cliente
    private int tipo_empleado; //
    private List<Cuenta> cuentas;
    
    
    public Usuario2(String nombre, String tipo_doc, int num_doc, String email, int telefono, String nacionalidad,
            String direccion, String ciudad, String departamento, int tipo_usuario, int tipo_empleado, List<Cuenta> cuentas) {
        
        this.nombre = nombre;
        this.tipo_doc = tipo_doc;
        this.num_doc = num_doc;
        this.email = email;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.tipo_usuario = tipo_usuario;
        this.tipo_empleado = tipo_empleado;
        this.cuentas = cuentas;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public int getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(int num_doc) {
        this.num_doc = num_doc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public int getTipo_empleado() {
        return tipo_empleado;
    }

    public void setTipo_empleado(int tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }


    
    
    
}

