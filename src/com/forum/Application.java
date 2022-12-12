package com.forum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.github.likes.Like;
import com.github.likes.Likes;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioExterno;
import es.lanyu.forum.UsuarioImpl;
import es.lanyu.forum.test.DatosPrueba;

public class Application {

	// hago una copia de la lista
   private final static List<Comentario> COMENTARIOS = new ArrayList<>(DatosPrueba.COMENTARIOS);
    // Creo con eclipse el getComentarios( ) 
   public static List<Comentario> getComentarios() {
		return COMENTARIOS;
	}

	public static void main(String[] args) {
	
		COMENTARIOS.addAll(DatosPrueba.COMENTARIOS);
		
		for (Comentario comentario : getComentarios()) {
	            System.out.println(comentario);
	        }
		
		 Usuario usuario3 = new UsuarioImpl("user3");
	     Tema tema1 = getComentarios().get(0).getTema();
	    
	     comentar(usuario3, tema1, "Comentario añadido");
         System.out.println("---------------------"); 
	     for (Comentario comentario : getComentarios()) {
	            System.out.println(comentario);
	        }
	         // o usando
	        // getComentarios().forEach(System.out::println);
	     
	     Usuario usuarioExterno = new UsuarioExterno("usuarioExterno");
	     comentar(usuarioExterno, tema1, "Soy un usuario externo");
	     System.out.println("-----Imprimiendo al usuario externo ---------");
	     getComentarios().forEach(System.out::println);
         
	     System.out.println("\n----ordeno naturalmente los comentarios por orden descendente-------");	     
         getComentarios().sort(null);
         getComentarios().forEach(System.out::println);
         
         // punto 9.1
         System.out.println("\nRecomendaciones (Likes):");
         Comentario comentario1 = getComentarios().get(0);
         Comentario comentario2 = getComentarios().get(1);
         Likes.recomendar(comentario1, usuario3.getUsername());
         Likes.recomendar(comentario2, usuario3.getUsername());
         Likes.recomendar(comentario2, usuarioExterno.getUsername());
         System.out.println(textoRecomendacion(comentario1));
         System.out.println(textoRecomendacion(comentario2));


                    
          System.out.println("\nOrdenado por recomendación (likes):");
      	Usuario usuario1 = getComentarios().get(0).getUsuario();
      	Usuario usuario2 = getComentarios().get(1).getUsuario();
          Comentario comentario3 = getComentarios().get(2);
          Likes.recomendar(comentario3, usuario1.getUsername());
          Likes.recomendar(comentario3, usuario2.getUsername());
          
          Comparator<Comentario> comparadorLikes = new Comparator<Comentario>() {

  			@Override
  			public int compare(Comentario c1, Comentario c2) {
  				int resultado = -Integer.compare(Likes.getLikesFor(c1).length, Likes.getLikesFor(c2).length);
  				if (resultado == 0) {
  					resultado = c1.getInstant().compareTo(c2.getInstant());
  				}
  				return resultado;
  			}
  		};
  		getComentarios().sort(comparadorLikes);
          for (Comentario comentario : getComentarios()) {
  			System.out.println(textoRecomendacion(comentario));
  		}
          
          System.out.println("\nUsando boundaries:");
          Likes.getLikes().add(new Like(comentario3, usuario3.getUsername()));
          getComentarios().sort(comparadorLikes);
          for (Comentario comentario : getComentarios()) {
  			System.out.println(textoRecomendacion(comentario));
  		}
      }

          
      	
	
	private static boolean comentar(Usuario usuario, Tema tema, String texto) {
		boolean resultado = getComentarios().add(new Comentario(usuario, texto, tema));
		System.err.println("El comentario " + (resultado ? "se añadió correctamente"
			                                              : "no se pudo añadir"));
	    return resultado;
	   
	}
	
	 private static <T extends Comentario> String textoRecomendacion(T contenido) {
		 // para conseguir las recomendaciones (like) ya hechas se usará el método getLikesfor(comentario)
	    	String[] likes = Likes.getLikesFor(contenido);
	    	Usuario[] usuariosConLike = new Usuario[likes.length];
	    	Set<Usuario> usuarios = getUsuariosEnComentarios();
	    	for (int i = 0; i < likes.length; i++) {
				usuariosConLike[i] = mapStringToUsuario(usuarios, likes[i]);
			}
	    	
	        return Comentario.textoRecortado(contenido.getTexto(), 20) + " "
	    			+ likes.length + "* " + Arrays.toString(usuariosConLike);
		}
	 
	 private static Set<Usuario> getUsuariosEnComentarios() {
	    	Set<Usuario> usuarios = new HashSet<Usuario>();
	    	for (Comentario comentario : getComentarios()) {
				usuarios.add(comentario.getUsuario());
			}
	    	
	    	return usuarios;
	    }
	 
	 static Usuario mapStringToUsuario(Collection<Usuario> usuarios, String username) {
	    	Usuario usuario = null;
	    	for (Usuario user : usuarios) {
				if (user.getUsername().equals(username)) {
					usuario = user;
					break;
				}
			}
	    	return usuario;
	    }


	

}
