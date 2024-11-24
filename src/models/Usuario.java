package models;

public abstract class Usuario {

    public Usuario(Long id, String nomeCompleto, String email, String telefone) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
    }

    public Long id;
    public String nomeCompleto;
    public String email;
    public String telefone;


}
