


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import project1.xquery.XQueryExecutor;
import project1.xquery.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        System.out.println("here");


        for (int i = 1; i <= 20; i++) {
            String filename = System.getProperty("user.dir").toString() + "/testcases/Query" + i;
            System.out.println(filename);
            System.out.println("start querying........." + i + "query");
            List<XMLElement> result = new ArrayList<>();
            try {
                result = XQueryExecutor.executeFromFile(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println(result.size() + " results below:");


            //system std output.
            Integer j = 0;
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("xml");
            for (XMLElement c : result) {
                System.out.println("result @" + j++);
                //System.out.println(c.toString());
                root.add(c.elem);
            }
            XMLWriter writer = null;
            OutputFormat format = OutputFormat.createPrettyPrint();
            try {
                writer = new XMLWriter(System.out, format);
                writer.write(document);
            } catch (Exception e) {
                e.printStackTrace();
            }

            FileOutputStream fop = null;
            File file;
            try {

                file = new File("output/"+i + ".xml");
                fop = new FileOutputStream(file);
                writer = new XMLWriter(fop, format);
                writer.write(document);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }

}
