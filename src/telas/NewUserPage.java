package telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import interfaces.Visible;
import models.SistemaMoveON;
/*
 * Created by JFormDesigner on Sat Nov 23 14:41:52 BRT 2024
 */



/**
 * @author User
 */
public class NewUserPage extends JFrame implements Visible {
    private JFrame Cadastro;
    public NewUserPage() {
        initComponents();
        configureListeners();
        Cadastro = this;
    }

    @Override
    public void abrir() {
        Cadastro.setVisible(true);
    }

    private void configureListeners() {
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Coletar dados dos campos
                String nomeCompleto = txtNomeCompleto.getText().trim();
                String email = txtEmail.getText().trim();
                String telefone = txtTelefone.getText().trim();
                String senha = new String(textSenha.getPassword()).trim();

                // Validação simples (verificar se os campos estão preenchidos)
                if (nomeCompleto.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Mensagens de depuração
                System.out.println("Nome: " + nomeCompleto);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);
                System.out.println("Senha: " + senha);

                // Chamar metodo para salvar usuário
                try {
                    SistemaMoveON sistema = new SistemaMoveON();
                    sistema.salvarUsuario("Cliente", nomeCompleto, email, telefone, senha, null);

                    // Mostrar mensagem de sucesso e limpar os campos
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    txtNomeCompleto.setText("");
                    txtEmail.setText("");
                    txtTelefone.setText("");
                    textSenha.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

                dispose(); // Fecha a janela atual
                new Login().abrir();
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
        panel1 = new JPanel();
        panel5 = new JPanel();
        label1 = new JLabel();
        panel4 = new JPanel();
        label3 = new JLabel();
        txtNomeCompleto = new JTextField();
        label4 = new JLabel();
        txtEmail = new JTextField();
        panel3 = new JPanel();
        label5 = new JLabel();
        txtTelefone = new JTextField();
        panel2 = new JPanel();
        label6 = new JLabel();
        textSenha = new JPasswordField();
        btnCadastrar = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "3*(default, $lcgap), 2*(pref, $lcgap), 6*(default, $lcgap), default",
            "2*(fill:pref, $lgap), default, $lgap, fill:pref, 5*($lgap, default)"));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "pref",
                "fill:pref"));
        }
        contentPane.add(panel1, CC.xy(7, 1));

        //======== panel5 ========
        {
            panel5.setLayout(new FormLayout(
                "default",
                "fill:pref"));
        }
        contentPane.add(panel5, CC.xy(3, 3));

        //---- label1 ----
        label1.setText("Novo por aqui?");
        label1.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 16));
        contentPane.add(label1, CC.xy(5, 3));

        //======== panel4 ========
        {
            panel4.setLayout(new FormLayout(
                "default",
                "default"));

            //---- label3 ----
            label3.setText("Nome completo");
            label3.setHorizontalAlignment(SwingConstants.LEFT);
            label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel4.add(label3, CC.xy(1, 1));
        }
        contentPane.add(panel4, CC.xy(3, 7));
        contentPane.add(txtNomeCompleto, CC.xywh(5, 7, 11, 1));

        //---- label4 ----
        label4.setText("email");
        label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label4, CC.xy(3, 9));
        contentPane.add(txtEmail, CC.xywh(5, 9, 11, 1));

        //======== panel3 ========
        {
            panel3.setLayout(new FormLayout(
                "pref, 6*($lcgap, default)",
                "default"));
        }
        contentPane.add(panel3, CC.xywh(9, 9, 11, 1));

        //---- label5 ----
        label5.setText("telefone");
        label5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label5, CC.xy(3, 11));

        //---- txtTelefone ----
        txtTelefone.setText("(XX) 9 XXXX-XXXX");
        txtTelefone.setForeground(Color.gray);
        contentPane.add(txtTelefone, CC.xywh(5, 11, 11, 1));

        //======== panel2 ========
        {
            panel2.setLayout(new FormLayout(
                "default",
                "default"));

            //---- label6 ----
            label6.setText("senha");
            label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel2.add(label6, CC.xy(1, 1));
        }
        contentPane.add(panel2, CC.xy(3, 13));
        contentPane.add(textSenha, CC.xywh(5, 13, 11, 1));

        //---- btnCadastrar ----
        btnCadastrar.setText("cadastrar");
        contentPane.add(btnCadastrar, CC.xy(5, 17));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
    private JPanel panel1;
    private JPanel panel5;
    private JLabel label1;
    private JPanel panel4;
    private JLabel label3;
    private JTextField txtNomeCompleto;
    private JLabel label4;
    private JTextField txtEmail;
    private JPanel panel3;
    private JLabel label5;
    private JTextField txtTelefone;
    private JPanel panel2;
    private JLabel label6;
    private JPasswordField textSenha;
    private JButton btnCadastrar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
