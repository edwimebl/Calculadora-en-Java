//importaciones biblicotecas de gráficas y manejo de eventos

import java.awt.*;
import java.awt.event.*;

import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder; //modificar bordes en los componentes gráficos

public class Calculator {

    // crear la ventana principal para la calculadora y sus dimensiones
    int boardWidth = 360;
    int boardHeight = 540;

    /*
     * Colores opcionales
     * Color colorBackground = new Color(43, 43, 43);
     * Color colorForeground = new Color(250, 250, 250);
     * Color colorBlue = new Color(0, 153, 255);
     * Color colorGray = new Color(80, 80, 80);
     * Color colorDarkGray = new Color(60, 60, 60);
     */
    // Definir los colores a utilizar en la calculadora
    Color customDarkGray = new Color(80, 80, 80);
    Color customLightGray = new Color(212, 212, 210);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 140, 0);

    // matrices para los botones de la calculadora
    String[] buttonValues = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "/", "*", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    JFrame frame = new JFrame("Calculadora");
    // crear la etiqueta para mostrar los números y resultados
    JLabel displayLabel = new JLabel(); // etiqueta para mostrar los números y resultados
    JPanel displayPanel = new JPanel(); // panel para mostrar la etiqueta de resultados
    JPanel buttonsPanel = new JPanel(); // panel para los botones de la calculadora

    // Creando variables para almacenar los números: A+B, A-B, A*B, A/B

    String A = "0";
    String operator = null;
    String B = null;

    // creando el constructor de la clase Calculator

    Calculator() {
        //frame.setVisible(true); // hacer visible la ventana
        frame.setSize(boardWidth, boardHeight); // definir tamaño de la ventana
        frame.setLocationRelativeTo(null);// centrar la ventana en la pantalla
        frame.setResizable(false); // evitar que se pueda redimensionar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar la aplicación al cerrar la ventana
        frame.setLayout(new BorderLayout()); // poner los componentes en el frame con BorderLayout en planos cardinales
        // estilos para el display y el panel del display
        displayLabel.setBackground(customBlack); // definir color de fondo
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80)); // definir fuente
        displayLabel.setForeground(Color.white); // definir color de texto
        displayLabel.setBorder(new LineBorder(customDarkGray, 1)); // definir borde
        displayLabel.setOpaque(true); // hacer opaco el fondo
        displayLabel.setHorizontalAlignment(JLabel.RIGHT); // alinear el texto a la derecha
        displayLabel.setText("0"); // texto inicial del display
        displayLabel.setOpaque(true); // hacer opaco el fondo

        displayPanel.setBackground(customDarkGray); // definir color de fondo
        displayPanel.setLayout(new BorderLayout()); // definir layout
        displayPanel.add(displayLabel, BorderLayout.CENTER); // agregar etiqueta al panel

        frame.add(displayPanel, BorderLayout.NORTH); // agregar panel al frame

        buttonsPanel.setLayout(new GridLayout(5, 4)); // definir layout de botones en grid de 5 filas y 4 columnas con
                                                      // espacio entre botones
        buttonsPanel.setBackground(customBlack); // definir color de fondo del panel de botones
        frame.add(buttonsPanel); // agregar panel de botones al frame

        // crear los botones y agregarlos al panel de botones
        for (String value : buttonValues) {
            JButton button = new JButton(value); // crear botón con el valor correspondiente
            button.setFont(new Font("Arial", Font.PLAIN, 30)); // definir fuente del botón
            button.setFocusPainted(false); // quitar el borde de enfoque al hacer clic
            button.setBorder(new LineBorder(customBlack, 1)); // definir borde del botón

            // definir colores de los botones según su función
            if (Arrays.asList(rightSymbols).contains(value)) {
                button.setBackground(customOrange); // botones de operaciones a la derecha
                button.setForeground(Color.white);
            } else if (Arrays.asList(topSymbols).contains(value)) {
                button.setBackground(customLightGray); // botones superiores
                // button.setForeground(Color.white); //texto en blanco
                button.setBorder(new LineBorder(customDarkGray, 1));
            } else {
                button.setBackground(customLightGray); // botones numéricos y otros
                button.setForeground(Color.black); // fondo negro cuando no se selecciona
                button.setBorder(new LineBorder(customDarkGray, 1)); // borde gris oscuro

            }
            buttonsPanel.add(button); // agregar botón al panel de botones

            // funcionalidad del botón
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // aquí va la lógica para manejar el clic en el botón
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    // aquí va la lógica para manejar el clic en el botón
                    System.out.println("Botón presionado: " + buttonValue);// mostrando acción realizada en la terminal
                    // Aquí puedes agregar la lógica para actualizar el display o realizar cálculos
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        // Lógica para manejar los botones de operación a la derecha
                        if (buttonValue == "=") {
                            if (A != null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator == "+") {
                                    displayLabel.setText(removeZeroDecimal(numA+numB));                                    
                                } else if (operator == "-") {
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                    
                                }else if (operator == "*") {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                } else if (operator == "/") {
                                    displayLabel.setText(removeZeroDecimal(numA / numB));
                                } else if (operator == "√") {
                                    displayLabel.setText(removeZeroDecimal(numA ));                                     
                                }
                                clearAll();
                            }

                        } else if ("+-*/".contains(buttonValue)) {
                            if (operator == null) {
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                            
                        }
                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        // Lógica para manejar los botones de función
                        if (buttonValue == "AC") {

                            clearAll(); // Función creada
                            displayLabel.setText("0");

                        } else if (buttonValue == "+/-") {

                            double numberDisplay = Double.parseDouble(displayLabel.getText());
                            numberDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numberDisplay));// removeZeroDecimal: función que define si hay decimales y de no haberlos hacer que el número sea un entero .

                        } else if (buttonValue == "%") {

                            double numberDisplay = Double.parseDouble(displayLabel.getText());
                            numberDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numberDisplay));// removeZeroDecimal: función que
                                                                                   // define si hay decimales y de no
                                                                                   // haberlos hacer que el número sea
                                                                                   // un entero .
                        }
                    } else {
                        if (buttonValue == ".") {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            } 
                        } else if (buttonValue.equals("√")) {
                            String currentText = displayLabel.getText();

                            try {
                                double numberDisplay = Double.parseDouble(currentText);

                                if (numberDisplay >= 0) {
                                    // 1. Calcular la raíz cuadrada
                                    numberDisplay = Math.sqrt(numberDisplay);

                                    // 2. Mostrar el resultado
                                    displayLabel.setText(removeZeroDecimal(numberDisplay));

                                    // 3. Limpiar variables de operación binaria si es necesario
                                    // Aunque es unario, si está en medio de un cálculo, es mejor resetear.
                                    clearAll();

                                } else {
                                    // Manejo de error si es negativo
                                    displayLabel.setText("Error");
                                    clearAll();
                                }
                            } catch (NumberFormatException ex) {
                                displayLabel.setText("Error");
                                clearAll();
                            }
                        }
                        // Lógica para manejar los botones numéricos
                        else if ("0123456789".contains(buttonValue)) {
                            if (displayLabel.getText() == "0") {
                                displayLabel.setText(buttonValue);
                            } else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
            frame.setVisible(true); // hacer visible la ventana después de configurar los botones y las funciones          
                                        //y renderice oportunamente.
        }

    }

    // Función de borrado total
    void clearAll() {
        A = "0";
        operator = null;
        B = null;

    }

    String removeZeroDecimal(double numberDisplay) {
        if (numberDisplay % 1 == 0) {
            return Integer.toString((int) numberDisplay);
        } else {
            return Double.toString(numberDisplay);
        }

    }

}
