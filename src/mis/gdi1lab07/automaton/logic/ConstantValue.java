package mis.gdi1lab07.automaton.logic;

public class ConstantValue<T> implements LogicExpression<T> {
	
	private boolean value;
	
	public ConstantValue(boolean value) {
		this.value = value;
	}

	@Override
	public boolean eval(T env) throws LogExpException {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		// TODO Auto-generated method stub
		if(value) handler.constTrue();
		else handler.constFalse();
	}

}
