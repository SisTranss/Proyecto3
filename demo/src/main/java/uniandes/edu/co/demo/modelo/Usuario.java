package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "usuarios")
@ToString
public class Usuario {

    @Id
    private String id;

    private String nombre;
    private String tipo_doc;
    private int num_doc;
    private String email;
    private int telefono;
    private String nacionalidad;
    private String direccion;
    private String ciudad;
    private String departamento;
    private String tipo_usuario; // 1: empleado, 2:cliente
    private String tipo_empleado; // 
    
    
    public Usuario(String nombre, String tipo_doc, int num_doc, String email, int telefono, String nacionalidad,
            String direccion, String ciudad, String departamento, String tipo_usuario, String tipo_empleado) {
        
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
    }
    
    
    
}
