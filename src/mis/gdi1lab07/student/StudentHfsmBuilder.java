package mis.gdi1lab07.student;

import java.util.Stack;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMBuilder;
import mis.gdi1lab07.automaton.logic.LogicExpression;

/**
 * This class must be implemented by students.
 */
public class StudentHfsmBuilder<T> implements HFSMBuilder<T> {

	private StudentHFSM<T> topHFSM;

	private Stack<StudentHFSM<T>> hfsms = new Stack<StudentHFSM<T>>();

	/**
	 * The empty standard constructor MUST be implemented in order to run
	 * automated tests. StudentHfsmBuilder MUST be fully functional after
	 * instantiation with this constructor!
	 */
	public StudentHfsmBuilder() {
		topHFSM = new StudentHFSM<T>();
		topHFSM.setName("Anonymous HFSM");
	}

	@Override
	public HFSM<T> getHFSM() throws AutomatonException {
		if (hfsms.size() != 0)
			throw new AutomatonException(
					"Couldn't get HFSM, hasn't finished building.");
		return topHFSM;
	}

	@Override
	public void beginState(String name, boolean isInitialState)
			throws AutomatonException {
		if (topHFSM.hasStates() && !isHierarchical())
			throw new AutomatonException(
					"Couldn't add substate, to a HFSM, which was initialized as a non hierarchical HFSM");
		
		StudentHFSM<T> newHFMS = new StudentHFSM<T>();
		if (hfsms.size() != 0) {
			StudentHFSM<T> curHFMS = hfsms.peek();
			curHFMS.addState(newHFMS);
			if (isInitialState) {
				if (curHFMS.getInitialState() != null)
					throw new AutomatonException(
							"Couldn't set HFSM as initial state, already exists.");
				curHFMS.setInitialState(newHFMS);
			}
		}else {
			topHFSM = newHFMS;
		}
		newHFMS.setName(name);
		hfsms.push(newHFMS);
	}

	@Override
	public void endState() throws AutomatonException {
		if (!isHierarchical())
			throw new AutomatonException(
					"Cannot end sub-state in a non hierarchical HFSM.");

		if (hfsms.size() > 0)
			hfsms.pop();
		else
			throw new AutomatonException(
					"Couldn't end state, all states have already been ended.");
	}

	@Override
	public void state(String name, boolean isInitialState)
			throws AutomatonException {
		StudentHFSM<T> newHFMS = new StudentHFSM<T>();
		newHFMS.setName(name);
		StudentHFSM<T> curHFMS = (isHierarchical()) ? hfsms.peek() : topHFSM;
		curHFMS.addState(newHFMS);
		
		if (isInitialState) {
			if (curHFMS.getInitialState() != null)
				throw new AutomatonException(
						"Couldn't set HFSM as initial state, already exists.");
			curHFMS.setInitialState(newHFMS);
		}
	}

	@Override
	public void transition(String startState, String targetState,
			String transitionName, LogicExpression<T> exp)
			throws AutomatonException {
		hfsms.peek()
				.addTransition(startState, targetState, transitionName, exp);
	}

	private boolean isHierarchical() {
		return (hfsms.size() > 0);
	}

}
