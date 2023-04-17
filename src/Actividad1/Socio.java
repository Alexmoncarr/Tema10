package Actividad1;

import java.io.Serializable;
import java.util.Date;

public class Socio implements Comparable<Socio>, Serializable {
    private String dni;
    private String nombre;
    private Date fechaAlta;

    public Socio(String dni, String nombre, Date fechaAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(Object o) {
        if (o instanceof Socio) {
            Socio s = (Socio) o;
            return dni.equals(s.getDni());
        }
        return false;
    }

    public int compareTo(Socio s) {
        return dni.compareTo(s.getDni());
    }

    public int antiguedad() {
        long tiempoActual = new Date().getTime();
        long tiempoAlta = fechaAlta.getTime();
        long diff = tiempoActual - tiempoAlta;
        return (int) (diff / (1000L * 60L * 60L * 24L * 365L));
    }

    public String toString() {
        return dni + ", " + nombre + ", " + fechaAlta;
    }
}
