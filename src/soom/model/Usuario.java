package soom.model;

public class Usuario {

    private int id;
    private String login;
    private String senha;

    public Usuario (){}


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
