package project1.xquery;
import project1.xquery.*;

import java.util.*;


public class QueryEnv {
    public boolean [] letFilter = null;
    public List<String> whereVar2 = new ArrayList<String>();
    private Stack<MyScope> varEnv = new Stack<>();
    public boolean inwhere = false;
    private Stack<NodeTextList> ctxElems = new Stack<>();
    public List<String> getWhereVar2() {
        return whereVar2;
    }
    public void clearWhereVar2(){

    }
    public QueryEnv() {
        varEnv.push(new MyScope());
    }

    public NodeTextList peekContextElement() {
        NodeTextList res = new NodeTextList(this.ctxElems.peek().size());
        for (XMLElement x : this.ctxElems.peek())
            if (x != null)
                res.add(x);
        return res;
    }

    public NodeTextList popContextElement() {
        return this.ctxElems.pop();
    }

    public void pushContextElement(XMLElement elem) {
        this.ctxElems.push(new NodeTextList(elem));
    }

    public void pushContextElement(NodeTextList elem) {
        ctxElems.push(elem);
    }

    public NodeTextList getVar(String var) {
        return varEnv.peek().getVar(var);
    }

    public MyScope pushVarEnv(MyScope ve) {
        return varEnv.push(ve);
    }

    public MyScope popVarEnv() {
        return varEnv.pop();
    }

    public MyScope cloneVarEnv() {
        return varEnv.peek().copy();
    }

}

