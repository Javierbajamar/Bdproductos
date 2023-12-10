package aed.hibernate;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "stock")
public class Stock {

    //relacion Tienda
    @Id
    @GeneratedValue(generator = "myForeign")
    @GenericGenerator(name = "myForeign", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "Tienda"))
    @Column(name = "Codproducto")

    private int Codproducto;



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Codtienda", insertable = false, updatable = false)
    private Tienda tienda;

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }


    @Id
    private char Codtienda;
    private int Unidades;

    public char getCodtienda() {
        return Codtienda;
    }

    public void setCodtienda(char codtienda) {
        Codtienda = codtienda;
    }

    public int getCodproducto() {
        return Codproducto;
    }

    public void setCodproducto(int codproducto) {
        Codproducto = codproducto;
    }

    public int getUnidades() {
        return Unidades;
    }

    public void setUnidades(int unidades) {
        Unidades = unidades;
    }
}
