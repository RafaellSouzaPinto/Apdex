// Rafael Souza Pinto - RM 555130
package org.example;

public class Apdex {

    public double calcularApdex(int s, int to, int ta) {
        if (ta <= 0) return 0;

        double resultado = (s + (to / 2.0)) / ta;

        if (resultado < 0) return 0;
        if (resultado > 1) return 1;
        return resultado;
    }

    public boolean calcularApdexExcelente(int s, int to, int ta) {
        double score = calcularApdex(s, to, ta);
        return score >= 0.94 && score <= 1.00;
    }

    public boolean calcularApdexBom(int s, int to, int ta) {
        double score = calcularApdex(s, to, ta);
        return score >= 0.85 && score <= 0.93;
    }

    public boolean calcularApdexRazoavel(int s, int to, int ta) {
        double score = calcularApdex(s, to, ta);
        return score >= 0.70 && score <= 0.84;
    }

    public boolean calcularApdexRuim(int s, int to, int ta) {
        double score = calcularApdex(s, to, ta);
        return score >= 0.50 && score <= 0.69;
    }

    public boolean calcularApdexInaceitavel(int s, int to, int ta) {
        double score = calcularApdex(s, to, ta);
        return score >= 0.00 && score <= 0.49;
    }

    public String classificarApdex(int s, int to, int ta) {
        double score = calcularApdex(s, to, ta);
        if (score >= 0.94) return "Excelente";
        if (score >= 0.85) return "Bom";
        if (score >= 0.70) return "Razoavel";
        if (score >= 0.50) return "Ruim";
        return "Inaceitavel";
    }
}
