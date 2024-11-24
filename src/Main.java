import telas.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Inicializa a interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Cria a tela de login
                    Login tela = new Login(); // Instância de loginPage
                    tela.setTitle("Tela de Login"); // Título da tela
                    tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ação ao fechar
                    tela.abrir(); // Exibe a tela de login
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
