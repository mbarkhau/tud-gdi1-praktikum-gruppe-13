package mis.gdi1lab07.automaton.logic;

public class Reference<T extends BooleanVariables> implements LogicExpression<T> {

	private String name;
	
	public Reference(String name) {
		this.name = name;
	}
	
	@Override
	public boolean eval(T env) throws LogExpException {
		// TODO Auto-generated method stub
		return env.get(this.name);
	}

	@Override
	public void serialize(LogExpHandler<T> handler) throws LogExpException {
		// TODO Auto-generated method stub
		handler.variableReference(name);

	}

}
