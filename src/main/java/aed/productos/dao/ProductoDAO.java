package aed.productos.dao;

import aed.hibernate.Familia;
import aed.hibernate.Producto;
import aed.hibernate.Producto_observacion;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static List<Producto> productos;

     static  {

        productos = new ArrayList<Producto>();

        Producto p1 = new Producto();
        p1.setCodproducto(1);
        p1.setDenoproducto("Producto 1");
        p1.setPrecioBase(100);
        p1.setCongelado(true);
        p1.setFamilia(new Familia());
        p1.setProducto_observacion(new Producto_observacion());

        p1.getFamilia().setCodfamilia(1);
        p1.getFamilia().setDenofamilia("Familia 1");
        p1.getProducto_observacion().setCodproducto(1);
        p1.getProducto_observacion().setObservacion("Observacion 1");
        productos.add(p1);


        Producto p2 = new Producto();
        p2.setCodproducto(2);
        p2.setDenoproducto("Macbook Pro");
        p2.setPrecioBase(2000);
        p2.setCongelado(false);
        p2.setFamilia(new Familia());
        p2.setProducto_observacion(new Producto_observacion());

        p2.getFamilia().setCodfamilia(2);
        p2.getFamilia().setDenofamilia("Familia 1");
        p2.getProducto_observacion().setCodproducto(1);
        p2.getProducto_observacion().setObservacion("Buen producto ");
         productos.add(p2);


        Producto p3 = new Producto();
        p3.setCodproducto(3);
        p3.setDenoproducto("Cristiano Ronaldo");
        p3.setPrecioBase(1000);
        p3.setCongelado(false);
        p3.setFamilia(new Familia());
        p3.setProducto_observacion(new Producto_observacion());

        p3.getFamilia().setCodfamilia(3);
        p3.getFamilia().setDenofamilia(" Familia Ronaldo");
        p3.getProducto_observacion().setCodproducto(1);
        p3.getProducto_observacion().setObservacion(" malo");
         productos.add(p3);



         List<Producto> productos = new ArrayList<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);


    }

    public static List<Producto> getProductos() {
        return productos;
    }


   public static void addProducto(Producto producto) {
      System.out.println("agregando producto" + producto);
       productos.add(producto);

   }
}
