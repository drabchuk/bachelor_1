package nn.data;

/**
 * Created by Denis on 24.03.2017.
 */
public class VectorizedExample {

    private double[] x;
    private double[] y;

    public VectorizedExample(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }
}
