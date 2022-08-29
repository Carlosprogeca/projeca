package dev.delivery.project.deliverymanagment.ui.Classes;

import android.widget.EditText;

public class Login {
    private EditText user;
    private EditText passoword;

    public Login(EditText user, EditText password){
        this.setUser(user);
        this.setPassoword(password);
    }

    public EditText getUser() {
        return user;
    }

    public void setUser(EditText user) {
        this.user = user;
    }

    public EditText getPassoword() {
        return passoword;
    }

    public void setPassoword(EditText passoword) {
        this.passoword = passoword;
    }
}
