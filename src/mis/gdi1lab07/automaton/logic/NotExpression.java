package mis.gdi1lab07.automaton.logic;

import mis.gdi1lab07.student.StudentLogExpPrettyPrinter;

public class NotExpression<T> implements LogicExpression<T> {

	LogicExpression<T> exp;
	
	public NotExpression(LogicExpression<T> exp) {
		this.exp = exp;
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		// TODO Auto-generated method stub
		return !(exp.eval(env));
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		// TODO Auto-generated method stub
		handler.beginNegation();
		exp.serialize(handler);
		handler.endNegation();
	}
	
	public String toString() {
		StudentLogExpPrettyPrinter<T> printer = new StudentLogExpPrettyPrinter<T>();
		try {
			this.serialize(printer);
		} catch (LogExpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return printer.toString();
		
	}

}
