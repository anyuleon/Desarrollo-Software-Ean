
package centralpacientes;


// Clase ListaPacientes - Implementa la lista enlazada
public class ListaPacientes {
    private Nodo cabeza;

    // Agregar un paciente a la lista
    public void agregarPaciente(Paciente paciente) {
        Nodo nuevoNodo = new Nodo(paciente);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    // Buscar un paciente por su ID
    public Paciente buscarPaciente(int id) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.paciente.id == id) {
                return actual.paciente;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // Eliminar un paciente por su ID
    public void eliminarPaciente(int id) {
        if (cabeza == null) return;
        
        if (cabeza.paciente.id == id) {
            cabeza = cabeza.siguiente;
            return;
        }
        
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.paciente.id == id) {
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }

    // Mostrar todos los pacientes
    public void mostrarPacientes() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.paciente);
            actual = actual.siguiente;
        }
    }
}