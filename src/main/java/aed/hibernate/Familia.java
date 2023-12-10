package aed.hibernate;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "familia")
public class Familia {

    @Id
    private int Codfamilia;
    private String Denofamilia;

    public int getCodfamilia() {
        return Codfamilia;
    }

    public void setCodfamilia(int codfamilia) {
        Codfamilia = codfamilia;
    }

    public String getDenofamilia() {
        return Denofamilia;
    }

    public void setDenofamilia(String denofamilia) {
        Denofamilia = denofamilia;
    }

    @Override
    public String toString() {
        return this.Denofamilia;
    }
}
