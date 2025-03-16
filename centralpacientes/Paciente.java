
package centralpacientes;


// Clase Paciente - Representa la información de un paciente
public class Paciente {
    int id;
    String nombre;
    int edad;
    String clinica;

    public Paciente(int id, String nombre, int edad, String clinica) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.clinica = clinica;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad + ", Clínica: " + clinica;
    }
}