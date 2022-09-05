package dev.delivery.project.deliverymanagment.ui.Classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Local {
    private String client;
    private String logradouro;
    private String bairro;
    private String contato;
    private int cpf;
    private boolean aceiteTermos;
    private String data;

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

    public void setAceiteTermos(boolean aceiteTermos) {
        this.aceiteTermos = aceiteTermos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        SimpleDateFormat formato =
                new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date datafinal = (Date) formato.parse(data);
    //se chegar até aqui não deu erro no parser
            this.data = data;
        } catch (ParseException e) {
            this.data = "";
        }
    }

    //CONSTRUTOR - iniciazaliza atributos
    public Local () {
        this.setClient("");
        this.setLogradouro("");
        this.setBairro("");
        this.setContato("");
        this.setCpf(0);
        this.setAceiteTermos(false);
        this.setData("");
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Local (JSONObject jp) {
        try {
            Integer cpf = (int) jp.get("cpf");
            this.setClient((String) jp.get("cliente"));
            this.setLogradouro((String) jp.get("logradouro"));
            this.setBairro((String) jp.get("bairro"));
            this.setContato((String) jp.get("contato"));
            boolean bool = Boolean.getBoolean(jp.get("aceite").toString());
            this.setAceiteTermos(bool);
            this.setData((String) jp.get("data"));

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
            json.put("cpf", this.cpf);
            json.put("contato", this.contato);
            json.put("aceite", this.aceiteTermos);
            json.put("data", this.data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}