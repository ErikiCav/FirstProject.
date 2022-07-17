package utilidades;
import outros.OutrasOpcoes;
import transferencias.TransferenciaPix;

import javax.swing.*;
import java.sql.SQLException;
public class Modos {
    public static void ModoGod() throws SQLException {
        int escolhaModoEtapa2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero da op��o desejada!\n\n1 - Abrir conta.\n2- Adicionar saldo por CPF.\n3- Sair."));
        switch (escolhaModoEtapa2) {
            case 1 -> {
                String cpfCase1 = (JOptionPane.showInputDialog("Digite o n�mero de seu CPF: "));
                String nomeCase1 = (JOptionPane.showInputDialog("Digite seu nome completo: "));
                String senhaCase1 = (JOptionPane.showInputDialog("Digite sua senha: "));
                JOptionPane.showMessageDialog(null, Database.AbrirConta(cpfCase1, nomeCase1, senhaCase1));
            }
            case 2 -> {
                String cpfCase2 = (JOptionPane.showInputDialog("Digite o n�mero de seu CPF: "));
                String valorCase2 = (JOptionPane.showInputDialog("Digite o valor que deseja adicionar: "));
                double valor = Double.parseDouble(Database.FormatarDouble(valorCase2));
                Database.AdicionarSaldo(cpfCase2, valor);
                JOptionPane.showMessageDialog(null, "Valor adicionado com sucesso!");
            }
            case 3 -> System.exit(0);
        }
    }
    public static void ModoUser() throws SQLException {
        while(true) {
            String contaCase2 = (JOptionPane.showInputDialog("Digite o n�mero de sua conta com d�gito verificador: "));
            String senhaCase2 = (JOptionPane.showInputDialog("Digite sua senha: "));
            String cpf = Database.BuscarConta(contaCase2)[1];
            if(!Database.LoginCorreto(cpf, senhaCase2)){
                JOptionPane.showMessageDialog(null, "Os dados informados est�o incorretos, verifique o n�mero de sua conta ou sua senha. Tente novamente ou clique em cancelar!");
            } else{
                while(true){
                    int escolhaModoEtapa2 = Integer.parseInt((JOptionPane.showInputDialog("Digite o n�mero da op��o desejada!\n\nMenu de transfer�ncia PIX.\n1- Fazer transfer�ncia PIX por chave CPF.\n2- Fazer transfer�ncia PIX por n�mero da conta.\n\nOutras op��es.\n3- Consultar saldo.\n4- Meus dados banc�rios.\n5- Sacar.\n6- Sair.")));
                    switch (escolhaModoEtapa2){
                        case 1:
                            TransferenciaPix.ChaveCpf(cpf);
                        case 2:
                            TransferenciaPix.ChaveConta(cpf);
                            break;
                        case 3:
                            OutrasOpcoes.ConsultarSaldo(cpf);
                            break;
                        case 4:
                            OutrasOpcoes.DadosBancarios(cpf);
                            break;
                        case 5:
                            OutrasOpcoes.Sacar(cpf);
                            break;
                        case 6:
                            System.exit(0);
                    }
                }
            }
        }
    }
}
