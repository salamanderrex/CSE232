
package project1.xquery.main;

import project1.utils.Debugger;
import project1.utils.XQueryExecutor;
import project1.xquery.saxTree.IXMLElement;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        String filename = System.getProperty("user.dir") + "/1.txt";
        System.out.println(filename);
        List<IXMLElement> result = new ArrayList<>();
        try {

            result = XQueryExecutor.executeFromFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length > 1 && args[1].equals("terminal")) {
            System.out.println(result.size() + " results below:");
            Integer i = 0;
            for (IXMLElement c : result) {
                Debugger.result("#" + i++);
                System.out.println(c.toString());
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
                for (IXMLElement c : result) {
                    writer.println(c.toString());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
