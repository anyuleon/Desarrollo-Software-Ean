package model;

public class Vehiculo {
    public String marca;
    public String modelo;
    public int añoFabricacion;
    public int cilindraje;
    public double avaluoComercial;
    public String tipoUso;
    
    public Vehiculo(String marca, String modelo, int añoFabricacion, int cilindraje, String tipoUso, double avaluoComercial) {
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.cilindraje = cilindraje;
        this.tipoUso = tipoUso;
        this.avaluoComercial = avaluoComercial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(int añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public double getAvaluoComercial() {
        return avaluoComercial;
    }

    public void setAvaluoComercial(double avaluoComercial) {
        this.avaluoComercial = avaluoComercial;
    }
}
