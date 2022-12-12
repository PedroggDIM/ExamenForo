package es.lanyu.forum;

import com.github.auth.User;

public class UsuarioExterno extends User implements Usuario {

    public UsuarioExterno(String username) {
        super(username);
    }

    @Override
    public String getUsername() {
        return getUserName();
    }
    
    @Override
    public String toString() {
    	return super.toString().toUpperCase();
    }

}
