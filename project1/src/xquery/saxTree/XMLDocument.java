package project1.xquery.saxTree;


import project1.xquery.saxTree.XMLElement;


import java.io.File;
import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
/**
 * Created by qingyu on 5/5/16.
 */
public class XMLDocument  {

    private Document doc;

    public XMLDocument(String fileName) {

        try {
            System.out.println("to read the xml file ......"+fileName);
            File inputFile = new File(System.getProperty("user.dir").toString()+"/"+fileName.substring(1,fileName.length()-1));
            SAXReader reader = new SAXReader();
            this.doc = reader.read( inputFile );

        } catch (DocumentException  e) {
            e.printStackTrace();
        }
    }


    public XMLElement root() {
        return new XMLElement(doc.getRootElement());
    }

}
