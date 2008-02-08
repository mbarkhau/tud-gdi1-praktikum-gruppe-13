package tests;

import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.LogExpBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLogExpBuilderOne {
	
	@Test
	public void testAnd00() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginAnd();
		builder.constTrue();
		builder.constTrue();
		builder.endAnd();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(null));
	}
	
	@Test
	public void testAnd01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginAnd();
		builder.constTrue();
		builder.constTrue();
			builder.beginAnd();
				builder.constFalse();
				builder.constFalse();
			builder.endAnd();
		builder.endAnd();
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(null));
	}
	
	@Test
	public void testOr00() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginOr();
		builder.constFalse();
		builder.constFalse();
		builder.endOr();
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(null));
	}
	
	@Test
	public void testOr01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginOr();
		builder.constFalse();
		builder.constFalse();
			builder.beginOr();
				builder.constFalse();
				builder.constTrue();
			builder.endOr();
		builder.endOr();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(null));
	}
	
	@Test
	public void testNegation00() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginNegation();
			builder.beginOr();
			builder.constFalse();
			builder.constFalse();
				builder.beginOr();
					builder.constFalse();
					builder.constTrue();
				builder.endOr();
			builder.endOr();
		builder.endNegation();
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(null));
	}
	
	@Test
	public void testNegation01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginNegation();
			builder.beginOr();
			builder.constFalse();
			builder.constFalse();
				builder.beginNegation();
					builder.beginOr();
						builder.beginNegation();
							builder.constFalse();
						builder.endNegation();
						builder.constTrue();
					builder.endOr();
				builder.endNegation();
			builder.endOr();
		builder.endNegation();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(null));
	}
	
	@Test
	public void testSpezial() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		
		builder.beginNegation();
			builder.beginNegation();
				builder.beginAnd();
					builder.beginOr();
						builder.beginAnd();
							builder.beginNegation();
								builder.beginAnd();
									builder.beginOr();
										builder.constTrue();
										builder.beginOr();
											builder.constFalse();
											builder.constFalse();
										builder.endOr();
										builder.constFalse();
									builder.endOr();
									builder.constTrue();
									builder.beginAnd();
										builder.constFalse();
										builder.beginOr();
											builder.constFalse();
											builder.constFalse();
										builder.endOr();
										builder.constTrue();
										builder.constTrue();
									builder.endAnd();
									builder.beginAnd();
										builder.constTrue();
										builder.constTrue();
									builder.endAnd();
								builder.endAnd();
							builder.endNegation();
							builder.constTrue();
							builder.constTrue();
							builder.beginAnd();
								builder.constTrue();
								builder.constTrue();
							builder.endAnd();
							builder.constTrue();
						builder.endAnd();
						builder.constFalse();
					builder.endOr();
					builder.constTrue();
					builder.beginOr();
						builder.constTrue();
						builder.constFalse();
					builder.endOr();
				builder.endAnd();
			builder.endNegation();
		builder.endNegation();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(null));
	}
}
