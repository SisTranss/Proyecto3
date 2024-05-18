package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="oficinas")
@ToString
public class Oficina {

    @Id
    String id;
    
    String nombre;
    String departamento;
    String  ciudad;
    String direccion;
    String numero_puntos_at;
    String gerente_oficina;

    public Oficina(){}

    public Oficina(String nombre, String departamento, String ciudad, String direccion, String numero_puntos_at,
            String gerente_oficina) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero_puntos_at = numero_puntos_at;
        this.gerente_oficina = gerente_oficina;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDepartamento() {
        return departamento;
    }


    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    public String getCiudad() {
        return ciudad;
    }


    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getNumero_puntos_at() {
        return numero_puntos_at;
    }


    public void setNumero_puntos_at(String numero_puntos_at) {
        this.numero_puntos_at = numero_puntos_at;
    }


    public String getGerente_oficina() {
        return gerente_oficina;
    }


    public void setGerente_oficina(String gerente_oficina) {
        this.gerente_oficina = gerente_oficina;
    }

    
}
