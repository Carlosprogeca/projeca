package dev.delivery.project.deliverymanagment.ui.Classes;

import org.json.JSONException;
import org.json.JSONObject;
public class Login {
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Login (JSONObject jp) {
        try {
            this.setUsuario((String) jp.get("usuario"));
            this.setSenha((String) jp.get("senha"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Construtor inicializa dados
    public Login () {
        this.setUsuario("");
        this.setSenha("");
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("usuario", this.usuario);
            json.put("senha", this.senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
