package utilidades;
import outros.OutrasOpcoes;
import transferencias.TransferenciaPix;

import javax.swing.*;
import java.sql.SQLException;
public class Modos {
    public static void ModoGod() throws SQLException {
        int escolhaModoEtapa2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da opção desejada!\n\n1 - Abrir conta.\n2- Adicionar saldo por CPF.\n3- Sair."));
        switch (escolhaModoEtapa2) {
            case 1 -> {
                String cpfCase1 = (JOptionPane.showInputDialog("Digite o número de seu CPF: "));
                String nomeCase1 = (JOptionPane.showInputDialog("Digite seu nome completo: "));
                String senhaCase1 = (JOptionPane.showInputDialog("Digite sua senha: "));
                JOptionPane.showMessageDialog(null, Database.AbrirConta(cpfCase1, nomeCase1, senhaCase1));
            }
            case 2 -> {
                String cpfCase2 = (JOptionPane.showInputDialog("Digite o número de seu CPF: "));
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
            String contaCase2 = (JOptionPane.showInputDialog("Digite o número de sua conta com dígito verificador: "));
            String senhaCase2 = (JOptionPane.showInputDialog("Digite sua senha: "));
            String cpf = Database.BuscarConta(contaCase2)[1];
            if(!Database.LoginCorreto(cpf, senhaCase2)){
                JOptionPane.showMessageDialog(null, "Os dados informados estão incorretos, verifique o número de sua conta ou sua senha. Tente novamente ou clique em cancelar!");
            } else{
                while(true){
                    int escolhaModoEtapa2 = Integer.parseInt((JOptionPane.showInputDialog("Digite o número da opção desejada!\n\nMenu de transferência PIX.\n1- Fazer transferência PIX por chave CPF.\n2- Fazer transferência PIX por número da conta.\n\nOutras opções.\n3- Consultar saldo.\n4- Meus dados bancários.\n5- Sacar.\n6- Sair.")));
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
