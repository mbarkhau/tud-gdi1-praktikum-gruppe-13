package mis.gdi1lab07.student;

import java.util.Stack;

import mis.gdi1lab07.automaton.logic.*;

/**
 * This class must be implemented by students.
 */
public class StudentLogExpBuilder implements LogExpBuilder<BooleanVariables> {

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentLogicExpressionBuilder MUST be fully functional
	 * after instantiation with this constructor!
	 */

	// Werte, die auf dem Stack abzulegen sind.
	static final int OR = 0;
	static final int AND = 1;
	static final int NOT = 2;

	// Zu and, or und not entsprechende Strings
	String[] ExpressionNames = { "or", "and", "not" };

	Stack<Integer> ExpressionStack;
	Stack<LogicExpression> ObjectStack;

	public StudentLogExpBuilder() {
		// TODO This constructor MUST be implemented correctly!!
		ExpressionStack = new Stack<Integer>();
		ObjectStack = new Stack<LogicExpression>();
	}

	@Override
	public void beginAnd() throws LogExpException {
		// TODO Auto-generated method stub
		ExpressionStack.push(AND);

	}

	@Override
	public void beginNegation() throws LogExpException {
		// TODO Auto-generated method stub
		ExpressionStack.push(NOT);

	}

	@Override
	public void beginOr() throws LogExpException {
		// TODO Auto-generated method stub
		ExpressionStack.push(OR);
	}

	@Override
	public void constFalse() throws LogExpException {
		// TODO Auto-generated method stub
		ObjectStack.add(new ConstantValue(false));
	}

	@Override
	public void constTrue() throws LogExpException {
		// TODO Auto-generated method stub
		ObjectStack.add(new ConstantValue(true));

	}

	@Override
	public void endAnd() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == AND) {
			LogicExpression exp1, exp2;
			exp2 = ObjectStack.pop();
			exp1 = ObjectStack.pop();
			ExpressionStack.pop();
			ObjectStack.push(new AndExpression(exp1, exp2));
		} else
			throw new LogExpException(
					"Error: Called endAnd(), but last Expression was "
							+ ExpressionNames[ExpressionStack.peek()]);
	}

	@Override
	public void endNegation() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == NOT) {
			LogicExpression exp;
			exp = ObjectStack.pop();
			ExpressionStack.pop();
			ObjectStack.push(new NotExpression(exp));
		} else
			throw new LogExpException(
					"Error: Called endAnd(), but last Expression was "
							+ ExpressionNames[ExpressionStack.peek()]);
	}

	@Override
	public void endOr() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == OR) {
			LogicExpression exp1, exp2;
			exp2 = ObjectStack.pop();
			exp1 = ObjectStack.pop();
			ExpressionStack.pop();
			ObjectStack.push(new OrExpression(exp1, exp2));
		} else
			throw new LogExpException(
					"Error: Called endOr(), but last Expression was "
							+ ExpressionNames[ExpressionStack.peek()]);
	}

	@Override
	public LogicExpression<BooleanVariables> getLogExp() throws LogExpException {
		// TODO Auto-generated method stub
		return ObjectStack.peek();
	}

	public void variableReference(String name) throws LogExpException {
		// TODO Auto-generated method stub
		ObjectStack.push(new Reference(name));

	}

}
