package models;

public class Motorista extends Usuario {
    private String placa;

    public Motorista(Long id, String nomeCompleto, String email, String telefone, String placa) {
        super(id, nomeCompleto, email, telefone);
        this.placa = placa;

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}