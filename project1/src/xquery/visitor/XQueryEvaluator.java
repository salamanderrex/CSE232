package project1.xquery.visitor;


import project1.xquery.value.*;
import project1.xquery.visitor.*;
import project1.xquery.context.*;
import project1.xquery.xmltree.*;
import project1.xquery.parser.*;

public class XQueryEvaluator {
    protected XQueryBaseVisitor<IXQueryValue> visitor;
    protected QueryContext qc;

    protected XQueryEvaluator(XQueryBaseVisitor<IXQueryValue> visitor, QueryContext qc) {
        this.visitor = visitor;
        this.qc = qc;
    }
}
