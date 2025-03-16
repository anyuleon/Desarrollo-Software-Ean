
package evaluador;

public class VerificadorParentesis {
    public static boolean estanBalanceados(String expresion) {
        Pila<Character> pila = new Pila<>();
        for (char caracter : expresion.toCharArray()) {
            if (caracter == '(' || caracter == '[' || caracter == '{') {
                pila.push(caracter);
            } else if (caracter == ')' || caracter == ']' || caracter == '}') {
                if (pila.isEmpty()) return false;
                char top = pila.pop();
                if ((caracter == ')' && top != '(') ||
                    (caracter == ']' && top != '[') ||
                    (caracter == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return pila.isEmpty();
    }
}
