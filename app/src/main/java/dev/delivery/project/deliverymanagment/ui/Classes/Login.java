package dev.delivery.project.deliverymanagment.ui.Classes;

import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Login {
    private String user;
    private String password;

    public String getUser () {
        return user;
    }

    public void setUser (String user){
        this.user = user;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Login (JSONObject jp) {
        try {
            this.setUser((String) jp.get("user"));
            this.setPassword((String) jp.get("senha"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Construtor inicializa dados
    public Login () {
        this.setUser("");
        this.setPassword("");
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("user", this.user);
            json.put("password", this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
