package dev.delivery.project.deliverymanagment.ui.Classes;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Entrega {
    private String cliente;
    private String logradouro;
    private String bairro;
    private String contato;
    private int cpf;
    private String dataentrega;
    private String veiculo;
    private String vendedor;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public void setDataentrega(String dataentrega) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date data = (Date) formato.parse(dataentrega);
            //se chegar até aqui não deu erro no parser
            this.dataentrega = dataentrega;
        } catch (ParseException e) {
            this.dataentrega = "";
        }
    }

    public String getDataentrega() {
        return dataentrega;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    //CONSTRUTOR - iniciazaliza atributos
    public Entrega() {
        this.setCliente("");
        this.setLogradouro("");
        this.setBairro("");
        this.setContato("");
        this.setCpf(0);
        this.setDataentrega("");
        this.setVeiculo("");
        this.setVendedor("");
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Entrega(JSONObject jp) {
        try {
            Integer cpf = (int) jp.get("cpf");
            this.setCliente((String) jp.get("cliente"));
            this.setLogradouro((String) jp.get("logradouro"));
            this.setBairro((String) jp.get("bairro"));
            this.setContato((String) jp.get("contato"));
            this.setDataentrega((String) jp.get("dataentrega"));
            this.setVeiculo((String) jp.get("veiculo"));
            this.setVendedor((String) jp.get("vendedor"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("cliente", this.cliente);
            json.put("logradouro", this.logradouro);
            json.put("bairro", this.bairro);
            json.put("cpf", this.cpf);
            json.put("contato", this.contato);
            json.put("dataentrega", this.dataentrega);
            json.put("veiculo", this.veiculo);
            json.put("vendedor", this.vendedor);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}