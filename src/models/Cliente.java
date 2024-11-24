package models;

import java.util.List;

public class Cliente extends Usuario {

    private List<Viagem> historicoViagens;

    public Cliente(Long id, String nomeCompleto, String email, String telefone, List<Viagem> historicoViagens) {
        super(id, nomeCompleto, email, telefone);
        this.historicoViagens = historicoViagens;
    }

    public List<Viagem> getHistoricoViagens() {
        return historicoViagens;
    }

    public void setHistoricoViagens(List<Viagem> historicoViagens) {
        this.historicoViagens = historicoViagens;
    }



}
