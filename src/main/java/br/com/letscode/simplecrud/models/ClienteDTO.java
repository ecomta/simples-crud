package br.com.letscode.simplecrud.models;

public class ClienteDTO {

    String nome;
    String email;

    public ClienteDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
