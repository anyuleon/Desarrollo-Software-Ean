import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Feria feria = new Feria(); // Creamos una instancia de la clase Feria
        Scanner scanner = new Scanner(System.in); // Scanner para leer la entrada del usuario
        int opcion;

        do {
            // Menú de opciones
            System.out.println("\n--- Menú de la Feria Empresarial ---");
            System.out.println("1. Registrar Empresa");
            System.out.println("2. Editar Empresa");
            System.out.println("3. Eliminar Empresa");
            System.out.println("4. Asignar Stand a Empresa");
            System.out.println("5. Registrar Visitante");
            System.out.println("6. Agregar Comentario a un Stand");
            System.out.println("7. Generar Reporte de Empresas");
            System.out.println("8. Generar Reporte de Visitantes");
            System.out.println("9. Generar Reporte de Calificaciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    // Registrar una empresa
                    System.out.print("Ingrese el nombre de la empresa: ");
                    String nombreEmpresa = scanner.nextLine();
                    System.out.print("Ingrese el sector de la empresa: ");
                    String sectorEmpresa = scanner.nextLine();
                    System.out.print("Ingrese el email de la empresa: ");
                    String emailEmpresa = scanner.nextLine();

                    Empresa empresa = new Empresa(nombreEmpresa, sectorEmpresa, emailEmpresa);
                    feria.registrarEmpresa(empresa);
                    System.out.println("Empresa registrada con éxito.");
                    break;

                case 2:
                    // Editar una empresa
                    System.out.print("Ingrese el nombre de la empresa a editar: ");
                    String nombreEditar = scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese el nuevo sector: ");
                    String nuevoSector = scanner.nextLine();
                    System.out.print("Ingrese el nuevo email: ");
                    String nuevoEmail = scanner.nextLine();

                    if (feria.editarEmpresa(nombreEditar, nuevoNombre, nuevoSector, nuevoEmail)) {
                        System.out.println("Empresa editada con éxito.");
                    } else {
                        System.out.println("Empresa no encontrada.");
                    }
                    break;

                case 3:
                    // Eliminar una empresa
                    System.out.print("Ingrese el nombre de la empresa a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    if (feria.eliminarEmpresa(nombreEliminar)) {
                        System.out.println("Empresa eliminada con éxito.");
                    } else {
                        System.out.println("Empresa no encontrada.");
                    }
                    break;

                case 4:
                    // Asignar un stand a una empresa
                    System.out.print("Ingrese el número del stand: ");
                    int numeroStand = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese la ubicación del stand: ");
                    String ubicacionStand = scanner.nextLine();
                    System.out.print("Ingrese el tamaño del stand (Pequeño, Mediano, Grande): ");
                    String tamañoStand = scanner.nextLine();

                    Stand stand = new Stand(numeroStand, ubicacionStand, tamañoStand);

                    System.out.print("Ingrese el nombre de la empresa a asignar: ");
                    String nombreEmpresaAsignar = scanner.nextLine();
                    Empresa empresaAsignar = null;

                    if (feria.getEmpresas() != null) {
                        for (Empresa emp : feria.getEmpresas()) {
                            if (emp.getNombre().equals(nombreEmpresaAsignar)) {
                                empresaAsignar = emp;
                                break;
                            }
                        }
                    } else {
                        System.out.println("No hay empresas registradas.");
                    }

                    if (empresaAsignar != null) {
                        feria.asignarStand(empresaAsignar, stand);
                        System.out.println("Stand asignado con éxito.");
                    } else {
                        System.out.println("Empresa no encontrada.");
                    }
                    break;

                case 5:
                    // Registrar un visitante
                    System.out.print("Ingrese el nombre del visitante: ");
                    String nombreVisitante = scanner.nextLine();
                    System.out.print("Ingrese el número de identificación del visitante: ");
                    String idVisitante = scanner.nextLine();
                    System.out.print("Ingrese el email del visitante: ");
                    String emailVisitante = scanner.nextLine();

                    Visitante visitante = new Visitante(nombreVisitante, idVisitante, emailVisitante);
                    feria.registrarVisitante(visitante);
                    System.out.println("Visitante registrado con éxito.");
                    break;

                case 6:
                    // Agregar un comentario a un stand
                    System.out.print("Ingrese el número del stand: ");
                    int numeroStandComentario = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    Stand standComentario = null;

                    if(feria.getStands() != null){
                        for (Stand st : feria.getStands()) {
                            if (st.getNumero() == numeroStandComentario) {
                                standComentario = st;
                                break;
                            }
                        }
                    } else {
                        System.out.println("No hay stands registrados");
                    }
                    

                    if (standComentario != null) {
                        System.out.print("Ingrese el nombre del visitante: ");
                        String nombreVisitanteComentario = scanner.nextLine();
                        Visitante visitanteComentario = null;

                        if(feria.getVisitantes() != null){
                            // Buscar el visitante por nombre
                            for (Visitante vis : feria.getVisitantes()) {
                                if (vis.getNombre().equals(nombreVisitanteComentario)) {
                                    visitanteComentario = vis;
                                    break;
                                }
                            }
                        } else {
                            System.out.println("No hay visitantes registrados");
                        }

                        if (visitanteComentario != null) {
                            System.out.print("Ingrese la calificación (1-5): ");
                            int calificacion = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer

                            Comentario comentario = new Comentario(visitanteComentario, standComentario, new Date(), calificacion);
                            feria.agregarComentario(comentario);
                            System.out.println("Comentario agregado con éxito.");
                        } else {
                            System.out.println("Visitante no encontrado.");
                        }
                    } else {
                        System.out.println("Stand no encontrado.");
                    }
                    break;

                case 7:
                    // Generar reporte de empresas
                    feria.generarReporteEmpresas();
                    break;

                case 8:
                    // Generar reporte de visitantes
                    feria.generarReporteVisitantes();
                    break;

                case 9:
                    // Generar reporte de calificaciones
                    feria.generarReporteCalificaciones();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);

        scanner.close(); // Cerrar el scanner
    }
}