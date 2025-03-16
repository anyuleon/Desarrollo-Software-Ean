
package evaluador;

import java.util.Scanner;

// Clase principal para interactuar con el usuario
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expresion;
        
        do {
            System.out.print("Ingrese una expresión aritmética en notación infija: ");
            expresion = scanner.nextLine();
            if (!VerificadorParentesis.estanBalanceados(expresion)) {
                System.out.println("Error: Los paréntesis no están balanceados. Intente nuevamente.");
            }
        } while (!VerificadorParentesis.estanBalanceados(expresion));
        
        String postfija = ConvertidorPostfijo.convertirApostfijo(expresion);
        System.out.println("Expresión en notación postfija: " + postfija);
        
        int resultado = EvaluadorPostfijo.evaluarPostfija(postfija);
        System.out.println("Resultado de la expresión: " + resultado);
    }
}