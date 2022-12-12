package es.lanyu.forum;

import java.time.Instant;
import java.util.Comparator;



public class Comentario implements Comparable<Comentario>{

	private Usuario usuario;
    private String texto;
    private Tema tema;
    private Instant instant;
    
	public static String textoRecortado(String texto, int longitudMaxima) {
		String textoRecortado = texto;
		if (texto.length() > longitudMaxima) {
			textoRecortado = texto.substring(0, longitudMaxima) + "...";
		}
		return textoRecortado;
	}

    public Usuario getUsuario() {
        return usuario;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public Tema getTema() {
        return tema;
    }
    
    public Instant getInstant() {
        return instant;
    }
    // constructor por defecto
    public Comentario() {
        this(null, null, null, Instant.now());
    }
    
    public Comentario(Usuario usuario, String texto, Tema tema) {
        this(usuario, texto, tema, Instant.now());
    }
    
    public Comentario(Usuario usuario, String texto, Tema tema, Instant instant) {
        this.usuario = usuario;
        this.texto = texto;
        this.tema = tema;
        this.instant = instant;
    }
    
    @Override
    public String toString() {
        return getUsuario() + ": " +  textoRecortado(getTexto(), 20) +" en " + getTema() + " a las " + getInstant();
    }

	@Override
	public int compareTo(Comentario otro) {		
	  //  return o.instant.compareTo(getInstant());
		return -getInstant().compareTo(otro.getInstant());
	}

	public static void sort(Comparator<Comentario> comparator) {
		
		
	}

	
	
	
}
