package project1.xquery;


import project1.xquery.parser.*;
import project1.xquery.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;


import java.io.IOException;
import java.util.List;


public class XQueryExecutor {

    public static List<XMLElement> executeFromFile(String queryFilePath) throws IOException {
        XQueryLexer lexer = new XQueryLexer(new ANTLRFileStream(queryFilePath));
        XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));
        XQueryVisitor visitor = new myXQueryVisitor();
        XQueryParser.XqContext context = parser.xq();
        return (NodeTextList) visitor.visit(context);
    }

}
