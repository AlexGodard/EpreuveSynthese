package ca.qc.cstj.android.epreuvesynthese.models;

/**
 * Created by 0738628 on 2014-11-28.
 */
public class Token {

    private String token;
    private int expires;

    public Token(String tok, int exp) {
        this.token = tok;
        this.expires = exp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
