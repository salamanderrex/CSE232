package project1.xquery.visitor;

import org.antlr.v4.runtime.misc.NotNull;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import project1.xquery.parser.XQueryParser;
import project1.xquery.value.*;
import project1.xquery.parser.*;
import project1.xquery.xmlElement.*;
import project1.xquery.context.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MyXQueryEvaluator {

    public int readFileCounter;
    public XQueryList cachedXQueryList;

    public XQueryBaseVisitor<MyQueryElement> visitor;
    public QueryEnv qc;
    public MyXQueryEvaluator(XQueryBaseVisitor<MyQueryElement> visitor, QueryEnv qc) {

        this.visitor = visitor;
        this.qc = qc;
        readFileCounter = 0;
        cachedXQueryList = null;

    }

    public XQueryList evalAp(@NotNull XQueryParser.ApContext ctx) {
        Document doc=null;
        XMLElement freshRoot =null;

        if (readFileCounter <=1) {

            try {
                String filename = ctx.fileName.getText();
                System.out.println("to read the xml file ......" +filename);
                File inputFile = new File(System.getProperty("user.dir").toString() + "/" + filename.substring(1, filename.length() - 1));
                SAXReader reader = new SAXReader();
                doc = reader.read(inputFile);
                freshRoot = new XMLElement(doc.getRootElement());

            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        if (readFileCounter == 0) {
            XQueryList results = new XQueryList();
            qc.pushContextElement(freshRoot);

            switch (ctx.slash.getType()) {
                case XQueryParser.SLASH:
                    results.addAll((XQueryList) visitor.visit(ctx.rp()));
                    break;
                case XQueryParser.SSLASH:
                    qc.pushContextElement(freshRoot.descendants());
                    results.addAll((XQueryList) visitor.visit(ctx.rp()));
                    break;
                default:
                    System.out.println("Oops, shouldn't be here");
                    break;
            }
            readFileCounter++;
            return results.unique();
        }
        if (readFileCounter == 1) {
            XQueryList results = new XQueryList();


            qc.pushContextElement(freshRoot);

            switch (ctx.slash.getType()) {
                case XQueryParser.SLASH:
                    results.addAll((XQueryList) visitor.visit(ctx.rp()));
                    break;
                case XQueryParser.SSLASH:
                    qc.pushContextElement(freshRoot.descendants());
                    results.addAll((XQueryList) visitor.visit(ctx.rp()));
                    break;
                default:
                    System.out.println("Oops, shouldn't be here");
                    break;
            }
            readFileCounter++;
            cachedXQueryList = results.unique();
            return cachedXQueryList;

        }
        return cachedXQueryList;

    }

    public XQueryList evalTagName(@NotNull XQueryParser.RpTagNameContext ctx) {
        String tagName = ctx.getText();

        XQueryList x = evalWildCard().stream().filter(
                e -> e.tag().equalsIgnoreCase(tagName)
        ).collect(Collectors.toCollection(XQueryList::new));

        return x;
    }

    public XQueryList evalWildCard() {
        XQueryList res = new XQueryList();

        for (XMLElement context : qc.peekContextElement())
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
    }

    public XQueryList evalText() {

        List<XMLElement> values = new ArrayList<XMLElement>();
        Iterator elementIterator = qc.peekContextElement().iterator();
        while (elementIterator.hasNext()) {
            XMLElement tt = (XMLElement) elementIterator.next();
            XMLElement ntt = new XMLElement(tt.elem.getText());
            ntt.istext = 1;
            values.add(ntt);
        }
        XQueryList x = new XQueryList(values);
        return x;
    }

    public XQueryList evalAttr(XQueryParser.RpAttrContext ctx) {
        String attrib = ctx.Identifier().getSymbol().getText();
        XQueryList res = new XQueryList(qc.peekContextElement().size());
        if (attrib == null)
            return res;

        for (XMLElement x : qc.peekContextElement()) {
            if (x.attrib(attrib) != null)
                res.add(x.attrib(attrib));
        }
        return res;
    }

    public XQueryList evalParen(@NotNull XQueryParser.RpParenExprContext ctx) {
        return (XQueryList) visitor.visit(ctx.rp());
    }

    public XQueryList evalSlashes(@NotNull XQueryParser.RpSlashContext ctx) {
        XQueryList results = new XQueryList();
        switch (ctx.slash.getType()) {
            case XQueryParser.SLASH:
                results = evalRpSlash(ctx);
                break;
            case XQueryParser.SSLASH:
                results = evalRpSlashSlash(ctx);
                break;
            default:
                System.out.println("ERROR!!");
                break;
        }
        return results;
    }

    private XQueryList evalRpSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        XQueryList y = new XQueryList();
        XQueryList x = (XQueryList) visitor.visit(ctx.left);
        qc.pushContextElement(x);
        XQueryList context = (XQueryList) visitor.visit(ctx.right);
        y.addAll(context);
        qc.popContextElement();
        return y.unique();
    }

    private XQueryList evalRpSlashSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        XQueryList l = (XQueryList) visitor.visit(ctx.left);
        XQueryList descendants = new XQueryList();

        for (XMLElement x : l) {
            descendants.addAll(x.descendants());
        }

        qc.pushContextElement(descendants);
        XQueryList r = (XQueryList) visitor.visit(ctx.right);
        qc.popContextElement();

        return r.unique();
    }

    public XQueryList evalFilter(@NotNull XQueryParser.RpFilterContext ctx) {
        XQueryList res = new XQueryList();
        XQueryList xs = (XQueryList) visitor.visit(ctx.rp());
        for (XMLElement x : xs) {
            qc.pushContextElement(x);
            XQueryBoolean y = (XQueryBoolean) visitor.visit(ctx.f());
            qc.popContextElement();
            if (y.booleanFlag == true)
                res.add(x);
        }
        return res;
    }

    public XQueryList evalConcat(@NotNull XQueryParser.RpConcatContext ctx) {
        XQueryList l = (XQueryList) visitor.visit(ctx.left);
        XQueryList r = (XQueryList) visitor.visit(ctx.right);
        l.addAll(r);
        return l;
    }



    public XQueryBoolean evalIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx){
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsId(r);
    }
    public XQueryBoolean evalValEqual(@NotNull XQueryParser.CondValEqualContext ctx){
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsVal(r);
    }

    public XQueryBoolean evalEmpty(@NotNull XQueryParser.CondEmptyContext ctx){
        XQueryList res = (XQueryList)visitor.visit(ctx.xq());
        return res.empty();
    }

    public XQueryBoolean evalSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx){
        MyScope ve = qc.cloneVarEnv();

        for(int i = 0; i < ctx.xq().size(); i++) {
            XQueryList res = (XQueryList)visitor.visit(ctx.xq(i));
            ve.put(ctx.Var(i).getText(), res);
        }

        qc.pushVarEnv(ve);

        XQueryBoolean res = (XQueryBoolean)visitor.visit(ctx.cond());

        qc.popVarEnv();

        return res;
    }

    public XQueryBoolean evalParen(@NotNull XQueryParser.CondParenExprContext ctx){
        return (XQueryBoolean)visitor.visit(ctx.cond());
    }

    public XQueryBoolean evalAnd(@NotNull XQueryParser.CondAndContext ctx){
        XQueryBoolean l = (XQueryBoolean)visitor.visit(ctx.left);

        XQueryBoolean r = (XQueryBoolean)visitor.visit(ctx.right);

        return l.and(r);
    }

    public XQueryBoolean evalOr(@NotNull XQueryParser.CondOrContext ctx){
        XQueryBoolean l = (XQueryBoolean)visitor.visit(ctx.left);
        XQueryBoolean r = (XQueryBoolean)visitor.visit(ctx.right);
        return l.or(r);
    }

    public XQueryBoolean evalNot(@NotNull XQueryParser.CondNotContext ctx){
        XQueryBoolean res = (XQueryBoolean)visitor.visit(ctx.cond());
        return res.not();
    }

    public XQueryBoolean evalFRp(XQueryParser.FRpContext ctx) {
        XQueryList resultR = (XQueryList)visitor.visit(ctx.rp());
        if(resultR.size() > 0)
            return  XQueryBoolean.XQueryBooleanFactory(true);
        return XQueryBoolean.XQueryBooleanFactory(false);
    }

    public XQueryBoolean evalParen(XQueryParser.FParenContext ctx) {
        return (XQueryBoolean)visitor.visit(ctx.f());
    }

    public XQueryBoolean evalAnd(XQueryParser.FAndContext ctx) {
        XQueryBoolean l = (XQueryBoolean)visitor.visit(ctx.left);
        XQueryBoolean r = (XQueryBoolean)visitor.visit(ctx.right);
        return l.and(r);
    }

    public XQueryBoolean evalOr(XQueryParser.FOrContext ctx) {
        XQueryBoolean l = (XQueryBoolean)visitor.visit(ctx.left);
        XQueryBoolean r = (XQueryBoolean)visitor.visit(ctx.right);
        return l.or(r);
    }

    public XQueryBoolean evalNot(XQueryParser.FNotContext ctx) {
        XQueryBoolean v = (XQueryBoolean)visitor.visit(ctx.f());
        return v.not();
    }

    public XQueryBoolean evalValEqual(XQueryParser.FValEqualContext ctx) {
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsVal(r);
    }

    public XQueryBoolean evalIdEqual(XQueryParser.FIdEqualContext ctx) {
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsId(r);
    }

    public ScopeList evalFor(@NotNull XQueryParser.ForClauseContext ctx) {
        ScopeList varEnvs = new ScopeList();
        varEnvs.addAll(getVarEnvs(0, ctx, qc.cloneVarEnv()));
        return varEnvs;
    }

    private ScopeList getVarEnvs(int var, @NotNull XQueryParser.ForClauseContext ctx, MyScope previousVE) {
        ScopeList varEnvs = new ScopeList();
        XQueryList res = (XQueryList) visitor.visit(ctx.xq(var));
        if (var == ctx.xq().size() - 1) {
            for (XMLElement elem : res) {
                MyScope currentVE = new MyScope();
                currentVE.putAll(previousVE);
                currentVE.put(ctx.Var(var).getText(), new XQueryList(elem));
                varEnvs.add(currentVE);
            }
            return varEnvs;
        }
        for (XMLElement elem : res) {
            qc.pushContextElement(elem);
            MyScope currentVE = previousVE.copy();
            currentVE.put(ctx.Var(var).getText(), new XQueryList(elem));
            qc.pushVarEnv(currentVE);
            varEnvs.addAll(getVarEnvs(var + 1, ctx, currentVE));
            qc.popVarEnv();
            qc.popContextElement();
        }
        return varEnvs;
    }
    public MyScope evalLet(@NotNull XQueryParser.LetClauseContext ctx) {
        MyScope ve = qc.cloneVarEnv();
        if (qc.letFilter==null) {
            for (int i = 0; i < ctx.xq().size(); i++) {
                XQueryList res = (XQueryList) visitor.visit(ctx.xq(i));
                ve.put(ctx.Var(i).getText(), res);
                qc.pushVarEnv(ve);
            }

        } else {
            for (int i = 0; i < ctx.xq().size(); i++) {
                XQueryList res = (XQueryList) visitor.visit(ctx.xq(i));
                if (!qc.letFilter[i]) {
                    ve.put(ctx.Var(i).getText(), res);
                    qc.pushVarEnv(ve);
                }

            }
        }


        return ve;
    }

    public XQueryBoolean evalWhere(@NotNull XQueryParser.WhereClauseContext ctx) {
        qc.inwhere = true;
        XQueryBoolean ans = (XQueryBoolean) visitor.visit(ctx.cond());
        qc.inwhere = false;
        return ans;
    }

    public XQueryList evalReturn(@NotNull XQueryParser.ReturnClauseContext ctx) {

        return (XQueryList) visitor.visit(ctx.xq());
    }

    public XQueryList evalStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx) {

        // it will become < CASExxx> remove them
        String text = ctx.StringLiteral().getText();
        XMLElement t = new XMLElement(text.substring(1, text.length() - 1));
        t.isconstantstring = 1;
        return new XQueryList(t);
    }

    public XQueryList evalAp(@NotNull XQueryParser.XqApContext ctx) {
        return (XQueryList) visitor.visit(ctx.ap());
    }

    public XQueryList evalParen(@NotNull XQueryParser.XqParenExprContext ctx) {
        return (XQueryList) visitor.visit(ctx.xq());
    }


    public XQueryList evalVar(@NotNull XQueryParser.XqVarContext ctx) {

        XQueryList ans = qc.getVar(ctx.getText());
        List<String> WhereVar2 = qc.getWhereVar2();
        if (qc.inwhere) {
            if (!WhereVar2.contains(ctx.getText()))
                WhereVar2.add(ctx.getText());

        }
        return ans;

    }

    public XQueryList evalConcat(@NotNull XQueryParser.XqConcatContext ctx) {
        //qc.openScope();
        XQueryList l = (XQueryList) visitor.visit(ctx.left);
        //qc.closeScope();

        //qc.openScope();
        XQueryList r = (XQueryList) visitor.visit(ctx.right);
        //qc.closeScope();

        l.addAll(r);
        return l;
    }

    private XQueryList evalXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        XQueryList xq = (XQueryList) visitor.visit(ctx.xq());

        qc.pushContextElement(xq);
        XQueryList rp = (XQueryList) visitor.visit(ctx.rp());
        qc.popContextElement();

        return rp.unique();
    }

    private XQueryList evalXqSlashSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        XQueryList l = (XQueryList) visitor.visit(ctx.xq());
        XQueryList descendants = new XQueryList();

        for (XMLElement x : l) {
            descendants.addAll(x.descendants());
        }

        qc.pushContextElement(descendants);

        XQueryList r = (XQueryList) visitor.visit(ctx.rp());

        qc.popContextElement();

        return r.unique();
    }

    public XQueryList evalSlashes(@NotNull XQueryParser.XqSlashContext ctx) {
        XQueryList results = new XQueryList();
        switch (ctx.slash.getType()) {
            case XQueryParser.SLASH:
                results = evalXqSlash(ctx);
                break;
            case XQueryParser.SSLASH:
                results = evalXqSlashSlash(ctx);
                break;
            default:
                System.out.println("ERROR!!");
                break;
        }
        return results;
    }

    public XQueryList evalTagname(@NotNull XQueryParser.XqTagNameContext ctx) {
        if (!ctx.open.getText().equals(ctx.close.getText()))
            System.out.println(ctx.open.getText() + "is not closed properly. You closed it with " + ctx.close.getText());

        XQueryList xq = (XQueryList) visitor.visit(ctx.xq());
        XMLElement res = new XMLElement(ctx.open.getText());

        for (XMLElement v : xq) {
            if (v.isConstantStr())
                res.add( v);
            else
                res.add( v);
        }

        return new XQueryList(res);
    }

    public void Plusone(int[] base, int[] next) {

        for (int i = next.length - 1; i >= 0; i--) {
            if (next[i] < base[i]){
                if (next[i] + 1 == base[i]){
                    next[i] = 0;
                }else{
                    next[i]++;
                    break;
                }
            }

        }

    }

    public XQueryList evalFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        ScopeList veFor = (ScopeList) visitor.visit(ctx.forClause());

        XQueryList res = new XQueryList();
        for (MyScope ve : veFor) {
            qc.pushVarEnv(ve);
            if (ctx.letClause() != null) {
                MyScope letEnv = (MyScope) visitor.visit(ctx.letClause());
                qc.pushVarEnv(letEnv);
            }

            if (ctx.whereClause() != null) {




                if (ctx.letClause() != null) {

                    visitor.visit(ctx.whereClause());
                    List<String> wherevar = qc.getWhereVar2();

                    int[] whereList = new int[wherevar.size()];

                    for (int i = 0; i < whereList.length; i++) {
                        whereList[i] = qc.getVar(wherevar.get(i)).size();
                    }

                    int[] counter = new int[wherevar.size()];
                    int total = 1;
                    for (int i = 0; i < whereList.length; i++) {
                        total = total * whereList[i];
                    }
                    for (int i = 0; i < total; i++) {
                        MyScope v = qc.cloneVarEnv();
                        for (int j = 0; j < counter.length; j++) {
                            String varName = wherevar.get(j);
                            XMLElement e2 = v.get(wherevar.get(j)).get(counter[j]);
                            XQueryList xqList = new XQueryList(e2);
                            v.put(varName, xqList);
                        }
                        qc.pushVarEnv(v);
                        if ( ((XQueryBoolean)(visitor.visit(ctx.whereClause()))).booleanFlag) {
                            res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
                        }
                        qc.popVarEnv();
                        Plusone(whereList,counter);
                    }


                } else {
                    if  ( ((XQueryBoolean)(visitor.visit(ctx.whereClause()))).booleanFlag)
                        res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
                }

            } else
                res.addAll((XQueryList) visitor.visit(ctx.returnClause()));

            qc.popVarEnv();

            if (ctx.letClause() != null)
                for (int i = 0; i < ctx.letClause().xq().size(); i++) {
                    qc.popVarEnv();
                }

        }
        qc.whereVar2 = new ArrayList<String>();
        return res;
    }

    public XQueryList evalLet(@NotNull XQueryParser.XqLetContext ctx) {

        MyScope ve = (MyScope) visitor.visit(ctx.letClause());

        qc.pushVarEnv(ve);

        XQueryList res = (XQueryList) visitor.visit(ctx.xq());

        qc.popVarEnv();

        return res;
    }

    public MyQueryElement evalJoin(XQueryParser.JoinClauseContext ctx) {

        XQueryList list1 = (XQueryList) visitor.visit(ctx.xq1);
        XQueryList list2 = (XQueryList) visitor.visit(ctx.xq2);

        XQueryList res = new XQueryList();


        String idl1 = ctx.IdentifierList(0).getText();
        String idl2 = ctx.IdentifierList(1).getText();
        List<String> joinVars1 = Arrays.asList(idl1.substring(1, idl1.length() - 1).split(","));
        List<String> joinVars2 = Arrays.asList(idl2.substring(1, idl2.length() - 1).split(","));

        for (XMLElement elem1 : list1)
            for (XMLElement elem2 : list2) {
                boolean join = true;
                for (int i = 0; i < joinVars1.size(); i++) {
                    XQueryList list1Elems = elem1.getChildByTag(joinVars1.get(i));
                    XQueryList list2Elems = elem2.getChildByTag(joinVars2.get(i));
                    for (XMLElement listElem1 : list1Elems)
                        for (XMLElement listElem2 : list2Elems) {
                            if (!listElem1.childrenEquals(listElem2)) {
                                join = false;
                            }
                        }
                }
                if (join) {
                    XMLElement tuple = new XMLElement("tuple");
                    tuple.addAll(elem1.children());
                    tuple.addAll(elem2.children());
                    res.add(tuple);
                }
            }
        return res;
    }

}
