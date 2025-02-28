package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaImpuestos extends JFrame{
    private JTextField txtMarca, txtModelo, txtAño, txtCilindraje, txtAvaluo;
    private JComboBox<String> cbTipoUso;
    private JButton btnCalcular;
    private JLabel lblResultado;

    public VistaImpuestos(){
        setTitle("Calcula tu impuesto");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Marca"), gbc);
        gbc.gridx = 1;
        txtMarca = new JTextField(15);
        add(txtMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        txtModelo = new JTextField(15);
        add(txtModelo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Año de Fabricación:"), gbc);
        gbc.gridx = 1;
        txtAño = new JTextField(15);
        add(txtAño, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cilindraje:"), gbc);
        gbc.gridx = 1;
        txtCilindraje = new JTextField(15);
        add(txtCilindraje, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Avalúo Comercial:"), gbc);
        gbc.gridx = 1;
        txtAvaluo = new JTextField(15);
        add(txtAvaluo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Tipo de Uso:"), gbc);
        gbc.gridx = 1;
        cbTipoUso = new JComboBox<>(new String[]{"Particular","Público"});
        add(cbTipoUso, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        btnCalcular = new JButton("Calcular Impuesto");
        add(btnCalcular, gbc);

        gbc.gridy++;
        lblResultado = new JLabel("Resultado: ");
        add(lblResultado, gbc);

        pack();
    }

    public String getMarca() { return txtMarca.getText(); }
    public String getModelo() { return txtModelo.getText(); }
    public int getAño() { return Integer.parseInt(txtAño.getText()); }
    public int getCilindraje() { return Integer.parseInt(txtCilindraje.getText()); }
    public double getAvaluo() { return Double.parseDouble(txtAvaluo.getText()); }
    public String getTipoUso() { return (String) cbTipoUso.getSelectedItem(); }

    public void setResultado(double resultado) {
        lblResultado.setText("Resultado: " + resultado);
    }

    public void agregarListener(ActionListener listener) {
        btnCalcular.addActionListener(listener);
    }
}
