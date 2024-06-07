import java.util.Random;

class Neuron {
    Random random = new Random();
    private Double oldBias = random.nextDouble(-1, 1), bias = random.nextDouble(-1, 1);
    public Double oldWeight1 = random.nextDouble(-1, 1), weight1 = random.nextDouble(-1, 1);
    private Double oldWeight2 = random.nextDouble(-1, 1), weight2 = random.nextDouble(-1, 1);
    private double learningRate;

    public static void main(String[] args) {
        Neuron neuron = new Neuron();
        double output = neuron.compute(0.1, 0.7);
        System.out.println("Output: " + output);

        // Learn space
        neuron.setLearningRate(0.1);
        neuron.learn(0.1, 0.7, 1);

        output = neuron.compute(4, 0.3);
        System.out.println("Output after learning: " + output);
    }

    public String toString() {
        return String.format(
                "oldBias: %.15f | bias: %.15f | oldWeight1: %.15f | weight1: %.15f | oldWeight2: %.15f | weight2: %.15f",
                this.oldBias, this.bias, this.oldWeight1, this.weight1, this.oldWeight2, this.weight2);
    }

    public void mutate(Double learnFactor) {
        int propertyToChange = random.nextInt(0, 3);
        Double changeFactor = (learnFactor == null) ? random.nextDouble(-1, 1)
                : (learnFactor * random.nextDouble(-1, 1));
        if (propertyToChange == 0) {
            this.bias += changeFactor;
        } else if (propertyToChange == 1) {
            this.weight1 += changeFactor;
        } else {
            this.weight2 += changeFactor;
        }
        ;
    }

    public void forget() {
        bias = oldBias;
        weight1 = oldWeight1;
        weight2 = oldWeight2;
    }

    public void remember() {
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }

    public double compute(double input1, double input2) {
        // this.input1 = input1; this.input2 = input2;
        double preActivation = (this.weight1 * input1) + (this.weight2 * input2) + this.bias;
        double output = Util.sigmoid(preActivation);
        return output;
    }

    public void learn(double input1, double input2, double desiredOutput) {
        double output = compute(input1, input2);
        double error = desiredOutput - output;
        weight1 += learningRate * error * input1;
        weight2 += learningRate * error * input2;
        bias += learningRate * error;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
