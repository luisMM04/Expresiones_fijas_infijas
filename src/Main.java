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

    public static String Infijo_a_Sufijo(String infijo) {
        if (infijo == null || infijo.length() == 0) {
            return infijo;
        }

        Stack<Character> pila = new Stack<>();
        String sufijo = "";

        for (char c : infijo.toCharArray()) {
            if (c == '(') {
                pila.add(c);
            } else if (c == ')') {
                while (pila.peek() != '(') {
                    sufijo += pila.pop();
                }
                pila.pop();
            } else if (EsOperador(c)) {
                sufijo += c;
            } else {
                while (!pila.isEmpty() && caracteres(c) >= caracteres(pila.peek())) {
                    sufijo += pila.pop();
                }
                pila.add(c);
            }
        }

        while (!pila.isEmpty()) {
            sufijo += pila.pop();
        }

        return sufijo;
    }

    public static void main(String[] args) {
        String infijo = "A*(B*C+D*E)+F";

        String sufijo = Infijo_a_Sufijo(infijo);
        System.out.println(sufijo);
    }
}
