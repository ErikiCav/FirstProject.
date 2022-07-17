package transferencias;

import utilidades.Database;

import javax.swing.*;
import java.sql.SQLException;

public class TransferenciaPix {
    public static void ChaveCpf(String cpfOrigem) throws SQLException {
        String cpfDestino = (JOptionPane.showInputDialog("Digite o número de CPF do destinatário com pontos e traço: "));
        String valorDigitado = (JOptionPane.showInputDialog("Digite o valor que deseja transferir: "));
        double valor = Double.parseDouble(Database.FormatarDouble(valorDigitado));
        if(Database.ContaExistente(cpfDestino)){
            if(Database.CpfDuplicado(cpfOrigem, cpfDestino)){
                JOptionPane.showMessageDialog(null, "É impossível enviar dinheiro a sí mesmo. Está tentando gerar dinheiro infinito? Usuário expulso!");
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
                String mensagemConfirmacao = "Os dados abaixo estão corretos? Digite SIM e aperte em \"OK\" para confirmar. \n\nNOME: " +nomeDestino+
                        "\nCPF: "+cpfDestinoFormatado+
                        "\nAGÊNCIA: "+agencia+
                        "\nCONTA: "+conta+
                        "\n\nVALOR: "+valorFormatado;
                String confirmacaoResultado = (JOptionPane.showInputDialog(mensagemConfirmacao));
                if(confirmacaoResultado.toLowerCase().contains("sim")){
                    if(Database.TransferirSaldo(cpfOrigem, cpfDestino, valor)) {
                        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso. Operação finalizada!");
                        System.exit(0);
                    } else{
                        JOptionPane.showMessageDialog(null, "Desculpe, você não possui saldo suficiente para esta transação. operação finalizada!");
                        System.exit(0);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ok, tente informar os dados corretos da próxima vez!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Desculpe, não encontrei nenhuma conta com o CPF informado. Tente novamente!");
        }
    }
    public static void ChaveConta(String cpfOrigem) throws SQLException {
        String contaDestino = (JOptionPane.showInputDialog("Digite o número da conta do destinatário com digito verificador: "));
        String valorDigitado = (JOptionPane.showInputDialog("Digite o valor que deseja transferir: "));
        double valor = Double.parseDouble(Database.FormatarDouble(valorDigitado));
        String[] dadosBancariosBuscaCpf = Database.BuscarConta(contaDestino);
        String cpfDestino = dadosBancariosBuscaCpf[1];
        if(Database.ContaExistente(cpfDestino)){
            if(Database.CpfDuplicado(cpfOrigem, cpfDestino)){
                JOptionPane.showMessageDialog(null, "É impossível enviar dinheiro a sí mesmo. Está tentando gerar dinheiro infinito? Usuário expulso!");
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
                String mensagemConfirmacao = "Os dados abaixo estão corretos? Digite SIM e aperte em \"OK\" para confirmar. \n\nNOME: " +nomeDestino+
                        "\nCPF: "+cpfDestinoFormatado+
                        "\nAGÊNCIA: "+agencia+
                        "\nCONTA: "+conta+
                        "\n\nVALOR: "+valorFormatado;
                String confirmacaoResultado = (JOptionPane.showInputDialog(mensagemConfirmacao));
                if(confirmacaoResultado.toLowerCase().contains("sim")){
                    if(Database.TransferirSaldo(cpfOrigem, cpfDestino, valor)) {
                        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso. Operação finalizada!");
                        System.exit(0);
                    } else{
                        JOptionPane.showMessageDialog(null, "Desculpe, você não possui saldo suficiente para esta transação. operação finalizada!");
                        System.exit(0);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ok, tente informar os dados corretos da próxima vez!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Desculpe, não encontrei nenhuma conta com o CPF informado. Tente novamente!");
        }
    }
}
