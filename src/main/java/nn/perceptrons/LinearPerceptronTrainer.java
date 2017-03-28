package nn.perceptrons;

import nn.data.TrainingData;

import static nn.algebra.Alg.*;

public class LinearPerceptronTrainer implements Trainer {
    LinearOneLayerPerceptron nn;
    TrainingData td;

    public LinearPerceptronTrainer(NeuralNetwork network, TrainingData trainingData) {
        this.nn = (LinearOneLayerPerceptron) network;
        this.td = trainingData;
    }

    @Override
    public void train() {
        final double ALPHA = -0.02;
        double[][] grad;
        double[][] nextTheta;
        System.out.println("1: " + this.cost());
        double prevCost = 100;
        for (int i = 0; i < 1000; i++) {
            grad = this.grad();
            double[][] theta = nn.getTheta();
            nextTheta = sum(theta, dotMult(ALPHA, grad));
            nn.setTheta(nextTheta);
            double cost = this.cost();
            if (cost > prevCost) {
                System.out.println("UP");
            }
            System.out.println(i + 2);
            System.out.println(cost);
            prevCost = cost;
        }
    }

    public double[][] grad() {
        double[][] x = td.x;
        double[][] y = td.y;
        double[][] h = nn.predict(x);
        double[][] res = new double[nn.outputLayerSize][nn.outputLayerSize];

        //double[][] first = dotMult(y, sub(1.0, h));
        //double[][] second = dotMult(sub(1.0, y), h);
        //double[][] third = sum (first, second);
        //double[][] fourth = mult(x, "transpose", third);
        return dotDiv(
                mult(x, "transpose",
                        sub (
                                h
                                , y
                        )
                )
                , (td.length() / 2.0)
        );

    }

    public double cost() {
        double[][] x = td.x;
        double[][] y = td.y;
        double[][] h = nn.predict(x);
        double sum = 0.0;
        for (int i = 0; i < y.length; i++) {
            sum += Math.pow(y[i][0] - h[i][0], 2);
        }
        return ( sum
                ) / (double) td.length();
    }

}
