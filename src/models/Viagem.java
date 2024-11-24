package models;

import enums.StatusViagem;
import org.apache.poi.hpsf.Decimal;

import java.text.NumberFormat;
import java.util.Random;

public class Viagem {
    public StatusViagem status;
    private Long id;
    private String codigo;
    private Cliente cliente;
    private Motorista motorista;
    private String origem;
    private String destino;
    private StatusViagem statusViagem;
    private String valor;

    // Objeto para formatar o preco como moeda local.
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public Viagem(Long id, String codigo, Cliente cliente, Motorista motorista,
                  String origem, String destino, StatusViagem statusViagem,
                  Double valor) {
        this.id = id;
        this.codigo = codigo;
        this.cliente = cliente;
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.statusViagem = statusViagem;
        this.valor = currencyFormat.format(valor);
    }

    public Viagem(Long id, Cliente cliente, Motorista motorista,
                  String origem, String destino, StatusViagem statusViagem,
                  Double valor) {
        this.id = id;
        this.codigo = gerarCodigoViagem();
        this.cliente = cliente;
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.statusViagem = statusViagem;
        this.valor = currencyFormat.format(valor);
    }

    // Metodo que gera um codigo de 6 digitos aleatorios para a corrida
    public String gerarCodigoViagem() {
        Random random = new Random();// Objeto para gerar numeros aleatorios.
        StringBuilder codigo = new StringBuilder(); // StringBuilder para construir o codigo.
        for (int i = 0; i < 6; i++) {
            codigo.append(random.nextInt(10)); // Adiciona dígitos de 0 a 9
        }
        return codigo.toString();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public StatusViagem getStatusViagem() {
        return statusViagem;
    }

    public void setStatusViagem(StatusViagem statusViagem) {
        this.statusViagem = statusViagem;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = currencyFormat.format(valor);
    }
}
