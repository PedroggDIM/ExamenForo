package es.lanyu.forum;

public class Tema {
     
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tema(String nombre) {		
		setNombre(nombre);
	}

	public Tema() {
		
	}

	@Override
	public String toString() {
		return "Tema: " + nombre;
	}
    
}
