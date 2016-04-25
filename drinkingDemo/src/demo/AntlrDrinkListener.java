package demo;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Created by kezhang on 4/24/16.
 */
public class AntlrDrinkListener extends DrinkBaseListener {

    @Override
    public void enterDrink(DrinkParser.DrinkContext ctx) {
        System.out.println(ctx.getText());
    }

    private void printDrink(String drinkSentence) {
        // Get our lexer
        DrinkLexer lexer = new DrinkLexer(new ANTLRInputStream(drinkSentence));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        DrinkParser parser = new DrinkParser(tokens);

        // Specify our entry point
        DrinkParser.DrinkSentenceContext drinkSentenceContext = parser.drinkSentence();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        AntlrDrinkListener listener = new AntlrDrinkListener();
        walker.walk(listener, DrinkParser.drinkSentenceContext);
    }
    public static void main(String[] args){
        System.out.print("hello");
    }
}