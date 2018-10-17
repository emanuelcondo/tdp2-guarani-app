package ar.edu.uba.fi.tdp2.guaraniapp.login;

public class Token {

    public static String id;
    public static boolean conectado = false;

    private String token;
    private String rol;
    private String expiracionToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getExpiracionToken() {
        return expiracionToken;
    }

    public void setExpiracionToken(String expiracionToken) {
        this.expiracionToken = expiracionToken;
    }
}
