package uniandes.edu.co.demo.modelo;

import java.util.List;

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
    int numero_puntos_at;
    int gerente_oficina;
    List<Integer> puntos_atencion; 

    public Oficina(){}

    public Oficina(String nombre, String departamento, String ciudad, String direccion, int numero_puntos_at,
            int gerente_oficina) {
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


    public int getNumero_puntos_at() {
        return numero_puntos_at;
    }


    public void setNumero_puntos_at(int numero_puntos_at) {
        this.numero_puntos_at = numero_puntos_at;
    }


    public int getGerente_oficina() {
        return gerente_oficina;
    }


    public void setGerente_oficina(int gerente_oficina) {
        this.gerente_oficina = gerente_oficina;
    }

    public List<Integer> getPuntos_atencion() {
        return puntos_atencion;
    }

    public void setPuntos_atencion(List<Integer> puntos_atencion) {
        this.puntos_atencion = puntos_atencion;
    }

    
}
