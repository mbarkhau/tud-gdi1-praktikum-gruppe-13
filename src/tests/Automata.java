package tests;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.FSMBuilder;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMBuilder;
import mis.gdi1lab07.automaton.logic.LogicExpression;



class Automata implements Constants {
	
	static FSM<Integer> buildFSM00() throws AutomatonException {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
	
		fsmBuilder.beginFSM();
		fsmBuilder.state(a0_s0, true);
		fsmBuilder.state(a0_s1, false);
		fsmBuilder.state(a0_s2, false);
		fsmBuilder.state(a0_s3, false);
		fsmBuilder.state(a0_s4, false);
		fsmBuilder.state(a0_s5, false);

		fsmBuilder.transition(a0_s0, a0_s1, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s1, a0_s2, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s2, a0_s3, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s3, a0_s4, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s4, a0_s1, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s2, a0_s4, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a0_s4, a0_s3, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a0_s3, a0_s2, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a0_s2, a0_s5, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a0_s5, a0_s3, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a0_s3, a0_s1, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a0_s2, a0_s1, "42", ClassGetter.newIntEq(42));
		fsmBuilder.endFSM();
		
		return fsmBuilder.getFSM();
	}
	
	static FSM<Integer> buildFSM01() throws AutomatonException {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		
		fsmBuilder.beginFSM();
		fsmBuilder.state(a1_s0, true);
		fsmBuilder.state(a1_s1, false);
		fsmBuilder.state(a1_s2, false);
		fsmBuilder.state(a1_s3, false);
		fsmBuilder.state(a1_s4, false);
		fsmBuilder.state(a1_s5, false);
		fsmBuilder.state(a1_s6, false);
		fsmBuilder.state(a1_s7, false);
		fsmBuilder.state(a1_s8, false);
		fsmBuilder.state(a1_s9, false);
		
		fsmBuilder.transition(a1_s0, a1_s1, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s1, a1_s2, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s2, a1_s3, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s3, a1_s0, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s2, a1_s4, "42", ClassGetter.newIntEq(42));
		fsmBuilder.transition(a1_s2, a1_s7, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a1_s4, a1_s6, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s4, a1_s5, "6", ClassGetter.newIntEq(6));
		fsmBuilder.transition(a1_s5, a1_s6, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a1_s6, a1_s5, "42", ClassGetter.newIntEq(42));
		fsmBuilder.transition(a1_s7, a1_s8, "6", ClassGetter.newIntEq(6));
		fsmBuilder.transition(a1_s8, a1_s7, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s7, a1_s9, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a1_s9, a1_s7, "6", ClassGetter.newIntEq(6));
		fsmBuilder.transition(a1_s8, a1_s9, "6", ClassGetter.newIntEq(6));
		fsmBuilder.transition(a1_s9, a1_s8, "5", ClassGetter.newIntEq(5));
		fsmBuilder.endFSM();
		
		return fsmBuilder.getFSM();
	}

	static FSM<Integer> buildFSM02() throws AutomatonException {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		
		fsmBuilder.beginFSM();
		fsmBuilder.state(a0_s0, true);
		fsmBuilder.state(a0_s1, false);
		fsmBuilder.state(a0_s2, false);
		fsmBuilder.state(a0_s3, false);
		fsmBuilder.state(a0_s4, false);
		fsmBuilder.state(a0_s5, false);

		fsmBuilder.transition(a0_s0, a0_s1, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s1, a0_s2, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s2, a0_s3, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s3, a0_s4, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s4, a0_s1, "333", ClassGetter.newIntEq(333));
		fsmBuilder.transition(a0_s2, a0_s4, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a0_s4, a0_s3, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a0_s3, a0_s2, "23", ClassGetter.newIntEq(23));
		fsmBuilder.transition(a0_s2, a0_s5, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a0_s5, a0_s3, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a0_s3, a0_s1, "5", ClassGetter.newIntEq(5));
		fsmBuilder.transition(a0_s2, a0_s1, "42", ClassGetter.newIntEq(42));
		fsmBuilder.transition(a0_s4, a0_s4, "42", ClassGetter.newIntEq(42));
		fsmBuilder.transition(a0_s0, a0_s0, "42", ClassGetter.newIntEq(42));
		fsmBuilder.endFSM();

		return fsmBuilder.getFSM();
	}
	
	static HFSM<Integer> buildHFSM01() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();

		// Alpha
		hfsmBuilder.beginState(A, true);

		// A
		hfsmBuilder.beginState(a2_s0, true);
		hfsmBuilder.state(a3_s0, true);
		hfsmBuilder.state(a3_s1, false);
		hfsmBuilder.state(a3_s2, false);
		hfsmBuilder.transition(a3_s0, a3_s1, "439", ClassGetter.newIntEq(439));
		hfsmBuilder.transition(a3_s0, a3_s2, "33", ClassGetter.newIntEq(33));
		hfsmBuilder.transition(a3_s1, a3_s2, "111", ClassGetter.newIntEq(111));
		hfsmBuilder.transition(a3_s2, a3_s0, "448", ClassGetter.newIntEq(448));
		hfsmBuilder.endState();

		// B
		hfsmBuilder.beginState(a2_s1, false);
			hfsmBuilder.beginState(a4_s0, true);
				hfsmBuilder.state(a6_s0, true);
				hfsmBuilder.state(a6_s1, false);
				hfsmBuilder.state(a6_s2, false);
				hfsmBuilder.transition(a6_s0, a6_s2, "44", ClassGetter.newIntEq(44));
				hfsmBuilder.transition(a6_s2, a6_s1, "45", ClassGetter.newIntEq(45));
				hfsmBuilder.transition(a6_s1, a6_s0, "44", ClassGetter.newIntEq(44));
			hfsmBuilder.endState();
		hfsmBuilder.state(a4_s1, false);
		hfsmBuilder.state(a4_s2, false);
		hfsmBuilder.state(a4_s3, false);
		hfsmBuilder.transition(a4_s0, a4_s3, "11", ClassGetter.newIntEq(11));
		hfsmBuilder.transition(a4_s3, a4_s2, "22", ClassGetter.newIntEq(22));
		hfsmBuilder.transition(a4_s2, a4_s1, "33", ClassGetter.newIntEq(33));
		hfsmBuilder.transition(a4_s1, a4_s0, "44", ClassGetter.newIntEq(44));
		hfsmBuilder.endState();

		// C
		hfsmBuilder.beginState(a2_s2, false);
		hfsmBuilder.state(a5_s0, true);
		hfsmBuilder.beginState(a5_s1, false);
			hfsmBuilder.state(a7_s0, true);
			hfsmBuilder.state(a7_s1, false);
			hfsmBuilder.transition(a7_s0, a7_s1, "30", ClassGetter.newIntEq(30));
		hfsmBuilder.endState();
		hfsmBuilder.beginState(a5_s2, false);
			hfsmBuilder.state(a8_s0, true);
		hfsmBuilder.endState();
		hfsmBuilder.transition(a5_s0, a5_s2, "4", ClassGetter.newIntEq(4));
		hfsmBuilder.transition(a5_s0, a5_s1, "7", ClassGetter.newIntEq(7));
		hfsmBuilder.transition(a5_s1, a5_s2, "3", ClassGetter.newIntEq(3));
		hfsmBuilder.transition(a5_s2, a5_s1, "5", ClassGetter.newIntEq(5));
		hfsmBuilder.endState();

		hfsmBuilder.transition(a2_s0, a2_s1, "33", ClassGetter.newIntEq(33));
		hfsmBuilder.transition(a2_s1, a2_s0, "99", ClassGetter.newIntEq(99));
		hfsmBuilder.transition(a2_s1, a2_s2, "55", ClassGetter.newIntEq(55));
		hfsmBuilder.transition(a2_s2, a2_s1, "66", ClassGetter.newIntEq(66));
		hfsmBuilder.endState();

		return hfsmBuilder.getHFSM();
	}
	
	static HFSM<Integer> buildHFSM00() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();

		// Alpha
		hfsmBuilder.beginState(A, true);

		// A
		hfsmBuilder.beginState(a2_s0, true);
		hfsmBuilder.state(a3_s0, true);
		hfsmBuilder.state(a3_s1, false);
		hfsmBuilder.state(a3_s2, false);
		hfsmBuilder.transition(a3_s0, a3_s1, "439", ClassGetter.newIntEq(439));
		hfsmBuilder.transition(a3_s0, a3_s2, "33", ClassGetter.newIntEq(33));
		hfsmBuilder.transition(a3_s1, a3_s2, "111", ClassGetter.newIntEq(111));
		hfsmBuilder.transition(a3_s2, a3_s0, "448", ClassGetter.newIntEq(448));
		hfsmBuilder.endState();

		// B
		hfsmBuilder.beginState(a2_s1, false);
			hfsmBuilder.beginState(a4_s0, true);
				hfsmBuilder.state(a6_s0, true);
				hfsmBuilder.state(a6_s1, false);
				hfsmBuilder.state(a6_s2, false);
				hfsmBuilder.transition(a6_s0, a6_s2, "44", ClassGetter.newIntEq(44));
				hfsmBuilder.transition(a6_s2, a6_s1, "45", ClassGetter.newIntEq(45));
				hfsmBuilder.transition(a6_s1, a6_s0, "44", ClassGetter.newIntEq(44));
			hfsmBuilder.endState();
		hfsmBuilder.state(a4_s1, false);
		hfsmBuilder.state(a4_s2, false);
		hfsmBuilder.state(a4_s3, false);
		hfsmBuilder.transition(a4_s0, a4_s3, "11", ClassGetter.newIntEq(11));
		hfsmBuilder.transition(a4_s0, a4_s0, "42", ClassGetter.newIntEq(42));
		hfsmBuilder.transition(a4_s3, a4_s2, "22", ClassGetter.newIntEq(22));
		hfsmBuilder.transition(a4_s2, a4_s1, "33", ClassGetter.newIntEq(33));
		hfsmBuilder.transition(a4_s1, a4_s0, "44", ClassGetter.newIntEq(44));
		hfsmBuilder.endState();

		// C
		hfsmBuilder.beginState(a2_s2, false);
		hfsmBuilder.state(a5_s0, true);
		hfsmBuilder.beginState(a5_s1, false);
			hfsmBuilder.state(a7_s0, true);
			hfsmBuilder.state(a7_s1, false);
			hfsmBuilder.transition(a7_s0, a7_s1, "30", ClassGetter.newIntEq(30));
		hfsmBuilder.endState();
		hfsmBuilder.beginState(a5_s2, false);
			hfsmBuilder.state(a8_s0, true);
			hfsmBuilder.transition(a8_s0, a8_s0, "42", ClassGetter.newIntEq(42));
		hfsmBuilder.endState();
		hfsmBuilder.transition(a5_s0, a5_s2, "4", ClassGetter.newIntEq(4));
		hfsmBuilder.transition(a5_s0, a5_s1, "7", ClassGetter.newIntEq(7));
		hfsmBuilder.transition(a5_s1, a5_s2, "3", ClassGetter.newIntEq(3));
		hfsmBuilder.transition(a5_s2, a5_s1, "5", ClassGetter.newIntEq(5));
		hfsmBuilder.endState();

		hfsmBuilder.transition(a2_s0, a2_s1, "33", ClassGetter.newIntEq(33));
		hfsmBuilder.transition(a2_s0, a2_s0, "42", ClassGetter.newIntEq(42));
		hfsmBuilder.transition(a2_s1, a2_s0, "99", ClassGetter.newIntEq(99));
		hfsmBuilder.transition(a2_s1, a2_s2, "55", ClassGetter.newIntEq(55));
		hfsmBuilder.transition(a2_s2, a2_s1, "66", ClassGetter.newIntEq(66));
		hfsmBuilder.endState();

		return hfsmBuilder.getHFSM();
	}
	
	static HFSM<Integer> buildEx02() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		LogicExpression<Integer> dummy = ClassGetter.newDummy();
		
		hfsmBuilder.beginState("Alpha", true);
			hfsmBuilder.beginState("A", true);
				hfsmBuilder.state("a", true);
				hfsmBuilder.state("b", false);
				hfsmBuilder.state("c", false);
				hfsmBuilder.transition("a", "a", "2", dummy);
				hfsmBuilder.transition("a", "b", "1", dummy);
				hfsmBuilder.transition("b", "a", "2", dummy);
				hfsmBuilder.transition("b", "c", "1", dummy);
				hfsmBuilder.transition("c", "b", "1", dummy);
				hfsmBuilder.transition("c", "c", "2", dummy);
			hfsmBuilder.endState();
			hfsmBuilder.beginState("B", false);
				hfsmBuilder.state("d", true);
				hfsmBuilder.state("e", false);
				hfsmBuilder.transition("d", "d", "2", dummy);
				hfsmBuilder.transition("d", "e", "1", dummy);
				hfsmBuilder.transition("e", "d", "2", dummy);
				hfsmBuilder.transition("e", "e", "1", dummy);
			hfsmBuilder.endState();
			hfsmBuilder.transition("A", "B", "3", dummy);
			hfsmBuilder.transition("B", "A", "3", dummy);
		hfsmBuilder.endState();
		
		return hfsmBuilder.getHFSM();
	}
}
