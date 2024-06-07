import java.util.List;
import java.lang.Math;

public class Util {
    public static double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    public static double sigmoidDeriv(double in) {
        double sigmoid = Util.sigmoid(in);
        return sigmoid * (1 - in);
    }

    /** Assumes array args are same length */
    public static Double meanSquareLoss(List<Double> correctAnswers, List<Double> predictedAnswers) {
        double sumSquare = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            double error = correctAnswers.get(i) - predictedAnswers.get(i);
            sumSquare += (error * error);
        }
        return sumSquare / (correctAnswers.size());
    }
}