
package project1.xquery;

import org.dom4j.*;
import project1.xquery.*;


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

        if (child.isText()) {
            this.elem.addText(child.elem.getName());
        }

        else
         this.elem.add(child.elem.createCopy());


    }


    public void addAll(NodeTextList list) {
        for (XMLElement e : list.values)
            this.add((XMLElement) e);
    }

    public void add(String txt) {
        elem.addText(txt);
    }


    public NodeTextList parent() {
        NodeTextList res = new NodeTextList(1);

        Element parentEl = elem.getParent();

        if (parentEl != null)
            res.add(new XMLElement(parentEl));
        return res;
    }


    public NodeTextList children() {
        List<XMLElement> values = new ArrayList<XMLElement>();
        Iterator elementIterator = this.elem.elementIterator();
        while (elementIterator.hasNext()) {
            Element tt = (Element) elementIterator.next();
            values.add(new XMLElement(tt));
        }
        return new NodeTextList(values);
    }


    public NodeTextList getChildByTag(String tagName) {
        NodeTextList res = new NodeTextList();
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


    public XMLElement attrib(String attName) {

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

    public NodeTextList descendants() {
        NodeTextList res = new NodeTextList();
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
            return this.elem.getName().equals(e.elem.getName());
        }

        if (e.isText() && this.isText()) {
            return this.elem.getName().equals(e.elem.getName());
        }

        if (o instanceof XMLElement) {
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

    public boolean childrenAndItselfEquals(Object o) {
        if (o instanceof XMLElement) {

            XMLElement e = (XMLElement) o;
            if (e.children().size()!=this.children().size()) {
                return false;
            }
            if (e.children().size()==0) {
                return e.txt().equals(this.txt());
            }

            if (e.txt()!=null &&this.txt() == null) {
                return  false;
            }

            if (e.txt() == null && this.txt() != null) {
                return false;
            }
            if (e.txt() !=null && this.txt() !=null) {
                if (!this.txt().equals(e.txt())) {
                    return false;
                }
            }


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

