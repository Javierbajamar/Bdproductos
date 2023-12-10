package aed.hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            while (true) {
                System.out.println("1. Insertar Producto");
                System.out.println("2. Actualizar Producto");
                System.out.println("3. Eliminar Producto");
                System.out.println("4. Visualizar todos los productos con observaciones");
                System.out.println("5. Visualizar todos los productos con stock, tiendas y familias");
                System.out.println("6. Salir");
                System.out.println("Ingrese una opción:");

                int opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        insertarNuevoProducto(session, transaction);
                        break;
                    case 2:
                        actualizarProducto(session);
                        break;
                    case 3:
                        eliminarProducto(session);
                        break;
                    case 4:
                        visualizarProductosConObservaciones(session);
                        break;
                    case 5:
                        visualizarProductosConStock(session);
                        break;
                    case 6:
                        transaction.commit();
                        return;
                    default:
                        System.out.println("Opción inválida");
                }
            }
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Error en la transacción: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
            session.close();
        }
    }

    private static void visualizarProductosConObservaciones(Session session) {
        try {
            Query<Producto> query = session.createQuery("FROM Producto p LEFT JOIN FETCH p.producto_observacion", Producto.class);
            List<Producto> productos = query.getResultList();

            System.out.println("Productos con observaciones:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar productos con observaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void visualizarProductosConStock(Session session) {
        try {
            Query<Producto> query = session.createQuery("FROM Producto p LEFT JOIN FETCH p.stock s LEFT JOIN FETCH s.tienda LEFT JOIN FETCH p.familia", Producto.class);
            List<Producto> productos = query.getResultList();

            System.out.println("Productos con stock, tiendas y familias:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        } catch (Exception e) {
            System.err.println("Error al visualizar productos con stock: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertarNuevoProducto(Session session, Transaction transaction) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el nombre del producto: ");
            String nombreProducto = scanner.nextLine();

            System.out.print("Ingrese el precio base del producto: ");
            double precioBase = scanner.nextDouble();

            System.out.print("¿Es congelado? (true/false): ");
            boolean congelado = scanner.nextBoolean();

            Producto producto = new Producto();
            producto.setDenoproducto(nombreProducto);
            producto.setPrecioBase(precioBase);
            producto.setCongelado(congelado);

            // Crear una nueva Familia y asignarla al Producto
            Familia nuevaFamilia = new Familia();
            nuevaFamilia.setDenofamilia("Nombre de la familia"); // Ajusta según tus necesidades
            producto.setFamilia(nuevaFamilia);

            System.out.print("¿Desea agregar observaciones? (true/false): ");
            boolean agregarObservaciones = scanner.nextBoolean();
            scanner.nextLine(); // Consumir el salto de línea

            if (agregarObservaciones) {
                System.out.print("Ingrese la observación del producto: ");
                String observacion = scanner.nextLine();

                Producto_observacion productoObservacion = new Producto_observacion();
                productoObservacion.setObservacion(observacion);

                producto.setProducto_observacion(productoObservacion);
            }

            session.save(producto);
            transaction.commit();

            System.out.println("Producto agregado con éxito.");
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Error al insertar un nuevo producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void actualizarProducto(Session session) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el ID del producto que desea actualizar: ");
            int idProducto = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            // Obtén el producto por su ID
            Producto producto = session.get(Producto.class, idProducto);

            if (producto != null) {
                System.out.println("Producto actual:");
                System.out.println(producto);

                System.out.print("Ingrese el nuevo nombre del producto: ");
                String nuevoNombre = scanner.nextLine();

                System.out.print("Ingrese el nuevo precio base del producto: ");
                double nuevoPrecio = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea

                System.out.print("¿Es congelado? (true/false): ");
                boolean nuevoCongelado = scanner.nextBoolean();
                scanner.nextLine(); // Consumir el salto de línea

                // Actualiza los atributos del producto
                producto.setDenoproducto(nuevoNombre);
                producto.setPrecioBase(nuevoPrecio);
                producto.setCongelado(nuevoCongelado);

                // Guarda los cambios en la base de datos
                session.update(producto);

                System.out.println("Producto actualizado con éxito.");
            } else {
                System.out.println("No se encontró un producto con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void eliminarProducto(Session session) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el ID del producto que desea eliminar: ");
            int idProducto = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            // Obtén el producto por su ID
            Producto producto = session.get(Producto.class, idProducto);

            if (producto != null) {
                System.out.println("Producto a eliminar:");
                System.out.println(producto);

                System.out.print("¿Está seguro de que desea eliminar este producto? (true/false): ");
                boolean confirmacion = scanner.nextBoolean();
                scanner.nextLine(); // Consumir el salto de línea

                if (confirmacion) {
                    // Elimina el producto de la base de datos
                    session.delete(producto);
                    System.out.println("Producto eliminado con éxito.");
                } else {
                    System.out.println("Operación de eliminación cancelada.");
                }
            } else {
                System.out.println("No se encontró un producto con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
