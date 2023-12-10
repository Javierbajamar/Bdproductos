package aed.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos_observacion")
public class Producto_observacion {

    @Id
    private int Codproducto;
    private String Observacion;

    public int getCodproducto() {
        return Codproducto;
    }

    public void setCodproducto(int codproducto) {
        Codproducto = codproducto;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    @Override
    public String toString() {
        return this.Observacion;
    }
}
