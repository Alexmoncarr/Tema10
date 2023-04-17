package Actividad1;

import java.io.*;
import java.util.*;

public class ClubSocios {
    private ArrayList<Socio> socios;

    public ClubSocios() {
        socios = new ArrayList<Socio>();
        leerDatos();
    }

    public void alta(Socio s) {
        if (!socios.contains(s)) {
            socios.add(s);
            System.out.println("Alta realizada.");
        } else {
            System.out.println("El socio ya existe.");
        }
    }

    public void baja(Socio s) {
        if (socios.contains(s)) {
            socios.remove(s);
            System.out.println("Baja realizada.");
        } else {
            System.out.println("El socio no existe.");
        }
    }

    public void modificacion(Socio s) {
        if (socios.contains(s)) {
            int index = socios.indexOf(s);
            socios.set(index, s);
            System.out.println("Modificación realizada.");
        } else {
            System.out.println("El socio no existe.");
        }
    }

    public void listadoDNI() {
        Collections.sort(socios);
        for (Socio s : socios) {
            System.out.println(s);
        }
    }

    public void listadoAntiguedad() {
        Collections.sort(socios, new Comparator<Socio>() {
            public int compare(Socio s1, Socio s2) {
                return s1.antiguedad() - s2.antiguedad();
            }
        });
        for (Socio s : socios) {
            System.out.println(s);
        }
    }

    public void salir() {
        guardarDatos();
        System.out.println("Hasta luego.");
        System.exit(0);
    }

    private void leerDatos() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("socios.dat"));
            socios = (ArrayList<Socio>) in.readObject();
            in.close();
            System.out.println("Datos leídos.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer los datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer los datos.");
        }
    }

    private void guardarDatos() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("socios.dat"));
            out.writeObject(socios);
            out.close();
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos.");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ClubSocios club = new ClubSocios();
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Alta");
            System.out.println("2. Baja");
            System.out.println("3. Modificación");
            System.out.println("4. Listado por DNI");
            System.out.println("5. Listado por antigüedad");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("DNI: ");
                    String dni = input.next();
                    System.out.print("Nombre: ");
                    String nombre = input.next();
                    Date fechaAlta = new Date();
                    Socio s = new Socio(dni, nombre, fechaAlta);
                    club.alta(s);
                    break;
                case 2:
                    System.out.print("DNI: ");
                    dni = input.next();
                    s = new Socio(dni, "", null);
                    club.baja(s);
                    break;
                case 3:
                    System.out.print("DNI: ");
                    dni = input.next();
                    System.out.print("Nombre: ");
                    nombre = input.next();
                    fechaAlta = null;
                    s = new Socio(dni, nombre, fechaAlta);
                    club.modificacion(s);
                    break;
                case 4:
                    club.listadoDNI();
                    break;
                case 5:
                    club.listadoAntiguedad();
                    break;
                case 6:
                    club.salir();
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }}

