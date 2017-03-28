package nn.data;

/**
 * Created by Denis on 24.03.2017.
 */
public class MeanVarianceNormalizer {

    private double[] mean;
    private double[] variance;
    private double[][] raw;
    private double[][] normalized;

    public double[][] unNormalize(double[][] p) {
        int n = p.length;
        int features = p[0].length;
        double[][] res = new double[n][features];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < features; j++) {
                res[i][j] = (p[i][j] * variance[j]) + mean[j];
            }
        }
        return res;
    }

    public double[][] getNormalized() {
        return normalized;
    }

    public double[] getNormalized(double[] x) {
        double[] res = new double[x.length];
        for (int j = 0; j < x.length; j++) {
            res[j] = (x[j] - mean[j]) / variance[j];
        }
        return res;
    }

    public MeanVarianceNormalizer(double[][] x) {
        raw = x;
        int n = x.length;
        int features = x[0].length;
        double[] sum = new double[features];
        double[] sumSqr = new double[features];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < features; j++) {
                sum[j] += x[i][j];
                sumSqr[j] += Math.pow(x[i][j], 2);
            }
        }
        mean = new double[features];
        variance = new double[features];
        for (int j = 0; j < features; j++) {
            mean[j] = sum[j] / (double) n;
            variance[j] = Math.sqrt(sumSqr[j] / (double) n - Math.pow(mean[j], 2));
        }
        normalized = new double[n][features];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < features; j++) {
                normalized[i][j] = (raw[i][j] - mean[j]) / variance[j];
            }
        }
    }

}
