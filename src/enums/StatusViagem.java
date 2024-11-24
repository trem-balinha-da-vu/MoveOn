package enums;

public enum StatusViagem {
    CONCLUIDA("concluida"),
    EM_ANDAMENTO("em andamento"),
    PENDENTE("pendente");

    public String statusDaViagem;
    StatusViagem(String s) {
        this.statusDaViagem = s;
    }

    public String getStatusDaViagem()
    {
        return statusDaViagem;
    }
}
