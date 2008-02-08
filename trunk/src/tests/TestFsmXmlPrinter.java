package tests;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import static org.junit.Assert.*;

import mis.gdi1lab07.automaton.FSM;
import mis.gdi1lab07.automaton.FSMHandler;

import org.junit.Test;

public class TestFsmXmlPrinter {
	
	private final static URL xsdfsm = TestFsmXmlPrinter.class.getResource("data/fsm.xsd");
	
	@Test
	public void testFSMXMLPrinter00() throws Exception {
		FSM<Integer> fsm = Automata.buildFSM00();
		
		StringWriter sw = new StringWriter();
		FSMHandler<Integer> printer = ClassGetter.newFsmXmlPrinter(sw);

		fsm.serialize(printer);
		sw.flush();
		
		try {
			ClassGetter.newValidator(xsdfsm).validateDocumentRaw(new StringReader(sw.toString()));
		}
		catch(Exception e) {
			fail("Invalid XML: " + e.getMessage());
		}
	}
	
	@Test
	public void testFSMXMLPrinter01() throws Exception {
		FSM<Integer> fsm = Automata.buildFSM01();
		
		StringWriter sw = new StringWriter();
		FSMHandler<Integer> printer = ClassGetter.newFsmXmlPrinter(sw);

		fsm.serialize(printer);
		sw.flush();
		
		try {
			ClassGetter.newValidator(xsdfsm).validateDocumentRaw(new StringReader(sw.toString()));
		}
		catch(Exception e) {
			fail("Invalid XML: " + e.getMessage());
		}
	}
}
