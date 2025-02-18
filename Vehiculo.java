import java.time.LocalDateTime;

public class Vehiculo {
    public String placa;
    public String marca;
    public String modelo;
    public LocalDateTime horaEntrada;

    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    
}