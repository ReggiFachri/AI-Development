
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Data preview");

        try {
            CSVLoader loader = new CSVLoader();
            List<ArrayList<String>> data = loader.loadCSV(
                    "D:\\Coolyeah\\S1 Sistem Informasi\\Genap 23-24\\Pengembangan Sistem Cerdas\\Code\\Java\\ID3\\src\\playtennis.csv");
            TablePrinter.printArrayList(data);

            List<String> attributes = data.get(0);
            List<List<String>> instances = new ArrayList<>(data.subList(1, data.size()));
            int targetIndex = attributes.indexOf("Play Tennis");

            System.out.println("\nGain and Entropy Calculation");
            Node root = buildDecisionTree(instances, attributes, targetIndex);
            Tree tree = new Tree();
            tree.setRoot(root);

            System.out.println("\nTree Visualization");
            tree.print();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static Node buildDecisionTree(List<List<String>> instances, List<String> attributes, int targetIndex) {
        // Base cases: all instances have the same target value or no attributes left
        if (instances.stream()
                .allMatch(instance -> instance.get(targetIndex).equals(instances.get(0).get(targetIndex)))) {
            return new Node(instances.get(0).get(targetIndex));
        }
        if (attributes.size() == 1) { // Only target attribute is left
            return new Node(mostCommonValue(instances, targetIndex));
        }

        // Calculate information gain for each attribute
        int bestAttributeIndex = -1;
        double bestInfoGain = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < attributes.size(); i++) {
            if (i != targetIndex) {
                double infoGain = informationGain(instances, i, targetIndex);
                System.out.println("Information Gain for " + attributes.get(i) + ": " + infoGain);
                if (infoGain > bestInfoGain) {
                    bestInfoGain = infoGain;
                    bestAttributeIndex = i;
                }
            }
        }

        System.out.println("Best Attribute: " + attributes.get(bestAttributeIndex));

        // Create a new node with the best attribute
        String bestAttribute = attributes.get(bestAttributeIndex);
        Node node = new Node(bestAttribute);

        // Split the instances by the best attribute's values
        Map<String, List<List<String>>> splitInstances = splitByAttribute(instances, bestAttributeIndex);
        for (Map.Entry<String, List<List<String>>> entry : splitInstances.entrySet()) {
            List<String> newAttributes = new ArrayList<>(attributes);
            newAttributes.remove(bestAttributeIndex);
            Node child = buildDecisionTree(entry.getValue(), newAttributes, targetIndex);
            Node attributeValueNode = new Node(entry.getKey());
            attributeValueNode.addChild(child);
            node.addChild(attributeValueNode);
        }
        return node;
    }

    private static double informationGain(List<List<String>> instances, int attributeIndex, int targetIndex) {
        double entropyBeforeSplit = entropy(instances, targetIndex);
        System.out.println("Entropy before split: " + entropyBeforeSplit);
        Map<String, List<List<String>>> splitInstances = splitByAttribute(instances, attributeIndex);
        double weightedEntropyAfterSplit = 0.0;
        for (List<List<String>> subset : splitInstances.values()) {
            double subsetEntropy = entropy(subset, targetIndex);
            weightedEntropyAfterSplit += ((double) subset.size() / instances.size()) * subsetEntropy;
            System.out.println("Subset entropy for attribute value: " + subsetEntropy);
        }
        return entropyBeforeSplit - weightedEntropyAfterSplit;
    }

    private static double entropy(List<List<String>> instances, int targetIndex) {
        Map<String, Integer> counts = new HashMap<>();
        for (List<String> instance : instances) {
            String targetValue = instance.get(targetIndex);
            counts.put(targetValue, counts.getOrDefault(targetValue, 0) + 1);
        }
        double entropy = 0.0;
        for (int count : counts.values()) {
            double probability = (double) count / instances.size();
            entropy -= probability * Math.log(probability) / Math.log(2);
        }
        return entropy;
    }

    private static Map<String, List<List<String>>> splitByAttribute(List<List<String>> instances, int attributeIndex) {
        Map<String, List<List<String>>> splitInstances = new HashMap<>();
        for (List<String> instance : instances) {
            String attributeValue = instance.get(attributeIndex);
            if (!splitInstances.containsKey(attributeValue)) {
                splitInstances.put(attributeValue, new ArrayList<>());
            }
            splitInstances.get(attributeValue).add(instance);
        }
        return splitInstances;
    }

    private static String mostCommonValue(List<List<String>> instances, int targetIndex) {
        Map<String, Integer> counts = new HashMap<>();
        for (List<String> instance : instances) {
            String targetValue = instance.get(targetIndex);
            counts.put(targetValue, counts.getOrDefault(targetValue, 0) + 1);
        }
        return counts.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
