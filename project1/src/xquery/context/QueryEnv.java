package project1.xquery.context;
import project1.xquery.xmlElement.XMLElement;
import project1.xquery.value.*;

import java.util.*;


public class QueryEnv {
    public boolean [] letFilter = null;
    public List<String> whereVar2 = new ArrayList<String>();
    private Stack<MyScope> varEnv = new Stack<>();
    public boolean inwhere = false;
    private Stack<XQueryList> ctxElems = new Stack<>();
    public List<String> getWhereVar2() {
        return whereVar2;
    }
    public void clearWhereVar2(){

    }
    public QueryEnv() {
        varEnv.push(new MyScope());
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

    public XQueryList getVar(String var) {
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

