package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.farmacia.domain.Imoveis;
import br.com.farmacia.factory.ConexaoFactory;

public class ImoveisDAO {
	public void salvar(Imoveis i) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO imoveis ");
		sql.append("(nome) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, i.getNome());
		comando.executeUpdate();
	}
	
	public void excluir (Imoveis i) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM imoveis ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, i.getCodigo());
		comando.executeUpdate();
	}
		
	public void editar (Imoveis i) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE imoveis ");
		sql.append("SET nome = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, i.getNome());
		comando.setLong(2, i.getCodigo());
		comando.executeUpdate();
	}
	
	public Imoveis buscaPorCodigo(Imoveis i)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome ");
		sql.append("FROM imoveis ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, i.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Imoveis retorno = null;
		
		if(resultado.next()){
			retorno = new Imoveis();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setNome(resultado.getString("nome"));
		}
		return retorno;
	}
	
	public ArrayList<Imoveis>buscarPorNome(Imoveis i)throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome ");
		sql.append("FROM imoveis ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + i.getNome() + "%");
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Imoveis>lista = new ArrayList<Imoveis>();
		
		while(resultado.next()){
			Imoveis item = new Imoveis();
			item.setCodigo(resultado.getLong("codigo"));
			item.setNome(resultado.getString("nome"));
			lista.add(item);
		}
		return lista;
	}
	
	public ArrayList<Imoveis> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome ");
		sql.append("FROM imoveis ");
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Imoveis>lista = new ArrayList<Imoveis>();
		
		while(resultado.next()){
			Imoveis i = new Imoveis();
			i.setCodigo(resultado.getLong("codigo"));
			i.setNome(resultado.getString("nome"));
			lista.add(i);
		}
		return lista;
	}
	
   /*
	public static void main(String[] args) {
		Imoveis i1 = new Imoveis();
		f1.setDescricao("ROBERTO");
		
		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("VALDIR");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			System.out.println("Salvo com sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Erro ao salvar");
			e.printStackTrace();
		}   
	*/	
		
	/*	Fornecedores f1 = new Fornecedores();
		f1.setCodigo(3L);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.excluir(f1);
			
			System.out.println("Deletado com sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar");
			e.printStackTrace();
		}   */
		
		/*
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
		f1.setDescricao("HUGO VASCONCELOS");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.editar(f1);
			
			System.out.println("Editado com sucesso!!");
			
		} catch (SQLException e) {
			System.out.println("Erro ao Editar");
			e.printStackTrace();
		}  */
		
		
		/*
		 Fornecedores f1 = new Fornecedores();
			f1.setCodigo(2);
			
			Fornecedores f2 = new Fornecedores();
			f2.setCodigo(3);
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try {
				Fornecedores f3 = fdao.buscaPorCodigo(f1);
				Fornecedores f4 = fdao.buscaPorCodigo(f2);
				
				System.out.println("Resultado 1: " + f3);
				System.out.println("Resultado 2: " + f4);
				
			} catch (SQLException e) {
				System.out.println("Erro ao buscar");
				e.printStackTrace();
			}   
		*/
		
		/*
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			
			ArrayList<Fornecedores>lista = fdao.listar();
			
			for (Fornecedores f : lista){
			System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar");
			e.printStackTrace();
		}   
		*/
		
		
		/*Fornecedores f1 = new Fornecedores();
			f1.setDescricao("de");
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			
			try {
				
				
				
				ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
				
				for (Fornecedores f : lista){
				System.out.println("Resultado " + f);
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao buscar");
				e.printStackTrace();
			}  */ 
			
}

