package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "usuarios")
@ToString
public class Usuario {

    @Id
    private String _id;

    public String nombre;
    public String tipo_doc;
    public int num_doc;
    public String email;
    public int telefono;
    public String nacionalidad;
    public String direccion;
    public String ciudad;
    public String departamento;
    public String tipo_usuario; // 1: empleado, 2:cliente
    public String tipo_empleado; // 
    
    
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
