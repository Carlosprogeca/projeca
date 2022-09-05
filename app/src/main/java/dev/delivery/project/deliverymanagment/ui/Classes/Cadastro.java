package dev.delivery.project.deliverymanagment.ui.Classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Cadastro {
    private String caduser;
    private String cadpassword;
    private String cademail;

    public String getCaduser() {
        return caduser;
    }

    public void setCaduser(String caduser) {
        this.caduser = caduser;
    }

    public String getCadpassword() {
        return cadpassword;
    }

    public void setCadpassword(String cadpassword) {
        this.cadpassword = cadpassword;
    }

    public String getCademail() {
        return cademail;
    }

    public void setCademail(String cademail) {
        this.cademail = cademail;
    }


    public Cadastro (JSONObject jp) {
        try {
            this.setCadpassword((String) jp.get("cadsenha"));
            this.setCaduser((String) jp.get("caduser"));
            this.setCademail((String) jp.get("cademail"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Cadastro () {
        this.setCaduser("");
        this.setCadpassword("");
        this.setCademail("");
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("cadpassword", this.cadpassword);
            json.put("caduser", this.caduser);
            json.put("cademail", this.cademail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
