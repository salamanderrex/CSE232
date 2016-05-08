package project1.xquery.visitor;

import project1.xquery.value.*;
import project1.xquery.context.*;
import project1.xquery.parser.*;
import org.antlr.v4.runtime.misc.NotNull;

public class CondEvaluator extends XQueryEvaluator {
    public CondEvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc) {
        super(visitor, qc);
    }

    public XQueryFilter evalIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx){
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsId(r);
    }
    public XQueryFilter evalValEqual(@NotNull XQueryParser.CondValEqualContext ctx){
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsVal(r);
    }

    public XQueryFilter evalEmpty(@NotNull XQueryParser.CondEmptyContext ctx){
        XQueryList res = (XQueryList)visitor.visit(ctx.xq());
        return res.empty();
    }

    public XQueryFilter evalSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx){
        VarEnvironment ve = qc.cloneVarEnv();

        for(int i = 0; i < ctx.xq().size(); i++) {
            XQueryList res = (XQueryList)visitor.visit(ctx.xq(i));
            ve.put(ctx.Var(i).getText(), res);
        }

        //System.out.println("here in eval push ");
        qc.pushVarEnv(ve);

        XQueryFilter res = (XQueryFilter)visitor.visit(ctx.cond());

        qc.popVarEnv();

        return res;
    }

    public XQueryFilter evalParen(@NotNull XQueryParser.CondParenExprContext ctx){
        return (XQueryFilter)visitor.visit(ctx.cond());
    }

    public XQueryFilter evalAnd(@NotNull XQueryParser.CondAndContext ctx){
        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
        return l.and(r);
    }

    public XQueryFilter evalOr(@NotNull XQueryParser.CondOrContext ctx){
        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
        return l.or(r);
    }

    public XQueryFilter evalNot(@NotNull XQueryParser.CondNotContext ctx){
        XQueryFilter res = (XQueryFilter)visitor.visit(ctx.cond());
        return res.not();
    }

}

