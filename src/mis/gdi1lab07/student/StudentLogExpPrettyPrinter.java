package mis.gdi1lab07.student;

import java.util.Stack;

import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;

public class StudentLogExpPrettyPrinter implements LogExpHandler {

	StringBuffer Expression;
	Stack<Integer> ExpressionStack; // Speichert die noch zu schlieﬂenden
	// Expressions

	// Werte, die auf dem Stack abzulegen sind.
	static final int OR = 0;
	static final int AND = 1;
	static final int NOT = 2;
	static final int WRITE_EXPRESSION = 3; // Gibt an, dass die Funktion noch
											// geschrieben werden muss.

	// Zu and, or und not entsprechende Strings
	String[] ExpressionNames = { "or", "and", "not", "WRITE_EXPRESSION" };

	public StudentLogExpPrettyPrinter() {
		Expression = new StringBuffer();
		ExpressionStack = new Stack<Integer>();
	}

	public void beginLogExp() throws LogExpException {
		// TODO Auto-generated method stub

	}

	public void endLogExp() throws LogExpException {
		// TODO Auto-generated method stub
		if (!ExpressionStack.isEmpty())
			throw new LogExpException(
					"There are still Expressions to end!\n Expression Stack is: "
							+ ExpressionStack.toString());
	}

	public void beginAnd() throws LogExpException {
		// TODO Auto-generated method stub
		ExpressionStack.push(AND);
		ExpressionStack.push(WRITE_EXPRESSION);
		Expression.append("(");

	}

	public void beginNegation() throws LogExpException {
		// TODO Auto-generated method stub
		ExpressionStack.push(NOT);
		Expression.append("(not ");

	}

	public void beginOr() throws LogExpException {
		// TODO Auto-generated method stub
		ExpressionStack.push(OR);
		ExpressionStack.push(WRITE_EXPRESSION);
		Expression.append("(");
	}

	public void constFalse() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == WRITE_EXPRESSION) {
			ExpressionStack.pop();
			Expression.append("false "
					+ ExpressionNames[ExpressionStack.peek()] + " ");
		} else {
			Expression.append("false");
		}

	}

	public void constTrue() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == WRITE_EXPRESSION) {
			ExpressionStack.pop();
			Expression.append("true " + ExpressionNames[ExpressionStack.peek()]
					+ " ");
		} else {
			Expression.append("true");
		}
	}

	public void endAnd() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == AND) {
			ExpressionStack.pop();
			Expression.append(")");
			printExpression();
		} else
			throw new LogExpException(
					"Error: called endAnd(), but last Expression was "
							+ ExpressionNames[ExpressionStack.peek()]);
	}

	public void endNegation() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == NOT) {
			ExpressionStack.pop();
			Expression.append(")");
			printExpression();
		} else
			throw new LogExpException(
					"Error: called endNegation(), but last Expression was "
							+ ExpressionNames[ExpressionStack.peek()]);
	}

	public void endOr() throws LogExpException {
		// TODO Auto-generated method stub
		if (ExpressionStack.peek() == OR) {
			ExpressionStack.pop();
			Expression.append(")");
			printExpression();
		} else
			throw new LogExpException(
					"Error: called endOr(), but last Expression was "
							+ ExpressionNames[ExpressionStack.peek()]);
	}

	public void variableReference(String name) throws LogExpException {
		// TODO Auto-generated method stub
		Expression.append(name);
		printExpression();
	}

	public void printExpression() {
		if (!ExpressionStack.empty()) {
			if (ExpressionStack.peek() == WRITE_EXPRESSION) {
				ExpressionStack.pop();
				Expression.append(" " + ExpressionNames[ExpressionStack.peek()]
						+ " ");
			}
		}
	}

	public String toString() {
		return Expression.toString();
	}

}
