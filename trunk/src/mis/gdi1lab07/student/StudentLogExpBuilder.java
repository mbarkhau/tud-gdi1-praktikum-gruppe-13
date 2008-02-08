package mis.gdi1lab07.student;

import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.LogExpBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentLogExpBuilder implements LogExpBuilder<BooleanVariables> {

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentLogicExpressionBuilder MUST be fully functional
	 * after instantiation with this constructor!
	 */
	public StudentLogExpBuilder() {
		// TODO This constructor MUST be implemented correctly!!
	}

	@Override
	public void beginAnd() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginNegation() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginOr() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void constFalse() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void constTrue() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endAnd() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endNegation() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endOr() throws LogExpException {
		// TODO Auto-generated method stub

	}

	@Override
	public LogicExpression<BooleanVariables> getLogExp() throws LogExpException {
		// TODO Auto-generated method stub
		return null;
	}

	public void variableReference(String name) throws LogExpException {
		// TODO Auto-generated method stub

	}

}
