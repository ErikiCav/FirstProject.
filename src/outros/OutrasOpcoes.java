package outros;

import utilidades.Database;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;

public class OutrasOpcoes {
    public static void ConsultarSaldo(String cpf) throws SQLException {
        String retorno;
        String[] dadosBancarios = Database.BuscarContaCpf(cpf);
        String conta = dadosBancarios[2];
        String agencia = dadosBancarios[3];
        double saldo = Double.parseDouble(dadosBancarios[4]);
        retorno = String.format("AG�NCIA: %s\nCONTA: %s \nSALDO: R$ %,.2f", agencia, conta, saldo);
        JOptionPane.showMessageDialog(null, retorno);
        JOptionPane.showMessageDialog(null, "Opera��o finalizada!");
        System.exit(0);
    }

    public static void DadosBancarios(String cpf) throws SQLException {
        String retorno;
        String[] dadosBancarios = Database.BuscarContaCpf(cpf);
        String nome = dadosBancarios[0];
        String conta = dadosBancarios[2];
        String agencia = dadosBancarios[3];
        retorno = String.format("Estes s�o seus dados banc�rios!\n\nNOME: %s\nCPF: %s\nAG�NCIA: %s\nCONTA: %s", nome, cpf, agencia, conta);
        JOptionPane.showMessageDialog(null, retorno);
        JOptionPane.showMessageDialog(null, "Opera��o finalizada!");
        System.exit(0);
    }

    public static void Sacar(String cpf) throws SQLException {
        String valorDigitado = (JOptionPane.showInputDialog("Digite o valor que deseja sacar: "));
        double valor = Double.parseDouble(Database.FormatarDouble(valorDigitado));
        if(!Database.Sacado(cpf, String.valueOf(valor))){
            JOptionPane.showMessageDialog(null, "Desculpe, voc� n�o possui saldo suficiente. Opera��o finalizada!");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso. Opera��o finalizada!");
            System.exit(0);
        }
    }
}
