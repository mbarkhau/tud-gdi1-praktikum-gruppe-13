package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.LogExpBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;

import org.junit.Test;


public class TestLogExpBuilderTwo {

	@Test
	public void testAnd00() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("true", true);
		context.add("false", false);
		
		builder.beginAnd();
			builder.constTrue();
			builder.constTrue();
			builder.variableReference("true");
			builder.variableReference("true");
		builder.endAnd();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testAnd01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("true", true);
		context.add("false", false);
		
		builder.beginAnd();
		builder.constTrue();
		builder.constTrue();
			builder.beginAnd();
				builder.variableReference("false");
				builder.constFalse();
			builder.endAnd();
		builder.endAnd();
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testOr00() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("true", true);
		context.add("false", false);
		
		builder.beginOr();
		builder.constFalse();
		builder.constFalse();
		builder.endOr();
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testOr01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("true", true);
		context.add("false", false);
		
		builder.beginOr();
		builder.constFalse();
		builder.constFalse();
			builder.beginOr();
				builder.variableReference("false");
				builder.constTrue();
			builder.endOr();
		builder.endOr();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testNegation00() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("true", true);
		context.add("false", false);
		context.add("DerTutorHatImmerRecht", true);
		
		builder.beginNegation();
			builder.beginOr();
			builder.constFalse();
			builder.constFalse();
				builder.beginOr();
					builder.constFalse();
					builder.variableReference("DerTutorHatImmerRecht");
				builder.endOr();
			builder.endOr();
		builder.endNegation();
		
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testNegation01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("5und5ist11", false);
		StringBuilder str = new StringBuilder();
		str.append("5und5");
		str.append("ist11");
		
		builder.beginNegation();
			builder.beginOr();
			builder.constFalse();
			builder.constFalse();
				builder.beginNegation();
					builder.beginOr();
						builder.beginNegation();
							builder.variableReference(str.toString());
						builder.endNegation();
						builder.constTrue();
					builder.endOr();
				builder.endNegation();
			builder.endOr();
		builder.endNegation();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testAddExp() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("sonnenschein", false);
		context.add("huhu!", true);
		
		builder.beginNegation();
			builder.beginOr();
			builder.constFalse();
				builder.beginNegation();
					builder.beginOr();
						builder.beginNegation();
							builder.variableReference("sonnenschein");
						builder.endNegation();
						builder.constTrue();
					builder.endOr();
				builder.endNegation();
			builder.endOr();
		builder.endNegation();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(context));
	}
	
	@Test
	public void testSpezial() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		context.add("true", true);
		context.add("false", false);
		
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
											builder.variableReference("false");
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
						builder.variableReference("true");
						builder.constFalse();
					builder.endOr();
				builder.endAnd();
			builder.endNegation();
		builder.endNegation();
		
		System.out.println(builder.getLogExp().toString());
		assertTrue(builder.getLogExp().eval(context));
	}
	
}
