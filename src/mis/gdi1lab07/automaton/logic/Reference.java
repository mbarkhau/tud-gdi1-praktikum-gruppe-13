package mis.gdi1lab07.automaton.logic;

public class Reference implements LogicExpression<BooleanVariables> {

	private String name;
	
	public Reference(String name) {
		this.name = name;
	}
	
	@Override
	public boolean eval(BooleanVariables env) throws LogExpException {
		// TODO Auto-generated method stub
		return env.get(name);
	}

	@Override
	public void serialize(LogExpHandler handler) throws LogExpException {
		// TODO Auto-generated method stub
		handler.variableReference(name);

	}

}
