package dev.delivery.project.deliverymanagment.ui.Classes;

import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Login {
    private String user;
    private String passoword;

    public Login(String user, String password){
        this.setUser(user);
        this.setPassoword(password);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassoword() {
        return passoword;
    }

    public void setPassoword(String passoword) {
        this.passoword = passoword;
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Login (JSONObject jp) {
        try {
            Integer numero = (int) jp.get("id");
            this.setUser((String) jp.get("nome"));
            this.setPassoword((String) jp.get("senha"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("user", this.user);
            json.put("password", this.passoword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
