package logica;

public class Publicacion {

	private Usuario usuario;
	private String titulo;
	private String link;
	
	public Publicacion(Usuario usuario, String titulo, String link) {
		super();
		this.usuario = usuario;
		this.titulo = titulo;
		this.link = link;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getLink() {
		return link;
	}

}
