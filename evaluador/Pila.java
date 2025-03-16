
package evaluador;

import java.util.Stack;

// Implementación de la Pila
public class Pila<T> {
    private Stack<T> stack;
    
    public Pila() {
        this.stack = new Stack<>();
    }
    
    public void push(T elemento) {
        stack.push(elemento);
    }
    
    public T pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Error: la pila está vacía");
        }
        return stack.pop();
    }
    
    public T peek() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Error: la pila está vacía");
        }
        return stack.peek();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

