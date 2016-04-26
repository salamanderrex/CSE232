package dom;

/**
 * Created by kezhang on 4/25/16.
 */

//These are the JAXP APIs used by DOMEcho:
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//These classes are for the exceptions that can be thrown when the XML document is parsed:

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;
//These classes read the sample XML file and manage output:

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
//Finally, import the W3C definitions for a DOM, DOM exceptions, entities and nodes:

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



//Creating Nodes
//
//        You can create different types nodes using the methods of the Document interface.
//        For example, createElement, createComment, createCDATAsection, createTextNode, and so on.
//        The full list of methods for creating different nodes is provided in the API documentation
//        for org.w3c.dom.Document.


//Traversing Nodes
//
//        The org.w3c.dom.Node interface defines a number of methods you can use to traverse nodes,
//        including getFirstChild, getLastChild, getNextSibling, getPreviousSibling, and getParentNode.
//        Those operations are sufficient to get from anywhere in the tree to any other location in the tree.


//Creating Attributes
//
//        The org.w3c.dom.Element interface, which extends Node, defines a setAttribute operation,
//        which adds an attribute to that node. (A better name from the Java platform standpoint would have been addAttribute.
//        The attribute is not a property of the class, and a new object is created.)
//        You can also use the Document's createAttribute operation to create an instance of Attribute
//        and then use the setAttributeNode method to add it.

//Removing and Changing Nodes
//
//        To remove a node, you use its parent Node's removeChild method.
//        To change it, you can use either the parent node's replaceChild operation
//        or the node's setNodeValue operation.

//Inserting Nodes
//
//        The important thing to remember when creating new nodes is that
//        when you create an element node, the only data you specify is a name.
//        In effect, that node gives you a hook to hang things on.
//        You hang an item on the hook by adding to its list of child nodes.
//        For example, you might add a text node, a CDATA node, or an attribute node.
//        As you build, keep in mind the structure you have seen in this tutorial.
//        Remember: Each node in the hierarchy is extremely simple, containing only one data element.
public class DOMEcho {

    static final String outputEncoding = "UTF-8";
    private PrintWriter out;
    private int indent = 0;
    private final String basicIndent = " ";

    DOMEcho(PrintWriter out) {
        this.out = out;
    }
    private static void usage() {
        // ...
    }

    public static void main(String[] args) throws Exception {
        String filename = null;
        boolean dtdValidate = false;
        boolean xsdValidate = false;
        String schemaSource = null;


        boolean ignoreWhitespace = false;
        boolean ignoreComments = false;
        boolean putCDATAIntoText = false;
        boolean createEntityRefs = false;


        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-dtd")) {
                dtdValidate = true;
            } else if (args[i].equals("-ws")) {
                ignoreWhitespace = true;
            } else if (args[i].startsWith("-co")) {
                ignoreComments = true;
            } else if (args[i].startsWith("-cd")) {
                putCDATAIntoText = true;
            } else if (args[i].startsWith("-e")) {
                createEntityRefs = true;

                // ...
            } else if (args[i].equals("-xsd")) {
                xsdValidate = true;
            } else if (args[i].equals("-xsdss")) {
                if (i == args.length - 1) {
                    usage();
                }
                xsdValidate = true;
                schemaSource = args[++i];
            } else {
                filename = args[i];
                if (i != args.length - 1) {
                    usage();
                }
            }
        }

        if (filename == null) {
            usage();
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        The following DocumentBuilderFactory methods give you control over the lexical information you see in the DOM.
//
//        setCoalescing()
//        To convert CDATA nodes to Text nodes and append to an adjacent Text node (if any).
//
//        setExpandEntityReferences()
//        To expand entity reference nodes.
//
//                setIgnoringComments()
//        To ignore comments.
//
//                setIgnoringElementContentWhitespace()
//        To ignore whitespace that is not a significant part of element content.

        dbf.setIgnoringComments(ignoreComments);
        dbf.setIgnoringElementContentWhitespace(ignoreWhitespace);
        dbf.setCoalescing(putCDATAIntoText);
        dbf.setExpandEntityReferences(!createEntityRefs);

        dbf.setNamespaceAware(true);
        dbf.setValidating(dtdValidate || xsdValidate);

        // ...

        DocumentBuilder db = dbf.newDocumentBuilder();
        OutputStreamWriter errorWriter = new OutputStreamWriter(System.err,
                outputEncoding);
        db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));

        Document doc = db.parse(new File(filename));
//        doc.getFirstChild();
        //echo(doc.getFirstChild());
        DOMEcho demo = new DOMEcho(new PrintWriter(System.out));
        demo.echo(doc.getFirstChild());
    }

    /**
     * Find the named subnode in a node's sublist.
     * <ul>
     * <li>Ignores comments and processing instructions.
     * <li>Ignores TEXT nodes (likely to exist and contain
     *         ignorable whitespace, if not validating.
     * <li>Ignores CDATA nodes and EntityRef nodes.
     * <li>Examines element nodes to find one with
     *        the specified name.
     * </ul>
     * @param name  the tag name for the element to find
     * @param node  the element node to start searching from
     * @return the Node found
     */
    public Node findSubNode(String name, Node node) {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            System.err.println("Error: Search node not of element type");
            System.exit(22);
        }

        if (! node.hasChildNodes()) return null;

        NodeList list = node.getChildNodes();
        for (int i=0; i < list.getLength(); i++) {
            Node subnode = list.item(i);
            if (subnode.getNodeType() == Node.ELEMENT_NODE) {
                if (subnode.getNodeName().equals(name))
                    return subnode;
            }
        }
        return null;
    }





        /**
         * Return the text that a node contains. This routine:
         * <ul>
         * <li>Ignores comments and processing instructions.
         * <li>Concatenates TEXT nodes, CDATA nodes, and the results of
         *     recursively processing EntityRef nodes.
         * <li>Ignores any element nodes in the sublist.
         *     (Other possible options are to recurse into element
         *      sublists or throw an exception.)
         * </ul>
         * @param    node  a  DOM node
         * @return   a String representing its contents
         */
        public String getText(Node node) {
            StringBuffer result = new StringBuffer();
            if (! node.hasChildNodes()) return "";

            NodeList list = node.getChildNodes();
            for (int i=0; i < list.getLength(); i++) {
                Node subnode = list.item(i);
                if (subnode.getNodeType() == Node.TEXT_NODE) {
                    result.append(subnode.getNodeValue());
                }
                else if (subnode.getNodeType() == Node.CDATA_SECTION_NODE) {
                    result.append(subnode.getNodeValue());
                }
                else if (subnode.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
                    // Recurse into the subtree for text
                    // (and ignore comments)
                    result.append(getText(subnode));
                }
            }

            return result.toString();
        }


    private void printlnCommon(Node n) {
        out.print(" nodeName=\"" + n.getNodeName() + "\"");

        String val = n.getNamespaceURI();
        if (val != null) {
            out.print(" uri=\"" + val + "\"");
        }

        val = n.getPrefix();

        if (val != null) {
            out.print(" pre=\"" + val + "\"");
        }

        val = n.getLocalName();
        if (val != null) {
            out.print(" local=\"" + val + "\"");
        }

        val = n.getNodeValue();
        if (val != null) {
            out.print(" nodeValue=");
            if (val.trim().equals("")) {
                // Whitespace
                out.print("[WS]");
            }
            else {
                out.print("\"" + n.getNodeValue() + "\"");
            }
        }
        out.println();
    }
    private void outputIndentation() {
        for (int i = 0; i < indent; i++) {
            out.print(basicIndent);
        }
    }

    private void echo(Node n) {
        outputIndentation();
        int type = n.getNodeType();

        switch (type) {
            case Node.ATTRIBUTE_NODE:
                out.print("ATTR:");
                printlnCommon(n);
                break;

            case Node.CDATA_SECTION_NODE:
                out.print("CDATA:");
                printlnCommon(n);
                break;

            case Node.COMMENT_NODE:
                out.print("COMM:");
                printlnCommon(n);
                break;

            case Node.DOCUMENT_FRAGMENT_NODE:
                out.print("DOC_FRAG:");
                printlnCommon(n);
                break;

            case Node.DOCUMENT_NODE:
                out.print("DOC:");
                printlnCommon(n);
                break;

            case Node.DOCUMENT_TYPE_NODE:
                out.print("DOC_TYPE:");
                printlnCommon(n);
                NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
                indent += 2;
                for (int i = 0; i < nodeMap.getLength(); i++) {
                    Entity entity = (Entity)nodeMap.item(i);
                    echo(entity);
                }
                indent -= 2;
                break;

            case Node.ELEMENT_NODE:
                out.print("ELEM:");
                printlnCommon(n);

                NamedNodeMap atts = n.getAttributes();
                indent += 2;
                for (int i = 0; i < atts.getLength(); i++) {
                    Node att = atts.item(i);
                    echo(att);
                }
                indent -= 2;
                break;

            case Node.ENTITY_NODE:
                out.print("ENT:");
                printlnCommon(n);
                break;

            case Node.ENTITY_REFERENCE_NODE:
                out.print("ENT_REF:");
                printlnCommon(n);
                break;

            case Node.NOTATION_NODE:
                out.print("NOTATION:");
                printlnCommon(n);
                break;

            case Node.PROCESSING_INSTRUCTION_NODE:
                out.print("PROC_INST:");
                printlnCommon(n);
                break;

            case Node.TEXT_NODE:
                out.print("TEXT:");
                printlnCommon(n);
                break;

            default:
                out.print("UNSUPPORTED NODE: " + type);
                printlnCommon(n);
                break;
        }

        indent++;
        for (Node child = n.getFirstChild(); child != null;
             child = child.getNextSibling()) {
            echo(child);
        }
        indent--;
        out.flush();
    }



    private static class MyErrorHandler implements ErrorHandler {

        private PrintWriter out;

        MyErrorHandler(PrintWriter out) {
            this.out = out;
        }

        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }

            String info = "URI=" + systemId + " Line=" + spe.getLineNumber() +
                    ": " + spe.getMessage();
            return info;
        }

        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }

        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }

}
