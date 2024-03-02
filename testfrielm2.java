package graficos;

import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class testfrielm2 extends JFrame {

    private JTextField nombreField, edadField, pesoField, alturaField, imcField;

    public testfrielm2() {
  
        setTitle("Captura de Información de Persona");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(30);

        JLabel edadLabel = new JLabel("Edad:");
        edadField = new JTextField(10);

        JLabel pesoLabel = new JLabel("Peso (kg):");
        pesoField = new JTextField(10);

        JLabel alturaLabel = new JLabel("Altura (m):");
        alturaField = new JTextField(10);

        JLabel imcLabel = new JLabel("IMC:");
        imcField = new JTextField(30);
        imcField.setEditable(false);

        JButton calcularButton = new JButton("Calcular IMC");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        JButton guardarButton = new JButton("Guardar en Archivo");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEnArchivo();
            }
        });
        
        setLayout(new FlowLayout());
        add(nombreLabel);
        add(nombreField);
        add(edadLabel);
        add(edadField);
        add(pesoLabel);
        add(pesoField);
        add(alturaLabel);
        add(alturaField);
        add(imcLabel);
        add(imcField);
        add(calcularButton);
        add(guardarButton);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(pesoField.getText());
            double altura = Double.parseDouble(alturaField.getText());
            double imc = peso / (altura * altura);
            imcField.setText(String.format("%.2f", imc));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para peso y altura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("informacion_persona.txt"))) {
            writer.write("Nombre: " + nombreField.getText() + "\n");
            writer.write("Edad: " + edadField.getText() + "\n");
            writer.write("Peso: " + pesoField.getText() + " kg\n");
            writer.write("Altura: " + alturaField.getText() + " m\n");
            writer.write("IMC: " + imcField.getText() + "\n");

            JOptionPane.showMessageDialog(this, "Información guardada en el archivo.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
     
}
