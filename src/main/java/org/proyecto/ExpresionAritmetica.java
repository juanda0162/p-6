package org.proyecto;
import java.util.ArrayList;


public class ExpresionAritmetica extends Arbol<ObjetoAritmetico> {
    public ExpresionAritmetica() {
        raiz = null;
    }

    public ExpresionAritmetica(String expresion) {
        raiz = leerExpresion(expresion);
    }

    private Nodo<ObjetoAritmetico> leerExpresion(String expresion) {
        String expresionSinEspacios = expresion.trim();
        if (expresionSinEspacios.charAt(0) != '(') {
            return new Nodo<>(new Numero(Double.parseDouble(expresionSinEspacios)));
        }

        char operadorPrincipal = expresionSinEspacios.charAt(1);
        String operandos = expresionSinEspacios.substring(3, expresionSinEspacios.length() - 1);

        String[] operandosSeparados = separarOperandos(operandos);
        Nodo<ObjetoAritmetico> nodo = new Nodo<>(new Operador(operadorPrincipal));

        for (String operando : operandosSeparados) {
            Nodo<ObjetoAritmetico> nodoHijo = leerExpresion(operando);
            nodo.agregarHijo(nodoHijo);
        }
        return nodo;
    }

    private String[] separarOperandos(String operandos) {
        ArrayList<String> operandosSeparados = new ArrayList<>();
        int conteoParentesis = 0;
        StringBuilder sb = new StringBuilder();

        for (char c : operandos.toCharArray()) {
            if (c == '(') {
                conteoParentesis++;
            } else if (c == ')') {
                conteoParentesis--;
            }

            if (c == ' ' && conteoParentesis == 0) {
                operandosSeparados.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            operandosSeparados.add(sb.toString().trim());
        }

        return operandosSeparados.toArray(new String[0]);
    }
//    private boolean esOperador(char c) {
//        return c == '+' || c == '-' || c == '*' || c == '/';
//    }

    @Override
    public String toString() {
        return super.toString() + " = " + evaluar();
    }

    public double evaluar() {
        return evaluarExpresion(raiz);
    }

    private double evaluarExpresion(Nodo<ObjetoAritmetico> nodo) {
        if (nodo.getContenido() instanceof Numero) {
            return ((Numero) nodo.getContenido()).getValor();
        }

        Operacion operacion = ((Operador) nodo.getContenido()).getOperacion();

        double resultado = evaluarExpresion(nodo.getHijos().get(0));

        for (int i = 1; i < nodo.getHijos().size(); i++) {
            double valorHijo = evaluarExpresion(nodo.getHijos().get(i));

            switch (operacion) {
                case Suma:
                    resultado += valorHijo;
                    break;
                case Resta:
                    resultado -= valorHijo;
                    break;
                case Multiplicacion:
                    resultado *= valorHijo;
                    break;
                case Division:
                    resultado /= valorHijo;
                    break;
                default:
                    throw new IllegalArgumentException("Operación desconocida: " + operacion);
            }
        }

        return resultado;
    }
}
