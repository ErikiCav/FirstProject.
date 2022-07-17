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
        retorno = String.format("AGÊNCIA: %s\nCONTA: %s \nSALDO: R$ %,.2f", agencia, conta, saldo);
        JOptionPane.showMessageDialog(null, retorno);
        JOptionPane.showMessageDialog(null, "Operação finalizada!");
        System.exit(0);
    }

    public static void DadosBancarios(String cpf) throws SQLException {
        String retorno;
        String[] dadosBancarios = Database.BuscarContaCpf(cpf);
        String nome = dadosBancarios[0];
        String conta = dadosBancarios[2];
        String agencia = dadosBancarios[3];
        retorno = String.format("Estes são seus dados bancários!\n\nNOME: %s\nCPF: %s\nAGÊNCIA: %s\nCONTA: %s", nome, cpf, agencia, conta);
        JOptionPane.showMessageDialog(null, retorno);
        JOptionPane.showMessageDialog(null, "Operação finalizada!");
        System.exit(0);
    }

    public static void Sacar(String cpf) throws SQLException {
        String valorDigitado = (JOptionPane.showInputDialog("Digite o valor que deseja sacar: "));
        double valor = Double.parseDouble(Database.FormatarDouble(valorDigitado));
        if(!Database.Sacado(cpf, String.valueOf(valor))){
            JOptionPane.showMessageDialog(null, "Desculpe, você não possui saldo suficiente. Operação finalizada!");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso. Operação finalizada!");
            System.exit(0);
        }
    }
}
