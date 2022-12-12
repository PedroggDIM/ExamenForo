package es.lanyu.forum;

public class UsuarioImpl implements Usuario {
    
    private String username;

    public String getUsername() {
        return username;
    }
    
    public UsuarioImpl(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return getUsername().toUpperCase();
    }
    
}
