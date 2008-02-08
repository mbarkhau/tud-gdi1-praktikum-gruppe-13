package tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

import org.junit.Test;

import utilities.LogLevel;
import utilities.MultiLogger;
import utilities.StringConsoleLogger;
import utilities.TestLogger;
import utilities.TestLoggerException;

public class TestHFSM implements Constants {

	private LogicExpression<Integer> dummy = ClassGetter.newDummy();

	@Test
	public void testBuild00() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		hfsmBuilder.state(A, true);
		hfsmBuilder.state(B, false);
	}

	@Test
	public void testBuild01() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		hfsmBuilder.beginState(A+B, true);
		hfsmBuilder.state(A, true);
		hfsmBuilder.state(B, false);
		hfsmBuilder.endState();
	}

	@Test
	public void testBuild02() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		hfsmBuilder.beginState(A+B, true);
		hfsmBuilder.state(A, true);
		hfsmBuilder.state(B, false);
		hfsmBuilder.endState();
	}

	@Test
	public void testBuild03() {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		boolean caught = false;
		try {
			hfsmBuilder.beginState(A+B, true);
			hfsmBuilder.state(A, true);
			hfsmBuilder.state(B, true);
			hfsmBuilder.endState();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: more than one initial state defined");
	}

	@Test
	public void testBuild04() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		hfsmBuilder.beginState(A+B, true);
		hfsmBuilder.state(A, true);
		hfsmBuilder.state(B, false);
		hfsmBuilder.transition(A, B, e1, dummy);
		hfsmBuilder.transition(B, A, e1, dummy);
		hfsmBuilder.endState();
	}

	@Test
	public void testBuild05() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		hfsmBuilder.beginState(A+B, true);
		hfsmBuilder.state(A, true);
		hfsmBuilder.state(B, false);
		hfsmBuilder.transition(A, B, e1, dummy);
		hfsmBuilder.transition(A, B, e2, dummy);
		hfsmBuilder.endState();
	}

	@Test
	public void testBuild06() {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		boolean caught = false;
		try {
			hfsmBuilder.beginState(A+B, true);
			hfsmBuilder.state(A, true);
			hfsmBuilder.state(B, false);
			hfsmBuilder.transition(A, C, e1, dummy);
			hfsmBuilder.endState();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: successor state of transition not known");
	}

	@Test
	public void testBuild07() {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		boolean caught = false;
		try {
			hfsmBuilder.beginState(A+B, true);
			hfsmBuilder.state(A, true);
			hfsmBuilder.state(B, false);
			hfsmBuilder.transition(C, B, e1, dummy);
			hfsmBuilder.endState();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: predecessor state of transition not known");
	}

	@Test
	public void testBuild08() {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		boolean caught = false;
		try {
			hfsmBuilder.beginState(A+B, true);
			hfsmBuilder.state(A, true);
			hfsmBuilder.state(B, false);
			hfsmBuilder.transition(C, D, e1, dummy);
			hfsmBuilder.endState();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: predecessor/successor states of transition not known");
	}

	@Test
	public void testBuild09() throws AutomatonException {
		HFSMBuilder<Integer> hfsmBuilder = ClassGetter.newHfsmBuilder();
		hfsmBuilder.beginState(A+B, true);
		hfsmBuilder.beginState(A, true);
		hfsmBuilder.state(A+C, true);
		hfsmBuilder.state(A+D, false);
		hfsmBuilder.endState();
		hfsmBuilder.beginState(B, false);
		hfsmBuilder.state(B+C, true);
		hfsmBuilder.state(B+D, false);
		hfsmBuilder.endState();
		hfsmBuilder.endState();
	}

	@Test
	public void testInput00() throws LogExpException, AutomatonException {
		List<String> expected = new ArrayList<String>();
		HFSM<Integer> hfsm = Automata.buildHFSM00();
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s1);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s2);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s2);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s1);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s3);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s2);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s1);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s3);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s0);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s2);
		expected.add(a8_s0);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s1);
		expected.add(a7_s0);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s1);
		expected.add(a7_s1);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s1);
		expected.add(a7_s1);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s2);
		expected.add(a8_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		TestLogger testlogger = new TestLogger(expected);
  	StringConsoleLogger consoleLogger = new StringConsoleLogger("TestLogger");
  	MultiLogger multi = new MultiLogger("Multi");
  	multi.addLogger(consoleLogger);
  	multi.addLogger(testlogger);
  	multi.enableLogging();
  	multi.setLogLevel(LogLevel.Debug);
  	hfsm.setLog(multi);

		// test FSM
  	try {
  		hfsm.reset();
  		hfsm.input(439);
  		
  		hfsm.input(111);
  		
  		hfsm.input(448);
  		
  		hfsm.input(33);
  		
  		hfsm.input(44);
  		
  		hfsm.input(45);
  		
  		hfsm.input(11);
  		
  		hfsm.input(22);
  		
  		hfsm.input(33);
  		
  		hfsm.input(44);
  		
  		hfsm.input(99);
  		
  		hfsm.input(33);
  		
  		hfsm.input(11);
  		
  		hfsm.input(55);
  		
  		hfsm.input(4);
  		
  		hfsm.input(5);
  		
  		hfsm.input(30);
  		
  		hfsm.input(42);
  		
  		hfsm.input(3);
  		
  		hfsm.input(66);
  	}
  	catch(TestLoggerException e) {
  		fail("Invalid input behaviour detected: " + e.getMessage());
  	}
  	if(!testlogger.sawAllStrings())
  		fail("Invalid input behaviour detected: Input was not consumed completely");
	}
	
	@Test
	public void testInput01() throws LogExpException, AutomatonException {
		List<String> expected = new ArrayList<String>();
		HFSM<Integer> hfsm = Automata.buildHFSM00();
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s1);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s2);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s2);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s1);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s3);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s2);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s1);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s3);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s0);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s2);
		expected.add(a8_s0);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s1);
		expected.add(a7_s0);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s1);
		expected.add(a7_s1);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s1);
		expected.add(a7_s1);
		
		expected.add(A);
		expected.add(a2_s2);
		expected.add(a5_s2);
		expected.add(a8_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s2);
		
		expected.add(A);
		expected.add(a2_s1);
		expected.add(a4_s0);
		expected.add(a6_s0);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s0);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s1);
		
		expected.add(A);
		expected.add(a2_s0);
		expected.add(a3_s0);
		
		TestLogger testlogger = new TestLogger(expected);
  	StringConsoleLogger consoleLogger = new StringConsoleLogger("TestLogger");
  	MultiLogger multi = new MultiLogger("Multi");
  	multi.addLogger(consoleLogger);
  	multi.addLogger(testlogger);
  	multi.enableLogging();
  	multi.setLogLevel(LogLevel.Debug);
  	hfsm.setLog(multi);

		// test FSM
  	try {
  		hfsm.reset();
  		hfsm.input(439);
  		
  		hfsm.input(111);
  		
  		hfsm.input(448);
  		
  		hfsm.input(33);
  		
  		hfsm.input(44);
  		
  		hfsm.input(45);
  		
  		hfsm.input(11);
  		
  		hfsm.input(22);
  		
  		hfsm.input(33);
  		
  		hfsm.input(44);
  		
  		hfsm.input(99);
  		
  		hfsm.input(33);
  		
  		hfsm.input(11);
  		
  		hfsm.input(55);
  		
  		hfsm.input(4);
  		
  		hfsm.input(5);
  		
  		hfsm.input(30);
  		
  		hfsm.input(42);
  		
  		hfsm.input(3);
  		
  		hfsm.input(66);
  		
  		hfsm.input(44);
  		
  		hfsm.input(42);
  		
  		hfsm.input(99);
  		
  		hfsm.input(439);
  		
  		hfsm.input(42);
  	}
  	catch(TestLoggerException e) {
  		fail("Invalid input behaviour detected: " + e.getMessage());
  	}
  	if(!testlogger.sawAllStrings())
  		fail("Invalid input behaviour detected: Input was not consumed completely");
	}
}
