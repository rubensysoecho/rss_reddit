package logica;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GenerarPublicaciones {

	private static ArrayList<Publicacion> listaPublicaciones = new ArrayList<Publicacion>();
	
	private static void asignarPublicaciones()	{
		try {
			Document doc = UtilidadesDOM.gernerararbolDOMURL("https://www.reddit.com/r/dune/.rss");
			Element raiz = doc.getDocumentElement();
			
			NodeList publicaciones = raiz.getElementsByTagName("entry");
			for (int i = 0; i < publicaciones.getLength(); i++) {
				Element publicacion = (Element) publicaciones.item(i);
				String titulo = publicacion.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
				Element linkNodo = (Element) publicacion.getElementsByTagName("link").item(0);
				String link = linkNodo.getAttribute("href");
				
				Element autor = (Element) publicacion.getElementsByTagName("author").item(0);
				String nickUsuario = autor.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				String url = autor.getElementsByTagName("uri").item(0).getFirstChild().getNodeValue();
				Usuario nuevoUsuario = new Usuario(nickUsuario, url);
				
				listaPublicaciones.add(new Publicacion(nuevoUsuario, titulo, link));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Publicacion> devolverPublicaciones()	{
		asignarPublicaciones();
		return listaPublicaciones;
	}
}
