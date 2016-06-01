package project1.xquery;

import org.antlr.v4.runtime.misc.NotNull;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import project1.xquery.parser.XQueryParser;
import project1.xquery.*;
import project1.xquery.parser.*;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MyXQueryEvaluator {

    public int readFileCounter;
    public NodeTextList cachedXQueryList;

    public XQueryBaseVisitor<MyQueryElement> visitor;
    public QueryEnv qc;
    public MyXQueryEvaluator(XQueryBaseVisitor<MyQueryElement> visitor, QueryEnv qc) {

        this.visitor = visitor;
        this.qc = qc;
        readFileCounter = 0;
        cachedXQueryList = null;

    }

    public NodeTextList evalAp(@NotNull XQueryParser.ApContext ctx) {
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
            NodeTextList results = new NodeTextList();
            qc.pushContextElement(freshRoot);

            switch (ctx.slash.getType()) {
                case XQueryParser.SLASH:
                    results.addAll((NodeTextList) visitor.visit(ctx.rp()));
                    break;
                case XQueryParser.SSLASH:
                    qc.pushContextElement(freshRoot.descendants());
                    results.addAll((NodeTextList) visitor.visit(ctx.rp()));
                    break;
                default:
                    System.out.println("Oops, shouldn't be here");
                    break;
            }
            readFileCounter++;
            return results.unique();
        }
        if (readFileCounter == 1) {
            NodeTextList results = new NodeTextList();


            qc.pushContextElement(freshRoot);

            switch (ctx.slash.getType()) {
                case XQueryParser.SLASH:
                    results.addAll((NodeTextList) visitor.visit(ctx.rp()));
                    break;
                case XQueryParser.SSLASH:
                    qc.pushContextElement(freshRoot.descendants());
                    results.addAll((NodeTextList) visitor.visit(ctx.rp()));
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

    public NodeTextList evalTagName(@NotNull XQueryParser.RpTagNameContext ctx) {
        String tagName = ctx.getText();

        NodeTextList x = evalWildCard().stream().filter(
                e -> e.tag().equalsIgnoreCase(tagName)
        ).collect(Collectors.toCollection(NodeTextList::new));

        return x;
    }

    public NodeTextList evalWildCard() {
        NodeTextList res = new NodeTextList();

        for (XMLElement context : qc.peekContextElement())
            res.addAll(context.children());
        return res;
    }

    public NodeTextList evalDot() {
        return qc.peekContextElement();
    }

    public NodeTextList evalDotDot() {
        return qc.peekContextElement().stream().map(
                e -> e.parent().get(0)
        ).collect(Collectors.toCollection(NodeTextList::new)).unique();
    }

    public NodeTextList evalText() {

        List<XMLElement> values = new ArrayList<XMLElement>();
        Iterator elementIterator = qc.peekContextElement().iterator();
        while (elementIterator.hasNext()) {
            XMLElement tt = (XMLElement) elementIterator.next();
            XMLElement ntt = new XMLElement(tt.elem.getText());
            ntt.istext = 1;
            values.add(ntt);
        }
        NodeTextList x = new NodeTextList(values);
        return x;
    }

    public NodeTextList evalAttr(XQueryParser.RpAttrContext ctx) {
        String attrib = ctx.Identifier().getSymbol().getText();
        NodeTextList res = new NodeTextList(qc.peekContextElement().size());
        if (attrib == null)
            return res;

        for (XMLElement x : qc.peekContextElement()) {
            if (x.attrib(attrib) != null)
                res.add(x.attrib(attrib));
        }
        return res;
    }

    public NodeTextList evalParen(@NotNull XQueryParser.RpParenExprContext ctx) {
        return (NodeTextList) visitor.visit(ctx.rp());
    }

    public NodeTextList evalSlashes(@NotNull XQueryParser.RpSlashContext ctx) {
        NodeTextList results = new NodeTextList();
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

    private NodeTextList evalRpSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        NodeTextList y = new NodeTextList();
        NodeTextList x = (NodeTextList) visitor.visit(ctx.left);
        qc.pushContextElement(x);
        NodeTextList context = (NodeTextList) visitor.visit(ctx.right);
        y.addAll(context);
        qc.popContextElement();
        return y.unique();
    }

    private NodeTextList evalRpSlashSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        NodeTextList l = (NodeTextList) visitor.visit(ctx.left);
        NodeTextList descendants = new NodeTextList();

        for (XMLElement x : l) {
            descendants.addAll(x.descendants());
        }

        qc.pushContextElement(descendants);
        NodeTextList r = (NodeTextList) visitor.visit(ctx.right);
        qc.popContextElement();

        return r.unique();
    }

    public NodeTextList evalFilter(@NotNull XQueryParser.RpFilterContext ctx) {
        NodeTextList res = new NodeTextList();
        NodeTextList xs = (NodeTextList) visitor.visit(ctx.rp());
        for (XMLElement x : xs) {
            qc.pushContextElement(x);
            XQueryBoolean y = (XQueryBoolean) visitor.visit(ctx.f());
            qc.popContextElement();
            if (y.booleanFlag == true)
                res.add(x);
        }
        return res;
    }

    public NodeTextList evalConcat(@NotNull XQueryParser.RpConcatContext ctx) {
        NodeTextList l = (NodeTextList) visitor.visit(ctx.left);
        NodeTextList r = (NodeTextList) visitor.visit(ctx.right);
        l.addAll(r);
        return l;
    }



    public XQueryBoolean evalIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx){
        NodeTextList l = (NodeTextList)visitor.visit(ctx.left);
        NodeTextList r = (NodeTextList)visitor.visit(ctx.right);
        return l.equalsId(r);
    }
    public XQueryBoolean evalValEqual(@NotNull XQueryParser.CondValEqualContext ctx){
        NodeTextList l = (NodeTextList)visitor.visit(ctx.left);
        NodeTextList r = (NodeTextList)visitor.visit(ctx.right);
        return l.equalsVal(r);
    }

    public XQueryBoolean evalEmpty(@NotNull XQueryParser.CondEmptyContext ctx){
        NodeTextList res = (NodeTextList)visitor.visit(ctx.xq());
        return res.empty();
    }

    public XQueryBoolean evalSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx){
        MyScope ve = qc.cloneVarEnv();

        for(int i = 0; i < ctx.xq().size(); i++) {
            NodeTextList res = (NodeTextList)visitor.visit(ctx.xq(i));
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
        NodeTextList resultR = (NodeTextList)visitor.visit(ctx.rp());
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
        NodeTextList l = (NodeTextList)visitor.visit(ctx.left);
        NodeTextList r = (NodeTextList)visitor.visit(ctx.right);
        return l.equalsVal(r);
    }

    public XQueryBoolean evalIdEqual(XQueryParser.FIdEqualContext ctx) {
        NodeTextList l = (NodeTextList)visitor.visit(ctx.left);
        NodeTextList r = (NodeTextList)visitor.visit(ctx.right);
        return l.equalsId(r);
    }

    public ScopeList evalFor(@NotNull XQueryParser.ForClauseContext ctx) {
        ScopeList varEnvs = new ScopeList();
        varEnvs.addAll(getVarEnvs(0, ctx, qc.cloneVarEnv()));
        return varEnvs;
    }

    private ScopeList getVarEnvs(int var, @NotNull XQueryParser.ForClauseContext ctx, MyScope previousVE) {
        ScopeList varEnvs = new ScopeList();
        NodeTextList res = (NodeTextList) visitor.visit(ctx.xq(var));
        if (var == ctx.xq().size() - 1) {
            for (XMLElement elem : res) {
                MyScope currentVE = new MyScope();
                currentVE.putAll(previousVE);
                currentVE.put(ctx.Var(var).getText(), new NodeTextList(elem));
                varEnvs.add(currentVE);
            }
            return varEnvs;
        }
        for (XMLElement elem : res) {
            qc.pushContextElement(elem);
            MyScope currentVE = previousVE.copy();
            currentVE.put(ctx.Var(var).getText(), new NodeTextList(elem));
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
                NodeTextList res = (NodeTextList) visitor.visit(ctx.xq(i));
                ve.put(ctx.Var(i).getText(), res);
                qc.pushVarEnv(ve);
            }

        } else {
            for (int i = 0; i < ctx.xq().size(); i++) {
                NodeTextList res = (NodeTextList) visitor.visit(ctx.xq(i));
                if (!qc.letFilter[i]) {
                    ve.put(ctx.Var(i).getText(), res);
                    qc.pushVarEnv(ve);
                }

            }
        }


        return ve;
    }

    public XQueryBoolean evalWhere(@NotNull XQueryParser.WhereClauseContext ctx) {
        //qc.inwhere = true;
        XQueryBoolean ans = (XQueryBoolean) visitor.visit(ctx.cond());
        qc.inwhere = false;
        return ans;
    }

    public NodeTextList evalReturn(@NotNull XQueryParser.ReturnClauseContext ctx) {

        return (NodeTextList) visitor.visit(ctx.xq());
    }

    public NodeTextList evalStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx) {

        String text = ctx.StringLiteral().getText();
        XMLElement t = new XMLElement(text.substring(1, text.length() - 1));
        t.isconstantstring = 1;
        return new NodeTextList(t);
    }

    public NodeTextList evalAp(@NotNull XQueryParser.XqApContext ctx) {
        return (NodeTextList) visitor.visit(ctx.ap());
    }

    public NodeTextList evalParen(@NotNull XQueryParser.XqParenExprContext ctx) {
        return (NodeTextList) visitor.visit(ctx.xq());
    }


    public NodeTextList evalVar(@NotNull XQueryParser.XqVarContext ctx) {

        NodeTextList ans = qc.getVar(ctx.getText());
        List<String> WhereVar2 = qc.getWhereVar2();
        if (qc.inwhere) {
            if (!WhereVar2.contains(ctx.getText()))
                WhereVar2.add(ctx.getText());

        }
        return ans;

    }

    public NodeTextList evalConcat(@NotNull XQueryParser.XqConcatContext ctx) {
        //qc.openScope();
        NodeTextList l = (NodeTextList) visitor.visit(ctx.left);
        //qc.closeScope();

        //qc.openScope();
        NodeTextList r = (NodeTextList) visitor.visit(ctx.right);
        //qc.closeScope();

        //make a deep copy
        NodeTextList temp = new NodeTextList(l.values.size());
        for (int i =0 ; i < l.values.size();i++) {
            temp.values.add(l.values.get(i));
        }
        //l.addAll(r);
        temp.addAll(r);
        return temp;
    }

    private NodeTextList evalXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        NodeTextList xq = (NodeTextList) visitor.visit(ctx.xq());

        qc.pushContextElement(xq);
        NodeTextList rp = (NodeTextList) visitor.visit(ctx.rp());
        qc.popContextElement();

        return rp.unique();
    }

    private NodeTextList evalXqSlashSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        NodeTextList l = (NodeTextList) visitor.visit(ctx.xq());
        NodeTextList descendants = new NodeTextList();

        for (XMLElement x : l) {
            descendants.addAll(x.descendants());
        }

        qc.pushContextElement(descendants);

        NodeTextList r = (NodeTextList) visitor.visit(ctx.rp());

        qc.popContextElement();

        return r.unique();
    }

    public NodeTextList evalSlashes(@NotNull XQueryParser.XqSlashContext ctx) {
        NodeTextList results = new NodeTextList();
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

    public NodeTextList evalTagname(@NotNull XQueryParser.XqTagNameContext ctx) {
        if (!ctx.open.getText().equals(ctx.close.getText()))
            System.out.println(ctx.open.getText() + "is not closed properly. You closed it with " + ctx.close.getText());

        NodeTextList xq = (NodeTextList) visitor.visit(ctx.xq());
        XMLElement res = new XMLElement(ctx.open.getText());

        for (XMLElement v : xq) {
            if (v.isConstantStr())
                res.add( v);
            else
                res.add( v);
        }

        return new NodeTextList(res);
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

    public NodeTextList evalFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        ScopeList veFor = (ScopeList) visitor.visit(ctx.forClause());

        NodeTextList res = new NodeTextList();
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
                            NodeTextList xqList = new NodeTextList(e2);
                            v.put(varName, xqList);
                        }
                        qc.pushVarEnv(v);
                        if ( ((XQueryBoolean)(visitor.visit(ctx.whereClause()))).booleanFlag) {
                            res.addAll((NodeTextList) visitor.visit(ctx.returnClause()));
                        }
                        qc.popVarEnv();
                        Plusone(whereList,counter);
                    }


                } else {
                    if  ( ((XQueryBoolean)(visitor.visit(ctx.whereClause()))).booleanFlag)
                        res.addAll((NodeTextList) visitor.visit(ctx.returnClause()));
                }

            } else
                res.addAll((NodeTextList) visitor.visit(ctx.returnClause()));

            qc.popVarEnv();

            if (ctx.letClause() != null)
                for (int i = 0; i < ctx.letClause().xq().size(); i++) {
                    qc.popVarEnv();
                }

        }
        qc.whereVar2 = new ArrayList<String>();
        return res;
    }

    public NodeTextList evalLet(@NotNull XQueryParser.XqLetContext ctx) {

        MyScope ve = (MyScope) visitor.visit(ctx.letClause());

        qc.pushVarEnv(ve);

        NodeTextList res = (NodeTextList) visitor.visit(ctx.xq());

        qc.popVarEnv();

        return res;
    }

    public MyQueryElement evalJoin(XQueryParser.JoinClauseContext ctx) {

        NodeTextList list1 = (NodeTextList) visitor.visit(ctx.xq1);
        NodeTextList list2 = (NodeTextList) visitor.visit(ctx.xq2);

        NodeTextList res = new NodeTextList();


        String idl1 = ctx.IdentifierList(0).getText();
        String idl2 = ctx.IdentifierList(1).getText();
        List<String> joinVars1 = Arrays.asList(idl1.substring(1, idl1.length() - 1).split(","));
        List<String> joinVars2 = Arrays.asList(idl2.substring(1, idl2.length() - 1).split(","));

        //use hash join
        //build
        /*
        HashMap <XMLElement,HashMap <Integer,NodeTextList>> map1 = new HashMap<>();

        for (XMLElement elem1 : list1) {
            HashMap <Integer,NodeTextList> temp = new HashMap<>();
            for (int i = 0 ; i < joinVars1.size();i++) {
                temp.put(i,elem1.getChildByTag(joinVars1.get(i)));
            }
            map1.put(elem1,temp);
        }

        //probe
        for (XMLElement elem1 : list1) {
            for (XMLElement elem2 : list2) {
                boolean join = true;
                for (int i = 0; i < joinVars1.size(); i++) {
                   // NodeTextList list1Elems = elem1.getChildByTag(joinVars1.get(i));
                    NodeTextList list2Elems = elem2.getChildByTag(joinVars2.get(i));
                    NodeTextList list1Elems = map1.get(elem1).get(i);
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
        }
*/

        for (XMLElement elem1 : list1)
            for (XMLElement elem2 : list2) {
                boolean join = true;
                for (int i = 0; i < joinVars1.size(); i++) {
                    NodeTextList list1Elems = elem1.getChildByTag(joinVars1.get(i));
                    NodeTextList list2Elems = elem2.getChildByTag(joinVars2.get(i));
                    for (XMLElement listElem1 : list1Elems)
                        for (XMLElement listElem2 : list2Elems) {
                            if (!listElem1.childrenEquals(listElem2)) {
                                join = false;
                                break;
                            }
                        }
                    if(!join) {
                        break;
                    }
                }


                if (join) {
                    XMLElement tuple = new XMLElement("tuple");
                    tuple.addAll(elem1.children());

                    //tuple.addAll(elem2.children());
                    res.add(tuple);
                }
            }


        return res;
    }


}
