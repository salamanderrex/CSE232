// Generated from XQuery.g4 by ANTLR 4.5.3

package dk.martinbmadsen.xquery.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XQueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, DOT=17, 
		UP=18, WILDCARD=19, THEN=20, LPAREN=21, RPAREN=22, LBRACE=23, RBRACE=24, 
		LBRACK=25, RBRACK=26, LANGLE=27, RANGLE=28, ASSIGN=29, EQLS=30, EQUAL=31, 
		SLASH=32, SSLASH=33, IS=34, EQ=35, AND=36, OR=37, NOT=38, StringLiteral=39, 
		Var=40, Identifier=41, IdentifierList=42, WS=43;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "DOT", "UP", 
		"WILDCARD", "THEN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", 
		"RBRACK", "LANGLE", "RANGLE", "ASSIGN", "EQLS", "EQUAL", "SLASH", "SSLASH", 
		"IS", "EQ", "AND", "OR", "NOT", "StringLiteral", "StringCharacters", "SPACE", 
		"StringCharacter", "Var", "Identifier", "IdentifierList", "Letter", "LetterOrDigit", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'<'", "'</'", "'join'", "'for'", "'in'", "'let'", "'where'", "'return'", 
		"'empty('", "'some'", "'satisfies'", "'not '", "'doc('", "'document('", 
		"'text()'", "'@'", "'.'", "'..'", "'*'", "','", "'('", "')'", "'{'", "'}'", 
		"'['", "']'", null, "'>'", "':='", "'='", "'=='", "'/'", "'//'", "'is'", 
		"'eq'", "'and'", "'or'", "'not'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "DOT", "UP", "WILDCARD", "THEN", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "LANGLE", "RANGLE", 
		"ASSIGN", "EQLS", "EQUAL", "SLASH", "SSLASH", "IS", "EQ", "AND", "OR", 
		"NOT", "StringLiteral", "Var", "Identifier", "IdentifierList", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public XQueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2-\u0121\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\5\34\u00d1\n\34\3\35\3\35\3\36"+
		"\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3"+
		"%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\5(\u00f5\n(\3(\3(\3)\6)\u00fa\n"+
		")\r)\16)\u00fb\3*\3*\3+\3+\3,\3,\3,\3-\3-\7-\u0107\n-\f-\16-\u010a\13"+
		"-\3.\3.\3.\3.\7.\u0110\n.\f.\16.\u0113\13.\3.\3.\3/\3/\3\60\3\60\3\61"+
		"\6\61\u011c\n\61\r\61\16\61\u011d\3\61\3\61\2\2\62\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q\2S\2U\2W*Y+[,]\2_\2a-\3\2\7\4\2\16\16\"\"\5\2$$BB^^\5\2C\\aac|\7"+
		"\2//\62;C\\aac|\5\2\13\f\17\17\"\"\u0121\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2a\3\2\2\2\3c\3\2\2"+
		"\2\5e\3\2\2\2\7h\3\2\2\2\tm\3\2\2\2\13q\3\2\2\2\rt\3\2\2\2\17x\3\2\2\2"+
		"\21~\3\2\2\2\23\u0085\3\2\2\2\25\u008c\3\2\2\2\27\u0091\3\2\2\2\31\u009b"+
		"\3\2\2\2\33\u00a0\3\2\2\2\35\u00a5\3\2\2\2\37\u00af\3\2\2\2!\u00b6\3\2"+
		"\2\2#\u00b8\3\2\2\2%\u00ba\3\2\2\2\'\u00bd\3\2\2\2)\u00bf\3\2\2\2+\u00c1"+
		"\3\2\2\2-\u00c3\3\2\2\2/\u00c5\3\2\2\2\61\u00c7\3\2\2\2\63\u00c9\3\2\2"+
		"\2\65\u00cb\3\2\2\2\67\u00d0\3\2\2\29\u00d2\3\2\2\2;\u00d4\3\2\2\2=\u00d7"+
		"\3\2\2\2?\u00d9\3\2\2\2A\u00dc\3\2\2\2C\u00de\3\2\2\2E\u00e1\3\2\2\2G"+
		"\u00e4\3\2\2\2I\u00e7\3\2\2\2K\u00eb\3\2\2\2M\u00ee\3\2\2\2O\u00f2\3\2"+
		"\2\2Q\u00f9\3\2\2\2S\u00fd\3\2\2\2U\u00ff\3\2\2\2W\u0101\3\2\2\2Y\u0104"+
		"\3\2\2\2[\u010b\3\2\2\2]\u0116\3\2\2\2_\u0118\3\2\2\2a\u011b\3\2\2\2c"+
		"d\7>\2\2d\4\3\2\2\2ef\7>\2\2fg\7\61\2\2g\6\3\2\2\2hi\7l\2\2ij\7q\2\2j"+
		"k\7k\2\2kl\7p\2\2l\b\3\2\2\2mn\7h\2\2no\7q\2\2op\7t\2\2p\n\3\2\2\2qr\7"+
		"k\2\2rs\7p\2\2s\f\3\2\2\2tu\7n\2\2uv\7g\2\2vw\7v\2\2w\16\3\2\2\2xy\7y"+
		"\2\2yz\7j\2\2z{\7g\2\2{|\7t\2\2|}\7g\2\2}\20\3\2\2\2~\177\7t\2\2\177\u0080"+
		"\7g\2\2\u0080\u0081\7v\2\2\u0081\u0082\7w\2\2\u0082\u0083\7t\2\2\u0083"+
		"\u0084\7p\2\2\u0084\22\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087\7o\2\2\u0087"+
		"\u0088\7r\2\2\u0088\u0089\7v\2\2\u0089\u008a\7{\2\2\u008a\u008b\7*\2\2"+
		"\u008b\24\3\2\2\2\u008c\u008d\7u\2\2\u008d\u008e\7q\2\2\u008e\u008f\7"+
		"o\2\2\u008f\u0090\7g\2\2\u0090\26\3\2\2\2\u0091\u0092\7u\2\2\u0092\u0093"+
		"\7c\2\2\u0093\u0094\7v\2\2\u0094\u0095\7k\2\2\u0095\u0096\7u\2\2\u0096"+
		"\u0097\7h\2\2\u0097\u0098\7k\2\2\u0098\u0099\7g\2\2\u0099\u009a\7u\2\2"+
		"\u009a\30\3\2\2\2\u009b\u009c\7p\2\2\u009c\u009d\7q\2\2\u009d\u009e\7"+
		"v\2\2\u009e\u009f\7\"\2\2\u009f\32\3\2\2\2\u00a0\u00a1\7f\2\2\u00a1\u00a2"+
		"\7q\2\2\u00a2\u00a3\7e\2\2\u00a3\u00a4\7*\2\2\u00a4\34\3\2\2\2\u00a5\u00a6"+
		"\7f\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7e\2\2\u00a8\u00a9\7w\2\2\u00a9"+
		"\u00aa\7o\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad\7v\2\2"+
		"\u00ad\u00ae\7*\2\2\u00ae\36\3\2\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7"+
		"g\2\2\u00b1\u00b2\7z\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7*\2\2\u00b4\u00b5"+
		"\7+\2\2\u00b5 \3\2\2\2\u00b6\u00b7\7B\2\2\u00b7\"\3\2\2\2\u00b8\u00b9"+
		"\7\60\2\2\u00b9$\3\2\2\2\u00ba\u00bb\7\60\2\2\u00bb\u00bc\7\60\2\2\u00bc"+
		"&\3\2\2\2\u00bd\u00be\7,\2\2\u00be(\3\2\2\2\u00bf\u00c0\7.\2\2\u00c0*"+
		"\3\2\2\2\u00c1\u00c2\7*\2\2\u00c2,\3\2\2\2\u00c3\u00c4\7+\2\2\u00c4.\3"+
		"\2\2\2\u00c5\u00c6\7}\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7\177\2\2\u00c8"+
		"\62\3\2\2\2\u00c9\u00ca\7]\2\2\u00ca\64\3\2\2\2\u00cb\u00cc\7_\2\2\u00cc"+
		"\66\3\2\2\2\u00cd\u00d1\7>\2\2\u00ce\u00cf\7>\2\2\u00cf\u00d1\7\61\2\2"+
		"\u00d0\u00cd\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d18\3\2\2\2\u00d2\u00d3\7"+
		"@\2\2\u00d3:\3\2\2\2\u00d4\u00d5\7<\2\2\u00d5\u00d6\7?\2\2\u00d6<\3\2"+
		"\2\2\u00d7\u00d8\7?\2\2\u00d8>\3\2\2\2\u00d9\u00da\7?\2\2\u00da\u00db"+
		"\7?\2\2\u00db@\3\2\2\2\u00dc\u00dd\7\61\2\2\u00ddB\3\2\2\2\u00de\u00df"+
		"\7\61\2\2\u00df\u00e0\7\61\2\2\u00e0D\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2"+
		"\u00e3\7u\2\2\u00e3F\3\2\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7s\2\2\u00e6"+
		"H\3\2\2\2\u00e7\u00e8\7c\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7f\2\2\u00ea"+
		"J\3\2\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed\7t\2\2\u00edL\3\2\2\2\u00ee\u00ef"+
		"\7p\2\2\u00ef\u00f0\7q\2\2\u00f0\u00f1\7v\2\2\u00f1N\3\2\2\2\u00f2\u00f4"+
		"\7$\2\2\u00f3\u00f5\5Q)\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f7\7$\2\2\u00f7P\3\2\2\2\u00f8\u00fa\5U+\2\u00f9"+
		"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fcR\3\2\2\2\u00fd\u00fe\t\2\2\2\u00feT\3\2\2\2\u00ff\u0100\n\3"+
		"\2\2\u0100V\3\2\2\2\u0101\u0102\7&\2\2\u0102\u0103\5Y-\2\u0103X\3\2\2"+
		"\2\u0104\u0108\5]/\2\u0105\u0107\5_\60\2\u0106\u0105\3\2\2\2\u0107\u010a"+
		"\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109Z\3\2\2\2\u010a"+
		"\u0108\3\2\2\2\u010b\u010c\7]\2\2\u010c\u0111\5Y-\2\u010d\u010e\7.\2\2"+
		"\u010e\u0110\5Y-\2\u010f\u010d\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f"+
		"\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0114\3\2\2\2\u0113\u0111\3\2\2\2\u0114"+
		"\u0115\7_\2\2\u0115\\\3\2\2\2\u0116\u0117\t\4\2\2\u0117^\3\2\2\2\u0118"+
		"\u0119\t\5\2\2\u0119`\3\2\2\2\u011a\u011c\t\6\2\2\u011b\u011a\3\2\2\2"+
		"\u011c\u011d\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f"+
		"\3\2\2\2\u011f\u0120\b\61\2\2\u0120b\3\2\2\2\t\2\u00d0\u00f4\u00fb\u0108"+
		"\u0111\u011d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}