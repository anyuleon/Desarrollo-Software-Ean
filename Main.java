import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nParqueadero");
            System.out.println("1. Registrar entrada de vehiculo");
            System.out.println("2. Registrar salida de vehiculo");
            System.out.println("3. Consultar estado del parqueadero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            var selected = 0;
            try{
                int opcion = scanner.nextInt();
                selected = opcion;
            } catch (Exception ex){

            }

            scanner.nextLine();

            switch (selected) {
                case 1:
                    System.out.print("Ingrese tipo de vehículo (1: Automovil, 2: Motocicleta, 3: Camion): ");
                    try{
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese placa: ");
                        String placa = scanner.nextLine();
                        System.out.print("Ingrese marca: ");
                        String marca = scanner.nextLine();
                        System.out.print("Ingrese modelo: ");
                        String modelo = scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("Ingrese tipo de combustible: ");
                            String combustible = scanner.nextLine();
                            parqueadero.registrarEntrada(new Automovil(placa, marca, modelo, combustible));
                        } else if (tipo == 2) {
                            System.out.print("Ingrese cilindraje: ");
                            int cilindraje = scanner.nextInt();
                            parqueadero.registrarEntrada(new Motocicleta(placa, marca, modelo, cilindraje));
                        } else if (tipo == 3) {
                            System.out.print("Ingrese capacidad de carga (toneladas): ");
                            double capacidad = scanner.nextDouble();
                            parqueadero.registrarEntrada(new Camion(placa, marca, modelo, capacidad));
                        }
                    } catch(Exception ex) {
                        System.out.println("Tipo no válido");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese placa del vehiculo: ");
                    String placaSalida = scanner.nextLine();
                    try {
                        parqueadero.registrarSalida(placaSalida);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Vehiculos en el parqueadero:");
                    for (Vehiculo v : parqueadero.consultarEstado()) {
                        System.out.println(v.getPlaca());
                    }
                    break;

                case 4:
                    System.out.println("Vuelva pronto");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
                    break;

            }
        }
    }
}