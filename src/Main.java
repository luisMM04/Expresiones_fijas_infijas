import java.util.Stack;

class Main {
    public static int caracteres(char caracter) {
        if (caracter == '*' || caracter == '/') {
            return 3;
        }
        if (caracter == '+' || caracter == '-') {
            return 4;
        }
        if (caracter == '&') {
            return 8;
        }
        if (caracter == '^') {
            return 9;
        }
        if (caracter == '|') {
            return 10;
        }
        return Integer.MAX_VALUE;
    }

    public static boolean EsOperador(char caracter) {
        return (caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z') || (caracter >= '0' && caracter <= '9');
    }

    public static String Infijo_a_Posfijo(String infijo) {
        if (infijo == null || infijo.length() == 0) {
            return infijo;
        }

        Stack<Character> pila = new Stack<>();
        String posfijo = "";

        for (char c : infijo.toCharArray()) {
            if (c == '(') {
                pila.add(c);
            } else if (c == ')') {
                while (pila.peek() != '(') {
                    posfijo += pila.pop();
                }
                pila.pop();
            } else if (EsOperador(c)) {
                posfijo += c;
            } else {
                while (!pila.isEmpty() && caracteres(c) >= caracteres(pila.peek())) {
                    posfijo += pila.pop();
                }
                pila.add(c);
            }
        }

        while (!pila.isEmpty()) {
            posfijo += pila.pop();
        }

        return posfijo;
    }

    public static void main(String[] args) {
        String infix = "A*(B*C+D*E)+F";

        String postfix = Infijo_a_Posfijo(infix);
        System.out.println(postfix);
    }
}
