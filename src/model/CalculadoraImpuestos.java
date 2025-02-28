package model;

public class CalculadoraImpuestos {
    private static final double TARIFA_PARTICULAR = 0.02; // 2%
    private static final double TARIFA_PUBLICO = 0.04; // 4%

    public double calcularImpuesto(Vehiculo vehiculo){
        double tarifa = vehiculo.getTipoUso().equalsIgnoreCase("Particular") ? TARIFA_PARTICULAR : TARIFA_PUBLICO;
        // double tarifa = 0.0;
        // if(vehiculo.getTipoUso() == "Particular"){
        //     tarifa = TARIFA_PARTICULAR;
        // } else {
        //     tarifa = TARIFA_PUBLICO;
        // }
        return vehiculo.getAvaluoComercial() * tarifa;
    }
}
