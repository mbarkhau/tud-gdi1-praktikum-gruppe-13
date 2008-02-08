package tests;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import mis.gdi1lab07.automaton.FSMBuilder;
import mis.gdi1lab07.automaton.FSMHandler;
import mis.gdi1lab07.automaton.HFSMBuilder;
import mis.gdi1lab07.automaton.HFSMHandler;
import mis.gdi1lab07.automaton.logic.BooleanVariables;
import mis.gdi1lab07.automaton.logic.LogExpBuilder;
import mis.gdi1lab07.automaton.logic.LogExpException;
import mis.gdi1lab07.automaton.logic.LogExpHandler;
import mis.gdi1lab07.automaton.logic.LogicExpression;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



class ClassGetter {
	static <T> FSMBuilder<T> newFsmBuilder() {
		return new mis.gdi1lab07.student.StudentFsmBuilder<T>();
	}

	static <T> HFSMBuilder<T> newHfsmBuilder() {
		return new mis.gdi1lab07.student.StudentHfsmBuilder<T>();
	}

	static <T> FSMHandler<T> newFsmXmlPrinter(Writer w) {
		return new mis.gdi1lab07.student.StudentFsmXmlPrinter<T>(w);
	}

	static <T> HFSMHandler<T> newHfsmXmlPrinter(Writer w) {
		return new mis.gdi1lab07.student.StudentHfsmXmlPrinter<T>(w);
	}

	static LogExpBuilder<BooleanVariables> newLogicExpBuilder() {
		return new mis.gdi1lab07.student.StudentLogExpBuilder();
	}

	static BooleanVariables newBooleanValues() {
		return new mis.gdi1lab07.student.StudentBooleanVariables();
	}

	static <T> LogicExpression<T> newDummy() {
		return new DummyAtom<T>();
	}

	static <T> LogicExpression<T> newTrue() {
		return new ConstTrue<T>();
	}

	static <T> LogicExpression<T> newFalse() {
		return new ConstFalse<T>();
	}

	static XmlValidator newValidator(URL xsd) throws IOException {
		return new XmlValidator(xsd);
	}

	static LogicExpression<Integer> newIntEq(int i) {
		return new IntegerEquals(i);
	}

	static class DummyAtom<T> implements LogicExpression<T> {

		@Override
		public boolean eval(T context) {
			return true;
		}

		@Override
		public void serialize(LogExpHandler<T> handler) throws LogExpException {
			handler.variableReference("Dummy");
		}
	}

	static class ConstTrue<T> implements LogicExpression<T> {

		@Override
		public boolean eval(T context) {
			return true;
		}

		@Override
		public void serialize(LogExpHandler<T> handler) throws LogExpException {
			handler.variableReference("constTrue");
		}
	}

	static class ConstFalse<T> implements LogicExpression<T> {

		@Override
		public boolean eval(T context) {
			return false;
		}

		@Override
		public void serialize(LogExpHandler<T> handler) throws LogExpException {
			handler.variableReference("constFalse");
		}
	}

	static class IntegerEquals implements LogicExpression<Integer> {

		private Integer x = null;

		public IntegerEquals(Integer x) {
			this.x = x;
		}

		@Override
		public boolean eval(Integer context) {
			return x.equals(context);
		}

		@Override
		public void serialize(LogExpHandler<Integer> handler) throws LogExpException {
			handler.variableReference("IntegerEquals");
		}
	}

	static class XmlValidator {

		protected Schema schema = null;

		public XmlValidator(URL schemaUrl) throws IOException {
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				dbf.setValidating(false);

				if (schemaUrl != null) {
					SchemaFactory sf = SchemaFactory
							.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
					schema = sf.newSchema(sourceFromUrl(schemaUrl));
				}
			} catch (Exception e) {
				throw new RuntimeException("Unable to instantiate XmlWorker.",
						e);
			}
		}

		public void validateDocumentRaw(Reader in) throws SAXException,
				IOException {
			if (schema == null)
				throw new RuntimeException("No schema defined.");

			Validator v = schema.newValidator();
			v.validate(new SAXSource(new InputSource(in)));
		}

		private static Source sourceFromUrl(URL url) throws IOException {
			InputSource is = new InputSource(url.openStream());
			return new SAXSource(is);
		}
	}
}
