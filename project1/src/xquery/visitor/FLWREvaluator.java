package project1.xquery.visitor;

import org.antlr.v4.runtime.misc.NotNull;

import project1.xquery.xmlElement.XMLElement;
import project1.xquery.value.*;
import project1.xquery.context.*;
;
import project1.xquery.parser.*;

public class FLWREvaluator extends XQueryEvaluator {
    public FLWREvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc) {
        super(visitor, qc);
    }

    public VarEnvironmentList evalFor(@NotNull XQueryParser.ForClauseContext ctx) {
        VarEnvironmentList varEnvs = new VarEnvironmentList();
        varEnvs.addAll(getVarEnvs(0, ctx, qc.cloneVarEnv()));
        return varEnvs;
    }

    private VarEnvironmentList getVarEnvs(int var, @NotNull XQueryParser.ForClauseContext ctx, VarEnvironment previousVE) {
        VarEnvironmentList varEnvs = new VarEnvironmentList();
        XQueryList res = (XQueryList) visitor.visit(ctx.xq(var));
        if (var == ctx.xq().size() - 1) {
            for (XMLElement elem : res) {
                VarEnvironment currentVE = new VarEnvironment();
                currentVE.putAll(previousVE);
                currentVE.put(ctx.Var(var).getText(), new XQueryList(elem));
                varEnvs.add(currentVE);
            }
            return varEnvs;
        }
        for (XMLElement elem : res) {
            qc.pushContextElement(elem);
            VarEnvironment currentVE = previousVE.copy();
            currentVE.put(ctx.Var(var).getText(), new XQueryList(elem));
            qc.pushVarEnv(currentVE);
            varEnvs.addAll(getVarEnvs(var + 1, ctx, currentVE));
            qc.popVarEnv();
            qc.popContextElement();
        }
        return varEnvs;
    }

    /**
     * Evaluates a let expression, thus updating the global scope
     *
     * @param ctx list of xquery and variable expression
     */
    public VarEnvironment evalLet(@NotNull XQueryParser.LetClauseContext ctx) {
        VarEnvironment ve = qc.cloneVarEnv();
        if (qc.letFilter==null) {
            for (int i = 0; i < ctx.xq().size(); i++) {
                XQueryList res = (XQueryList) visitor.visit(ctx.xq(i));
                ve.put(ctx.Var(i).getText(), res);
                qc.pushVarEnv(ve);
            }

        } else {
            for (int i = 0; i < ctx.xq().size(); i++) {
                XQueryList res = (XQueryList) visitor.visit(ctx.xq(i));
                if (!qc.letFilter[i]) {
                    ve.put(ctx.Var(i).getText(), res);
                    qc.pushVarEnv(ve);
                }

            }
        }


        return ve;
    }

    public XQueryFilter evalWhere(@NotNull XQueryParser.WhereClauseContext ctx) {
        qc.inwhere = true;
        XQueryFilter ans = (XQueryFilter) visitor.visit(ctx.cond());
        qc.inwhere = false;
        return ans;
    }

    public XQueryList evalReturn(@NotNull XQueryParser.ReturnClauseContext ctx) {

        return (XQueryList) visitor.visit(ctx.xq());
    }
}
