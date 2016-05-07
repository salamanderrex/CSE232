
package project1.xquery.saxTree;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import project1.xquery.value.XQueryList;
import project1.xquery.saxTree.IXMLElement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class XMLElement implements IXMLElement {
    public Element elem;

    public int textFlag = 0;

    @Override
    public boolean TextFlag() {
        if (this.textFlag == 1)
            return true;
        return false;
    }

    public XMLElement(Element element) {
        elem = element;
    }

    public XMLElement(String tagName) {
        elem = DocumentHelper.createElement(tagName);
    }


    public void add(XMLElement child) {


        this.elem.add(child.elem.createCopy());


    }


    public void addAll(XQueryList list) {
        for (IXMLElement e : list.values)
            this.add((XMLElement) e);
    }

    public void add(String txt) {
        elem.addText(txt);
    }

    @Override
    public XQueryList parent() {
        XQueryList res = new XQueryList(1);

        Element parentEl = elem.getParent();

        if (parentEl != null)
            res.add(new XMLElement(parentEl));
        return res;
    }

    @Override
    public XQueryList children() {
        List<IXMLElement> values = new ArrayList<IXMLElement>();
        Iterator elementIterator = this.elem.elementIterator();
        while (elementIterator.hasNext()) {
            Element tt = (Element) elementIterator.next();
            values.add(new XMLElement(tt));
        }
        return new XQueryList(values);
    }

    @Override
    public XQueryList getChildByTag(String tagName) {
        XQueryList res = new XQueryList();
        for (IXMLElement e : this.children())
            if (e.tag().equals(tagName))
                res.add(e);
        return res;
    }

    @Override
    public String tag() {
        return elem.getName();
    }

    @Override
    public String txt() {
        return elem.getText();
    }


    //??? what ?
    @Override
    public IXMLElement attrib(String attName) {
        // TODO: This should not return an {@link IXMLElement}, but probably an {@link IXQueryValue} of type Text, or something
        // TODO: This is the root of all evil (returning null!)
        String att = elem.valueOf("@" + attName);
        if (att == null)
            return null;

        Element attEl = DocumentHelper.createElement(attName);
        attEl.addText(att);

        return new XMLElement(attEl);
    }

    public String getAttribute(String attNAME, Element x) {
        return x.valueOf("@" + attNAME);
    }

    @Override
    public String toString() {
        return this.elem.asXML();

    }

    @Override
    public boolean equalsRef(IXMLElement o) {
        //text only compare content???????
        if (this.textFlag == 1) {
            XMLElement e = (XMLElement) o;
            return this.elem.getText().equals(e.elem.getText());
        }


        if (o instanceof XMLElement) {
            XMLElement e = (XMLElement) o;
            return elem.equals(e.elem);
        }
        return false;
    }

    @Override
    public List<String> getAttribNames() {

        List<String> attributes = new ArrayList<>();
        for (Iterator i = this.elem.attributeIterator(); i.hasNext(); ) {
            Attribute attr = (Attribute) i.next();
            attributes.add(attr.getName());
        }
        return attributes;
    }

    @Override
    public XQueryList descendants() {
        XQueryList res = new XQueryList();
        res.add(this);

        for (IXMLElement e : children()) {
            res.add(e);
            res.addAll(e.descendants());
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        XMLElement e = (XMLElement) o;

        if (e.textFlag == 1) {
//            System.out.println("compare constant string");
//            System.out.println(e.elem.getName());
//            System.out.println(e.elem.asXML());
//            System.out.print("xx"+e.elem.getData());
//
//            System.out.print("this"+ this.elem.asXML());
//            System.out.print("this"+ this.elem.getText());
            // System.out.println("elem"+this.elem.get());


            return this.elem.getText().equals(e.elem.getName());
        }

        if (o instanceof XMLElement) {

    //System.out.println("this"+this.elem.asXML());
            //  System.out.println("e"+e.elem.asXML());

            return this.elem.asXML().equals(e.elem.asXML());
        }
        return false;
    }

    @Override
    public boolean childrenEquals(Object o) {
        if (o instanceof XMLElement) {
            XMLElement e = (XMLElement) o;

            for (IXMLElement e1 : this.children())
                for (IXMLElement e2 : e.children())
                    if (!e1.equals(e2))
                        return false;
            return true;
        }
        return false;
    }

    @Override
    public String toCompactString() {
        return this.elem.asXML();
    }
}

