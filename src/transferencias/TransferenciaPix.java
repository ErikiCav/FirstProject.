package transferencias;

import utilidades.Database;

import javax.swing.*;
import java.sql.SQLException;

public class TransferenciaPix {
    public static void ChaveCpf(String cpfOrigem) throws SQLException {
        String cpfDestino = (JOptionPane.showInputDialog("Digite o n�mero de CPF do destinat�rio com pontos e tra�o: "));
        String valorDigitado = (JOptionPane.showInputDialog("Digite o valor que deseja transferir: "));
        double valor = Double.parseDouble(Database.FormatarDouble(valorDigitado));
        if(Database.ContaExistente(cpfDestino)){
            if(Database.CpfDuplicado(cpfOrigem, cpfDestino)){
                JOptionPane.showMessageDialog(null, "� imposs�vel enviar dinheiro a s� mesmo. Est� tentando gerar dinheiro infinito? Usu�rio expulso!");
                System.exit(0);
            }else{
                String cpfDestinoFatiadoPart1 = (cpfDestino.split("\\.", 8))[1];
                String cpfDestinoFatiadoPart2 = String.valueOf((cpfDestino.split("\\.", 8))[2].split("\\-")[0]);
                String cpfDestinoFormatado = "***."+cpfDestinoFatiadoPart1+"."+cpfDestinoFatiadoPart2+"-**";
                String[] dadosBancarios = Database.BuscarContaCpf(cpfDestino);
                String nomeDestino = dadosBancarios[0];
                String conta = dadosBancarios[2];
                String agencia = dadosBancarios[3];
                String valorFormatado = String.format("R$ %,.2f", valor);
                String mensagemConfirmacao = "Os dados abaixo est�o corretos? Digite SIM e aperte em \"OK\" para confirmar. \n\nNOME: " +nomeDestino+
                        "\nCPF: "+cpfDestinoFormatado+
                        "\nAG�NCIA: "+agencia+
                        "\nCONTA: "+conta+
                        "\n\nVALOR: "+valorFormatado;
                String confirmacaoResultado = (JOptionPane.showInputDialog(mensagemConfirmacao));
                if(confirmacaoResultado.toLowerCase().contains("sim")){
                    if(Database.TransferirSaldo(cpfOrigem, cpfDestino, valor)) {
                        JOptionPane.showMessageDialog(null, "Transfer�ncia realizada com sucesso. Opera��o finalizada!");
                        System.exit(0);
                    } else{
                        JOptionPane.showMessageDialog(null, "Desculpe, voc� n�o possui saldo suficiente para esta transa��o. opera��o finalizada!");
                        System.exit(0);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ok, tente informar os dados corretos da pr�xima vez!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Desculpe, n�o encontrei nenhuma conta com o CPF informado. Tente novamente!");
        }
    }
    public static void ChaveConta(String cpfOrigem) throws SQLException {
        String contaDestino = (JOptionPane.showInputDialog("Digite o n�mero da conta do destinat�rio com digito verificador: "));
        String valorDigitado = (JOptionPane.showInputDialog("Digite o valor que deseja transferir: "));
        double valor = Double.parseDouble(Database.FormatarDouble(valorDigitado));
        String[] dadosBancariosBuscaCpf = Database.BuscarConta(contaDestino);
        String cpfDestino = dadosBancariosBuscaCpf[1];
        if(Database.ContaExistente(cpfDestino)){
            if(Database.CpfDuplicado(cpfOrigem, cpfDestino)){
                JOptionPane.showMessageDialog(null, "� imposs�vel enviar dinheiro a s� mesmo. Est� tentando gerar dinheiro infinito? Usu�rio expulso!");
                System.exit(0);
            }else{
                String cpfDestinoFatiadoPart1 = (cpfDestino.split("\\.", 8))[1];
                String cpfDestinoFatiadoPart2 = String.valueOf((cpfDestino.split("\\.", 8))[2].split("\\-")[0]);
                String cpfDestinoFormatado = "***."+cpfDestinoFatiadoPart1+"."+cpfDestinoFatiadoPart2+"-**";
                String[] dadosBancarios = Database.BuscarContaCpf(cpfDestino);
                String nomeDestino = dadosBancarios[0];
                String conta = dadosBancarios[2];
                String agencia = dadosBancarios[3];
                String valorFormatado = String.format("R$ %,.2f", valor);
                String mensagemConfirmacao = "Os dados abaixo est�o corretos? Digite SIM e aperte em \"OK\" para confirmar. \n\nNOME: " +nomeDestino+
                        "\nCPF: "+cpfDestinoFormatado+
                        "\nAG�NCIA: "+agencia+
                        "\nCONTA: "+conta+
                        "\n\nVALOR: "+valorFormatado;
                String confirmacaoResultado = (JOptionPane.showInputDialog(mensagemConfirmacao));
                if(confirmacaoResultado.toLowerCase().contains("sim")){
                    if(Database.TransferirSaldo(cpfOrigem, cpfDestino, valor)) {
                        JOptionPane.showMessageDialog(null, "Transfer�ncia realizada com sucesso. Opera��o finalizada!");
                        System.exit(0);
                    } else{
                        JOptionPane.showMessageDialog(null, "Desculpe, voc� n�o possui saldo suficiente para esta transa��o. opera��o finalizada!");
                        System.exit(0);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ok, tente informar os dados corretos da pr�xima vez!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Desculpe, n�o encontrei nenhuma conta com o CPF informado. Tente novamente!");
        }
    }
}
