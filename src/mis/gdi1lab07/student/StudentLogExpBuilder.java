package mis.gdi1lab07.student;

import java.util.Stack;

import mis.gdi1lab07.automaton.logic.AndExpression;
import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.ConstantValue;
import mis.gdi1lab07.automaton.logic.LogExpBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;
import mis.gdi1lab07.automaton.logic.NotExpression;
import mis.gdi1lab07.automaton.logic.OrExpression;
import mis.gdi1lab07.automaton.logic.Reference;

/**
 * This class must be implemented by students.
 */
public class StudentLogExpBuilder<ENV extends BooleanVariables> implements
		LogExpBuilder<ENV> {

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentLogicExpressionBuilder MUST be fully functional
	 * after instantiation with this constructor!
	 */

	// Werte, die auf dem Stack abzulegen sind.
	static final int OR = 0;
	static final int AND = 1;
	static final int NOT = 2;
	static final int PARAM_ONE = 3;
	static final int PARAM_TWO = 4;

	// Zu and, or und not entsprechende Strings
	String[] ExpressionNames = { "or", "and", "not" };

	Stack<Integer> ExpressionStack;
	Stack<LogicExpression<ENV>> ObjectStack;

	public StudentLogExpBuilder() {
		ExpressionStack = new Stack<Integer>();
		ObjectStack = new Stack<LogicExpression<ENV>>();
	}

	@Override
	public void beginAnd() throws LogExpException {
		combineParameters();
		updateParamCount();
		ExpressionStack.push(AND);

	}

	@Override
	public void beginNegation() throws LogExpException {
		combineParameters();
		updateParamCount();
		ExpressionStack.push(NOT);

	}

	@Override
	public void beginOr() throws LogExpException {
		combineParameters();
		updateParamCount();
		ExpressionStack.push(OR);
	}

	@Override
	public void constFalse() throws LogExpException {
		combineParameters();
		ObjectStack.add(new ConstantValue<ENV>(false));
		updateParamCount();
	}

	@Override
	public void constTrue() throws LogExpException {
		combineParameters();
		ObjectStack.add(new ConstantValue<ENV>(true));
		updateParamCount();

	}

	@Override
	public void endAnd() throws LogExpException {
		if (ExpressionStack.peek() <= PARAM_ONE)
			throw new LogExpException("Error: And needs two parameters!");
		else {
			removeParamCount();
			if (ExpressionStack.peek() == AND) {
				LogicExpression<ENV> exp1, exp2;
				exp2 = ObjectStack.pop();
				exp1 = ObjectStack.pop();
				ExpressionStack.pop();
				ObjectStack.push(new AndExpression<ENV>(exp1, exp2));
			} else
				throw new LogExpException(
						"Error: Called endAnd(), but last Expression was "
								+ ExpressionNames[ExpressionStack.peek()]);
		}
	}

	@Override
	public void endNegation() throws LogExpException {
		if (!(ExpressionStack.peek() == PARAM_ONE))
			throw new LogExpException("Error: Not needs one parameter!");
		else {
			removeParamCount();
			if (ExpressionStack.peek() == NOT) {
				LogicExpression<ENV> exp;
				exp = ObjectStack.pop();
				ExpressionStack.pop();
				ObjectStack.push(new NotExpression<ENV>(exp));
			} else
				throw new LogExpException(
						"Error: Called endAnd(), but last Expression was "
								+ ExpressionNames[ExpressionStack.peek()]);
		}
	}

	@Override
	public void endOr() throws LogExpException {
		if (ExpressionStack.peek() <= PARAM_ONE)
			throw new LogExpException("Error: Or needs two parameters!");
		else {
			removeParamCount();
			if (ExpressionStack.peek() == OR) {
				LogicExpression<ENV> exp1, exp2;
				exp2 = ObjectStack.pop();
				exp1 = ObjectStack.pop();
				ExpressionStack.pop();
				ObjectStack.push(new OrExpression<ENV>(exp1, exp2));
			} else
				throw new LogExpException(
						"Error: Called endOr(), but last Expression was "
								+ ExpressionNames[ExpressionStack.peek()]);
		}
	}

	@Override
	public LogicExpression<ENV> getLogExp() throws LogExpException {
		if ((ExpressionStack.size() == 0) && (ObjectStack.size() == 1))
			return ObjectStack.peek();
		else
			throw new LogExpException(
					"Der Ausdruck ist nicht korrekt fertiggestellt.");
	}

	public void variableReference(String name) throws LogExpException {
		combineParameters();
		ObjectStack.push(new Reference<ENV>(name));
		updateParamCount();

	}

	private void updateParamCount() throws LogExpException {
		if (!ExpressionStack.empty()) {
			int lastExp = ExpressionStack.peek();
			if (lastExp < PARAM_ONE) {
				ExpressionStack.push(PARAM_ONE);
			} else if (lastExp == PARAM_ONE) {
				ExpressionStack.pop();
				if (ExpressionStack.peek() == NOT) {
					throw new LogExpException(
							"Error: Only one param allowed for not.");
				}
				ExpressionStack.push(PARAM_TWO);
			}
		}
	}

	private void removeParamCount() {
		if (!ExpressionStack.empty()) {
			if (ExpressionStack.peek() >= PARAM_ONE) {
				ExpressionStack.pop();
			}
		}
	}

	private void combineParameters() {
		if (!ExpressionStack.empty()) {
			if (ExpressionStack.peek() == PARAM_TWO) {
				ExpressionStack.pop();
				LogicExpression<ENV> e2 = ObjectStack.pop(), e1 = ObjectStack
						.pop();
				switch (ExpressionStack.peek()) {
				case AND:
					ObjectStack.push(new AndExpression<ENV>(e1, e2));
					break;
				case OR:
					ObjectStack.push(new OrExpression<ENV>(e1, e2));
					break;
				}
				ExpressionStack.push(PARAM_TWO);
			}
		}
	}

}
