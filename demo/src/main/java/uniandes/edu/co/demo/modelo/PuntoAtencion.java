package uniandes.edu.co.demo.modelo;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="puntos_atencion")
@ToString
public class PuntoAtencion {

    @Id
    String _id;

    int id;
    String tipoPunto;
    List<Integer> operaciones;

    public PuntoAtencion(){}
    
    public PuntoAtencion(int id, String tipoPunto) {
        this.id = id;
        this.tipoPunto = tipoPunto;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_punto() {
        return tipoPunto;
    }

    public void setTipo_punto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public List<Integer> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<Integer> operaciones) {
        this.operaciones = operaciones;
    }

    
    
}
