import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Network {
    int epochs = 0;
    Double learnFactor = null;
    List<Neuron> neurons = Arrays.asList(
            new Neuron(), new Neuron(), new Neuron(),
            new Neuron(), new Neuron(),
            new Neuron());

    // public Network(int epochs) {
    // this.epochs = epochs;
    // }

    public Network(int epochs, Double learnFactor) {
        this.epochs = epochs;
        this.learnFactor = learnFactor;
    }

    public Double predict(Integer input1, Integer input2) {
        return neurons.get(5).compute(
                neurons.get(4).compute(
                        neurons.get(2).compute(input1, input2),
                        neurons.get(1).compute(input1, input2)),
                neurons.get(3).compute(
                        neurons.get(1).compute(input1, input2),
                        neurons.get(0).compute(input1, input2)));
    }

    public void train(List<List<Integer>> data, List<Double> answers) {
        Double bestEpochLoss = null;
        for (int epoch = 0; epoch < epochs; epoch++) {

            Neuron epochNeuron = neurons.get(epoch % 6);
            epochNeuron.mutate(this.learnFactor);

            List<Double> predictions = new ArrayList<Double>();
            for (int i = 0; i < data.size(); i++) {
                predictions.add(i, this.predict(data.get(i).get(0), data.get(i).get(1)));
            }
            Double thisEpochLoss = Util.meanSquareLoss(answers, predictions);

            if (epoch % 10 == 0)
                System.out.println(String.format("Epoch: %s | bestEpochLoss: %.15f | thisEpochLoss: %.15f", epoch,
                        bestEpochLoss, thisEpochLoss));

            if (bestEpochLoss == null) {
                bestEpochLoss = thisEpochLoss;
                epochNeuron.remember();
            } else {
                if (thisEpochLoss < bestEpochLoss) {
                    bestEpochLoss = thisEpochLoss;
                    epochNeuron.remember();
                } else {
                    epochNeuron.forget();
                }
            }
        }
    }
}
