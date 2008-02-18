package mis.gdi1lab07.automaton;

import java.util.ArrayList;
import java.util.List;

import mis.gdi1lab07.automaton.logic.LogExpException;

public class State<ENV> {

	private final String name;

	private List<StateTransition<ENV>> transitions = new ArrayList<StateTransition<ENV>>();

	public State(String name) {
		this.name = name;
	}

	public State<ENV> getTransition(ENV input) throws AutomatonException,
			LogExpException {
		State<ENV> result = null;
		for (StateTransition<ENV> trans : transitions) {
			if (trans.eval(input)) {
				if (result != null)
					throw new AutomatonException(
							"Multiple transitions possible.");
				result = trans.getTargetState();
			}
		}
		return (result == null) ? this : result;
	}

	/**
	 * @param input
	 * @param resultState
	 *            der folgezustand bei input
	 * @throws AutomatonException
	 */
	public void addTransition(StateTransition<ENV> transition)
			throws AutomatonException {
		if (transition.getStartState() != this)
			throw new AutomatonException(
					"Cannot add transition, which doesn't have this state as the start state.l");
		transitions.add(transition);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public void serialize(FSMHandler<ENV> handler) throws AutomatonException {
		for (StateTransition<ENV> trans : transitions) 
			handler.transition(trans.getStartState().toString(), trans
					.getTargetState().toString(), trans.getName(), trans
					.getExp());

	}
}
