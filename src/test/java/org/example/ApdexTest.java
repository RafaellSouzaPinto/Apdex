// Rafael Souza Pinto - RM 555130
package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApdexTest {

    private final Apdex apdex = new Apdex();

    // Excelente: 0,94 - 1,00
    @Test
    @DisplayName("Excelente - 0,94")
    public void excelente_094() {
        assertEquals(0.94, apdex.calcularApdex(94, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Excelente - 0,97")
    public void excelente_097() {
        assertEquals(0.97, apdex.calcularApdex(94, 6, 100), 0.001);
    }

    @Test
    @DisplayName("Excelente - 1,00")
    public void excelente_100() {
        assertEquals(1.00, apdex.calcularApdex(100, 0, 100), 0.001);
    }

    // Bom: 0,85 - 0,93
    @Test
    @DisplayName("Bom - 0,85")
    public void bom_085() {
        assertEquals(0.85, apdex.calcularApdex(85, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Bom - 0,90")
    public void bom_090() {
        assertEquals(0.90, apdex.calcularApdex(90, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Bom - 0,93")
    public void bom_093() {
        assertEquals(0.93, apdex.calcularApdex(86, 14, 100), 0.001);
    }

    // Razoável: 0,70 - 0,84
    @Test
    @DisplayName("Razoavel - 0,70")
    public void razoavel_070() {
        assertEquals(0.70, apdex.calcularApdex(70, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Razoavel - 0,75")
    public void razoavel_075() {
        assertEquals(0.75, apdex.calcularApdex(70, 10, 100), 0.001);
    }

    @Test
    @DisplayName("Razoavel - 0,84")
    public void razoavel_084() {
        assertEquals(0.84, apdex.calcularApdex(68, 32, 100), 0.001);
    }

    // Ruim: 0,50 - 0,69
    @Test
    @DisplayName("Ruim - 0,50")
    public void ruim_050() {
        assertEquals(0.50, apdex.calcularApdex(50, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Ruim - 0,60")
    public void ruim_060() {
        assertEquals(0.60, apdex.calcularApdex(50, 20, 100), 0.001);
    }

    @Test
    @DisplayName("Ruim - 0,69")
    public void ruim_069() {
        assertEquals(0.69, apdex.calcularApdex(64, 10, 100), 0.001);
    }

    // Inaceitável: 0,00 - 0,49
    @Test
    @DisplayName("Inaceitavel - 0,00")
    public void inaceitavel_000() {
        assertEquals(0.00, apdex.calcularApdex(0, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Inaceitavel - 0,25")
    public void inaceitavel_025() {
        assertEquals(0.25, apdex.calcularApdex(0, 50, 100), 0.001);
    }

    @Test
    @DisplayName("Inaceitavel - 0,49")
    public void inaceitavel_049() {
        assertEquals(0.49, apdex.calcularApdex(48, 2, 100), 0.001);
    }

    // Bordas
    @Test
    @DisplayName("Borda - denominador zero retorna 0,00")
    public void borda_denominador_zero() {
        assertEquals(0.00, apdex.calcularApdex(0, 0, 0), 0.001);
    }

    @Test
    @DisplayName("Borda - resultado acima de 1 clampa para 1,00")
    public void borda_acima_de_um() {
        assertEquals(1.00, apdex.calcularApdex(120, 0, 100), 0.001);
    }

    @Test
    @DisplayName("Borda - valores negativos clampa para 0,00")
    public void borda_valores_negativos() {
        assertEquals(0.00, apdex.calcularApdex(-10, -10, 100), 0.001);
    }
}


