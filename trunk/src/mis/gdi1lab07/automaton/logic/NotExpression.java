package mis.gdi1lab07.automaton.logic;

import mis.gdi1lab07.student.StudentLogExpPrettyPrinter;

public class NotExpression implements LogicExpression<Object> {

	LogicExpression<Object> exp;
	
	public NotExpression(LogicExpression<Object> exp) {
		this.exp = exp;
	}
	
	@Override
	public boolean eval(Object env) throws LogExpException {
		// TODO Auto-generated method stub
		return !(exp.eval(env));
	}

	@Override
	public void serialize(LogExpHandler handler) throws LogExpException {
		// TODO Auto-generated method stub
		handler.beginNegation();
		exp.serialize(handler);
		handler.endNegation();
	}
	
	public String toString() {
		StudentLogExpPrettyPrinter printer = new StudentLogExpPrettyPrinter();
		try {
			this.serialize(printer);
		} catch (LogExpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return printer.toString();
		
	}

}
