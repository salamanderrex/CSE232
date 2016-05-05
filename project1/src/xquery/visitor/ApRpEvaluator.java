package project1.xquery.visitor;

import org.antlr.v4.runtime.misc.NotNull;

import project1.xquery.parser.XQueryParser;
import project1.xquery.value.*;
import project1.xquery.parser.*;
import project1.xquery.xmltree.*;
import project1.xquery.context.*;
import project1.utils.*;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.Arrays;

public class ApRpEvaluator extends XQueryEvaluator {

    public int readFileCounter;
    public XQueryList cachedXQueryList;
    public ApRpEvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc) {
        super(visitor, qc);
        readFileCounter = 0;
        cachedXQueryList= null;
    }

    public XQueryList evalAp(@NotNull  XQueryParser.ApContext ctx) {
       // System.out.println("start ap xml document");
        if (readFileCounter ==0) {
            XMLDocument document = new XMLDocument(ctx.fileName.getText());
            XQueryList results = new XQueryList();

        /* Replace the below code with this commented out code
         * in case documents roots have to excplictly be referenced
         * e.g. doc("j_caesar.xml")/PLAY/TITLE/text() instead of
         *      doc("j_caesar.xml")/TITLE/text() which is the current setting
        XMLElement root = new XMLElement("root");
        root.add(document.root());
        qc.pushContextElement(root);
        */

            qc.pushContextElement(document.root());

            switch(ctx.slash.getType()) {
                case XQueryParser.SLASH:
                    results.addAll((XQueryList)visitor.visit(ctx.rp()));
                    break;
                case XQueryParser.SSLASH:
                    qc.pushContextElement(document.root().descendants());
                    results.addAll( (XQueryList)visitor.visit(ctx.rp()) );
                    break;
                default:
                    Debugger.error("Oops, shouldn't be here");
                    break;
            }
            readFileCounter++;
            return results.unique();
        }

        if (readFileCounter ==1) {

            XMLDocument document = new XMLDocument(ctx.fileName.getText());
            XQueryList results = new XQueryList();

        /* Replace the below code with this commented out code
         * in case documents roots have to excplictly be referenced
         * e.g. doc("j_caesar.xml")/PLAY/TITLE/text() instead of
         *      doc("j_caesar.xml")/TITLE/text() which is the current setting
        XMLElement root = new XMLElement("root");
        root.add(document.root());
        qc.pushContextElement(root);
        */

            qc.pushContextElement(document.root());

            switch(ctx.slash.getType()) {
                case XQueryParser.SLASH:
                    results.addAll((XQueryList)visitor.visit(ctx.rp()));
                    break;
                case XQueryParser.SSLASH:
                    qc.pushContextElement(document.root().descendants());
                    results.addAll( (XQueryList)visitor.visit(ctx.rp()) );
                    break;
                default:
                    Debugger.error("Oops, shouldn't be here");
                    break;
            }
            readFileCounter++;
            cachedXQueryList = results.unique();
            return cachedXQueryList;

        }
        return cachedXQueryList;

    }

    public XQueryList evalTagName(@NotNull  XQueryParser.RpTagNameContext ctx) {
        String tagName = ctx.getText();
       // System.out.println("in evalTagName tagName: "+tagName);
/*
        XQueryList  context = qc.peekContextElement();
        XQueryList  context2 = evalWildCard();
        */
        XQueryList x =  evalWildCard().stream().filter(
                e -> e.tag().equalsIgnoreCase(tagName)
        ).collect(Collectors.toCollection(XQueryList::new));

        /*
        if (tagName.equals("title")) {
            System.out.println(x.toArray().toString());
        }
*/
        return x;
    }

    public XQueryList evalWildCard() {
        XQueryList res = new XQueryList();

        for(IXMLElement context : qc.peekContextElement())
            res.addAll(context.children());
        return res;
    }

    public XQueryList evalDot() {
        return qc.peekContextElement();
    }

    public XQueryList evalDotDot() {
        return qc.peekContextElement().stream().map(
                e -> e.parent().get(0)
        ).collect(Collectors.toCollection(XQueryList::new)).unique();
        /* ^^^ is equal to vvv
        XQueryList res = new XQueryList();
        for(IXMLElement e : qc.peekContextElement()) {
            res.add(e.parent().get(0));
        }
        return res.unique();
        */
    }

    public XQueryList evalText() {
        //System.out.println("nc is ctxElems " );
       // System.out.println(qc.nc.ctxElems );
        /*
        System.out.println("st var env is " );
        Iterator <VarEnvironment> it = qc.st.varEnv.iterator();
        if(it.hasNext()){

            System.out.print(it.next().varEnv.keySet());
        }

*/
        XQueryList x =qc.peekContextElement().stream().map(
                IXMLElement::txt).collect(Collectors.toCollection(XQueryList::new));
       // System.out.println(x.values);
       return x;
        /* ^^^is equal to vvv*/
        /*
        XQueryList res = new XQueryList();
        for(IXMLElement x : qc.peekContextElement()) {
            System.out.println(x.txt());
            res.add(x.txt());
        }
        return res;
        */

    }

    public XQueryList evalAttr(XQueryParser.RpAttrContext ctx) {
        // TODO: Don't return a list value here, return an attribute value... or something!
        String attrib = ctx.Identifier().getSymbol().getText();
        XQueryList res = new XQueryList(qc.peekContextElement().size());

        // If @ followed by nothing
        if(attrib == null)
            return res;

        for(IXMLElement x : qc.peekContextElement()) {
            if(x.attrib(attrib) != null)
                res.add(x.attrib(attrib));
        }
        return res;
    }

    public XQueryList evalParen(@NotNull  XQueryParser.RpParenExprContext ctx) {
        return (XQueryList)visitor.visit(ctx.rp());
    }

    public XQueryList evalSlashes(@NotNull  XQueryParser.RpSlashContext ctx) {
        XQueryList results = new XQueryList();
        switch(ctx.slash.getType()) {
            case XQueryParser.SLASH:
                results = evalRpSlash(ctx);
                break;
            case XQueryParser.SSLASH:
                results = evalRpSlashSlash(ctx);
                break;
            default:
                Debugger.error("Oops, shouldn't be here");
                break;
        }
        return results;
    }

    private XQueryList evalRpSlash(@NotNull  XQueryParser.RpSlashContext ctx) {
       // System.out.println("in evalRpSlash ");
        XQueryList y = new XQueryList();
        XQueryList x = (XQueryList)visitor.visit(ctx.left);

        qc.pushContextElement(x);
        XQueryList context = (XQueryList)visitor.visit(ctx.right);
        y.addAll(context);

        qc.popContextElement();
        return y.unique();
    }

    private XQueryList evalRpSlashSlash(@NotNull  XQueryParser.RpSlashContext ctx) {
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList descendants = new XQueryList();

        for(IXMLElement x : l) {
            descendants.addAll(x.descendants());
        }

        qc.pushContextElement(descendants);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        qc.popContextElement();

        return r.unique();
    }

    public XQueryList evalFilter(@NotNull  XQueryParser.RpFilterContext ctx) {
        XQueryList res = new XQueryList();

        // Evaluate rp to get x
        XQueryList xs = (XQueryList)visitor.visit(ctx.rp());

        for(IXMLElement x : xs) {
            qc.pushContextElement(x);
            XQueryFilter y = (XQueryFilter)visitor.visit(ctx.f());
            qc.popContextElement();

            if(y == XQueryFilter.trueValue())
                res.add(x);
        }
        return res;
    }

    public XQueryList evalConcat(@NotNull  XQueryParser.RpConcatContext ctx) {
        //System.out.println("in aprp concat");
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);

        l.addAll(r);
        return l;
    }
}
