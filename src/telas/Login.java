/*
 * Created by JFormDesigner on Sun Nov 24 12:21:22 BRT 2024
 */

package telas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import interfaces.Visible;
import models.Cliente;
import models.SistemaMoveON;

import static java.util.Objects.nonNull;

/**
 * @author User
 */
public class Login extends JFrame implements Visible {
    private SistemaMoveON sistema; // Instância do SistemaMoveON para validações

    public Login() {
        sistema = new SistemaMoveON(); // Inicializa o sistema
        initComponents();
        configureListeners();
    }

    @Override
    public void abrir() {
        setVisible(true); // Exibe a janela de login
    }

    private void configureListeners() {
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuarioEmail.getText();
                String senha = new String(txtSenha.getPassword());
                Cliente cliente = sistema.recuperarUsuario(usuario, senha);
                if (nonNull(cliente)) {
                    JOptionPane.showMessageDialog(Login.this, "Login realizado com sucesso!");

                    // abrir tela incial
                    new InicialPage(cliente).abrir();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Ação do botão "Cadastrar-se"
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new NewUserPage().abrir(); // Abre a tela de cadastro
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Izabelle Barbosa (IZABELLE GARCEZ BARBOSA)
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        txtUsuarioEmail = new JTextField();
        label3 = new JLabel();
        txtSenha = new JPasswordField();
        buttonBar = new JPanel();
        btnEntrar = new JButton();
        btnCadastrar = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(Borders.createEmptyBorder("7dlu, 7dlu, 7dlu, 7dlu"));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new FormLayout(
                    "14*(default, $lcgap), default",
                    "14*(default, $lgap), default"));

                //---- label1 ----
                label1.setText("Sistema MoveOn");
                label1.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 18));
                contentPanel.add(label1, CC.xy(7, 3));

                //---- label2 ----
                label2.setText("email");
                contentPanel.add(label2, CC.xy(3, 9));
                contentPanel.add(txtUsuarioEmail, CC.xy(7, 9));

                //---- label3 ----
                label3.setText("senha");
                contentPanel.add(label3, CC.xy(3, 13));
                contentPanel.add(txtSenha, CC.xy(7, 13));
            }
            dialogPane.add(contentPanel, BorderLayout.WEST);

            //======== buttonBar ========
            {
                buttonBar.setBorder(Borders.createEmptyBorder("5dlu, 0dlu, 0dlu, 0dlu"));
                buttonBar.setLayout(new FormLayout(
                    "$glue, $button, $rgap, $button",
                    "pref"));

                //---- btnEntrar ----
                btnEntrar.setText("entrar");
                buttonBar.add(btnEntrar, CC.xy(2, 1));

                //---- btnCadastrar ----
                btnCadastrar.setText("cadastrar-se");
                buttonBar.add(btnCadastrar, CC.xy(4, 1));
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
    private JLabel label1;
    private JLabel label2;
    private JTextField txtUsuarioEmail;
    private JLabel label3;
    private JPasswordField txtSenha;
    private JPanel buttonBar;
    private JButton btnEntrar;
    private JButton btnCadastrar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
