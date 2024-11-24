/*
 * Created by JFormDesigner on Sat Nov 23 17:23:16 BRT 2024
 */

package telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import info.clearthought.layout.*;
import interfaces.Visible;
import models.Cliente;
import models.Motorista;
import models.SistemaMoveON;

/**
 * @author User
 */
public class InicialPage extends JFrame implements Visible {
    private SistemaMoveON sistema;
    public Cliente cliente;

    public InicialPage(Cliente cliente) {
        this.sistema = new SistemaMoveON(); // Inicializa o sistema
        this.cliente = cliente;
        initComponents();
        configureListeners(cliente);
    }

    private void configureListeners(Cliente cliente) {
        // Listener para o botão Pedir Corrida
        btnPedirCorrida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Informações da corrida
                Motorista motorista = sistema.escolheMotoristaAleatorio();
                String origem = txtOrigem.getText();
                String destino = txtDestino.getText();

                if (origem.isEmpty() || destino.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Origem e Destino são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Double preco = gerarPreco();
                String nomeMotorista = motorista.nomeCompleto;
                String placaDoCarro = motorista.getPlaca();

                // Configurar e exibir o JDialog
                ConfirmaCorrida dialog = new ConfirmaCorrida(InicialPage.this, cliente, motorista);
                dialog.setDadosCorrida(origem, destino, nomeMotorista, placaDoCarro, preco);
                dialog.setVisible(true);

                txtOrigem.setText("");
                txtDestino.setText("");
            }
        });

        // Listener para o Botao Sair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().abrir();
                dispose(); // Fecha a janela atual
            }
        });

        //Listener para o botao de Histórico de Viagens
        btnHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Historico(cliente).abrir();
            }
        });
    }

    @Override
    public void abrir() {
        setVisible(true); // Exibe a janela de login
    }

    // Gera um preço aleatório
    public Double gerarPreco() {
        Random random = new Random(); // Objeto para gerar números aleatórios
        double distancia = random.nextDouble() * 12; // Gera uma distância aleatória de 0 a 12 km
        double taxaPorKm = 1.3; // Taxa por quilômetro (pode ser ajustada)

        double preco = distancia * taxaPorKm; // Calcula o preço
        return Math.round(preco * 100.0) / 100.0; // Retorna o preço arredondado para 2 casas decimais
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
        label1 = new JLabel();
        label2 = new JLabel();
        txtOrigem = new JTextField();
        label3 = new JLabel();
        txtDestino = new JTextField();
        panel1 = new JPanel();
        btnPedirCorrida = new JButton();
        btnSair = new JButton();
        btnHistorico = new JButton();
        panel2 = new JPanel();
        vSpacer1 = new JPanel(null);

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED},
            {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //---- label1 ----
        label1.setText("Para onde voc\u00ea quer ir hoje?");
        label1.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 15));
        contentPane.add(label1, new TableLayoutConstraints(6, 2, 6, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label2 ----
        label2.setText("origem");
        label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label2, new TableLayoutConstraints(2, 4, 3, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        contentPane.add(txtOrigem, new TableLayoutConstraints(4, 4, 6, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- label3 ----
        label3.setText("destino");
        label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label3, new TableLayoutConstraints(2, 6, 3, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        contentPane.add(txtDestino, new TableLayoutConstraints(4, 6, 6, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== panel1 ========
        {
            panel1.setLayout(new TableLayout(new double[][] {
                {TableLayout.PREFERRED, TableLayout.PREFERRED},
                {TableLayout.PREFERRED}}));
            ((TableLayout)panel1.getLayout()).setHGap(5);
            ((TableLayout)panel1.getLayout()).setVGap(5);
        }
        contentPane.add(panel1, new TableLayoutConstraints(6, 7, 6, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- btnPedirCorrida ----
        btnPedirCorrida.setText("Pedir Corrida");
        contentPane.add(btnPedirCorrida, new TableLayoutConstraints(6, 8, 6, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- btnSair ----
        btnSair.setText("Sair");
        btnSair.setBackground(Color.red);
        btnSair.setForeground(Color.white);
        btnSair.setFont(btnSair.getFont().deriveFont(btnSair.getFont().getStyle() | Font.BOLD));
        contentPane.add(btnSair, new TableLayoutConstraints(6, 9, 6, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- btnHistorico ----
        btnHistorico.setText("historico");
        contentPane.add(btnHistorico, new TableLayoutConstraints(7, 9, 7, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== panel2 ========
        {
            panel2.setLayout(new TableLayout(new double[][] {
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED},
                {TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)panel2.getLayout()).setHGap(5);
            ((TableLayout)panel2.getLayout()).setVGap(5);
            panel2.add(vSpacer1, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        contentPane.add(panel2, new TableLayoutConstraints(6, 10, 7, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
    private JLabel label1;
    private JLabel label2;
    private JTextField txtOrigem;
    private JLabel label3;
    private JTextField txtDestino;
    private JPanel panel1;
    private JButton btnPedirCorrida;
    private JButton btnSair;
    private JButton btnHistorico;
    private JPanel panel2;
    private JPanel vSpacer1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
