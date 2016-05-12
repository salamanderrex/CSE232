
package project1.xquery.xmlElement;

import org.dom4j.*;
import project1.xquery.value.XQueryList;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class XMLElement {
    public Element elem;

    public int isconstantstring = 0;
    public int istext = 0;


    public boolean isConstantStr() {
        if (this.isconstantstring == 1)
            return true;
        return false;
    }

    public boolean isText() {
        if (this.istext==1 ) {
            return true;
        }
        return false;
    }

    public XMLElement(Element element) {
        elem = element;
    }

    public XMLElement(String tagName) {
        elem = DocumentHelper.createElement(tagName);
    }


    public void add(XMLElement child) {

      //  System.out.println("add"+child.toString());
      //  System.out.println("add"+child.elem.getName());
        if (child.isText()) {
        //    System.out.println("is text");
            this.elem.addText(child.elem.getName());
        }

        else
         this.elem.add(child.elem.createCopy());


    }


    public void addAll(XQueryList list) {
        for (XMLElement e : list.values)
            this.add((XMLElement) e);
    }

    public void add(String txt) {
        elem.addText(txt);
    }


    public XQueryList parent() {
        XQueryList res = new XQueryList(1);

        Element parentEl = elem.getParent();

        if (parentEl != null)
            res.add(new XMLElement(parentEl));
        return res;
    }


    public XQueryList children() {
        List<XMLElement> values = new ArrayList<XMLElement>();
        Iterator elementIterator = this.elem.elementIterator();
        while (elementIterator.hasNext()) {
            Element tt = (Element) elementIterator.next();
            values.add(new XMLElement(tt));
        }
        return new XQueryList(values);
    }


    public XQueryList getChildByTag(String tagName) {
        XQueryList res = new XQueryList();
        for (XMLElement e : this.children())
            if (e.tag().equals(tagName))
                res.add(e);
        return res;
    }


    public String tag() {
        return elem.getName();
    }

    public String txt() {
        return elem.getText();
    }


    //??? what ?

    public XMLElement attrib(String attName) {
        // TODO: This should not return an {@link XMLElement}, but probably an {@link IXQueryValue} of type Text, or something
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


    public String toString() {

        return this.elem.asXML();

    }


    public boolean equalsRef(XMLElement o) {
        //text only compare content???????
        if (this.isconstantstring == 1) {
            XMLElement e = (XMLElement) o;
            return this.elem.getText().equals(e.elem.getText());
        }


        if (o instanceof XMLElement) {
            XMLElement e = (XMLElement) o;
            return elem.equals(e.elem);
        }
        return false;
    }


    public List<String> getAttribNames() {

        List<String> attributes = new ArrayList<>();
        for (Iterator i = this.elem.attributeIterator(); i.hasNext(); ) {
            Attribute attr = (Attribute) i.next();
            attributes.add(attr.getName());
        }
        return attributes;
    }

    public XQueryList descendants() {
        XQueryList res = new XQueryList();
        res.add(this);

        for (XMLElement e : children()) {
            res.add(e);
            res.addAll(e.descendants());
        }
        return res;
    }


    public boolean equals(Object o) {
        XMLElement e = (XMLElement) o;

        if (e.isconstantstring == 1) {

//            if (e.elem.getName().equals("SCENE II. The Forum."))
//                if (this.elem.getName().equals("SCENE II. The Forum."))
//                    System.out.println("YES");
                //System.out.println(this.elem.getName());
//            System.out.println("compare constant string");
//            System.out.println("elem" + e.elem.getName());
//            System.out.println("elem" + e.elem.asXML());
//            System.out.print("elem" + e.elem.getText());
//
//
//            System.out.println("this" + this.elem.getName());
//            System.out.print("this" + this.elem.asXML());
//            System.out.print("this" + this.elem.getText());


           // return this.elem.getText().equals(e.elem.getName());
            return this.elem.getName().equals(e.elem.getName());
        }

        if (e.isText() && this.isText()) {
            //System.out.println("here!!!!");
            return this.elem.getName().equals(e.elem.getName());
        }

        if (o instanceof XMLElement) {
//
//            System.out.println("this" + this.elem.asXML());
//            System.out.println("e" + e.elem.asXML());

            return this.elem.asXML().equals(e.elem.asXML());
        }
        return false;
    }

    public boolean childrenEquals(Object o) {
        if (o instanceof XMLElement) {
            XMLElement e = (XMLElement) o;

            for (XMLElement e1 : this.children())
                for (XMLElement e2 : e.children())
                    if (!e1.equals(e2))
                        return false;
            return true;
        }
        return false;
    }

   
    public String toCompactString() {
        return this.elem.asXML();
    }
}

