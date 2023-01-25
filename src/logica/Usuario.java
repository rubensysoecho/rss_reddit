package logica;

public class Usuario {

	private String nick;
	private String url;
	
	public Usuario(String nick, String url)	{
		this.nick = nick;
		this.url = url;
	}

	public String getNick() {
		return nick;
	}

	public String getUrl() {
		return url;
	}
	
	
}
