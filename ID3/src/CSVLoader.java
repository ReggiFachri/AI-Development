import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVLoader {
    public List<ArrayList<String>> loadCSV(String filepath) throws FileNotFoundException {
        List<ArrayList<String>> data = new ArrayList<>();
        Scanner scan = new Scanner(new File(filepath));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            ArrayList<String> lineArrayList = new ArrayList<>(Arrays.asList(line.split(",")));
            data.add(lineArrayList);
        }
        scan.close();
        return data;
    }
}