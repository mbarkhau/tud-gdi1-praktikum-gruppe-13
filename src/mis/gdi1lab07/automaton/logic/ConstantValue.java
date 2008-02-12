package mis.gdi1lab07.automaton.logic;

public class ConstantValue implements LogicExpression {
	
	private boolean value;
	
	public ConstantValue(boolean value) {
		this.value = value;
	}

	@Override
	public boolean eval(Object env) throws LogExpException {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void serialize(LogExpHandler handler) throws LogExpException {
		// TODO Auto-generated method stub
		if(value) handler.constTrue();
		else handler.constFalse();
	}

}
