package project1.xquery.parser;


import project1.xquery.context.QueryContext;
import project1.xquery.value.*;
import project1.xquery.visitor.*;
import org.antlr.v4.runtime.misc.NotNull;
/**
 * Created by qingyu on 4/28/16.
 */
public class myXQueryVisitor  extends XQueryBaseVisitor<IXQueryValue> {
    private QueryContext qc = new QueryContext();
    private ApRpEvaluator rpEval = new ApRpEvaluator(this, qc);
    private FEvaluator fEval = new FEvaluator(this, qc);
    private XqEvaluator xqEval = new XqEvaluator(this, qc);
    private FLWREvaluator FLWREval = new FLWREvaluator(this, qc);
    private CondEvaluator condEval = new CondEvaluator(this, qc);

    /** APS **/

    @Override
    public XQueryList visitAp(@NotNull XQueryParser.ApContext ctx) {
        return rpEval.evalAp(ctx);
    }

    /** RPS **/

    @Override
    public XQueryList visitRpTagName(@NotNull XQueryParser.RpTagNameContext ctx) {
        return rpEval.evalTagName(ctx);
    }

    @Override
    public XQueryList visitRpWildcard(@NotNull XQueryParser.RpWildcardContext ctx) {
        return rpEval.evalWildCard();
    }

    @Override
    public XQueryList visitRpDot(@NotNull XQueryParser.RpDotContext ctx) {
        return rpEval.evalDot();
    }

    @Override
    public XQueryList visitRpDotDot(@NotNull XQueryParser.RpDotDotContext ctx) {
        return rpEval.evalDotDot();
    }

    @Override
    public XQueryList visitRpText(@NotNull XQueryParser.RpTextContext ctx) {
        return rpEval.evalText();
    }

    @Override
    public XQueryList visitRpParenExpr(@NotNull XQueryParser.RpParenExprContext ctx) {
        return rpEval.evalParen(ctx);
    }

    @Override
    public XQueryList visitRpSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        return rpEval.evalSlashes(ctx);
    }

    @Override
    public XQueryList visitRpAttr(@NotNull XQueryParser.RpAttrContext ctx) {
        return rpEval.evalAttr(ctx);
    }

    @Override
    public XQueryList visitRpFilter(@NotNull XQueryParser.RpFilterContext ctx) {
        return rpEval.evalFilter(ctx);
    }

    @Override
    public XQueryList visitRpConcat(@NotNull XQueryParser.RpConcatContext ctx) {
        return rpEval.evalConcat(ctx);
    }

    /** FILTERS **/

    @Override
    public XQueryFilter visitFRp(@NotNull XQueryParser.FRpContext ctx) {
        return fEval.evalFRp(ctx);
    }

    @Override
    public IXQueryValue visitFValEqual(@NotNull XQueryParser.FValEqualContext ctx) {
        return fEval.evalValEqual(ctx);
    }

    @Override
    public IXQueryValue visitFIdEqual(@NotNull XQueryParser.FIdEqualContext ctx) {
        return fEval.evalIdEqual(ctx);
    }

    @Override
    public XQueryFilter visitFParen(@NotNull XQueryParser.FParenContext ctx) {
        return fEval.evalParen(ctx);
    }

    @Override
    public XQueryFilter visitFAnd(@NotNull XQueryParser.FAndContext ctx) {
        return fEval.evalAnd(ctx);
    }

    @Override
    public XQueryFilter visitFOr(@NotNull XQueryParser.FOrContext ctx) {
        return fEval.evalOr(ctx);
    }

    @Override
    public XQueryFilter visitFNot(@NotNull XQueryParser.FNotContext ctx) {
        return fEval.evalNot(ctx);
    }

    /** XQ **/

    @Override
    public IXQueryValue visitXqVar(@NotNull XQueryParser.XqVarContext ctx) {
        return xqEval.evalVar(ctx);
    }

    @Override
    public IXQueryValue visitXqStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx) {
        return xqEval.evalStringConstant(ctx);
    }

    @Override
    public IXQueryValue visitXqAp(@NotNull XQueryParser.XqApContext ctx) {
        return xqEval.evalAp(ctx);
    }

    @Override
    public IXQueryValue visitXqParenExpr(@NotNull XQueryParser.XqParenExprContext ctx) {
        return xqEval.evalParen(ctx);
    }

    @Override
    public IXQueryValue visitXqConcat(@NotNull XQueryParser.XqConcatContext ctx) {
        return xqEval.evalConcat(ctx);
    }

    @Override
    public IXQueryValue visitXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        return xqEval.evalSlashes(ctx);
    }

    @Override
    public IXQueryValue visitXqTagName(@NotNull XQueryParser.XqTagNameContext ctx) {
        return xqEval.evalTagname(ctx);
    }

    @Override
    public IXQueryValue visitXqFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        return xqEval.evalFLWR(ctx);
    }

    @Override
    public IXQueryValue visitXqLet(@NotNull XQueryParser.XqLetContext ctx) {
        return xqEval.evalLet(ctx);
    }

    /** FLOWR CLAUSES **/

    @Override
    public IXQueryValue visitForClause(@NotNull XQueryParser.ForClauseContext ctx) {
        return FLWREval.evalFor(ctx);
    }

    @Override
    public IXQueryValue visitLetClause(@NotNull XQueryParser.LetClauseContext ctx) {
        return FLWREval.evalLet(ctx);
    }

    @Override
    public IXQueryValue visitWhereClause(@NotNull XQueryParser.WhereClauseContext ctx) {
        return FLWREval.evalWhere(ctx);
    }

    @Override
    public IXQueryValue visitReturnClause(@NotNull XQueryParser.ReturnClauseContext ctx) {
        return FLWREval.evalReturn(ctx);
    }

    /** CONDITION CLAUSES **/

    @Override
    public IXQueryValue visitCondValEqual(@NotNull XQueryParser.CondValEqualContext ctx) {
        return condEval.evalValEqual(ctx);
    }

    @Override
    public IXQueryValue visitCondIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx) {
        return condEval.evalIdEqual(ctx);
    }

    @Override
    public IXQueryValue visitCondEmpty(@NotNull XQueryParser.CondEmptyContext ctx) {
        return condEval.evalEmpty(ctx);
    }

    @Override
    public IXQueryValue visitCondSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx) {
        return condEval.evalSomeSatis(ctx);
    }

    @Override
    public IXQueryValue visitCondParenExpr(@NotNull XQueryParser.CondParenExprContext ctx) {
        return condEval.evalParen(ctx);
    }

    @Override
    public IXQueryValue visitCondAnd(@NotNull XQueryParser.CondAndContext ctx) {
        return condEval.evalAnd(ctx);
    }

    @Override
    public IXQueryValue visitCondOr(@NotNull XQueryParser.CondOrContext ctx) {
        return condEval.evalOr(ctx);
    }

    @Override
    public IXQueryValue visitCondNot(@NotNull XQueryParser.CondNotContext ctx) {
        return condEval.evalNot(ctx);
    }

    @Override
    public IXQueryValue visitXqJoin(@NotNull XQueryParser.XqJoinContext ctx) {
        return visit(ctx.joinClause());
    }

    @Override
    public IXQueryValue visitJoinClause(@NotNull XQueryParser.JoinClauseContext ctx) {
        return xqEval.evalJoin(ctx);
    }
}