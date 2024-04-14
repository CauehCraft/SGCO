package model.user;

public class User {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private boolean admin;

    // Construtor
    public User(int id, String nome, String email, String senha, String endereco, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.admin = admin;
    }

    // Métodos
    public void criarUsuario() {

    }

    public User lerUsuario(int id) {

        return null; 
    }

    public void visualizarUsuario(int id) {

    }

    public void listarUsuarios() {

    }

    public void atualizarUsuario(int id) {

    }

    public void deletarUsuario(int id) {

    }

    public boolean autenticarUsuario() {

        return false;
    }
}