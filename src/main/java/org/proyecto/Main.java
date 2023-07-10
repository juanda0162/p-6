package org.proyecto;

public class Main {
    public static void main(String[] args) {
        ExpresionAritmetica expr1 = new ExpresionAritmetica("(+ 1 1)");
        System.out.println("Resultado: " + expr1.evaluar());

        ExpresionAritmetica expr2 = new ExpresionAritmetica("1");
        System.out.println("Resultado: " + expr2.evaluar());

        ExpresionAritmetica expr3 = new ExpresionAritmetica("(- (+ 10 5) 10)");
        System.out.println("Resultado: " + expr3.evaluar());

        ExpresionAritmetica expr4 = new ExpresionAritmetica("(+ 10 5 (- 3 2) (* 1 10))");
        System.out.println("Resultado: " + expr4.evaluar());
    }
}