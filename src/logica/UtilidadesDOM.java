package logica;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class UtilidadesDOM {

	public static void crearficheroxml(Document arbolDOM,String nombrefichero) throws TransformerException, IOException {
		arbolDOM.normalize();
		arbolDOM.setXmlStandalone(true);
		TransformerFactory tsfactoria=TransformerFactory.newInstance();
		tsfactoria.setAttribute("indent-number",4);
		Transformer trans=tsfactoria.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT,"yes");
		DOMSource origendom=new DOMSource(arbolDOM);
		StringWriter salidaString=new StringWriter();
		StreamResult sr=new StreamResult(salidaString);
		trans.transform(origendom, sr);
		Path rutaficheroSalida=Paths.get(nombrefichero);
		Charset cs=Charset.forName("utf-8");
		BufferedWriter ficherosalida=Files.newBufferedWriter(rutaficheroSalida,cs);
		ficherosalida.write(salidaString.toString());
		ficherosalida.close();
	}

	public static Document generararbolDOMvacio() throws ParserConfigurationException {
		DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbfactory.newDocumentBuilder();
		Document arbolDOM=db.newDocument();
		return arbolDOM;
	}
	
	public static Document generararbolDOMString(String texto) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
		dbfactory.setIgnoringComments(true);
		dbfactory.setCoalescing(true);
		DocumentBuilder db=dbfactory.newDocumentBuilder();
		Document arbolDOM=db.parse(new ByteArrayInputStream(texto.getBytes("UTF-8")));
		return arbolDOM;
	}
	
	public static Document generararbolDOMfichero(String nombrefichero) throws ParserConfigurationException, SAXException, IOException {
		FileInputStream ficheroentrada=new FileInputStream(nombrefichero);
		DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
		dbfactory.setIgnoringComments(true);
		dbfactory.setCoalescing(true);
		DocumentBuilder db=dbfactory.newDocumentBuilder();
		Document arbolDOM=db.parse(ficheroentrada);
		return arbolDOM;
	}

	public static Document gernerararbolDOMURL(String web) throws IOException, ParserConfigurationException, SAXException {
		URL url = new URL(web);
		URLConnection urlcon=url.openConnection();
		urlcon.setUseCaches(false);
		urlcon.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		InputStream entrada=urlcon.getInputStream();
		DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
		dbfactory.setIgnoringComments(true);
		dbfactory.setCoalescing(true);
		DocumentBuilder db=dbfactory.newDocumentBuilder();
		Document arbolDOM=db.parse(entrada);
		return arbolDOM;
	}
	
	
}