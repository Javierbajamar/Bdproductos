package aed.hibernate;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "producto")
public class Producto {

    //relacion producto_observacion
    @Id
    @GeneratedValue(generator = "myForeign")
    @GenericGenerator(name = "myForeign", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "producto_observacion"))
    @Column(name = "codproducto")
    private int codProducto;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "codproducto")
    private Producto_observacion producto_observacion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "codfamilia")
    private Familia familia;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "Codproducto", referencedColumnName = "codproducto"),
            @JoinColumn(name = "Codtienda", referencedColumnName = "codtienda")
    })
    private Stock stock;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    //relacion Tabla familia
    @Id
    @GeneratedValue(generator = "myForeign")
    @GenericGenerator(name = "myForeign", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "Familia"))
    @Column(name = "codfamilia")

    private int Codfamilia;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public Producto_observacion getProducto_observacion() {
        return producto_observacion;
    }

    public void setProducto_observacion(Producto_observacion producto_observacion) {
        this.producto_observacion = producto_observacion;
    }

    private String Denoproducto;
    private double PrecioBase;
    private boolean Congelado;

    public boolean isCongelado() {
        return Congelado;
    }

    public void setCongelado(boolean congelado) {
        Congelado = congelado;
    }


    public void setCodproducto(int codproducto) {
        codproducto = codproducto;
    }

    public String getDenoproducto() {
        return Denoproducto;
    }

    public void setDenoproducto(String denoproducto) {
        Denoproducto = denoproducto;
    }

    public double getPrecioBase() {
        return PrecioBase;
    }

    public void setPrecioBase(double precioBase) {
        PrecioBase = precioBase;
    }

    public int getCodfamilia() {
        return Codfamilia;
    }

    public void setCodfamilia(int codfamilia) {
        Codfamilia = codfamilia;
    }

    @Override
    public String toString() {
        return this.Denoproducto;
    }


}
