/*
 * Created by JFormDesigner on Sat Nov 23 17:29:12 BRT 2024
 */

package telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import enums.StatusViagem;
import info.clearthought.layout.*;
import models.Cliente;
import models.Motorista;
import models.SistemaMoveON;
import models.Viagem;

/**
 * @author User
 */
public class ConfirmaCorrida extends JDialog {

    private boolean confirmado;
    public Cliente clienteObj;
    public Motorista motoristaObj;
    public String codigo;
    private Viagem v;

    public ConfirmaCorrida(Window owner, Cliente cliente, Motorista motorista) {
        super(owner);
        confirmado = false;// Inicialmente, nenhuma ação foi confirmada
        this.clienteObj = cliente;
        this.motoristaObj = motorista;
        this.codigo = new Viagem(0L, cliente, motorista, "", "", StatusViagem.PENDENTE, 0.0).gerarCodigoViagem();
        initComponents();
        configureListeners();
    }

    // Metodo para configurar os textos do JDialog com base nos dados da corrida
    public void setDadosCorrida(String origem, String destino, String motorista, String placa, Double preco) {
        lblOrigem.setText(origem);
        this.destino.setText(destino);
        this.motorista.setText(motorista);
        this.placa.setText(placa);
        this.preco.setText(String.format("R$ %.2f", preco));
    }

    // Metodo para saber se o usuário confirmou a corrida
    public boolean isConfirmado() {
        return confirmado;
    }

    public void mudaStatus(boolean status)
    {
        this.confirmado = status;
    }

    private void configureListeners() {
        // Listener para o botão Confirmar
        okButton.addActionListener(e -> {
            confirmado = true;
            JOptionPane.showMessageDialog(null, "Corrida confirmada! Redirecionando...", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            SistemaMoveON sistema = new SistemaMoveON();
            // Remover o 'R$' e substituir a vírgula por ponto
            String precoString = preco.getText().replace("R$", "").replace(",", ".");
            // Converter para Double
            Double precoDouble = Double.valueOf(precoString);
            sistema.salvarViagem(clienteObj, motoristaObj, codigo, this.lblOrigem.getText(),this.destino.getText(), precoDouble, StatusViagem.CONCLUIDA);
            dispose();
        });

        // Listener para o botão Cancelar
        cancelButton.addActionListener(e -> {
            confirmado = false;
            JOptionPane.showMessageDialog(null, "Corrida cancelada!", "Cancelado", JOptionPane.WARNING_MESSAGE);
            dispose();
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
        dialogPane = new JPanel();
        panel9 = new JPanel();
        label3 = new JLabel();
        buttonBar = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        label4 = new JLabel();
        lblOrigem = new JLabel();
        panel5 = new JPanel();
        label5 = new JLabel();
        destino = new JLabel();
        panel6 = new JPanel();
        label6 = new JLabel();
        motorista = new JLabel();
        label7 = new JLabel();
        placa = new JLabel();
        label9 = new JLabel();
        preco = new JLabel();
        panel7 = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        scrollPane1 = new JScrollPane();
        panel2 = new JPanel();
        panel1 = new JPanel();
        panel8 = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new TableLayout(new double[][] {
                {TableLayout.PREFERRED, TableLayout.FILL, TableLayout.PREFERRED},
                {TableLayout.PREFERRED, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));

            //======== panel9 ========
            {
                panel9.setLayout(new TableLayout(new double[][] {
                    {TableLayout.FILL},
                    {TableLayout.FILL}}));

                //---- label3 ----
                label3.setText("Informa\u00e7\u00f5es da corrida:");
                label3.setFont(new Font("JetBrains Mono SemiBold", Font.PLAIN, 16));
                panel9.add(label3, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            }
            dialogPane.add(panel9, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new TableLayout(new double[][] {
                    {TableLayout.FILL, TableLayout.PREFERRED, TableLayout.PREFERRED},
                    {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
                ((TableLayout)buttonBar.getLayout()).setHGap(5);
                ((TableLayout)buttonBar.getLayout()).setVGap(5);

                //======== panel3 ========
                {
                    panel3.setLayout(new TableLayout(new double[][] {
                        {TableLayout.FILL},
                        {TableLayout.PREFERRED}}));
                    ((TableLayout)panel3.getLayout()).setHGap(5);
                    ((TableLayout)panel3.getLayout()).setVGap(5);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new TableLayout(new double[][] {
                            {TableLayout.FILL},
                            {TableLayout.PREFERRED}}));
                        ((TableLayout)panel4.getLayout()).setHGap(5);
                        ((TableLayout)panel4.getLayout()).setVGap(5);

                        //---- label4 ----
                        label4.setText("Origem:");
                        label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel4.add(label4, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                    }
                    panel3.add(panel4, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                }
                buttonBar.add(panel3, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- lblOrigem ----
                lblOrigem.setText("oooooooooooooooooooo");
                buttonBar.add(lblOrigem, new TableLayoutConstraints(1, 0, 2, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //======== panel5 ========
                {
                    panel5.setLayout(new TableLayout(new double[][] {
                        {TableLayout.FILL},
                        {TableLayout.PREFERRED}}));
                    ((TableLayout)panel5.getLayout()).setHGap(5);
                    ((TableLayout)panel5.getLayout()).setVGap(5);

                    //---- label5 ----
                    label5.setText("Destino: ");
                    label5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel5.add(label5, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                }
                buttonBar.add(panel5, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- destino ----
                destino.setText("text");
                buttonBar.add(destino, new TableLayoutConstraints(1, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //======== panel6 ========
                {
                    panel6.setLayout(new TableLayout(new double[][] {
                        {TableLayout.FILL},
                        {TableLayout.PREFERRED}}));
                    ((TableLayout)panel6.getLayout()).setHGap(5);
                    ((TableLayout)panel6.getLayout()).setVGap(5);

                    //---- label6 ----
                    label6.setText("Motorista: ");
                    panel6.add(label6, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                }
                buttonBar.add(panel6, new TableLayoutConstraints(0, 2, 0, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- motorista ----
                motorista.setText("text");
                buttonBar.add(motorista, new TableLayoutConstraints(1, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- label7 ----
                label7.setText("Placa do carro:");
                buttonBar.add(label7, new TableLayoutConstraints(0, 3, 0, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- placa ----
                placa.setText("text");
                buttonBar.add(placa, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- label9 ----
                label9.setText("Pre\u00e7o: ");
                buttonBar.add(label9, new TableLayoutConstraints(0, 4, 0, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- preco ----
                preco.setText("text");
                buttonBar.add(preco, new TableLayoutConstraints(1, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //======== panel7 ========
                {
                    panel7.setLayout(new TableLayout(new double[][] {
                        {TableLayout.FILL},
                        {TableLayout.PREFERRED}}));
                    ((TableLayout)panel7.getLayout()).setHGap(5);
                    ((TableLayout)panel7.getLayout()).setVGap(5);

                    //---- okButton ----
                    okButton.setText("Confirmar Viagem");
                    okButton.setBackground(new Color(0xccffcc));
                    panel7.add(okButton, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
                }
                buttonBar.add(panel7, new TableLayoutConstraints(0, 6, 0, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //---- cancelButton ----
                cancelButton.setText("Cancelar");
                cancelButton.setBackground(new Color(0xffff66));
                buttonBar.add(cancelButton, new TableLayoutConstraints(2, 6, 2, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

                //======== scrollPane1 ========
                {

                    //======== panel2 ========
                    {
                        panel2.setLayout(new BorderLayout());
                    }
                    scrollPane1.setViewportView(panel2);
                }
                buttonBar.add(scrollPane1, new TableLayoutConstraints(0, 8, 0, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            }
            dialogPane.add(buttonBar, new TableLayoutConstraints(1, 2, 2, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //======== panel1 ========
            {
                panel1.setLayout(new TableLayout(new double[][] {
                    {TableLayout.FILL},
                    {TableLayout.PREFERRED}}));

                //======== panel8 ========
                {
                    panel8.setLayout(new TableLayout(new double[][] {
                        {TableLayout.FILL, TableLayout.PREFERRED},
                        {TableLayout.PREFERRED, TableLayout.PREFERRED}}));
                }
                panel1.add(panel8, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            }
            dialogPane.add(panel1, new TableLayoutConstraints(1, 3, 1, 3, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
    private JPanel dialogPane;
    private JPanel panel9;
    private JLabel label3;
    private JPanel buttonBar;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label4;
    private JLabel lblOrigem;
    private JPanel panel5;
    private JLabel label5;
    private JLabel destino;
    private JPanel panel6;
    private JLabel label6;
    private JLabel motorista;
    private JLabel label7;
    private JLabel placa;
    private JLabel label9;
    private JLabel preco;
    private JPanel panel7;
    private JButton okButton;
    private JButton cancelButton;
    private JScrollPane scrollPane1;
    private JPanel panel2;
    private JPanel panel1;
    private JPanel panel8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
