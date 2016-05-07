package project1.xquery.visitor;


import project1.xquery.parser.XQueryParser;
import project1.xquery.value.*;
import project1.xquery.parser.*;
import project1.xquery.context.*;

public class FEvaluator extends XQueryEvaluator {
    public FEvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc) {
        super(visitor, qc);
    }

    public XQueryFilter evalFRp(XQueryParser.FRpContext ctx) {
        XQueryList resultR = (XQueryList)visitor.visit(ctx.rp());
        if(resultR.size() > 0)
            return XQueryFilter.trueValue();
        return XQueryFilter.falseValue();
    }

    public XQueryFilter evalParen(XQueryParser.FParenContext ctx) {
        return (XQueryFilter)visitor.visit(ctx.f());
    }

    public XQueryFilter evalAnd(XQueryParser.FAndContext ctx) {
        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
        return l.and(r);
    }

    public XQueryFilter evalOr(XQueryParser.FOrContext ctx) {
        XQueryFilter l = (XQueryFilter)visitor.visit(ctx.left);
        XQueryFilter r = (XQueryFilter)visitor.visit(ctx.right);
        return l.or(r);
    }

    public XQueryFilter evalNot(XQueryParser.FNotContext ctx) {
        XQueryFilter v = (XQueryFilter)visitor.visit(ctx.f());
        return v.not();
    }

    public XQueryFilter evalValEqual(XQueryParser.FValEqualContext ctx) {
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsVal(r);
    }

    public XQueryFilter evalIdEqual(XQueryParser.FIdEqualContext ctx) {
        XQueryList l = (XQueryList)visitor.visit(ctx.left);
        XQueryList r = (XQueryList)visitor.visit(ctx.right);
        return l.equalsId(r);
    }
}
