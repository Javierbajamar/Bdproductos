package aed.hibernate;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tienda")
public class Tienda {


    @Id
    private char Codtienda;
    private String Denotienda;
    private char CodigoPostal;

    public char getCodtienda() {
        return Codtienda;
    }

    public void setCodtienda(char codtienda) {
        Codtienda = codtienda;
    }

    public String getDenotienda() {
        return Denotienda;
    }

    public void setDenotienda(String denotienda) {
        Denotienda = denotienda;
    }

    public char getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(char codigoPostal) {
        CodigoPostal = codigoPostal;
    }
}
