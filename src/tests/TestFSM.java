package tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.FSMBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogicExpression;

import org.junit.Test;

import utilities.LogLevel;
import utilities.MultiLogger;
import utilities.StringConsoleLogger;
import utilities.TestLogger;
import utilities.TestLoggerException;

public class TestFSM implements Constants {

	private LogicExpression<Integer> dummy = ClassGetter.newDummy();
	
	@Test
	public void testBuild00() throws AutomatonException {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		fsmBuilder.beginFSM();
		fsmBuilder.state(A, true);
		fsmBuilder.state(B, false);
		fsmBuilder.endFSM();
	}

	@Test
	public void testBuild01() {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		boolean caught = false;
		try {
			fsmBuilder.beginFSM();
			fsmBuilder.state(A, true);
			fsmBuilder.state(B, true);
			fsmBuilder.endFSM();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: more than one initial state defined");
	}

	@Test
	public void testBuild02() throws AutomatonException {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		fsmBuilder.beginFSM();
		fsmBuilder.state(A, false);
		fsmBuilder.state(B, false);
		fsmBuilder.beginFSM();
	}

	@Test
	public void testBuild03() throws AutomatonException {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		fsmBuilder.beginFSM();
		fsmBuilder.state(A, true);
		fsmBuilder.state(B, false);
		fsmBuilder.transition(A, B, e1, dummy);
		fsmBuilder.transition(B, A, e1, dummy);
		fsmBuilder.transition(A, B, e2, dummy);
		fsmBuilder.transition(B, A, e2, dummy);
		fsmBuilder.transition(A, A, e3, dummy);
		fsmBuilder.transition(B, B, e3, dummy);
		fsmBuilder.beginFSM();
	}

	@Test
	public void testBuild04() {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		boolean caught = false;
		try {
			fsmBuilder.beginFSM();
			fsmBuilder.state(A, true);
			fsmBuilder.state(B, false);
			fsmBuilder.transition(A, C, e1, dummy);
			fsmBuilder.beginFSM();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: successor state of transition not known");
	}

	@Test
	public void testBuild05() {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		boolean caught = false;
		try {
			fsmBuilder.beginFSM();
			fsmBuilder.state(A, true);
			fsmBuilder.state(B, false);
			fsmBuilder.transition(C, A, e1, dummy);
			fsmBuilder.beginFSM();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: predecessor state of transition not known");
	}

	@Test
	public void testBuild06() {
		FSMBuilder<Integer> fsmBuilder = ClassGetter.newFsmBuilder();
		boolean caught = false;
		try {
			fsmBuilder.beginFSM();
			fsmBuilder.state(A, true);
			fsmBuilder.state(B, false);
			fsmBuilder.transition(C, D, e1, dummy);
			fsmBuilder.beginFSM();
		}
		catch(AutomatonException e) {
			caught = true;
		}
		if(!caught)
			fail("Expected HandlerException to be thrown: predecessor/successor states of transition not known");
	}

	@Test
	public void testInput00() throws LogExpException, AutomatonException {
		List<String> expected = new ArrayList<String>();
		FSM<Integer> fsm = Automata.buildFSM00();
		
		expected.add(a0_s0);
		expected.add(a0_s1);
		expected.add(a0_s2);
		expected.add(a0_s3);
		expected.add(a0_s4);
		expected.add(a0_s3);
		expected.add(a0_s2);
		expected.add(a0_s4);
		expected.add(a0_s4);
		expected.add(a0_s3);
		expected.add(a0_s1);
		expected.add(a0_s2);
		expected.add(a0_s1);
		expected.add(a0_s2);
		expected.add(a0_s5);
		expected.add(a0_s3);
		expected.add(a0_s4);
		
		TestLogger testlogger = new TestLogger(expected);
  	StringConsoleLogger consoleLogger = new StringConsoleLogger("TestLogger");
  	MultiLogger multi = new MultiLogger("Multi");
  	multi.addLogger(consoleLogger);
  	multi.addLogger(testlogger);
  	multi.enableLogging();
  	multi.setLogLevel(LogLevel.Debug);
  	fsm.setLog(multi);

		// test FSM
  	try {
  		fsm.reset();
  		fsm.input(5);
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(23);
  		fsm.input(23);
  		fsm.input(23);
  		fsm.input(10);
  		fsm.input(23);
  		fsm.input(5);
  		fsm.input(333);
  		fsm.reset();
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(333);
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
		FSM<Integer> fsm = Automata.buildFSM01();
		
		expected.add(a1_s1);
		expected.add(a1_s2);
		expected.add(a1_s3);
		expected.add(a1_s0);
		expected.add(a1_s1);
		expected.add(a1_s2);
		expected.add(a1_s4);
		expected.add(a1_s6);
		expected.add(a1_s5);
		expected.add(a1_s6);
		expected.add(a1_s5);
		expected.add(a1_s6);
		expected.add(a1_s1);
		expected.add(a1_s2);
		expected.add(a1_s7);
		expected.add(a1_s8);
		expected.add(a1_s9);
		expected.add(a1_s7);
		expected.add(a1_s8);
		expected.add(a1_s8);
		expected.add(a1_s7);
		expected.add(a1_s9);
		expected.add(a1_s8);
		
		TestLogger testlogger = new TestLogger(expected);
  	StringConsoleLogger consoleLogger = new StringConsoleLogger("TestLogger");
  	MultiLogger multi = new MultiLogger("Multi");
  	multi.addLogger(consoleLogger);
  	multi.addLogger(testlogger);
  	multi.enableLogging();
  	multi.setLogLevel(LogLevel.Debug);
  	fsm.setLog(multi);

		// test FSM
  	try {
  		fsm.reset();
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(42);
  		fsm.input(5);
  		fsm.input(42);
  		fsm.input(23);
  		fsm.input(42);
  		fsm.input(23);
  		fsm.reset();
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(23);
  		fsm.input(6);
  		fsm.input(6);
  		fsm.input(6);
  		fsm.input(6);
  		fsm.input(3);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(5);
  	}
  	catch(TestLoggerException e) {
  		fail("Invalid input behaviour detected: " + e.getMessage());
  	}
  	if(!testlogger.sawAllStrings())
  		fail("Invalid input behaviour detected: Input was not consumed completely");
	}
	
	@Test
	public void testInput02() throws LogExpException, AutomatonException {
		List<String> expected = new ArrayList<String>();
		FSM<Integer> fsm = Automata.buildFSM00();
		
		expected.add(a0_s0);
		expected.add(a0_s1);
		expected.add(a0_s2);
		expected.add(a0_s3);
		expected.add(a0_s4);
		expected.add(a0_s3);
		expected.add(a0_s2);
		expected.add(a0_s4);
		expected.add(a0_s4);
		expected.add(a0_s3);
		expected.add(a0_s1);
		expected.add(a0_s2);
		expected.add(a0_s1);
		expected.add(a0_s2);
		expected.add(a0_s5);
		expected.add(a0_s3);
		expected.add(a0_s4);
		expected.add(a0_s4);
		expected.add(a0_s0);
		
		TestLogger testlogger = new TestLogger(expected);
  	StringConsoleLogger consoleLogger = new StringConsoleLogger("TestLogger");
  	MultiLogger multi = new MultiLogger("Multi");
  	multi.addLogger(consoleLogger);
  	multi.addLogger(testlogger);
  	multi.enableLogging();
  	multi.setLogLevel(LogLevel.Debug);
  	fsm.setLog(multi);

		// test FSM
  	try {
  		fsm.reset();
  		fsm.input(5);
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(23);
  		fsm.input(23);
  		fsm.input(23);
  		fsm.input(10);
  		fsm.input(23);
  		fsm.input(5);
  		fsm.input(333);
  		fsm.reset();
  		fsm.input(333);
  		fsm.input(333);
  		fsm.input(5);
  		fsm.input(5);
  		fsm.input(333);
  		fsm.input(42);
  		fsm.reset();
  		fsm.input(42);
  	}
  	catch(TestLoggerException e) {
  		fail("Invalid input behaviour detected: " + e.getMessage());
  	}
  	if(!testlogger.sawAllStrings())
  		fail("Invalid input behaviour detected: Input was not consumed completely");
	}
}
