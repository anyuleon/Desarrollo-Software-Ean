import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    public List<Vehiculo> Vehiculos;

    public Parqueadero(){
        this.Vehiculos = new ArrayList<>();
    }

    public void registrarEntrada(Vehiculo vehiculo){
        vehiculo.setHoraEntrada(LocalDateTime.now());
        this.Vehiculos.add(vehiculo);
        System.out.println("Vehiculo con placas " + vehiculo.placa + " registrado exitosamente");
    }

    public double registrarSalida(String placa){
        for (Vehiculo vehiculo : this.Vehiculos) {
            if(vehiculo.getPlaca().equals(placa)){
                LocalDateTime horaSalida = LocalDateTime.now();
                Duration duracion = Duration.between(vehiculo.getHoraEntrada(), horaSalida);
                long horas = duracion.toHours();
                if (duracion.toMinutes() % 60 > 0) {
                    horas++; // Redondeo hacia arriba si hay fracción de hora
                }
                double costo = calcularCosto(vehiculo, horas);
                this.Vehiculos.remove(vehiculo);
                System.out.println("Vehículo retirado: " + placa);
                System.out.println("Tiempo de estadia: " + horas + " horas");
                System.out.println("Costo total: $" + costo);
                return costo;
            }
        }
        throw new IllegalArgumentException("Vehiculo no encontrado");
    }

    private double calcularCosto(Vehiculo vehiculo, long horas){
        if (vehiculo instanceof Automovil){
            return horas * 5;
        } else if (vehiculo instanceof Motocicleta) {
            return horas * 3;
        } else if (vehiculo instanceof Camion) {
            return horas * 10;
        }
        return 0;
    }

    public List<Vehiculo> consultarEstado(){
        return this.Vehiculos;
    }
}
