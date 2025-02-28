package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.CalculadoraImpuestos;
import model.Vehiculo;
import view.VistaImpuestos;

public class ControladorImpuestos {
    private VistaImpuestos vista;
    private CalculadoraImpuestos calculadora;

    public ControladorImpuestos(){
        this.vista = new VistaImpuestos();
        this.calculadora = new CalculadoraImpuestos();

        this.vista.agregarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                calcularImpuesto();
            }
        });

        vista.setVisible(true);
    }

    private void calcularImpuesto(){
        Vehiculo vehiculo = new Vehiculo(
                vista.getMarca(),
                vista.getModelo(),
                vista.getAño(),
                vista.getCilindraje(),
                vista.getTipoUso(),
                vista.getAvaluo()
        );

        double impuesto = calculadora.calcularImpuesto(vehiculo);
        vista.setResultado(impuesto);
    }
}
