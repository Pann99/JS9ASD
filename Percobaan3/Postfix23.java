package Percobaan3;
public class Postfix23 {
    int top;
    char[] stack;

    public Postfix23(int total) {
        top = -1;
        stack = new char[total];
        push('(');
    }

    public void push(char c) {
        if (top < stack.length - 1) {
            top++;
            stack[top] = c;
        } else {
            System.out.println("Stack overflow!");
        }
    }

    public char pop() {
        if (top >= 0) {
            char item = stack[top];
            top--;
            return item;
        } else {
            System.out.println("Stack underflow!");
            return '\0';
        }
    }

    public boolean isOperand(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public boolean isOperator(char c) {
        return c == '^' || c == '%' || c == '/' || c == '*' || c == '-' || c == '+';
    }

    public int derajat(char c) {
        switch (c) {
            case '^':
                return 4;
            case '%':
            case '/':
            case '*':
                return 3;
            case '-':
            case '+':
                return 2;
            default:
                return 1;
        }
    }

    public String konversi(String Q) {
        if (Q == null || Q.isEmpty())
            return "";

        String P = "";
        char c;
        for (int i = 0; i < Q.length(); i++) {
            c = Q.charAt(i);
            if (Character.isWhitespace(c)) {
                continue;
            }

            if (isOperand(c)) {
                P += c;
            } else if (c == '(') {
                push(c);
            } else if (c == ')') {
                while (top >= 0 && stack[top] != '(') {
                    P += pop();
                }
                pop();
            } else if (isOperator(c)) {
                while (top >= 0 && derajat(stack[top]) >= derajat(c)) {
                    P += pop();
                }
                push(c);
            }
        }
        while (top >= 0) {
            P += pop();
        }
        return P;
    }
}
