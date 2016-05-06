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
    private MyXQueryEvaluator Evaluator = new MyXQueryEvaluator(this,qc);

    /** APS **/

    @Override
    public XQueryList visitAp(@NotNull XQueryParser.ApContext ctx) {
        return Evaluator.evalAp(ctx);
    }

    /** RPS **/

    @Override
    public XQueryList visitRpTagName(@NotNull XQueryParser.RpTagNameContext ctx) {
        return Evaluator.evalTagName(ctx);
    }

    @Override
    public XQueryList visitRpWildcard(@NotNull XQueryParser.RpWildcardContext ctx) {
        return Evaluator.evalWildCard();
    }

    @Override
    public XQueryList visitRpDot(@NotNull XQueryParser.RpDotContext ctx) {
        return Evaluator.evalDot();
    }

    @Override
    public XQueryList visitRpDotDot(@NotNull XQueryParser.RpDotDotContext ctx) {
        return Evaluator.evalDotDot();
    }

    @Override
    public XQueryList visitRpText(@NotNull XQueryParser.RpTextContext ctx) {
        return Evaluator.evalText();
    }

    @Override
    public XQueryList visitRpParenExpr(@NotNull XQueryParser.RpParenExprContext ctx) {
        return Evaluator.evalParen(ctx);
    }

    @Override
    public XQueryList visitRpSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        return Evaluator.evalSlashes(ctx);
    }

    @Override
    public XQueryList visitRpAttr(@NotNull XQueryParser.RpAttrContext ctx) {
        return Evaluator.evalAttr(ctx);
    }

    @Override
    public XQueryList visitRpFilter(@NotNull XQueryParser.RpFilterContext ctx) {
        return Evaluator.evalFilter(ctx);
    }

    @Override
    public XQueryList visitRpConcat(@NotNull XQueryParser.RpConcatContext ctx) {
        return Evaluator.evalConcat(ctx);
    }

    /** FILTERS **/

    @Override
    public XQueryFilter visitFRp(@NotNull XQueryParser.FRpContext ctx) {
        return Evaluator.evalFRp(ctx);
    }

    @Override
    public IXQueryValue visitFValEqual(@NotNull XQueryParser.FValEqualContext ctx) {
        return Evaluator.evalValEqual(ctx);
    }

    @Override
    public IXQueryValue visitFIdEqual(@NotNull XQueryParser.FIdEqualContext ctx) {
        return Evaluator.evalIdEqual(ctx);
    }

    @Override
    public XQueryFilter visitFParen(@NotNull XQueryParser.FParenContext ctx) {
        return Evaluator.evalParen(ctx);
    }

    @Override
    public XQueryFilter visitFAnd(@NotNull XQueryParser.FAndContext ctx) {
        return Evaluator.evalAnd(ctx);
    }

    @Override
    public XQueryFilter visitFOr(@NotNull XQueryParser.FOrContext ctx) {
        return Evaluator.evalOr(ctx);
    }

    @Override
    public XQueryFilter visitFNot(@NotNull XQueryParser.FNotContext ctx) {
        return Evaluator.evalNot(ctx);
    }

    /** XQ **/

    @Override
    public IXQueryValue visitXqVar(@NotNull XQueryParser.XqVarContext ctx) {
        return Evaluator.evalVar(ctx);
    }

    @Override
    public IXQueryValue visitXqStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx) {
        return Evaluator.evalStringConstant(ctx);
    }

    @Override
    public IXQueryValue visitXqAp(@NotNull XQueryParser.XqApContext ctx) {
        return Evaluator.evalAp(ctx);
    }

    @Override
    public IXQueryValue visitXqParenExpr(@NotNull XQueryParser.XqParenExprContext ctx) {
        return Evaluator.evalParen(ctx);
    }

    @Override
    public IXQueryValue visitXqConcat(@NotNull XQueryParser.XqConcatContext ctx) {
        return Evaluator.evalConcat(ctx);
    }

    @Override
    public IXQueryValue visitXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        return Evaluator.evalSlashes(ctx);
    }

    @Override
    public IXQueryValue visitXqTagName(@NotNull XQueryParser.XqTagNameContext ctx) {
        return Evaluator.evalTagname(ctx);
    }

    @Override
    public IXQueryValue visitXqFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        return Evaluator.evalFLWR(ctx);
    }

    @Override
    public IXQueryValue visitXqLet(@NotNull XQueryParser.XqLetContext ctx) {
        return Evaluator.evalLet(ctx);
    }

    /** FLOWR CLAUSES **/

    @Override
    public IXQueryValue visitForClause(@NotNull XQueryParser.ForClauseContext ctx) {
        return Evaluator.evalFor(ctx);
    }

    @Override
    public IXQueryValue visitLetClause(@NotNull XQueryParser.LetClauseContext ctx) {
        return Evaluator.evalLet(ctx);
    }

    @Override
    public IXQueryValue visitWhereClause(@NotNull XQueryParser.WhereClauseContext ctx) {
        return Evaluator.evalWhere(ctx);
    }

    @Override
    public IXQueryValue visitReturnClause(@NotNull XQueryParser.ReturnClauseContext ctx) {
        return Evaluator.evalReturn(ctx);
    }

    /** CONDITION CLAUSES **/

    @Override
    public IXQueryValue visitCondValEqual(@NotNull XQueryParser.CondValEqualContext ctx) {
        return Evaluator.evalValEqual(ctx);
    }

    @Override
    public IXQueryValue visitCondIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx) {
        return Evaluator.evalIdEqual(ctx);
    }

    @Override
    public IXQueryValue visitCondEmpty(@NotNull XQueryParser.CondEmptyContext ctx) {
        return Evaluator.evalEmpty(ctx);
    }

    @Override
    public IXQueryValue visitCondSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx) {
        return Evaluator.evalSomeSatis(ctx);
    }

    @Override
    public IXQueryValue visitCondParenExpr(@NotNull XQueryParser.CondParenExprContext ctx) {
        return Evaluator.evalParen(ctx);
    }

    @Override
    public IXQueryValue visitCondAnd(@NotNull XQueryParser.CondAndContext ctx) {
        return Evaluator.evalAnd(ctx);
    }

    @Override
    public IXQueryValue visitCondOr(@NotNull XQueryParser.CondOrContext ctx) {
        return Evaluator.evalOr(ctx);
    }

    @Override
    public IXQueryValue visitCondNot(@NotNull XQueryParser.CondNotContext ctx) {
        return Evaluator.evalNot(ctx);
    }

    @Override
    public IXQueryValue visitXqJoin(@NotNull XQueryParser.XqJoinContext ctx) {
        return visit(ctx.joinClause());
    }

    @Override
    public IXQueryValue visitJoinClause(@NotNull XQueryParser.JoinClauseContext ctx) {
        return Evaluator.evalJoin(ctx);
    }
}