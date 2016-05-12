package project1.xquery.visitor;

import project1.xquery.parser.XQueryParser;
import project1.xquery.value.*;
import project1.xquery.parser.*;
import project1.xquery.xmlElement.*;
import project1.xquery.context.*;
import org.antlr.v4.runtime.misc.NotNull;
import sun.invoke.util.VerifyAccess;


import java.util.*;
import java.util.Arrays;

public class XqEvaluator extends XQueryEvaluator {
    public XqEvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc) {
        super(visitor, qc);
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
                System.out.println("Oops, shouldn't be here");
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
        VarEnvironmentList veFor = (VarEnvironmentList) visitor.visit(ctx.forClause());

        XQueryList res = new XQueryList();
        for (VarEnvironment ve : veFor) {
            qc.pushVarEnv(ve);
            if (ctx.letClause() != null) {
                VarEnvironment letEnv = (VarEnvironment) visitor.visit(ctx.letClause());
                qc.pushVarEnv(letEnv);
            }

            if (ctx.whereClause() != null) {




                if (ctx.letClause() != null) {

                        visitor.visit(ctx.whereClause());
                        List<String> wherevar = qc.getWhereVar2();
                        //System.out.println(wherevar.toString());

                        int[] whereList = new int[wherevar.size()];

                        for (int i = 0; i < whereList.length; i++) {
                            whereList[i] = qc.st.getVar(wherevar.get(i)).size();
                        }
                       // System.out.println(Arrays.toString(whereList));

                        int[] counter = new int[wherevar.size()];
                        int total = 1;
                        for (int i = 0; i < whereList.length; i++) {
                            total = total * whereList[i];
                        }
                        //System.out.println(Arrays.toString(whereList));
                        for (int i = 0; i < total; i++) {
                            VarEnvironment v = qc.cloneVarEnv();
                           // System.out.println(Arrays.toString(counter));
                            for (int j = 0; j < counter.length; j++) {
                                String varName = wherevar.get(j);
                                XMLElement e2 = v.get(wherevar.get(j)).get(counter[j]);
                                XQueryList xqList = new XQueryList(e2);
                                v.put(varName, xqList);
                            }
                            qc.pushVarEnv(v);
                            if (visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue()) {
                                res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
                            }
                            qc.popVarEnv();
                            Plusone(whereList,counter);
                        }


                } else {
                    if (visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue())
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
        // Changes a bunch of stuff within the global scope
        VarEnvironment ve = (VarEnvironment) visitor.visit(ctx.letClause());

        qc.pushVarEnv(ve);

        XQueryList res = (XQueryList) visitor.visit(ctx.xq());

        qc.popVarEnv();

        return res;
    }

    public IXQueryValue evalJoin(XQueryParser.JoinClauseContext ctx) {
        // Get results of inner for loops
        XQueryList list1 = (XQueryList) visitor.visit(ctx.xq1);
        XQueryList list2 = (XQueryList) visitor.visit(ctx.xq2);
        //change latter!!!!!!!!!!!!!!!!!!!!!!

        XQueryList res = new XQueryList();

        // Get join attributes
        String idl1 = ctx.IdentifierList(0).getText();
        String idl2 = ctx.IdentifierList(1).getText();
        List<String> joinVars1 = Arrays.asList(idl1.substring(1, idl1.length() - 1).split(","));
        List<String> joinVars2 = Arrays.asList(idl2.substring(1, idl2.length() - 1).split(","));

        for (XMLElement elem1 : list1)
            for (XMLElement elem2 : list2) {
                boolean join = true;
                for (int i = 0; i < joinVars1.size(); i++) {
                    // Get elements to join on
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
                    // Create new dummy xmlElement "Tuple" and add to result
                    XMLElement tuple = new XMLElement("tuple");
                    tuple.addAll(elem1.children());
                    tuple.addAll(elem2.children());
                    res.add(tuple);
                }
            }
        return res;
    }
}
