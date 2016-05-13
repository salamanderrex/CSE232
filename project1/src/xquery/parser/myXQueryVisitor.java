package project1.xquery.parser;


import project1.xquery.context.QueryEnv;
import project1.xquery.value.*;
import project1.xquery.visitor.*;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by qingyu on 4/28/16.
 */
public class myXQueryVisitor  extends XQueryBaseVisitor<MyQueryElement> {
    private QueryEnv qc = new QueryEnv();
    public MyXQueryEvaluator evaluator = new MyXQueryEvaluator(this,qc);

    /** APS **/

    @Override
    public XQueryList visitAp(@NotNull XQueryParser.ApContext ctx) {
        return evaluator.evalAp(ctx);
    }

    /** RPS **/

    @Override
    public XQueryList visitRpTagName(@NotNull XQueryParser.RpTagNameContext ctx) {
        return evaluator.evalTagName(ctx);
    }

    @Override
    public XQueryList visitRpWildcard(@NotNull XQueryParser.RpWildcardContext ctx) {
        return evaluator.evalWildCard();
    }

    @Override
    public XQueryList visitRpDot(@NotNull XQueryParser.RpDotContext ctx) {
        return evaluator.evalDot();
    }

    @Override
    public XQueryList visitRpDotDot(@NotNull XQueryParser.RpDotDotContext ctx) {
        return evaluator.evalDotDot();
    }

    @Override
    public XQueryList visitRpText(@NotNull XQueryParser.RpTextContext ctx) {
        return evaluator.evalText();
    }

    @Override
    public XQueryList visitRpParenExpr(@NotNull XQueryParser.RpParenExprContext ctx) {
        return evaluator.evalParen(ctx);
    }

    @Override
    public XQueryList visitRpSlash(@NotNull XQueryParser.RpSlashContext ctx) {
        return evaluator.evalSlashes(ctx);
    }

    @Override
    public XQueryList visitRpAttr(@NotNull XQueryParser.RpAttrContext ctx) {
        return evaluator.evalAttr(ctx);
    }

    @Override
    public XQueryList visitRpFilter(@NotNull XQueryParser.RpFilterContext ctx) {
        return evaluator.evalFilter(ctx);
    }

    @Override
    public XQueryList visitRpConcat(@NotNull XQueryParser.RpConcatContext ctx) {
        return evaluator.evalConcat(ctx);
    }

    /** FILTERS **/

    @Override
    public XQueryBoolean visitFRp(@NotNull XQueryParser.FRpContext ctx) {
        return evaluator.evalFRp(ctx);
    }

    @Override
    public MyQueryElement visitFValEqual(@NotNull XQueryParser.FValEqualContext ctx) {
        return evaluator.evalValEqual(ctx);
    }

    @Override
    public MyQueryElement visitFIdEqual(@NotNull XQueryParser.FIdEqualContext ctx) {
        return evaluator.evalIdEqual(ctx);
    }

    @Override
    public XQueryBoolean visitFParen(@NotNull XQueryParser.FParenContext ctx) {
        return evaluator.evalParen(ctx);
    }

    @Override
    public XQueryBoolean visitFAnd(@NotNull XQueryParser.FAndContext ctx) {
        return evaluator.evalAnd(ctx);
    }

    @Override
    public XQueryBoolean visitFOr(@NotNull XQueryParser.FOrContext ctx) {
        return evaluator.evalOr(ctx);
    }

    @Override
    public XQueryBoolean visitFNot(@NotNull XQueryParser.FNotContext ctx) {
        return evaluator.evalNot(ctx);
    }

    /** XQ **/

    @Override
    public MyQueryElement visitXqVar(@NotNull XQueryParser.XqVarContext ctx) {
        return evaluator.evalVar(ctx);
    }

    @Override
    public MyQueryElement visitXqStringConstant(@NotNull XQueryParser.XqStringConstantContext ctx) {
        return evaluator.evalStringConstant(ctx);
    }

    @Override
    public MyQueryElement visitXqAp(@NotNull XQueryParser.XqApContext ctx) {
        return evaluator.evalAp(ctx);
    }

    @Override
    public MyQueryElement visitXqParenExpr(@NotNull XQueryParser.XqParenExprContext ctx) {
        return evaluator.evalParen(ctx);
    }

    @Override
    public MyQueryElement visitXqConcat(@NotNull XQueryParser.XqConcatContext ctx) {
        return evaluator.evalConcat(ctx);
    }

    @Override
    public MyQueryElement visitXqSlash(@NotNull XQueryParser.XqSlashContext ctx) {
        return evaluator.evalSlashes(ctx);
    }

    @Override
    public MyQueryElement visitXqTagName(@NotNull XQueryParser.XqTagNameContext ctx) {
        return evaluator.evalTagname(ctx);
    }

    @Override
    public MyQueryElement visitXqFLWR(@NotNull XQueryParser.XqFLWRContext ctx) {
        return evaluator.evalFLWR(ctx);
    }

    @Override
    public MyQueryElement visitXqLet(@NotNull XQueryParser.XqLetContext ctx) {
        return evaluator.evalLet(ctx);
    }

    /** FLOWR CLAUSES **/

    @Override
    public MyQueryElement visitForClause(@NotNull XQueryParser.ForClauseContext ctx) {
        return evaluator.evalFor(ctx);
    }

    @Override
    public MyQueryElement visitLetClause(@NotNull XQueryParser.LetClauseContext ctx) {
        return evaluator.evalLet(ctx);
    }

    @Override
    public MyQueryElement visitWhereClause(@NotNull XQueryParser.WhereClauseContext ctx) {
        return evaluator.evalWhere(ctx);
    }

    @Override
    public MyQueryElement visitReturnClause(@NotNull XQueryParser.ReturnClauseContext ctx) {
        return evaluator.evalReturn(ctx);
    }

    /** CONDITION CLAUSES **/

    @Override
    public MyQueryElement visitCondValEqual(@NotNull XQueryParser.CondValEqualContext ctx) {
        return evaluator.evalValEqual(ctx);
    }

    @Override
    public MyQueryElement visitCondIdEqual(@NotNull XQueryParser.CondIdEqualContext ctx) {
        return evaluator.evalIdEqual(ctx);
    }

    @Override
    public MyQueryElement visitCondEmpty(@NotNull XQueryParser.CondEmptyContext ctx) {
        return evaluator.evalEmpty(ctx);
    }

    @Override
    public MyQueryElement visitCondSomeSatis(@NotNull XQueryParser.CondSomeSatisContext ctx) {
        return evaluator.evalSomeSatis(ctx);
    }

    @Override
    public MyQueryElement visitCondParenExpr(@NotNull XQueryParser.CondParenExprContext ctx) {
        return evaluator.evalParen(ctx);
    }

    @Override
    public MyQueryElement visitCondAnd(@NotNull XQueryParser.CondAndContext ctx) {
        return evaluator.evalAnd(ctx);
    }

    @Override
    public MyQueryElement visitCondOr(@NotNull XQueryParser.CondOrContext ctx) {
        return evaluator.evalOr(ctx);
    }

    @Override
    public MyQueryElement visitCondNot(@NotNull XQueryParser.CondNotContext ctx) {
        return evaluator.evalNot(ctx);
    }

    @Override
    public MyQueryElement visitXqJoin(@NotNull XQueryParser.XqJoinContext ctx) {
        return visit(ctx.joinClause());
    }

    @Override
    public MyQueryElement visitJoinClause(@NotNull XQueryParser.JoinClauseContext ctx) {
        return evaluator.evalJoin(ctx);
    }
}
