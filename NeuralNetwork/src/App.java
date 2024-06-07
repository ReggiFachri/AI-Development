import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class App {
        public static void main(String[] args) {
                App app = new App();
                app.trainAndPredict();
        }

        public void trainAndPredict() {
                List<List<Integer>> data = new ArrayList<List<Integer>>();
                data.add(Arrays.asList(115, 66));
                data.add(Arrays.asList(175, 78));
                data.add(Arrays.asList(205, 72));
                data.add(Arrays.asList(120, 67));
                List<Double> answers = Arrays.asList(1.0, 0.0, 0.0, 1.0);

                Network network500 = new Network(500, 0.01);
                network500.train(data, answers);

                Network network1000 = new Network(1000, 0.01);
                network1000.train(data, answers);

                System.out.println("");
                System.out.println(String.format("  male, 167, 73: network500: %.10f | network1000: %.10f",
                                network500.predict(167, 73), network1000.predict(167, 73)));
                System.out.println(String.format("female, 105, 67: network500: %.10f | network1000: %.10f",
                                network500.predict(105, 67), network1000.predict(105, 67)));
                System.out.println(String.format("female, 120, 72: network500: %.10f | network1000: %.10f",
                                network500.predict(120, 72), network1000.predict(120, 72)));
                System.out.println(String.format("  male, 143, 67: network500: %.10f | network1000: %.10f",
                                network500.predict(143, 67), network1000.predict(120, 72)));
        }

}