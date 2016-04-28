import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Created by kezhang on 4/24/16.
 */
public class AntlrDrinkListener extends DrinkBaseListener {


    @Override
    public void enterDrink(DrinkParser.DrinkContext ctx) {
        //System.out.println(ctx.getText());
        System.out.println("just enter the drink");

    }
    public void enterDrinkSentence(DrinkParser.DrinkSentenceContext ctx) {
        System.out.println("just enter the sentence");
        //System.out.println(ctx.getText());
    }

    @Override
    public void exitDrinkSentence(DrinkParser.DrinkSentenceContext ctx) {
        System.out.println("hey I exit the drinkSentence");

    }
    @Override
    public void exitDrink(DrinkParser.DrinkContext ctx) {
        System.out.println("hey I exit the Drink");
    }

    public void printDrink(String drinkSentence) {
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
        walker.walk(listener, drinkSentenceContext);
    }
    public static void main(String[] args){

        AntlrDrinkListener al = new AntlrDrinkListener();
        al.printDrink("a pint of drink");
    }

}

