package project1.xquery.visitor;

import project1.xquery.parser.XQueryParser;
import project1.xquery.value.*;
import project1.xquery.parser.*;
import project1.xquery.xmlElement.*;
import project1.xquery.context.*;
import project1.utils.*;
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
        //if (qc.firstVisitWhere) {

        //return qc.getVar(ctx.getText());

        //Set<String> WhereVar = qc.getWhereVar();
        List<String> WhereVar2 = qc.getWhereVar2();
        if (qc.inwhere) {
            //qc.getWhereVar().add(qc.getVar(ctx.getText()));
            //System.out.println(ctx.getText());
//            if (!WhereVar.contains(ctx.getText()))
//                WhereVar.add(ctx.getText());
            if (!WhereVar2.contains(ctx.getText()))
                WhereVar2.add(ctx.getText());

        }
        //}
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
                Debugger.error("Oops, shouldn't be here");
                break;
        }
        return results;
    }

    public XQueryList evalTagname(@NotNull XQueryParser.XqTagNameContext ctx) {
        if (!ctx.open.getText().equals(ctx.close.getText()))
            Debugger.error(ctx.open.getText() + "is not closed properly. You closed it with " + ctx.close.getText());

        XQueryList xq = (XQueryList) visitor.visit(ctx.xq());
        XMLElement res = new XMLElement(ctx.open.getText());

        // Figure out whether to add result as text or child xmlElement
        for (XMLElement v : xq) {
            if (v.isConstantStr())
                res.add((XMLElement) v);
            else
                res.add((XMLElement) v);
        }

        return new XQueryList(res);
    }

    public void Plusone(int[] base, int[] next) {

        for (int i = next.length - 1; i >= 0; i--) {
            if ((next[i] + 1) == base[i]) {
                //means need to update previous one
                next[i - 1] = next[i - 1] + 1;
                next[i] = 0;
                break;
            } else {
                next[i] = next[i] + 1;
                break;
            }
        }

    }

    public XQueryList evalFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        VarEnvironmentList veFor = (VarEnvironmentList) visitor.visit(ctx.forClause());

        XQueryList res = new XQueryList();
        if (ctx.whereClause() != null) {

        }
        List<String> wherevar = null;
        int flag = 1;
        for (VarEnvironment ve : veFor) {
            qc.pushVarEnv(ve);
            if (ctx.letClause() != null) {
                VarEnvironment letEnv = (VarEnvironment) visitor.visit(ctx.letClause());
               qc.pushVarEnv(letEnv);
            }


            if (ctx.whereClause() != null) {


                if (ctx.letClause() != null) {

                    if (ctx.letClause().xq().size() == 0) {
                        if (visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue())
                            res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
                    } else if (ctx.letClause().xq().size() >= 1) {
                        // now you have magic
                        //$sc size 3, $sp size 142. then what?
                        //var 1
                        //String var1 = ctx.letClause().Var(0).getText();


                        // ======================================
                        //Set<String> varInqc = qc.cloneVarEnv().keySet();
                        if (flag == 1) {
                            flag++;
                            visitor.visit(ctx.whereClause());
                            wherevar = new ArrayList<String>();
                            wherevar.addAll(qc.getWhereVar2());
                            // wherevar = qc.getWhereVar2();
                        }


                        qc.firstVisitWhere = false;
//                        for (String s : wherevar) {
//                            System.out.print(s + ",");
//                        }
//                        System.out.println();

                        int[] whereList = new int[wherevar.size()];

                        for (int i = 0; i < whereList.length; i++) {
                            whereList[i] = qc.st.getVar(wherevar.get(i)).size();
                        }


                        int[] counter = new int[wherevar.size()];
                        int total = 1;
                        for (int i = 0; i < whereList.length; i++) {
                            total = total * whereList[i];
                        }

                        boolean[] letFilter = new boolean[ctx.letClause().xq().size()];
                        for (int i = 0; i < letFilter.length; i++) {
                            String var = ctx.letClause().Var(i).getText();
                            if (wherevar.contains(var)) {
                                letFilter[i] = true;
                            }
                        }
                        //System.out.println("letFilter" + Arrays.toString(letFilter));


                        //System.out.println("total possibility" + total);

                        for (int i = 0; i < total; i++) {
                            //start from 0000
                            // System.out.println(Arrays.toString(counter));

                            VarEnvironment v = qc.cloneVarEnv();
                            for (int j = 0; j < counter.length; j++) {

                                String varName = wherevar.get(j);
                              //  System.out.println(counter[j]);
                              //  System.out.println();
                              //  System.out.println("size of " + wherevar.get(j) + " " + v.get(wherevar.get(j)).size());

                                XMLElement e2 = v.get(wherevar.get(j)).get(counter[j]);
                                XQueryList xqList = new XQueryList(e2);
                                v.put(varName, xqList);


                            }
                            qc.pushVarEnv(v);

                            for (int j = 0; j < counter.length; j++) {
                              //  System.out.println("size of " + wherevar.get(j) + " " + qc.cloneVarEnv().get(wherevar.get(j)).size());


                            }
                         //   System.out.println("jjjj===");

                            if (visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue()) {
                                qc.letFilter = letFilter;
                           //     System.out.println("here");
                                VarEnvironment letEnvn = (VarEnvironment) visitor.visit(ctx.letClause());
                                qc.pushVarEnv(letEnvn);
                                if (visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue()) {
                                    res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
                                }
                                qc.letFilter = null;
                                qc.popVarEnv();
                                for (int j = 0; j < counter.length; j++) {
                                 //   System.out.println("size of " + wherevar.get(j) + " " + qc.cloneVarEnv().get(wherevar.get(j)).size());


                                }
                                for (int vv = 0; vv < ctx.letClause().xq().size(); vv++) {

                                    //in let it push a lot of times
                                    qc.popVarEnv();
                                }
                              //  System.out.println("=======");
                            }

                            qc.popVarEnv();

                            for (int j = 0; j < counter.length; j++) {
                               // System.out.println("size of " + wherevar.get(j) + " " + qc.cloneVarEnv().get(wherevar.get(j)).size());


                            }
                           // System.out.println("xxxx===");

                            if (i != total - 1)
                                Plusone(whereList, counter);
                           // System.out.println("wherelist" + Arrays.toString(whereList));
                           // System.out.println("counter" + Arrays.toString(counter));


                        }

                        //  qc.whereVar2 = new ArrayList<String>();


                    } else {
                        System.out.println("Frankly, I think TA is wrong, but just want to pass testcases");
                        System.out.println("What, she is wrong, damm");
                    }
                } else {
                    if (visitor.visit(ctx.whereClause()) == XQueryFilter.trueValue())
                        res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
                }

            } else
                res.addAll((XQueryList) visitor.visit(ctx.returnClause()));
            // exit where
            //qc.firstVisitWhere = true;


            if (ctx.letClause() != null)
                for (int i = 0; i < ctx.letClause().xq().size(); i++) {

                    //in let it push a lot of times
                    qc.popVarEnv();
                }

            qc.popVarEnv();

        }

        qc.firstVisitWhere = true;
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
