package ca.qc.cstj.android.epreuvesynthese.models;

//Classe Token
public class Token {

    //Attributs
    private String token;
    private int expires;

    //Constructeur public qui prend en paramètre le token (string) et l'expiration (integer)
    public Token(String tok, int exp) {
        this.token = tok;
        this.expires = exp;
    }

    //Méthode qui retourne le token
    public String getToken() {
        return token;
    }

    //Méthode qui set le token
    public void setToken(String token) {
        this.token = token;
    }

    //Méthode qui retourne l'expiration
    public int getExpires() {
        return expires;
    }

    //Méthode qui set l'expiration
    public void setExpires(int expires) {
        this.expires = expires;
    }
}
