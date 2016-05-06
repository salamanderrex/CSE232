package project1.xquery.visitor;

import project1.xquery.value.*;
import project1.xquery.visitor.*;
import project1.xquery.context.*;
import project1.xquery.xmltree.*;
import project1.xquery.parser.*;

import org.antlr.v4.runtime.misc.NotNull;

public class XQueryVisitor extends XQueryBaseVisitor<IXQueryValue> {
    private QueryContext qc = new QueryContext();
//    private ApRpEvaluator rpEval = new ApRpEvaluator(this, qc);
//    private FEvaluator fEval = new FEvaluator(this, qc);
//    private XqEvaluator xqEval = new XqEvaluator(this, qc);
//    private FLWREvaluator FLWREval = new FLWREvaluator(this, qc);
//    private CondEvaluator condEval = new CondEvaluator(this, qc);
    private MyXQueryEvaluator Evaluaotr = new MyXQueryEvaluator(this,qc);

    /** APS **/

    @Override
    public XQueryList visitAp(@NotNull XQueryParser.ApContext ctx) {
        return Evaluaotr.evalAp(ctx);
    }

    /** RPS **/

    @Override
    public XQueryList visitRpTagName(@NotNull XQueryParser.RpTagNameContext ctx) {
        return Evaluaotr.evalTagName(ctx);
    }

    @Override
    public XQueryList visitRpWildcard(@NotNull XQueryParser.RpWildcardContext ctx) {
        return Evaluaotr.evalWildCard();
    }

    @Override
    public XQueryList visitRpDot(@NotNull XQueryParser.RpDotContext ctx) {
        return Evaluaotr.evalDot();
    }

    @Override
    public XQueryList visitRpDotDot(@NotNull XQueryParser.RpDotDotContext ctx) {
        return Evaluaotr.evalDotDot();
    }

    @Override
    public XQueryList visitRpText(@NotNull XQueryParser.RpTextContext ctx) {
        return Evaluaotr.evalText();
    }

    @Override
    public XQueryList visitRpParenExpr(@NotNull XQueryParser.RpParenExprContext ctx) {
        return Evaluaotr.evalParen(ctx);
    }

    @Override
    public XQueryList visitRpSlash(@NotNull XQueryParser.RpSlashContext ctx) {

        return Evaluaotr.evalSlashes(ctx);
    }

    @Override
    public XQueryList visitRpAttr(@NotNull XQueryParser.RpAttrContext ctx) {
        return Evaluaotr.evalAttr(ctx);
    }

    @Override
    public XQueryList visitRpFilter(@NotNull XQueryParser.RpFilterContext ctx) {
        return Evaluaotr.evalFilter(ctx);
    }

    @Override
    public XQueryList visitRpConcat(@NotNull XQueryParser.RpConcatContext ctx) {
        return Evaluaotr.evalConcat(ctx);
    }

    /** FILTERS **/

    @Override
    public XQueryFilter visitFRp(@NotNull XQueryParser.FRpContext ctx) {
        return Evaluaotr.evalFRp(ctx);
    }

    @Override
    public IXQueryValue visitFValEqual(@NotNull XQueryParser.FValEqualContext ctx) {
        return Evaluaotr.evalValEqual(ctx);
    }

    @Override
    public IXQueryValue visitFIdEqual(@NotNull XQueryParser.FIdEqualContext ctx) {
        return Evaluaotr.evalIdEqual(ctx);
    }

    @Override
    public XQueryFilter visitFParen(@NotNull XQueryParser.FParenContext ctx) {
        return Evaluaotr.evalParen(ctx);
    }

    @Override
    public XQueryFilter visitFAnd(@NotNull XQueryParser.FAndContext ctx) {
        return Evaluaotr.evalAnd(ctx);
    }

    @Override
    public XQueryFilter visitFOr(@NotNull XQueryParser.FOrContext ctx) {
        return Evaluaotr.evalOr(ctx);
    }

    @Override
    public XQueryFilter visitFNot(@NotNull XQueryParser.FNotContext ctx) {
        return Evaluaotr.evalNot(ctx);
    }

    /** XQ **/

    @Override
    public IXQueryValue visitXqVar(@NotNull XQueryParser.XqVarContext ctx) {
        return Evaluaotr.evalVar(ctx);
    }

    @Override
    public IXQueryValue visitXqStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx) {
        return Evaluaotr.evalStringConstant(ctx);
    }

    @Override
    public IXQueryValue visitXqAp(@NotNull XQueryParser.XqApContext ctx) {
        return Evaluaotr.evalAp(ctx);
    }

    @Override
    public IXQueryValue visitXqParenExpr(@NotNull XQueryParser.XqParenExprContext ctx) {
        return Evaluaotr.evalParen(ctx);
    }

    @Override
    public IXQueryValue visitXqConcat(@NotNull XQueryParser.XqConcatContext ctx) {
        return Evaluaotr.evalConcat(ctx);
    }

    @Override
    public IXQueryValue visitXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        return Evaluaotr.evalSlashes(ctx);
    }

    @Override
    public IXQueryValue visitXqTagName(@NotNull XQueryParser.XqTagNameContext ctx) {
        return Evaluaotr.evalTagname(ctx);
    }

    @Override
    public IXQueryValue visitXqFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        return Evaluaotr.evalFLWR(ctx);
    }

    @Override
    public IXQueryValue visitXqLet(@NotNull XQueryParser.XqLetContext ctx) {
        return Evaluaotr.evalLet(ctx);
    }

    /** FLOWR CLAUSES **/

    @Override
    public IXQueryValue visitForClause(@NotNull XQueryParser.ForClauseContext ctx) {
        return Evaluaotr.evalFor(ctx);
    }

    @Override
    public IXQueryValue visitLetClause(@NotNull XQueryParser.LetClauseContext ctx) {
        return Evaluaotr.evalLet(ctx);
    }

    @Override
    public IXQueryValue visitWhereClause(@NotNull XQueryParser.WhereClauseContext ctx) {
        return Evaluaotr.evalWhere(ctx);
    }

    @Override
    public IXQueryValue visitReturnClause(@NotNull XQueryParser.ReturnClauseContext ctx) {
        return Evaluaotr.evalReturn(ctx);
    }

    /** CONDITION CLAUSES **/

    @Override
    public IXQueryValue visitCondValEqual(@NotNull XQueryParser.CondValEqualContext ctx) {
        return Evaluaotr.evalValEqual(ctx);
    }

    @Override
    public IXQueryValue visitCondIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx) {
        return Evaluaotr.evalIdEqual(ctx);
    }

    @Override
    public IXQueryValue visitCondEmpty(@NotNull XQueryParser.CondEmptyContext ctx) {
        return Evaluaotr.evalEmpty(ctx);
    }

    @Override
    public IXQueryValue visitCondSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx) {
        return Evaluaotr.evalSomeSatis(ctx);
    }

    @Override
    public IXQueryValue visitCondParenExpr(@NotNull XQueryParser.CondParenExprContext ctx) {
        return Evaluaotr.evalParen(ctx);
    }

    @Override
    public IXQueryValue visitCondAnd(@NotNull XQueryParser.CondAndContext ctx) {
        return Evaluaotr.evalAnd(ctx);
    }

    @Override
    public IXQueryValue visitCondOr(@NotNull XQueryParser.CondOrContext ctx) {
        return Evaluaotr.evalOr(ctx);
    }

    @Override
    public IXQueryValue visitCondNot(@NotNull XQueryParser.CondNotContext ctx) {
        return Evaluaotr.evalNot(ctx);
    }

    @Override
    public IXQueryValue visitXqJoin(@NotNull XQueryParser.XqJoinContext ctx) {
        return visit(ctx.joinClause());
    }

    @Override
    public IXQueryValue visitJoinClause(@NotNull XQueryParser.JoinClauseContext ctx) {
        return Evaluaotr.evalJoin(ctx);
    }
}
