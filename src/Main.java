import utilidades.Database;
import utilidades.Modos;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.CriarDefault(); //Inicia as colunas padr�es no mysql. (se n�o existirem)
        while(true){
            int escolhaModoEtapa1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero da op��o desejada!\n\n1- Modo God.\n2- Modo usu�rio comum.\n3- Sair."));
            switch (escolhaModoEtapa1) {
                case 1 -> Modos.ModoGod(); //Permite abrir contas e adicionar saldo por CPF.
                case 2 -> Modos.ModoUser(); //Permite fazer transfer�ncias, saques e outros.
                case 3 -> System.exit(0); //Fecha at� o processador do computador se vacilar.
            }
        }
    }
}