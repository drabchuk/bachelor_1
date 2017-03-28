package nn.data;

public class TrainingData {

    public double[][] x;
    public double[][] y;

    public TrainingData(double[][] x, double[][] y) {
        this.x = x;
        this.y = y;
    }

    public int length() {
        return y.length;
    }
}
