// Generated from XQuery.g4 by ANTLR 4.5.3

package project1.xquery.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XQueryParser extends Parser {
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
	public static final int
		RULE_xq = 0, RULE_joinClause = 1, RULE_forClause = 2, RULE_letClause = 3, 
		RULE_whereClause = 4, RULE_returnClause = 5, RULE_cond = 6, RULE_ap = 7, 
		RULE_rp = 8, RULE_f = 9;
	public static final String[] ruleNames = {
		"xq", "joinClause", "forClause", "letClause", "whereClause", "returnClause", 
		"cond", "ap", "rp", "f"
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

	@Override
	public String getGrammarFileName() { return "XQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class XqContext extends ParserRuleContext {
		public XqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xq; }
	 
		public XqContext() { }
		public void copyFrom(XqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class XqApContext extends XqContext {
		public ApContext ap() {
			return getRuleContext(ApContext.class,0);
		}
		public XqApContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqAp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqAp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqAp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqVarContext extends XqContext {
		public TerminalNode Var() { return getToken(XQueryParser.Var, 0); }
		public XqVarContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqJoinContext extends XqContext {
		public JoinClauseContext joinClause() {
			return getRuleContext(JoinClauseContext.class,0);
		}
		public XqJoinContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqJoin(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqParenExprContext extends XqContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public XqParenExprContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqFLWRContext extends XqContext {
		public ForClauseContext forClause() {
			return getRuleContext(ForClauseContext.class,0);
		}
		public ReturnClauseContext returnClause() {
			return getRuleContext(ReturnClauseContext.class,0);
		}
		public LetClauseContext letClause() {
			return getRuleContext(LetClauseContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public XqFLWRContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqFLWR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqFLWR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqFLWR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqTagNameContext extends XqContext {
		public Token open;
		public Token close;
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public List<TerminalNode> Identifier() { return getTokens(XQueryParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(XQueryParser.Identifier, i);
		}
		public XqTagNameContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqTagName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqTagName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqTagName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqSlashContext extends XqContext {
		public Token slash;
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public XqSlashContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqSlash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqSlash(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqSlash(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqConcatContext extends XqContext {
		public XqContext left;
		public XqContext right;
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public XqConcatContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqConcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqConcat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqConcat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqStringConstantContext extends XqContext {
		public TerminalNode StringLiteral() { return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.StringLiteral, 0); }
		public XqStringConstantContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqStringConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqStringConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqStringConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XqLetContext extends XqContext {
		public LetClauseContext letClause() {
			return getRuleContext(LetClauseContext.class,0);
		}
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public XqLetContext(XqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterXqLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitXqLet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitXqLet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XqContext xq() throws RecognitionException {
		return xq(0);
	}

	private XqContext xq(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		XqContext _localctx = new XqContext(_ctx, _parentState);
		XqContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_xq, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			switch (_input.LA(1)) {
			case Var:
				{
				_localctx = new XqVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(21);
				match(Var);
				}
				break;
			case StringLiteral:
				{
				_localctx = new XqStringConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(StringLiteral);
				}
				break;
			case T__12:
			case T__13:
				{
				_localctx = new XqApContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(23);
				ap();
				}
				break;
			case LPAREN:
				{
				_localctx = new XqParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				match(LPAREN);
				setState(25);
				xq(0);
				setState(26);
				match(RPAREN);
				}
				break;
			case T__0:
				{
				_localctx = new XqTagNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				match(T__0);
				setState(29);
				((XqTagNameContext)_localctx).open = match(Identifier);
				setState(30);
				match(RANGLE);
				setState(31);
				match(LBRACE);
				setState(32);
				xq(0);
				setState(33);
				match(RBRACE);
				setState(34);
				match(T__1);
				setState(35);
				((XqTagNameContext)_localctx).close = match(Identifier);
				setState(36);
				match(RANGLE);
				}
				break;
			case T__3:
				{
				_localctx = new XqFLWRContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38);
				forClause();
				setState(40);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(39);
					letClause();
					}
				}

				setState(43);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(42);
					whereClause();
					}
				}

				setState(45);
				returnClause();
				}
				break;
			case T__5:
				{
				_localctx = new XqLetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(47);
				letClause();
				setState(48);
				xq(2);
				}
				break;
			case T__2:
				{
				_localctx = new XqJoinContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				joinClause();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(59);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new XqConcatContext(new XqContext(_parentctx, _parentState));
						((XqConcatContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(53);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(54);
						match(THEN);
						setState(55);
						((XqConcatContext)_localctx).right = xq(7);
						}
						break;
					case 2:
						{
						_localctx = new XqSlashContext(new XqContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(56);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(57);
						((XqSlashContext)_localctx).slash = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SLASH || _la==SSLASH) ) {
							((XqSlashContext)_localctx).slash = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(58);
						rp(0);
						}
						break;
					}
					}
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class JoinClauseContext extends ParserRuleContext {
		public XqContext xq1;
		public XqContext xq2;
		public Token idl1;
		public Token idl2;
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public List<TerminalNode> IdentifierList() { return getTokens(dk.martinbmadsen.xquery.parser.XQueryParser.IdentifierList); }
		public TerminalNode IdentifierList(int i) {
			return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.IdentifierList, i);
		}
		public JoinClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterJoinClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitJoinClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitJoinClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinClauseContext joinClause() throws RecognitionException {
		JoinClauseContext _localctx = new JoinClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_joinClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__2);
			setState(65);
			match(LPAREN);
			setState(66);
			((JoinClauseContext)_localctx).xq1 = xq(0);
			setState(67);
			match(THEN);
			setState(68);
			((JoinClauseContext)_localctx).xq2 = xq(0);
			setState(69);
			match(THEN);
			setState(70);
			((JoinClauseContext)_localctx).idl1 = match(IdentifierList);
			setState(71);
			match(THEN);
			setState(72);
			((JoinClauseContext)_localctx).idl2 = match(IdentifierList);
			setState(73);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForClauseContext extends ParserRuleContext {
		public List<TerminalNode> Var() { return getTokens(dk.martinbmadsen.xquery.parser.XQueryParser.Var); }
		public TerminalNode Var(int i) {
			return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.Var, i);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public ForClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterForClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitForClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitForClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForClauseContext forClause() throws RecognitionException {
		ForClauseContext _localctx = new ForClauseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_forClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__3);
			setState(76);
			match(Var);
			setState(77);
			match(T__4);
			setState(78);
			xq(0);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==THEN) {
				{
				{
				setState(79);
				match(THEN);
				setState(80);
				match(Var);
				setState(81);
				match(T__4);
				setState(82);
				xq(0);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetClauseContext extends ParserRuleContext {
		public List<TerminalNode> Var() { return getTokens(dk.martinbmadsen.xquery.parser.XQueryParser.Var); }
		public TerminalNode Var(int i) {
			return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.Var, i);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public LetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterLetClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitLetClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitLetClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetClauseContext letClause() throws RecognitionException {
		LetClauseContext _localctx = new LetClauseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_letClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__5);
			setState(89);
			match(Var);
			setState(90);
			match(ASSIGN);
			setState(91);
			xq(0);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==THEN) {
				{
				{
				setState(92);
				match(THEN);
				setState(93);
				match(Var);
				setState(94);
				match(ASSIGN);
				setState(95);
				xq(0);
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereClauseContext extends ParserRuleContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__6);
			setState(102);
			cond(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnClauseContext extends ParserRuleContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public ReturnClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterReturnClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitReturnClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitReturnClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnClauseContext returnClause() throws RecognitionException {
		ReturnClauseContext _localctx = new ReturnClauseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_returnClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__7);
			setState(105);
			xq(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }

		public CondContext() { }
		public void copyFrom(CondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CondEmptyContext extends CondContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public CondEmptyContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondValEqualContext extends CondContext {
		public XqContext left;
		public XqContext right;
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public CondValEqualContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondValEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondValEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondValEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondSomeSatisContext extends CondContext {
		public List<TerminalNode> Var() { return getTokens(dk.martinbmadsen.xquery.parser.XQueryParser.Var); }
		public TerminalNode Var(int i) {
			return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.Var, i);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondSomeSatisContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondSomeSatis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondSomeSatis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondSomeSatis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondIdEqualContext extends CondContext {
		public XqContext left;
		public XqContext right;
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public CondIdEqualContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondIdEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondIdEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondIdEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondParenExprContext extends CondContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondParenExprContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondAndContext extends CondContext {
		public CondContext left;
		public CondContext right;
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public CondAndContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondOrContext extends CondContext {
		public CondContext left;
		public CondContext right;
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public CondOrContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondNotContext extends CondContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondNotContext(CondContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterCondNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitCondNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitCondNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		return cond(0);
	}

	private CondContext cond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondContext _localctx = new CondContext(_ctx, _parentState);
		CondContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_cond, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new CondValEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(108);
				((CondValEqualContext)_localctx).left = xq(0);
				setState(109);
				_la = _input.LA(1);
				if ( !(_la==EQLS || _la==EQ) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(110);
				((CondValEqualContext)_localctx).right = xq(0);
				}
				break;
			case 2:
				{
				_localctx = new CondIdEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				((CondIdEqualContext)_localctx).left = xq(0);
				setState(113);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==IS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(114);
				((CondIdEqualContext)_localctx).right = xq(0);
				}
				break;
			case 3:
				{
				_localctx = new CondEmptyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(T__8);
				setState(117);
				xq(0);
				setState(118);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new CondSomeSatisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				match(T__9);
				setState(121);
				match(Var);
				setState(122);
				match(T__4);
				setState(123);
				xq(0);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==THEN) {
					{
					{
					setState(124);
					match(THEN);
					setState(125);
					match(Var);
					setState(126);
					match(T__4);
					setState(127);
					xq(0);
					}
					}
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(133);
				match(T__10);
				setState(134);
				cond(5);
				}
				break;
			case 5:
				{
				_localctx = new CondParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(LPAREN);
				setState(137);
				cond(0);
				setState(138);
				match(RPAREN);
				}
				break;
			case 6:
				{
				_localctx = new CondNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				match(T__11);
				setState(141);
				cond(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(152);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(150);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new CondAndContext(new CondContext(_parentctx, _parentState));
						((CondAndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(144);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(145);
						match(AND);
						setState(146);
						((CondAndContext)_localctx).right = cond(4);
						}
						break;
					case 2:
						{
						_localctx = new CondOrContext(new CondContext(_parentctx, _parentState));
						((CondOrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(147);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(148);
						match(OR);
						setState(149);
						((CondOrContext)_localctx).right = cond(3);
						}
						break;
					}
					}
				}
				setState(154);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ApContext extends ParserRuleContext {
		public Token fileName;
		public Token slash;
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.StringLiteral, 0); }
		public ApContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterAp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitAp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitAp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApContext ap() throws RecognitionException {
		ApContext _localctx = new ApContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ap);
		int _la;
		try {
			setState(165);
			switch (_input.LA(1)) {
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				match(T__12);
				setState(156);
				((ApContext)_localctx).fileName = match(StringLiteral);
				setState(157);
				match(RPAREN);
				setState(158);
				((ApContext)_localctx).slash = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SLASH || _la==SSLASH) ) {
					((ApContext)_localctx).slash = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(159);
				rp(0);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				match(T__13);
				setState(161);
				((ApContext)_localctx).fileName = match(StringLiteral);
				setState(162);
				match(RPAREN);
				setState(163);
				((ApContext)_localctx).slash = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SLASH || _la==SSLASH) ) {
					((ApContext)_localctx).slash = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(164);
				rp(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RpContext extends ParserRuleContext {
		public RpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rp; }

		public RpContext() { }
		public void copyFrom(RpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RpDotContext extends RpContext {
		public RpDotContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpDot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpDot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpTextContext extends RpContext {
		public RpTextContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpText(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpDotDotContext extends RpContext {
		public RpDotDotContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpDotDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpDotDot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpDotDot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpSlashContext extends RpContext {
		public RpContext left;
		public Token slash;
		public RpContext right;
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public RpSlashContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpSlash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpSlash(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpSlash(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpConcatContext extends RpContext {
		public RpContext left;
		public RpContext right;
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public RpConcatContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpConcat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpConcat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpConcat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpTagNameContext extends RpContext {
		public TerminalNode Identifier() { return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.Identifier, 0); }
		public RpTagNameContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpTagName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpTagName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpTagName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpFilterContext extends RpContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public RpFilterContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpWildcardContext extends RpContext {
		public RpWildcardContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpWildcard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpWildcard(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpAttrContext extends RpContext {
		public TerminalNode Identifier() { return getToken(dk.martinbmadsen.xquery.parser.XQueryParser.Identifier, 0); }
		public RpAttrContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpAttr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpAttr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RpParenExprContext extends RpContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public RpParenExprContext(RpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterRpParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitRpParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitRpParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RpContext rp() throws RecognitionException {
		return rp(0);
	}

	private RpContext rp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RpContext _localctx = new RpContext(_ctx, _parentState);
		RpContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_rp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			switch (_input.LA(1)) {
			case WILDCARD:
				{
				_localctx = new RpWildcardContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(168);
				match(WILDCARD);
				}
				break;
			case DOT:
				{
				_localctx = new RpDotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				match(DOT);
				}
				break;
			case UP:
				{
				_localctx = new RpDotDotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				match(UP);
				}
				break;
			case T__14:
				{
				_localctx = new RpTextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(T__14);
				}
				break;
			case Identifier:
				{
				_localctx = new RpTagNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				match(Identifier);
				}
				break;
			case T__15:
				{
				_localctx = new RpAttrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(173);
				match(T__15);
				setState(174);
				match(Identifier);
				}
				break;
			case LPAREN:
				{
				_localctx = new RpParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				match(LPAREN);
				setState(176);
				rp(0);
				setState(177);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(194);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(192);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new RpSlashContext(new RpContext(_parentctx, _parentState));
						((RpSlashContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(181);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(182);
						((RpSlashContext)_localctx).slash = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SLASH || _la==SSLASH) ) {
							((RpSlashContext)_localctx).slash = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(183);
						((RpSlashContext)_localctx).right = rp(4);
						}
						break;
					case 2:
						{
						_localctx = new RpConcatContext(new RpContext(_parentctx, _parentState));
						((RpConcatContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(184);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(185);
						match(THEN);
						setState(186);
						((RpConcatContext)_localctx).right = rp(2);
						}
						break;
					case 3:
						{
						_localctx = new RpFilterContext(new RpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(187);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(188);
						match(LBRACK);
						setState(189);
						f(0);
						setState(190);
						match(RBRACK);
						}
						break;
					}
					}
				}
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FContext extends ParserRuleContext {
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }

		public FContext() { }
		public void copyFrom(FContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FNotContext extends FContext {
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public FNotContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FRpContext extends FContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public FRpContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFRp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFRp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFRp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FIdEqualContext extends FContext {
		public RpContext left;
		public RpContext right;
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public FIdEqualContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFIdEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFIdEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFIdEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FParenContext extends FContext {
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public FParenContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFParen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FOrContext extends FContext {
		public FContext left;
		public FContext right;
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public FOrContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FValEqualContext extends FContext {
		public RpContext left;
		public RpContext right;
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public FValEqualContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFValEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFValEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFValEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FAndContext extends FContext {
		public FContext left;
		public FContext right;
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public FAndContext(FContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).enterFAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XQueryListener ) ((XQueryListener)listener).exitFAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XQueryVisitor ) return ((XQueryVisitor<? extends T>)visitor).visitFAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FContext f() throws RecognitionException {
		return f(0);
	}

	private FContext f(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FContext _localctx = new FContext(_ctx, _parentState);
		FContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_f, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new FRpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(198);
				rp(0);
				}
				break;
			case 2:
				{
				_localctx = new FValEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(199);
				((FValEqualContext)_localctx).left = rp(0);
				setState(200);
				_la = _input.LA(1);
				if ( !(_la==EQLS || _la==EQ) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(201);
				((FValEqualContext)_localctx).right = rp(0);
				}
				break;
			case 3:
				{
				_localctx = new FIdEqualContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(203);
				((FIdEqualContext)_localctx).left = rp(0);
				setState(204);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==IS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(205);
				((FIdEqualContext)_localctx).right = rp(0);
				}
				break;
			case 4:
				{
				_localctx = new FParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(207);
				match(LPAREN);
				setState(208);
				f(0);
				setState(209);
				match(RPAREN);
				}
				break;
			case 5:
				{
				_localctx = new FNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(211);
				match(T__11);
				setState(212);
				f(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(223);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(221);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new FAndContext(new FContext(_parentctx, _parentState));
						((FAndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(215);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(216);
						match(AND);
						setState(217);
						((FAndContext)_localctx).right = f(5);
						}
						break;
					case 2:
						{
						_localctx = new FOrContext(new FContext(_parentctx, _parentState));
						((FOrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(218);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(219);
						match(OR);
						setState(220);
						((FOrContext)_localctx).right = f(4);
						}
						break;
					}
					} 
				}
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return xq_sempred((XqContext)_localctx, predIndex);
		case 6:
			return cond_sempred((CondContext)_localctx, predIndex);
		case 8:
			return rp_sempred((RpContext)_localctx, predIndex);
		case 9:
			return f_sempred((FContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean xq_sempred(XqContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean cond_sempred(CondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean rp_sempred(RpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 1);
		case 6:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean f_sempred(FContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3-\u00e5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\5\2+\n\2\3\2\5\2.\n\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\66\n"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2>\n\2\f\2\16\2A\13\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4V\n\4\f\4"+
		"\16\4Y\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5c\n\5\f\5\16\5f\13\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0083\n\b\f\b\16\b\u0086\13\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0091\n\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u0099\n\b\f\b\16\b\u009c\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u00a8\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\5\n\u00b6\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00c3\n"+
		"\n\f\n\16\n\u00c6\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00d8\n\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\7\13\u00e0\n\13\f\13\16\13\u00e3\13\13\3\13\2\6\2\16\22\24"+
		"\f\2\4\6\b\n\f\16\20\22\24\2\5\3\2\"#\4\2  %%\4\2!!$$\u00ff\2\65\3\2\2"+
		"\2\4B\3\2\2\2\6M\3\2\2\2\bZ\3\2\2\2\ng\3\2\2\2\fj\3\2\2\2\16\u0090\3\2"+
		"\2\2\20\u00a7\3\2\2\2\22\u00b5\3\2\2\2\24\u00d7\3\2\2\2\26\27\b\2\1\2"+
		"\27\66\7*\2\2\30\66\7)\2\2\31\66\5\20\t\2\32\33\7\27\2\2\33\34\5\2\2\2"+
		"\34\35\7\30\2\2\35\66\3\2\2\2\36\37\7\3\2\2\37 \7+\2\2 !\7\36\2\2!\"\7"+
		"\31\2\2\"#\5\2\2\2#$\7\32\2\2$%\7\4\2\2%&\7+\2\2&\'\7\36\2\2\'\66\3\2"+
		"\2\2(*\5\6\4\2)+\5\b\5\2*)\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\5\n\6\2-,\3\2"+
		"\2\2-.\3\2\2\2./\3\2\2\2/\60\5\f\7\2\60\66\3\2\2\2\61\62\5\b\5\2\62\63"+
		"\5\2\2\4\63\66\3\2\2\2\64\66\5\4\3\2\65\26\3\2\2\2\65\30\3\2\2\2\65\31"+
		"\3\2\2\2\65\32\3\2\2\2\65\36\3\2\2\2\65(\3\2\2\2\65\61\3\2\2\2\65\64\3"+
		"\2\2\2\66?\3\2\2\2\678\f\b\2\289\7\26\2\29>\5\2\2\t:;\f\7\2\2;<\t\2\2"+
		"\2<>\5\22\n\2=\67\3\2\2\2=:\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\3\3"+
		"\2\2\2A?\3\2\2\2BC\7\5\2\2CD\7\27\2\2DE\5\2\2\2EF\7\26\2\2FG\5\2\2\2G"+
		"H\7\26\2\2HI\7,\2\2IJ\7\26\2\2JK\7,\2\2KL\7\30\2\2L\5\3\2\2\2MN\7\6\2"+
		"\2NO\7*\2\2OP\7\7\2\2PW\5\2\2\2QR\7\26\2\2RS\7*\2\2ST\7\7\2\2TV\5\2\2"+
		"\2UQ\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\7\3\2\2\2YW\3\2\2\2Z[\7\b"+
		"\2\2[\\\7*\2\2\\]\7\37\2\2]d\5\2\2\2^_\7\26\2\2_`\7*\2\2`a\7\37\2\2ac"+
		"\5\2\2\2b^\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2e\t\3\2\2\2fd\3\2\2\2"+
		"gh\7\t\2\2hi\5\16\b\2i\13\3\2\2\2jk\7\n\2\2kl\5\2\2\2l\r\3\2\2\2mn\b\b"+
		"\1\2no\5\2\2\2op\t\3\2\2pq\5\2\2\2q\u0091\3\2\2\2rs\5\2\2\2st\t\4\2\2"+
		"tu\5\2\2\2u\u0091\3\2\2\2vw\7\13\2\2wx\5\2\2\2xy\7\30\2\2y\u0091\3\2\2"+
		"\2z{\7\f\2\2{|\7*\2\2|}\7\7\2\2}\u0084\5\2\2\2~\177\7\26\2\2\177\u0080"+
		"\7*\2\2\u0080\u0081\7\7\2\2\u0081\u0083\5\2\2\2\u0082~\3\2\2\2\u0083\u0086"+
		"\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0087\u0088\7\r\2\2\u0088\u0089\5\16\b\7\u0089\u0091\3"+
		"\2\2\2\u008a\u008b\7\27\2\2\u008b\u008c\5\16\b\2\u008c\u008d\7\30\2\2"+
		"\u008d\u0091\3\2\2\2\u008e\u008f\7\16\2\2\u008f\u0091\5\16\b\3\u0090m"+
		"\3\2\2\2\u0090r\3\2\2\2\u0090v\3\2\2\2\u0090z\3\2\2\2\u0090\u008a\3\2"+
		"\2\2\u0090\u008e\3\2\2\2\u0091\u009a\3\2\2\2\u0092\u0093\f\5\2\2\u0093"+
		"\u0094\7&\2\2\u0094\u0099\5\16\b\6\u0095\u0096\f\4\2\2\u0096\u0097\7\'"+
		"\2\2\u0097\u0099\5\16\b\5\u0098\u0092\3\2\2\2\u0098\u0095\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\17\3\2\2"+
		"\2\u009c\u009a\3\2\2\2\u009d\u009e\7\17\2\2\u009e\u009f\7)\2\2\u009f\u00a0"+
		"\7\30\2\2\u00a0\u00a1\t\2\2\2\u00a1\u00a8\5\22\n\2\u00a2\u00a3\7\20\2"+
		"\2\u00a3\u00a4\7)\2\2\u00a4\u00a5\7\30\2\2\u00a5\u00a6\t\2\2\2\u00a6\u00a8"+
		"\5\22\n\2\u00a7\u009d\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a8\21\3\2\2\2\u00a9"+
		"\u00aa\b\n\1\2\u00aa\u00b6\7\25\2\2\u00ab\u00b6\7\23\2\2\u00ac\u00b6\7"+
		"\24\2\2\u00ad\u00b6\7\21\2\2\u00ae\u00b6\7+\2\2\u00af\u00b0\7\22\2\2\u00b0"+
		"\u00b6\7+\2\2\u00b1\u00b2\7\27\2\2\u00b2\u00b3\5\22\n\2\u00b3\u00b4\7"+
		"\30\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00a9\3\2\2\2\u00b5\u00ab\3\2\2\2\u00b5"+
		"\u00ac\3\2\2\2\u00b5\u00ad\3\2\2\2\u00b5\u00ae\3\2\2\2\u00b5\u00af\3\2"+
		"\2\2\u00b5\u00b1\3\2\2\2\u00b6\u00c4\3\2\2\2\u00b7\u00b8\f\5\2\2\u00b8"+
		"\u00b9\t\2\2\2\u00b9\u00c3\5\22\n\6\u00ba\u00bb\f\3\2\2\u00bb\u00bc\7"+
		"\26\2\2\u00bc\u00c3\5\22\n\4\u00bd\u00be\f\4\2\2\u00be\u00bf\7\33\2\2"+
		"\u00bf\u00c0\5\24\13\2\u00c0\u00c1\7\34\2\2\u00c1\u00c3\3\2\2\2\u00c2"+
		"\u00b7\3\2\2\2\u00c2\u00ba\3\2\2\2\u00c2\u00bd\3\2\2\2\u00c3\u00c6\3\2"+
		"\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\23\3\2\2\2\u00c6\u00c4"+
		"\3\2\2\2\u00c7\u00c8\b\13\1\2\u00c8\u00d8\5\22\n\2\u00c9\u00ca\5\22\n"+
		"\2\u00ca\u00cb\t\3\2\2\u00cb\u00cc\5\22\n\2\u00cc\u00d8\3\2\2\2\u00cd"+
		"\u00ce\5\22\n\2\u00ce\u00cf\t\4\2\2\u00cf\u00d0\5\22\n\2\u00d0\u00d8\3"+
		"\2\2\2\u00d1\u00d2\7\27\2\2\u00d2\u00d3\5\24\13\2\u00d3\u00d4\7\30\2\2"+
		"\u00d4\u00d8\3\2\2\2\u00d5\u00d6\7\16\2\2\u00d6\u00d8\5\24\13\3\u00d7"+
		"\u00c7\3\2\2\2\u00d7\u00c9\3\2\2\2\u00d7\u00cd\3\2\2\2\u00d7\u00d1\3\2"+
		"\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00e1\3\2\2\2\u00d9\u00da\f\6\2\2\u00da"+
		"\u00db\7&\2\2\u00db\u00e0\5\24\13\7\u00dc\u00dd\f\5\2\2\u00dd\u00de\7"+
		"\'\2\2\u00de\u00e0\5\24\13\6\u00df\u00d9\3\2\2\2\u00df\u00dc\3\2\2\2\u00e0"+
		"\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\25\3\2\2"+
		"\2\u00e3\u00e1\3\2\2\2\24*-\65=?Wd\u0084\u0090\u0098\u009a\u00a7\u00b5"+
		"\u00c2\u00c4\u00d7\u00df\u00e1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}