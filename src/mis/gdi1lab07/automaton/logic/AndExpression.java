package mis.gdi1lab07.automaton.logic;

import mis.gdi1lab07.student.StudentLogExpPrettyPrinter;

public class AndExpression<T> implements LogicExpression<T> {

	private LogicExpression<T> e1, e2;
	
	public AndExpression(LogicExpression<T> e1, LogicExpression<T> e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		// TODO Auto-generated method stub
		return e1.eval(env) && e2.eval(env);
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		// TODO Auto-generated method stub
		handler.beginAnd();
		e1.serialize(handler);
		e2.serialize(handler);
		handler.endAnd();

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
