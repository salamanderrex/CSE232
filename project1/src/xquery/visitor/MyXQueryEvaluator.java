//package project1.xquery.visitor;
//import org.antlr.v4.runtime.misc.NotNull;
//
//import project1.xquery.parser.XQueryParser;
//import project1.xquery.value.*;
//import project1.xquery.parser.*;
//import project1.xquery.xmltree.*;
//import project1.xquery.context.*;
//import project1.utils.*;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.Arrays;
//
///**
// * Created by kezhang on 5/5/16.
// */
//public class MyXQueryEvaluator extends XQueryEvaluator{
//
//    public int readFileCounter;
//    public XQueryList cachedXQueryList;
//    public MyXQueryEvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc){
//        super(visitor, qc);
//        readFileCounter = 0;
//        cachedXQueryList= null;
//    }
//    public XQueryList evalAp(@NotNull  XQueryParser.ApContext ctx) {
//        // System.out.println("start ap xml document");
//        if (readFileCounter ==0) {
//            XMLDocument document = new XMLDocument(ctx.fileName.getText());
//            XQueryList results = new XQueryList();
//
//            qc.pushContextElement(document.root());
//
//            switch(ctx.slash.getType()) {
//                case XQueryParser.SLASH:
//                    results.addAll((XQueryList)visitor.visit(ctx.rp()));
//                    break;
//                case XQueryParser.SSLASH:
//                    qc.pushContextElement(document.root().descendants());
//                    results.addAll( (XQueryList)visitor.visit(ctx.rp()) );
//                    break;
//                default:
//                    Debugger.error("Oops, shouldn't be here");
//                    break;
//            }
//            readFileCounter++;
//            return results.unique();
//        }
//
//        if (readFileCounter ==1) {
//
//            XMLDocument document = new XMLDocument(ctx.fileName.getText());
//            XQueryList results = new XQueryList();
//
//        /* Replace the below code with this commented out code
//         * in case documents roots have to excplictly be referenced
//         * e.g. doc("j_caesar.xml")/PLAY/TITLE/text() instead of
//         *      doc("j_caesar.xml")/TITLE/text() which is the current setting
//        XMLElement root = new XMLElement("root");
//        root.add(document.root());
//        qc.pushContextElement(root);
//        */
//
//            qc.pushContextElement(document.root());
//
//            switch(ctx.slash.getType()) {
//                case XQueryParser.SLASH:
//                    results.addAll((XQueryList)visitor.visit(ctx.rp()));
//                    break;
//                case XQueryParser.SSLASH:
//                    qc.pushContextElement(document.root().descendants());
//                    results.addAll( (XQueryList)visitor.visit(ctx.rp()) );
//                    break;
//                default:
//                    Debugger.error("Oops, shouldn't be here");
//                    break;
//            }
//            readFileCounter++;
//            cachedXQueryList = results.unique();
//            return cachedXQueryList;
//
//        }
//        return cachedXQueryList;
//
//    }
//
//    public XQueryList evalTagName(@NotNull  XQueryParser.RpTagNameContext ctx) {
//        String tagName = ctx.getText();
//        // System.out.println("in evalTagName tagName: "+tagName);
///*
//        XQueryList  context = qc.peekContextElement();
//        XQueryList  context2 = evalWildCard();
//        */
//        XQueryList x =  evalWildCard().stream().filter(
//                e -> e.tag().equalsIgnoreCase(tagName)
//        ).collect(Collectors.toCollection(XQueryList::new));
//
//        /*
//        if (tagName.equals("title")) {
//            System.out.println(x.toArray().toString());
//        }
//*/
//        return x;
//    }
//
//    public XQueryList evalWildCard() {
//        XQueryList res = new XQueryList();
//
//        for(IXMLElement context : qc.peekContextElement())
//            res.addAll(context.children());
//        return res;
//    }
//
//    public XQueryList evalDot() {
//        return qc.peekContextElement();
//    }
//
//    public XQueryList evalDotDot() {
//        return qc.peekContextElement().stream().map(
//                e -> e.parent().get(0)
//        ).collect(Collectors.toCollection(XQueryList::new)).unique();
//        /* ^^^ is equal to vvv
//        XQueryList res = new XQueryList();
//        for(IXMLElement e : qc.peekContextElement()) {
//            res.add(e.parent().get(0));
//        }
//        return res.unique();
//        */
//    }
//
//    public XQueryList evalText() {
//        //System.out.println("nc is ctxElems " );
//        // System.out.println(qc.nc.ctxElems );
//        /*
//        System.out.println("st var env is " );
////        Iterator <VarEnvironment> it = qc.st.varEnv.iterator();
////        if(it.hasNext()){
////
////            System.out.print(it.next().varEnv.keySet());
////        }
//
//*/
//        XQueryList x =qc.peekContextElement().stream().map(
//                IXMLElement::txt).collect(Collectors.toCollection(XQueryList::new));
//        // System.out.println(x.values);
//        return x;
//        /* ^^^is equal to vvv*/
//        /*
//        XQueryList res = new XQueryList();
//        for(IXMLElement x : qc.peekContextElement()) {
//            System.out.println(x.txt());
//            res.add(x.txt());
//        }
//        return res;
//        */
//
//    }
//
//    public XQueryList evalAttr(XQueryParser.RpAttrContext ctx) {
//        // TODO: Don't return a list value here, return an attribute value... or something!
//        String attrib = ctx.Identifier().getSymbol().getText();
//        XQueryList res = new XQueryList(qc.peekContextElement().size());
//
//        // If @ followed by nothing
//        if(attrib == null)
//            return res;
//
//        for(IXMLElement x : qc.peekContextElement()) {
//            if(x.attrib(attrib) != null)
//                res.add(x.attrib(attrib));
//        }
//        return res;
//    }
//
//    public XQueryList evalParen(@NotNull  XQueryParser.RpParenExprContext ctx) {
//        return (XQueryList)visitor.visit(ctx.rp());
//    }
//
//    public XQueryList evalSlashes(@NotNull  XQueryParser.RpSlashContext ctx) {
//        XQueryList results = new XQueryList();
//        switch(ctx.slash.getType()) {
//            case XQueryParser.SLASH:
//                results = evalRpSlash(ctx);
//                break;
//            case XQueryParser.SSLASH:
//                results = evalRpSlashSlash(ctx);
//                break;
//            default:
//                Debugger.error("Oops, shouldn't be here");
//                break;
//        }
//        return results;
//    }
//
//    private XQueryList evalRpSlash(@NotNull  XQueryParser.RpSlashContext ctx) {
//        // System.out.println("in evalRpSlash ");
//        XQueryList y = new XQueryList();
//        XQueryList x = (XQueryList)visitor.visit(ctx.left);
//
//        qc.pushContextElement(x);
//        XQueryList context = (XQueryList)visitor.visit(ctx.right);
//        y.addAll(context);
//
//        qc.popContextElement();
//        return y.unique();
//    }
//
//    private XQueryList evalRpSlashSlash(@NotNull  XQueryParser.RpSlashContext ctx) {
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        XQueryList descendants = new XQueryList();
//
//        for(IXMLElement x : l) {
//            descendants.addAll(x.descendants());
//        }
//
//        qc.pushContextElement(descendants);
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//        qc.popContextElement();
//
//        return r.unique();
//    }
//
//    public XQueryList evalFilter(@NotNull  XQueryParser.RpFilterContext ctx) {
//        XQueryList res = new XQueryList();
//
//        // Evaluate rp to get x
//        XQueryList xs = (XQueryList)visitor.visit(ctx.rp());
//
//        for(IXMLElement x : xs) {
//            qc.pushContextElement(x);
//            XQueryFilter y = (XQueryFilter)visitor.visit(ctx.f());
//            qc.popContextElement();
//
//            if(y == XQueryFilter.trueValue())
//                res.add(x);
//        }
//        return res;
//    }
//
//    public XQueryList evalConcat(@NotNull  XQueryParser.RpConcatContext ctx) {
//        //System.out.println("in aprp concat");
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//
//        l.addAll(r);
//        return l;
//    }
//    public XQueryFilter evalIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx){
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//        return l.equalsId(r);
//    }
//    public XQueryFilter evalValEqual(@NotNull XQueryParser.CondValEqualContext ctx){
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//        return l.equalsVal(r);
//    }
//
//    public XQueryFilter evalEmpty(@NotNull XQueryParser.CondEmptyContext ctx){
//        XQueryList res = (XQueryList)visitor.visit(ctx.xq());
//        return res.empty();
//    }
//
//    public XQueryFilter evalSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx){
//        VarEnvironment ve = qc.cloneVarEnv();
//
//        for(int i = 0; i < ctx.xq().size(); i++) {
//            XQueryList res = (XQueryList)visitor.visit(ctx.xq(i));
//            ve.put(ctx.Var(i).getText(), res);
//        }
//
//        qc.pushVarEnv(ve);
//
//        XQueryFilter res = (XQueryFilter)visitor.visit(ctx.cond());
//
//        qc.popVarEnv();
//
//        return res;
//    }
//
//    public XQueryFilter evalParen(@NotNull XQueryParser.CondParenExprContext ctx){
//        return (XQueryFilter)visitor.visit(ctx.cond());
//    }
//
//    public XQueryFilter evalAnd(@NotNull XQueryParser.CondAndContext ctx){
//        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
//        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
//        return l.and(r);
//    }
//
//    public XQueryFilter evalOr(@NotNull XQueryParser.CondOrContext ctx){
//        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
//        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
//        return l.or(r);
//    }
//
//    public XQueryFilter evalNot(@NotNull XQueryParser.CondNotContext ctx){
//        XQueryFilter res = (XQueryFilter)visitor.visit(ctx.cond());
//        return res.not();
//    }
//    public XQueryFilter evalFRp(XQueryParser.FRpContext ctx) {
//        XQueryList resultR = (XQueryList)visitor.visit(ctx.rp());
//        if(resultR.size() > 0)
//            return XQueryFilter.trueValue();
//        return XQueryFilter.falseValue();
//    }
//
//    public XQueryFilter evalParen(XQueryParser.FParenContext ctx) {
//        return (XQueryFilter)visitor.visit(ctx.f());
//    }
//
//    public XQueryFilter evalAnd(XQueryParser.FAndContext ctx) {
//        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
//        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
//        return l.and(r);
//    }
//
//    public XQueryFilter evalOr(XQueryParser.FOrContext ctx) {
//        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
//        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
//        return l.or(r);
//    }
//
//    public XQueryFilter evalNot(XQueryParser.FNotContext ctx) {
//        XQueryFilter v = (XQueryFilter)visitor.visit(ctx.f());
//        return v.not();
//    }
//
//    public XQueryFilter evalValEqual(XQueryParser.FValEqualContext ctx) {
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//        return l.equalsVal(r);
//    }
//
//    public XQueryFilter evalIdEqual(XQueryParser.FIdEqualContext ctx) {
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//        return l.equalsId(r);
//    }
//    public VarEnvironmentList evalFor(@NotNull XQueryParser.ForClauseContext ctx){
//        VarEnvironmentList varEnvs = new VarEnvironmentList();
//        varEnvs.addAll(getVarEnvs(0, ctx, qc.cloneVarEnv()));
//        return varEnvs;
//    }
//
//    private VarEnvironmentList getVarEnvs (int var, @NotNull XQueryParser.ForClauseContext ctx, VarEnvironment previousVE){
//        VarEnvironmentList varEnvs = new VarEnvironmentList();
//        XQueryList res = (XQueryList)visitor.visit(ctx.xq(var));
//        if(var == ctx.xq().size() - 1){
//            for(IXMLElement elem : res){
//                VarEnvironment currentVE = new VarEnvironment();
//                currentVE.putAll(previousVE);
//                currentVE.put(ctx.Var(var).getText(), new XQueryList(elem));
//                varEnvs.add(currentVE);
//            }
//            return varEnvs;
//        }
//        for (IXMLElement elem : res){
//            qc.pushContextElement(elem);
//            VarEnvironment currentVE = previousVE.copy();
//            currentVE.put(ctx.Var(var).getText(), new XQueryList(elem));
//            qc.pushVarEnv(currentVE);
//            varEnvs.addAll(getVarEnvs(var + 1, ctx, currentVE));
//            qc.popVarEnv();
//            qc.popContextElement();
//        }
//        return varEnvs;
//    }
//
//    public VarEnvironment evalLet(@NotNull XQueryParser.LetClauseContext ctx){
//        VarEnvironment ve = qc.cloneVarEnv();
//
//        for(int i = 0; i < ctx.xq().size(); i++) {
//            XQueryList res = (XQueryList)visitor.visit(ctx.xq(i));
//            ve.put(ctx.Var(i).getText(), res);
//        }
//        return ve;
//    }
//
//    public XQueryFilter evalWhere(@NotNull XQueryParser.WhereClauseContext ctx){
//        return (XQueryFilter)visitor.visit(ctx.cond());
//    }
//
//    public XQueryList evalReturn(@NotNull XQueryParser.ReturnClauseContext ctx) {
//        return (XQueryList)visitor.visit(ctx.xq());
//    }
//    public XQueryList evalStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx){
//        return new XQueryList(new XMLText(ctx.StringLiteral().getText()));
//    }
//
//    public XQueryList evalAp(@NotNull XQueryParser.XqApContext ctx) {
//        return (XQueryList)visitor.visit(ctx.ap());
//    }
//
//    public XQueryList evalParen(@NotNull XQueryParser.XqParenExprContext ctx) {
//        return (XQueryList)visitor.visit(ctx.xq());
//    }
//
//    public XQueryList evalVar(@NotNull XQueryParser.XqVarContext ctx) {
//        return qc.getVar(ctx.getText());
//    }
//
//    public XQueryList evalConcat(@NotNull XQueryParser.XqConcatContext ctx){
//        //qc.openScope();
//        XQueryList l = (XQueryList)visitor.visit(ctx.left);
//        //qc.closeScope();
//
//        //qc.openScope();
//        XQueryList r = (XQueryList)visitor.visit(ctx.right);
//        //qc.closeScope();
//
//        l.addAll(r);
//        return l;
//    }
//
//    private XQueryList evalXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
//        XQueryList xq = (XQueryList)visitor.visit(ctx.xq());
//
//        qc.pushContextElement(xq);
//        XQueryList rp = (XQueryList)visitor.visit(ctx.rp());
//        qc.popContextElement();
//
//        return rp.unique();
//    }
//
//    private XQueryList evalXqSlashSlash(@NotNull XQueryParser.XqSlashContext ctx) {
//        XQueryList l = (XQueryList)visitor.visit(ctx.xq());
//        XQueryList descendants = new XQueryList();
//
//        for(IXMLElement x : l) {
//            descendants.addAll(x.descendants());
//        }
//
//        qc.pushContextElement(descendants);
//
//        XQueryList r = (XQueryList)visitor.visit(ctx.rp());
//
//        qc.popContextElement();
//
//        return r.unique();
//    }
//
//    public XQueryList evalSlashes(@NotNull XQueryParser.XqSlashContext ctx) {
//        XQueryList results = new XQueryList();
//        switch(ctx.slash.getType()) {
//            case XQueryParser.SLASH:
//                results = evalXqSlash(ctx);
//                break;
//            case XQueryParser.SSLASH:
//                results = evalXqSlashSlash(ctx);
//                break;
//            default:
//                Debugger.error("Oops, shouldn't be here");
//                break;
//        }
//        return results;
//    }
//
//    public XQueryList evalTagname(@NotNull XQueryParser.XqTagNameContext ctx) {
//        if(!ctx.open.getText().equals(ctx.close.getText()))
//            Debugger.error(ctx.open.getText() + "is not closed properly. You closed it with " + ctx.close.getText());
//
//        XQueryList xq = (XQueryList)visitor.visit(ctx.xq());
//        XMLElement res = new XMLElement(ctx.open.getText());
//
//        // Figure out whether to add result as text or child element
//        for(IXMLElement v : xq) {
//            if(v instanceof XMLText)
//                res.add((XMLText)v);
//            else if(v instanceof XMLElement)
//                res.add((XMLElement)v);
//        }
//
//        return new XQueryList(res);
//    }
//
//    public XQueryList evalFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
//        VarEnvironmentList veFor = (VarEnvironmentList)visitor.visit(ctx.forClause());
//
//        XQueryList res = new XQueryList();
//        for (VarEnvironment ve : veFor){
//            qc.pushVarEnv(ve);
//            if(ctx.letClause() != null) {
//                VarEnvironment letEnv = (VarEnvironment)visitor.visit(ctx.letClause());
//                qc.pushVarEnv(letEnv);
//            }
//
//            if(ctx.whereClause() != null) {
//                if(visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue())
//                    res.addAll((XQueryList)visitor.visit(ctx.returnClause()));
//            }
//            else
//                res.addAll((XQueryList)visitor.visit(ctx.returnClause()));
//
//            qc.popVarEnv();
//
//            if(ctx.letClause() != null)
//                qc.popVarEnv();
//
//        }
//        return res;
//    }
//
//    public XQueryList evalLet(@NotNull XQueryParser.XqLetContext ctx) {
//        // Changes a bunch of stuff within the global scope
//        VarEnvironment ve = (VarEnvironment)visitor.visit(ctx.letClause());
//
//        qc.pushVarEnv(ve);
//
//        XQueryList res = (XQueryList)visitor.visit(ctx.xq());
//
//        qc.popVarEnv();
//
//        return res;
//    }
//
//    public IXQueryValue evalJoin(XQueryParser.JoinClauseContext ctx) {
//        // Get results of inner for loops
//        XQueryList list1 = (XQueryList)visitor.visit(ctx.xq1);
//        XQueryList list2 = (XQueryList)visitor.visit(ctx.xq2);
//        //change latter!!!!!!!!!!!!!!!!!!!!!!
//
//        XQueryList res = new XQueryList();
//
//        // Get join attributes
//        String idl1 = ctx.IdentifierList(0).getText();
//        String idl2 = ctx.IdentifierList(1).getText();
//        List<String> joinVars1 = Arrays.asList(idl1.substring(1, idl1.length() - 1).split(","));
//        List<String> joinVars2 = Arrays.asList(idl2.substring(1, idl2.length() - 1).split(","));
//
//        for(IXMLElement elem1 : list1)
//            for(IXMLElement elem2 : list2) {
//                boolean join = true;
//                for (int i = 0; i < joinVars1.size(); i++) {
//                    // Get elements to join on
//                    XQueryList list1Elems = elem1.getChildByTag(joinVars1.get(i));
//                    XQueryList list2Elems = elem2.getChildByTag(joinVars2.get(i));
//                    for (IXMLElement listElem1 : list1Elems)
//                        for (IXMLElement listElem2 : list2Elems) {
//                            if (!listElem1.childrenEquals(listElem2)) {
//                                join = false;
//                            }
//                        }
//                }
//                if(join){
//                    // Create new dummy element "Tuple" and add to result
//                    XMLElement tuple = new XMLElement("tuple");
//                    tuple.addAll(elem1.children());
//                    tuple.addAll(elem2.children());
//                    res.add(tuple);
//                }
//            }
//        return res;
//    }
//}
