package dev.delivery.project.deliverymanagment.ui.Classes;

import org.json.JSONException;
import org.json.JSONObject;




//essa classe vai ser modificada futuramente, a classe em funcionamento é a local_entrega









public class Usuario {
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Usuario(JSONObject jp) {
        try {
            this.setPassword((String) jp.get("cadsenha"));
            this.setUsername((String) jp.get("caduser"));
            this.setEmail((String) jp.get("cademail"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Usuario() {
        this.setUsername("");
        this.setPassword("");
        this.setEmail("");
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("cadpassword", this.password);
            json.put("caduser", this.username);
            json.put("cademail", this.email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
