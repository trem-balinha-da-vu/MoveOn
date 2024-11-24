/*
 * Created by JFormDesigner on Sun Nov 24 16:59:26 BRT 2024
 */

package telas;

import interfaces.Visible;
import models.Cliente;
import models.Viagem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author User
 */
public class Historico extends JFrame implements Visible {
    private Cliente cliente;

    public Historico(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        populaHistorico();
        configureListeners();
    }

    private void configureListeners() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
            }
        });
    }

    private void populaHistorico() {
        // Obter o histórico de viagens do cliente
        List<Viagem> historico = cliente.getHistoricoViagens();

        // Verificar se o histórico está vazio
        if (historico == null || historico.isEmpty()) {
            System.out.println("Histórico de viagens está vazio!");
            return;
        }

        // Adicionar as viagens ao modelo da lista
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Viagem viagem : historico) {
            String statusDaViagem = viagem.status != null ? viagem.status.getStatusDaViagem() : "concluida";
            String item = String.format("Origem: %s, Destino: %s, Valor: %s, Status: %s",
                    viagem.getOrigem(), viagem.getDestino(), viagem.getValor(), statusDaViagem);
            listModel.addElement(item);
        }

        // Vincular o modelo à JList
        list1.setModel(listModel);
    }

    @Override
    public void abrir() {
        pack();
        setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        list1 = new JList<>();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new CardLayout());

                //======== scrollPane1 ========
                {

                    //---- list1 ----
                    list1.setBackground(new Color(0xd6d9df));
                    list1.setModel(new AbstractListModel<String>() {
                        String[] values = {
                            "historicoLista"
                        };
                        @Override
                        public int getSize() { return values.length; }
                        @Override
                        public String getElementAt(int i) { return values[i]; }
                    });
                    scrollPane1.setViewportView(list1);
                }
                contentPanel.add(scrollPane1, "card1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JList<String> list1;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
