package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {
	private boolean status = false;
	private String mensagem = ""; // variavel que vai informar o status da conexao
	private Connection con = null; // variavel para conexao
	private Statement statement;
	private ResultSet resultSet;

	private String servidor = "localhost:5452";
	private String nomeDoBanco = "trab_1_prog3";
	private String usuario = "root";
	private String senha = "";

	public Conexao() {}
	public Conexao(String pServidor, String pNomeDoBanco, String pUsuario, String pSenha) {
		this.servidor = pServidor;
		this.nomeDoBanco = pNomeDoBanco;
		this.usuario = pUsuario;
		this.senha = pSenha;
	}

	/**
	 * Abre uma conexao com o banco
	 * 
	 * @return Connection
	 */
	public Connection conectar() {
		try {
			// drive do banco
			Class.forName("org.postgresql.Driver");

			// local do banco, nome do banco, usuario e senha
			String url = "jdbc:postgresql://" + servidor + "/" + nomeDoBanco;
			this.setCon((Connection) DriverManager.getConnection(url, usuario, senha));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return this.getCon();
	}

	/**
	 * Executa consultas SQL
	 * 
	 * @param pSQL
	 * @return int
	 */
	public boolean executarSQL(String pSQL) {
		try {
			// createStatement de con para criar o Statement
			this.setStatement(getCon().createStatement());

			// Definido o Statement, executamos a query no banco de dados
			this.setResultSet(getStatement().executeQuery(pSQL));

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean executarUpdateDeleteSQL(String pSQL) {
		try {

			// createStatement de con para criar o Statement
			this.setStatement(getCon().createStatement());

			// Definido o Statement, executamos a query no banco de dados
			getStatement().executeUpdate(pSQL);

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Executa insert SQL
	 * 
	 * @param pSQL
	 * @return boolean
	 */
	public int insertSQL(String pSQL) {
		int status = 0;
		try {
			// createStatement de con para criar o Statement
			this.setStatement(getCon().createStatement());

			// Definido o Statement, executamos a query no banco de dados
			this.getStatement().executeUpdate(pSQL);

			// consulta o ultimo id inserido
			this.setResultSet(this.getStatement().executeQuery("SELECT last_insert_id();"));

			// recupera o ultimo id inserido
			while (this.resultSet.next()) {
				status = this.resultSet.getInt(1);
			}

			// retorna o ultimo id inserido
			return status;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return status;
		}
	}
	
	/**
     * encerra a conexão corrente
     * @return boolean
     */
    public boolean fecharConexao(){
       try {
           if((this.getResultSet() != null) && (this.statement != null)){
               this.getResultSet().close();
               this.statement.close();
           }
           this.getCon().close();
           return true;
       } catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       return false;
    }

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
