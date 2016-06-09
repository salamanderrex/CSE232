


import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import project1.xquery.*;
import project1.xquery.parser.XQueryLexer;
import project1.xquery.parser.XQueryParser;
import project1.xquery.parser.XQueryVisitor;
import project1.xquery.parser.myXQueryVisitor;


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


        for (int i = 21; i <= 24; i++) {
            long startTime = System.currentTimeMillis();


            String filename = System.getProperty("user.dir").toString() + "/testcases/Query" + i;
            System.out.println(filename);
            System.out.println("start querying........." + i + "query");
            List<XMLElement> result = new ArrayList<>();
            try {
            XQueryLexer lexer = new XQueryLexer(new ANTLRFileStream(filename));
            XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));
            XQueryVisitor visitor = new myXQueryVisitor();
            XQueryParser.XqContext context = parser.xq();
                result = (NodeTextList) visitor.visit(context);
            } catch (IOException e) {
                e.printStackTrace();
            }

            long endTime   = System.currentTimeMillis();
            System.out.println(result.size() + " results below:");

            Integer j = 0;
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("xml");
            for (XMLElement c : result) {
               // System.out.println("result @" + j++);
                //System.out.println(c.toString());
                root.add(c.elem);
            }

            XMLWriter writer = null;

            OutputFormat format = OutputFormat.createPrettyPrint();
            /*
            try {
                writer = new XMLWriter(System.out, format);
                writer.write(document);
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            long totalTime = endTime - startTime;
            System.out.println("total ------- time"+totalTime);
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
