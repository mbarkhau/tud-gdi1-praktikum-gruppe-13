package tests;

import static org.junit.Assert.fail;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import mis.gdi1lab07.automaton.HFSM;
import mis.gdi1lab07.automaton.HFSMHandler;

import org.junit.Test;

public class TestHfsmXmlPrinter {
	
	private final static URL xsdhfsm = TestHfsmXmlPrinter.class.getResource("data/hfsms.xsd");
	
	@Test
	public void testHFSMXMLPrinter00() throws Exception {
		HFSM<Integer> hfsm = Automata.buildHFSM00();
		
		StringWriter sw = new StringWriter();
		HFSMHandler<Integer> printer = ClassGetter.newHfsmXmlPrinter(sw);

		hfsm.serialize(printer);
		sw.flush();
		
		try {
			ClassGetter.newValidator(xsdhfsm).validateDocumentRaw(new StringReader(sw.toString()));
		}
		catch(Exception e) {
			fail("Invalid XML: " + e.getMessage());
		}
	}
	
	@Test
	public void testFSMXMLPrinter01() throws Exception {
		HFSM<Integer> hfsm = Automata.buildHFSM01();
		
		StringWriter sw = new StringWriter();
		HFSMHandler<Integer> printer = ClassGetter.newHfsmXmlPrinter(sw);

		hfsm.serialize(printer);
		sw.flush();
		
		try {
			ClassGetter.newValidator(xsdhfsm).validateDocumentRaw(new StringReader(sw.toString()));
		}
		catch(Exception e) {
			fail("Invalid XML: " + e.getMessage());
		}
	}
	
	@Test
	public void testFSMXMLPrinter02() throws Exception {
		HFSM<Integer> hfsm = Automata.buildEx02();
		
		StringWriter sw = new StringWriter();
		HFSMHandler<Integer> printer = ClassGetter.newHfsmXmlPrinter(sw);

		hfsm.serialize(printer);
		sw.flush();
		
		try {
			ClassGetter.newValidator(xsdhfsm).validateDocumentRaw(new StringReader(sw.toString()));
		}
		catch(Exception e) {
			fail("Invalid XML: " + e.getMessage());
		}
	}
}
