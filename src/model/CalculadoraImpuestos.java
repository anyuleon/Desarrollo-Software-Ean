package model;

public class CalculadoraImpuestos {
    public double calcularImpuesto(Vehiculo vehiculo) {
        if (vehiculo.getTipoUso().equalsIgnoreCase("Público")) {
            return 0;
        }

        double avaluo = vehiculo.getAvaluoComercial();
        double tasa;

        if (avaluo <= 55679000) {
            tasa = 0.015;
        } else if (avaluo <= 125274000) {
            tasa = 0.025;
        } else {
            tasa = 0.035;
        }

        return avaluo * tasa;
    }
}
