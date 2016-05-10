package project1.xquery.context;
import project1.xquery.xmlElement.XMLElement;
import project1.xquery.value.*;

/**
 * Encapsulates everything that has to do with the context of a query: from
 * handling where the engine is in the XML tree, to what XQuery variables are in scope.
 */
public class QueryContext {
    public SymbolTable st = new SymbolTable();
    private NodeContext nc = new NodeContext();
    public boolean [] letFilter = null;
    public QueryContext() {
    }

    public XQueryList peekContextElement() {
        return nc.peekContextElement();
    }

    public XQueryList popContextElement() {
        return nc.popContextElement();
    }

    public void pushContextElement(XMLElement elem) {
        nc.pushContextElement(elem);
    }

    public void pushContextElement(XQueryList elem) {
        nc.pushContextElement(elem);
    }

    public XQueryList getVar(String var) {
        return st.getVar(var);
    }

    public VarEnvironment pushVarEnv(VarEnvironment ve) {
        return st.pushVarEnv(ve);
    }

    public VarEnvironment popVarEnv() {
        return st.popVarEnv();
    }

    public VarEnvironment cloneVarEnv() {
        return st.cloneVarEnv();
    }
}
