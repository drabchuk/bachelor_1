package nn.data;

import model.AnnualGDPelement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 24.03.2017.
 */
public class GdpRawData implements RawData {

    private List<AnnualGDPelement> gdpList;
    private List<RawExample> rawExamples;
    private MeanVarianceNormalizer meanVarianceNormalizer;
    private MeanVarianceNormalizer meanVarianceNormalizerY;

    public GdpRawData(List<AnnualGDPelement> gdpList) {
        this.gdpList = gdpList;
        rawExamples = new LinkedList<>();
        for (AnnualGDPelement e: gdpList) {
            rawExamples.add(new GdpExample(e.getYear(), e.getRealGDP()));
        }
    }

    @Override
    public TrainingData toTrainingData() {
        double[][] x;
        double[][] y;
        int xLen = rawExamples.get(0).toVector().getX().length;
        int yLen = rawExamples.get(0).toVector().getY().length;
        x = new double[gdpList.size()][xLen];
        y = new double[gdpList.size()][yLen];
        int i = 0;
        /*for (AnnualGDPelement e: gdpList) {
            x[i][0] = e.getYear();
            y[i][0] = e.getRealGDP();
            i++;
        }*/
        for (RawExample re: rawExamples) {
            VectorizedExample ve = re.toVector();
            x[i] = ve.getX();
            y[i] = ve.getY();
            i++;
        }

        meanVarianceNormalizer = new MeanVarianceNormalizer(x);
        meanVarianceNormalizerY = new MeanVarianceNormalizer(y);
        double[][] xNormalize = meanVarianceNormalizer.getNormalized();
        double[][] yNormalize = meanVarianceNormalizerY.getNormalized();
        return new TrainingData(addOnes(xNormalize), yNormalize);
    }

    @Override
    public TrainingData toTrainingData(List<RawExample> rawExamples) {
        double[][] x;
        double[][] y;
        int xLen = rawExamples.get(0).toVector().getX().length;
        int yLen = rawExamples.get(0).toVector().getY().length;
        x = new double[gdpList.size()][xLen];
        y = new double[gdpList.size()][yLen];
        int i = 0;
        for (RawExample re: rawExamples) {
            VectorizedExample ve = re.toVector();
            x[i] = ve.getX();
            y[i] = ve.getY();
        }
        double[][] xNormalize = meanVarianceNormalizer.getNormalized();
        double[][] yNormalize = meanVarianceNormalizerY.getNormalized();
        return new TrainingData(addOnes(xNormalize), yNormalize);
    }

    private static double[][] addOnes(double[][] x) {
        double[][] res = new double[x.length][x[0].length + 1];
        for (int i = 0; i < x.length; i++) {
            res[i][0] = 1.0;
            for (int j = 1; j <= x[0].length; j++) {
                res[i][j] = x[i][j - 1];
            }
        }
        return res;
    }

}
