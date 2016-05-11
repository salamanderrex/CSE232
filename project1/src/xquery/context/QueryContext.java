package project1.xquery.context;
import project1.xquery.xmlElement.XMLElement;
import project1.xquery.value.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Encapsulates everything that has to do with the context of a query: from
 * handling where the engine is in the XML tree, to what XQuery variables are in scope.
 */
public class QueryContext {
    public SymbolTable st = new SymbolTable();
    private NodeContext nc = new NodeContext();
    public boolean [] letFilter = null;
//    public Set<String> whereVar = new HashSet<String>();
    public List<String> whereVar2 = new ArrayList<String>();
    public boolean inwhere = false;

//    public Set<String> getWhereVar() {
//        return this.whereVar;
//    }
//    public void clearWhereVar(){
//        whereVar.clear();
//    }

    public List<String> getWhereVar2() {
        return whereVar2;
    }
    public void clearWhereVar2(){

    }

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
