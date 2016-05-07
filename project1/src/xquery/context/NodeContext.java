package project1.xquery.context;
import java.util.Stack;

import project1.xquery.xmlElement.XMLElement;
import project1.xquery.value.*;


/**
 * This class keeps a context of node elements while visiting the XML tree and
 * evaluating different operators and queries.
 */
public class NodeContext {
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

    /**
     * Gets the current context xmlElement (WARNING: this pops it from the stack)
     * @return the {@link XMLElement} we are currently exploring
     */
    public XQueryList popContextElement() {
        return this.ctxElems.pop();
    }

    /**
     * Pushes an xmlElement/tree onto the context stack.
     * @param elem the tree/xmlElement to be added as context
     */
    public void pushContextElement(XMLElement elem) {
        this.ctxElems.push(new XQueryList(elem));
    }

    public void pushContextElement(XQueryList elem) {
        ctxElems.push(elem);
    }
}
