package aed.productos.dao;

import aed.hibernate.Familia;

import java.util.ArrayList;
import java.util.List;

public class FamiliaDAO {

    private static final List<Familia> familias;


    static {

        familias = new ArrayList<Familia>();

        Familia f1 = new Familia();
        f1.setCodfamilia(1);
        f1.setDenofamilia("Juguetes");
        familias.add(f1);

        Familia f2 = new Familia();
        f2.setCodfamilia(2);
        f2.setDenofamilia("Alimentacion");
        familias.add(f2);

        Familia f3 = new Familia();
        f3.setCodfamilia(3);
        f3.setDenofamilia("Petardos");
        familias.add(f3);

    }

    public static List<Familia> getFamilias() {
        return familias;
    }

    public static Familia findById(int codFamilia) {

        return familias.stream().filter(f -> f.getCodfamilia() == codFamilia).findFirst().get();


    }

}
