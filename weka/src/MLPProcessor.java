import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.Utils;

public class MLPProcessor {

    public MLPProcessor() {
        try {
            FileReader fr = new FileReader(
                    "D:\\Coolyeah\\S1 Sistem Informasi\\Genap 23-24\\Pengembangan Sistem Cerdas\\Code\\Java\\weka\\src\\weather.arff");
            Instances training = new Instances(fr);
            training.setClassIndex(training.numAttributes() - 1);
            MultilayerPerceptron mlp = new MultilayerPerceptron();
            mlp.setOptions(Utils.splitOptions("-L 0.01 -M 0.1 -N 1000 -V 0 -S 0 -E 50 -H t,o "));
            mlp.buildClassifier(training);
            FileReader tr = new FileReader(
                    "D:\\Coolyeah\\S1 Sistem Informasi\\Genap 23-24\\Pengembangan Sistem Cerdas\\Code\\Java\\weka\\src\\weathertest.arff");
            Instances testdata = new Instances(tr);
            testdata.setClassIndex(testdata.numAttributes() - 1);
            Evaluation eval = new Evaluation(training);
            eval.evaluateModel(mlp, testdata);
            System.out.println(eval.toSummaryString("\nResults\n*******\n", false));
            tr.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MLPProcessor mlp = new MLPProcessor();
    }
}
// javac -cp "lib\\weka.jar" -d bin src\\MLPProcessor.java
// java -cp "lib\\weka.jar;bin" MLPProcessor