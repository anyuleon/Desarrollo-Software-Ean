
package centralpacientes;

import java.util.Scanner;

// Clase principal con menú interactivo
public class CentralPacientes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaPacientes lista = new ListaPacientes();
        int opcion;

        do {
            System.out.println("\n--- MENÚ CENTRAL DE PACIENTES ---");
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Buscar Paciente");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Mostrar Todos los Pacientes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del paciente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese nombre del paciente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese edad del paciente: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese clínica del paciente: ");
                    String clinica = scanner.nextLine();
                    lista.agregarPaciente(new Paciente(id, nombre, edad, clinica));
                    System.out.println("Paciente agregado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el ID del paciente a buscar: ");
                    id = scanner.nextInt();
                    Paciente paciente = lista.buscarPaciente(id);
                    if (paciente != null) {
                        System.out.println("Paciente encontrado: " + paciente);
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el ID del paciente a eliminar: ");
                    id = scanner.nextInt();
                    lista.eliminarPaciente(id);
                    System.out.println("Paciente eliminado si existía en la lista.");
                    break;
                case 4:
                    System.out.println("\nLista de Pacientes:");
                    lista.mostrarPacientes();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
