import utilidades.Database;
import utilidades.Modos;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.CriarDefault(); //Inicia as colunas padrões no mysql. (se não existirem)
        while(true){
            int escolhaModoEtapa1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da opção desejada!\n\n1- Modo God.\n2- Modo usuário comum.\n3- Sair."));
            switch (escolhaModoEtapa1) {
                case 1 -> Modos.ModoGod(); //Permite abrir contas e adicionar saldo por CPF.
                case 2 -> Modos.ModoUser(); //Permite fazer transferências, saques e outros.
                case 3 -> System.exit(0); //Fecha até o processador do computador se vacilar.
            }
        }
    }
}