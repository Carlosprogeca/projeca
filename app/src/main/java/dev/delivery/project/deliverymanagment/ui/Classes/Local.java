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
    //CONSTRUTOR - iniciazaliza atributos
    public Local () {
        this.setClient("");
        this.setLogradouro("");
        this.setBairro("");
        this.setContato("");
        this.setCpf(0);
        this.setId(0);
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Local (JSONObject jp) {
        try {
            Integer id = (int) jp.get("id");
            this.setClient((String) jp.get("cliente"));
            this.setLogradouro((String) jp.get("logradouro"));
            this.setBairro((String) jp.get("bairro"));
            this.setContato((String) jp.get("contato"));
            Integer cpf = (int) jp.get("cpf");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("cliente", this.client);
            json.put("logradouro", this.logradouro);
            json.put("bairro", this.bairro);
            json.put("id", this.id);
            json.put("cpf", this.cpf);
            json.put("contato", this.contato);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
