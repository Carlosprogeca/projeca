package dev.delivery.project.deliverymanagment.ui.Classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Local {
    private String client;
    private String logradouro;
    private String bairro;
    private String contato;
    private int cpf;
    private int id;

    public Local(String user, String password){
        this.setUser(user);
        this.setPassoword(password);
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Local (JSONObject jp) {
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
