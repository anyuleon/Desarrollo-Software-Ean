
package evaluador;

// Clase para convertir expresiones de notación infija a postfija
public class ConvertidorPostfijo {
    public static String convertirApostfijo(String expresion) {
        Pila<Character> pila = new Pila<>();
        StringBuilder resultado = new StringBuilder();
        
        for (char caracter : expresion.toCharArray()) {
            if (Character.isDigit(caracter)) {
                resultado.append(caracter);
            } else if (caracter == '(') {
                pila.push(caracter);
            } else if (caracter == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop();
            } else {
                while (!pila.isEmpty() && precedencia(caracter) <= precedencia(pila.peek())) {
                    resultado.append(pila.pop());
                }
                pila.push(caracter);
            }
        }
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }
        return resultado.toString();
    }
    
    private static int precedencia(char operador) {
        switch (operador) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return -1;
        }
    }
}
