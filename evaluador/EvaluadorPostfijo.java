
package evaluador;

// Clase para evaluar expresiones postfijas
public class EvaluadorPostfijo {
    public static int evaluarPostfija(String expresion) {
        Pila<Integer> pila = new Pila<>();
        for (char caracter : expresion.toCharArray()) {
            if (Character.isDigit(caracter)) {
                pila.push(caracter - '0');
            } else {
                int val2 = pila.pop();
                int val1 = pila.pop();
                switch (caracter) {
                    case '+': pila.push(val1 + val2); break;
                    case '-': pila.push(val1 - val2); break;
                    case '*': pila.push(val1 * val2); break;
                    case '/': pila.push(val1 / val2); break;
                }
            }
        }
        return pila.pop();
    }
}
