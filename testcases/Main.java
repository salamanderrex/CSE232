


import project1.utils.Debugger;
import project1.utils.XQueryExecutor;
import project1.xquery.saxTree.XMLElement;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        System.out.println("here");


        for (int i = 1; i <= 9 ; i ++ ) {
            String filename = System.getProperty("user.dir").toString() + "/testcases/Query"+i;
            System.out.println(filename);
            System.out.println("start querying........."+i+"query");
            List<XMLElement> result = new ArrayList<>();
            try {
                result = XQueryExecutor.executeFromFile(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println(result.size() + " results below:");
            Integer j = 0;
            for (XMLElement c : result) {
                Debugger.result("#" + j++);
                System.out.println(c.toString());
            }


        }



    }

}
