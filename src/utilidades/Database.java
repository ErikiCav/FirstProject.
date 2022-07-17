package utilidades;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Random;
public class Database {
    public static Connection conexao;
    public static String host = "localhost:3306"; //Host de seu banco de dados mysql
    public static String name = "teste"; //Nome de seu schema mysql.
    public static String url = "jdbc:mysql://"+host + "/" + name;
    public static String username = "root"; //Usuário de seu banco de dados mysql.
    public static String password = "root"; //Senha de seu banco de dados mysql.

    public static Connection Conector () throws SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver"); //Driver mysql (independente) é requerido.
            conexao = DriverManager.getConnection(url, username, password);
            return conexao;
        }catch (SQLException | ClassNotFoundException erro){
            return (Connection) erro;
        }
    }
    public static boolean CriarDefault() throws SQLException {
        Conector().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS CONTA(CPF TEXT NOT NULL, NOME TEXT NOT NULL, CONTA TEXT NOT NULL, AGENCIA TEXT NOT NULL, SENHA TEXT NOT NULL, SALDO TEXT NOT NULL);");
        Conector().close();
        return true;
    }
    public static String AbrirConta(String cpf, String nome, String senha) throws SQLException {
        String STRING_CONTA_CRIADA, STRING_CONTA_EXISTENTE;

        if (!ContaExistente(cpf)) {
            Random random = new Random();
            String contaNumero = String.valueOf(random.nextInt(99999999));
            String contaDigito = String.valueOf(random.nextInt(9));
            String agencia = "0089";
            String conta = (contaNumero + "-" + contaDigito);

            Conector().createStatement().executeUpdate("INSERT INTO CONTA(CPF, NOME, CONTA, AGENCIA, SENHA, SALDO) VALUES ('" + cpf + "','" + nome + "', '" + conta + "', '" + agencia + "','" + senha + "','" + 0.00 + "" + "');");
            Conector().close();
            return STRING_CONTA_CRIADA = "Conta criada com sucesso!\n\nNome: "+nome+"\nCpf: "+cpf+"\nAgência: "+agencia+"\nConta: "+conta+"\nSenha: "+senha;
        } else {
            return STRING_CONTA_EXISTENTE = "CPF JÁ CADASTRADO: Desculpe, não foi possível abrir uma conta com este cpf!";
        }
    }

    public static String[] BuscarContaCpf(String cpf) throws SQLException {
        String nome = null, conta=null, agencia=null, saldo=null, senha=null;
        ResultSet dados = Conector().prepareStatement("SELECT * FROM CONTA WHERE CPF='"+cpf+"'").executeQuery();

        while(dados.next()){
            nome = dados.getString("nome");
            conta = dados.getString("conta");
            agencia = dados.getString("agencia");
            saldo = dados.getString("saldo");
            senha = dados.getString("senha");
        }
        dados.close();
        return new String[] {nome , cpf , conta , agencia , saldo, senha};
    }

    public static String[] BuscarConta(String conta) throws SQLException {
        String nome = null, cpf=null, agencia=null, saldo=null, senha=null;
        ResultSet dados = Conector().prepareStatement("SELECT * FROM CONTA WHERE CONTA='"+conta+"'").executeQuery();

        while(dados.next()){
            nome = dados.getString("nome");
            cpf = dados.getString("cpf");
            agencia = dados.getString("agencia");
            saldo = dados.getString("saldo");
            senha = dados.getString("senha");
        }
        dados.close();
        return new String[] {nome , cpf , conta , agencia , saldo, senha};
    }
    public static boolean ContaExistente(String cpf) throws SQLException {
        String resultado = (Arrays.toString(BuscarContaCpf(cpf)));
        return !resultado.contains("null");
    }

    public static boolean LoginCorreto(String cpf, String senha) throws SQLException {
        String senhaCadastrada = String.valueOf(BuscarContaCpf(cpf)[5]);
        if(senhaCadastrada.contains(senha)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean AlterarSaldo(String cpf, double novoSaldo) throws SQLException {
        Conector().createStatement().executeUpdate("UPDATE CONTA SET SALDO='"+novoSaldo+"' WHERE CPF='"+cpf+"'");
        Conector().close();
        return true;
    }

    public static boolean AdicionarSaldo(String cpf, double valor) throws SQLException {
        double saldoAnterior = Double.parseDouble(String.valueOf(BuscarContaCpf(cpf)[4]));
        double saldoSomado = saldoAnterior + valor;
        AlterarSaldo(cpf, saldoSomado);
        return true;
    }

    public static boolean RemoverSaldo(String cpf, double valor) throws SQLException {
        double saldoAnterior = Double.parseDouble(String.valueOf(BuscarContaCpf(cpf)[4]));
        double saldoSubtraido = saldoAnterior - valor;
        AlterarSaldo(cpf, saldoSubtraido);
        return true;
    }

    public static boolean SaldoSuficiente(String cpf, double valor) throws SQLException {
        double saldo = Double.parseDouble(String.valueOf(BuscarContaCpf(cpf)[4]));
        if(saldo >= valor){
            return true;
        } else{
            return false;
        }
    }

    public static boolean TransferirSaldo(String cpfOrigem, String cpfDestino, double valor) throws SQLException {
        double saldoOrigem = Double.parseDouble(String.valueOf(BuscarContaCpf(cpfOrigem)[4]));
        double saldoDestino = Double.parseDouble(String.valueOf(BuscarContaCpf(cpfDestino)[4]));
        if(SaldoSuficiente(cpfOrigem, valor)) {
            double saldoOrigemSubtraido = saldoOrigem - valor;
            double saldoDestinoSomado = saldoDestino + valor;
            AlterarSaldo(cpfOrigem, saldoOrigemSubtraido);
            AlterarSaldo(cpfDestino, saldoDestinoSomado);
            return true;
        }else{
            return false;
        }
    }
    public static boolean CpfDuplicado(String cpfOrigem, String cpfDestino) {
        if(cpfOrigem.contains(cpfDestino)){
            return true;
        } else{
            return false;
        }
    }
    public static String FormatarDouble(String valor) {
        String formatado;
        if(valor.contains(".") && valor.contains(",")){
            return formatado = valor.replace(".","").replace(",",".");
        }
        if(valor.contains(",")){
            return formatado = valor.replace(",",".");
        }
        return valor;
    }

    public static boolean Sacado(String cpf, String valor) throws SQLException {
        double saldo = Double.parseDouble(String.valueOf(BuscarContaCpf(cpf)[4]));
        if(SaldoSuficiente(cpf, Double.parseDouble(valor))) {
            double saldoSubtraido = saldo - Double.parseDouble(valor);
            AlterarSaldo(cpf, saldoSubtraido);
            return true;
        }else{
            return false;
        }
    }
}