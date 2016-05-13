package project1.xquery.context;
import project1.xquery.xmlElement.XMLElement;
import project1.xquery.value.*;

import java.util.*;


public class QueryContext {
    public SymbolTable st = new SymbolTable();
    private NodeContext nc = new NodeContext();
    public boolean [] letFilter = null;
    public List<String> whereVar2 = new ArrayList<String>();
    public boolean inwhere = false;


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

    public MyScope pushVarEnv(MyScope ve) {
        return st.pushVarEnv(ve);
    }

    public MyScope popVarEnv() {
        return st.popVarEnv();
    }

    public MyScope cloneVarEnv() {
        return st.cloneVarEnv();
    }

}
class SymbolTable {
    private Stack<MyScope> varEnv = new Stack<>();

    public SymbolTable() {
        varEnv.push(new MyScope());
    }

    public MyScope pushVarEnv(MyScope ve) {
        return varEnv.push(ve);
    }

    public XQueryList getVar(String var) {
        return varEnv.peek().getVar(var);
    }

    public MyScope popVarEnv() {
        return varEnv.pop();
    }

    public MyScope cloneVarEnv() {
        return varEnv.peek().copy();
    }
}
class NodeContext {
    private Stack<XQueryList> ctxElems = new Stack<>();
    public NodeContext() {

    }
    public XQueryList peekContextElement() {
        XQueryList res = new XQueryList(this.ctxElems.peek().size());
        for (XMLElement x : this.ctxElems.peek())
            if (x != null)
                res.add(x);
        return res;
    }

    public XQueryList popContextElement() {
        return this.ctxElems.pop();
    }

    public void pushContextElement(XMLElement elem) {
        this.ctxElems.push(new XQueryList(elem));
    }

    public void pushContextElement(XQueryList elem) {
        ctxElems.push(elem);
    }
}