import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestJoinOptimizer {

    public static void main(String[] args) {
        String filename = System.getProperty("user.dir").toString() + "/oldJoinOptimizer/" + "testcase";
        try {
            String query = openQueryFileAsString(filename);
            System.out.println(query);
            JoinOptimizer jo = new JoinOptimizer(query, "testcase");
            jo.getComparisonMap();

            jo.graphToPNG();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String openQueryFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}