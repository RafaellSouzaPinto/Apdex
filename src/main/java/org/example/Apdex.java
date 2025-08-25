// Rafael Souza Pinto - RM 555130
package org.example;

public final class Apdex {

    private Apdex() {
    }

    public enum Classification {
        EXCELLENT,
        GOOD,
        FAIR,
        POOR,
        UNACCEPTABLE
    }

    public static double computeApdexFromCounts(long satisfiedCount, long toleratingCount, long frustratedCount) {
        validateCounts(satisfiedCount, toleratingCount, frustratedCount);

        long totalSamples = satisfiedCount + toleratingCount + frustratedCount;
        if (totalSamples == 0) {
            throw new IllegalArgumentException("Total de amostras deve ser maior que zero");
        }

        double apdexScore = (satisfiedCount + 0.5d * toleratingCount) / (double) totalSamples;
        return clamp01(apdexScore);
    }

    public static double computeApdexFromSamples(double[] responseTimesMillis, double targetThresholdMillis) {
        if (responseTimesMillis == null) {
            throw new IllegalArgumentException("Array de tempos de resposta nulo");
        }
        if (targetThresholdMillis <= 0) {
            throw new IllegalArgumentException("O limiar T deve ser > 0");
        }
        long satisfied = 0L;
        long tolerating = 0L;
        long frustrated = 0L;

        double toleratedMax = 4.0d * targetThresholdMillis;
        for (double rt : responseTimesMillis) {
            if (rt <= targetThresholdMillis) {
                satisfied++;
            } else if (rt <= toleratedMax) {
                tolerating++;
            } else {
                frustrated++;
            }
        }
        return computeApdexFromCounts(satisfied, tolerating, frustrated);
    }

    public static Classification classify(double apdexScore) {
        if (Double.isNaN(apdexScore) || apdexScore < 0.0d || apdexScore > 1.0d) {
            throw new IllegalArgumentException("Apdex fora do intervalo [0,1]");
        }
        if (apdexScore >= 0.94d) {
            return Classification.EXCELLENT;
        }
        if (apdexScore >= 0.85d) {
            return Classification.GOOD;
        }
        if (apdexScore >= 0.70d) {
            return Classification.FAIR;
        }
        if (apdexScore >= 0.50d) {
            return Classification.POOR;
        }
        return Classification.UNACCEPTABLE;
    }

    public static Classification classifyFromSamples(double[] responseTimesMillis, double targetThresholdMillis) {
        double apdex = computeApdexFromSamples(responseTimesMillis, targetThresholdMillis);
        return classify(apdex);
    }

    private static void validateCounts(long satisfiedCount, long toleratingCount, long frustratedCount) {
        if (satisfiedCount < 0 || toleratingCount < 0 || frustratedCount < 0) {
            throw new IllegalArgumentException("Contagens n 3o podem ser negativas");
        }
    }

    private static double clamp01(double value) {
        if (value < 0.0d) {
            return 0.0d;
        }
        if (value > 1.0d) {
            return 1.0d;
        }
        return value;
    }
}
