package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Rafael Souza Pinto - RM 555130
public class ApdexCategoryTest {

    private final Apdex apdex = new Apdex();

    // ===== Excelente =====
    @Test
    @DisplayName("Excelente - true em 0,94 e false em 0,93")
    public void excelente_limites() {
        assertTrue(apdex.calcularApdexExcelente(94, 0, 100)); // 0.94
        assertFalse(apdex.calcularApdexExcelente(93, 0, 100)); // 0.93
    }

    // ===== Bom =====
    @Test
    @DisplayName("Bom - true em 0,85 e 0,93; false fora do intervalo")
    public void bom_limites() {
        assertTrue(apdex.calcularApdexBom(85, 0, 100)); // 0.85
        assertTrue(apdex.calcularApdexBom(86, 14, 100)); // 0.93
        assertFalse(apdex.calcularApdexBom(94, 0, 100)); // 0.94 -> Excelente
        assertFalse(apdex.calcularApdexBom(84, 0, 100)); // 0.84 -> Razoavel
    }

    // ===== Razoável =====
    @Test
    @DisplayName("Razoavel - true em 0,70 e 0,84; false fora do intervalo")
    public void razoavel_limites() {
        assertTrue(apdex.calcularApdexRazoavel(70, 0, 100)); // 0.70
        assertTrue(apdex.calcularApdexRazoavel(68, 32, 100)); // 0.84
        assertFalse(apdex.calcularApdexRazoavel(85, 0, 100)); // 0.85 -> Bom
        assertFalse(apdex.calcularApdexRazoavel(69, 0, 100)); // 0.69 -> Ruim
    }

    // ===== Ruim =====
    @Test
    @DisplayName("Ruim - true em 0,50 e 0,69; false fora do intervalo")
    public void ruim_limites() {
        assertTrue(apdex.calcularApdexRuim(50, 0, 100)); // 0.50
        assertTrue(apdex.calcularApdexRuim(64, 10, 100)); // 0.69
        assertFalse(apdex.calcularApdexRuim(70, 0, 100)); // 0.70 -> Razoavel
        assertFalse(apdex.calcularApdexRuim(49, 0, 100)); // 0.49 -> Inaceitavel
    }

    // ===== Inaceitável =====
    @Test
    @DisplayName("Inaceitavel - true em 0,49; false em 0,50")
    public void inaceitavel_limites() {
        assertTrue(apdex.calcularApdexInaceitavel(49, 0, 100)); // 0.49
        assertFalse(apdex.calcularApdexInaceitavel(50, 0, 100)); // 0.50
    }

    // ===== Classificação textual =====
    @Test
    @DisplayName("Classificar - Excelente")
    public void classificar_excelente() {
        assertEquals("Excelente", apdex.classificarApdex(94, 6, 100)); // 0.97
    }

    @Test
    @DisplayName("Classificar - Bom")
    public void classificar_bom() {
        assertEquals("Bom", apdex.classificarApdex(90, 0, 100)); // 0.90
    }

    @Test
    @DisplayName("Classificar - Razoavel")
    public void classificar_razoavel() {
        assertEquals("Razoavel", apdex.classificarApdex(70, 10, 100)); // 0.75
    }

    @Test
    @DisplayName("Classificar - Ruim")
    public void classificar_ruim() {
        assertEquals("Ruim", apdex.classificarApdex(50, 20, 100)); // 0.60
    }

    @Test
    @DisplayName("Classificar - Inaceitavel")
    public void classificar_inaceitavel() {
        assertEquals("Inaceitavel", apdex.classificarApdex(0, 50, 100)); // 0.25
    }

    @Test
    @DisplayName("Classificar - borda denominador zero -> Inaceitavel")
    public void classificar_borda_denominador_zero() {
        assertEquals("Inaceitavel", apdex.classificarApdex(0, 0, 0)); // score clampa 0
    }

    @Test
    @DisplayName("Classificar - borda resultado > 1 -> Excelente")
    public void classificar_borda_acima_de_um() {
        assertEquals("Excelente", apdex.classificarApdex(120, 0, 100)); // score clampa 1
    }

    @Test
    @DisplayName("Classificar - borda valores negativos -> Inaceitavel")
    public void classificar_borda_valores_negativos() {
        assertEquals("Inaceitavel", apdex.classificarApdex(-10, -10, 100)); // score clampa 0
    }
}


