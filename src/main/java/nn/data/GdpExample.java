package nn.data;

/**
 * Created by Denis on 24.03.2017.
 */
public class GdpExample implements RawExample {

    private double year;
    private double value;

    public GdpExample(double year, double value) {
        this.year = year;
        this.value = value;
    }

    @Override
    public VectorizedExample toVector() {
        double[] x = new double[1];
        double[] y = new double[1];
        x[0] = this.year;
        y[0] = this.value;
        return new VectorizedExample(x, y);
    }

}
