import java.util.ArrayList;
import java.util.List;

public class TablePrinter {
    public static void printArrayList(List<ArrayList<String>> data) {
        int colnum = 0;
        List<Integer> maxcolwidths = new ArrayList<>();

        // Determine the maximum width of each column
        for (ArrayList<String> row : data) {
            for (String item : row) {
                if (maxcolwidths.size() <= colnum) {
                    maxcolwidths.add(item.length());
                } else if (item.length() > maxcolwidths.get(colnum)) {
                    maxcolwidths.set(colnum, item.length());
                }
                colnum++;
            }
            colnum = 0;
        }

        // Print the table with formatted columns
        colnum = 0;
        for (ArrayList<String> row : data) {
            for (String item : row) {
                String format = "| %-" + maxcolwidths.get(colnum) + "s ";
                System.out.printf(format, item);
                colnum++;
            }
            colnum = 0;
            System.out.println("|");
        }
    }
}
