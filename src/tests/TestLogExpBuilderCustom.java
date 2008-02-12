package tests;

import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.LogExpBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLogExpBuilderCustom {

	@Test
	public void test01() throws LogExpException {
		LogExpBuilder<BooleanVariables> builder = ClassGetter.newLogicExpBuilder();
		BooleanVariables context = ClassGetter.newBooleanValues();
		
		builder.beginAnd();
		builder.beginOr();
			builder.constFalse();
			builder.constTrue();
		builder.endOr();
		builder.constFalse();
		builder.endAnd();
		
		System.out.println(builder.getLogExp().toString());
		assertFalse(builder.getLogExp().eval(context));
	}
}
