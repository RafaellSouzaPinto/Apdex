// Rafael Souza Pinto - RM 555130
package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApdexTest {

    private static final int RM_SAMPLES = 555130;
    private static double[] responsesRmDataset;

    @BeforeAll
    static void prepareLargeDataset() {
        responsesRmDataset = new double[RM_SAMPLES];

        int satisfied = 449_617;
        int tolerating = 100_000;
        int frustrated = RM_SAMPLES - satisfied - tolerating;

        int index = 0;
        for (int i = 0; i < satisfied; i++) {
            responsesRmDataset[index++] = 50.0;
        }
        for (int i = 0; i < tolerating; i++) {
            responsesRmDataset[index++] = 200.0;
        }
        for (int i = 0; i < frustrated; i++) {
            responsesRmDataset[index++] = 1000.0;
        }

        assertEquals(RM_SAMPLES, responsesRmDataset.length);
    }

    @Test
    void sampleBased_apdexAndClassification_withRmDataset() {
        double T = 100.0;
        double apdex = Apdex.computeApdexFromSamples(responsesRmDataset, T);
        assertEquals(0.90, apdex, 1e-9);
        assertEquals(Apdex.Classification.GOOD, Apdex.classify(apdex));
        assertEquals(Apdex.Classification.GOOD, Apdex.classifyFromSamples(responsesRmDataset, T));
    }

    @Test
    void countsBased_classification_excellent() {
        double apdex = Apdex.computeApdexFromCounts(94, 0, 6); // 0.94
        assertEquals(0.94, apdex, 1e-9);
        assertEquals(Apdex.Classification.EXCELLENT, Apdex.classify(apdex));
    }

    @Test
    void countsBased_classification_good() {
        double apdex = Apdex.computeApdexFromCounts(85, 0, 15); // 0.85
        assertEquals(0.85, apdex, 1e-9);
        assertEquals(Apdex.Classification.GOOD, Apdex.classify(apdex));
    }

    @Test
    void countsBased_classification_fair() {
        double apdex = Apdex.computeApdexFromCounts(70, 0, 30); // 0.70
        assertEquals(0.70, apdex, 1e-9);
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(apdex));
    }

    @Test
    void countsBased_classification_poor() {
        double apdex = Apdex.computeApdexFromCounts(50, 0, 50); // 0.50
        assertEquals(0.50, apdex, 1e-9);
        assertEquals(Apdex.Classification.POOR, Apdex.classify(apdex));
    }

    @Test
    void countsBased_classification_unacceptable() {
        double apdex = Apdex.computeApdexFromCounts(0, 98, 2); // 0.49
        assertEquals(0.49, apdex, 1e-9);
        assertEquals(Apdex.Classification.UNACCEPTABLE, Apdex.classify(apdex));
    }

    @Test
    void sampleBased_boundary_includesEquals() {
        double T = 100.0;
        double[] data = new double[]{100.0, 100.0, 400.0, 401.0, 1000.0};
        double apdex = Apdex.computeApdexFromSamples(data, T);
        assertEquals(0.5, apdex, 1e-9);
        assertEquals(Apdex.Classification.POOR, Apdex.classify(apdex));
    }

    @Test
    void countsBased_throwsOnZeroTotal() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Apdex.computeApdexFromCounts(0, 0, 0));
        assertTrue(ex.getMessage().toLowerCase().contains("amostras"));
    }

    @Test
    void countsBased_throwsOnNegativeCounts() {
        assertThrows(IllegalArgumentException.class,
                () -> Apdex.computeApdexFromCounts(-1, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Apdex.computeApdexFromCounts(0, -1, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Apdex.computeApdexFromCounts(0, 0, -1));
    }

    @Test
    void classification_boundary_excellent() {
        assertEquals(Apdex.Classification.EXCELLENT, Apdex.classify(0.94));
        assertEquals(Apdex.Classification.EXCELLENT, Apdex.classify(1.0));
        assertEquals(Apdex.Classification.EXCELLENT, Apdex.classify(0.95));
    }

    @Test
    void classification_boundary_good() {
        assertEquals(Apdex.Classification.GOOD, Apdex.classify(0.85));
        assertEquals(Apdex.Classification.GOOD, Apdex.classify(0.93));
        assertEquals(Apdex.Classification.GOOD, Apdex.classify(0.90));
    }

    @Test
    void classification_boundary_fair() {
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(0.70));
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(0.84));
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(0.75));
    }

    @Test
    void classification_boundary_poor() {
        assertEquals(Apdex.Classification.POOR, Apdex.classify(0.50));
        assertEquals(Apdex.Classification.POOR, Apdex.classify(0.69));
        assertEquals(Apdex.Classification.POOR, Apdex.classify(0.60));
    }

    @Test
    void classification_boundary_unacceptable() {
        assertEquals(Apdex.Classification.UNACCEPTABLE, Apdex.classify(0.49));
        assertEquals(Apdex.Classification.UNACCEPTABLE, Apdex.classify(0.0));
        assertEquals(Apdex.Classification.UNACCEPTABLE, Apdex.classify(0.25));
    }

    @Test
    void classification_invalid_values() {
        assertThrows(IllegalArgumentException.class, () -> Apdex.classify(-0.1));
        assertThrows(IllegalArgumentException.class, () -> Apdex.classify(1.1));
        assertThrows(IllegalArgumentException.class, () -> Apdex.classify(Double.NaN));
    }

    @Test
    void sampleBased_edge_cases() {
        double[] emptyArray = new double[0];
        double[] singleElement = {50.0};

        assertThrows(IllegalArgumentException.class,
            () -> Apdex.computeApdexFromSamples(emptyArray, 100.0));
        
        double apdex = Apdex.computeApdexFromSamples(singleElement, 100.0);
        assertEquals(1.0, apdex, 1e-9);
        assertEquals(Apdex.Classification.EXCELLENT, Apdex.classify(apdex));
    }

    @Test
    void sampleBased_threshold_validation() {
        double[] data = {100.0, 200.0, 300.0};
        assertThrows(IllegalArgumentException.class,
            () -> Apdex.computeApdexFromSamples(data, 0.0));
        assertThrows(IllegalArgumentException.class, 
            () -> Apdex.computeApdexFromSamples(data, -1.0));
        assertThrows(IllegalArgumentException.class,
            () -> Apdex.computeApdexFromSamples(null, 100.0));
    }

    @Test
    void countsBased_mixed_scenarios() {
        double apdex1 = Apdex.computeApdexFromCounts(80, 15, 5); // (80 + 7.5)/100 = 0.875
        assertEquals(0.875, apdex1, 1e-9);
        assertEquals(Apdex.Classification.GOOD, Apdex.classify(apdex1));
        
        double apdex2 = Apdex.computeApdexFromCounts(60, 30, 10); // (60 + 15)/100 = 0.75
        assertEquals(0.75, apdex2, 1e-9);
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(apdex2));
    }

    @Test
    void apdex_formula_verification() {
        long S = 70, T = 20, F = 10;
        double expected = (S + 0.5 * T) / (double)(S + T + F);
        double actual = Apdex.computeApdexFromCounts(S, T, F);
        assertEquals(expected, actual, 1e-9);
        assertEquals(0.8, actual, 1e-9);
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(actual));
    }

    @Test
    void sampleBased_fair_classification_example() {
        double T = 100.0;
        double[] data = new double[]{50.0, 75.0, 90.0, 100.0, 500.0};
        double apdex = Apdex.computeApdexFromSamples(data, T);
        assertEquals(0.8, apdex, 1e-9);
        assertEquals(Apdex.Classification.FAIR, Apdex.classify(apdex));
    }
}


