package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.farmacia.DAO.ImoveisDAO;
import br.com.farmacia.domain.Imoveis;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBImoveis")
@ViewScoped
public class ImoveisBean {

	private Imoveis imoveis;
	private ArrayList<Imoveis> itens;
	private ArrayList<Imoveis> itensFiltrados;

	public Imoveis getImoveis() {
		return imoveis;
	}

	public void setImoveis(Imoveis imoveis) {
		this.imoveis = imoveis;
	}

	public ArrayList<Imoveis> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Imoveis> itens) {
		this.itens = itens;
	}

	public ArrayList<Imoveis> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Imoveis> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			ImoveisDAO idao = new ImoveisDAO();
			itens = idao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		imoveis = new Imoveis();
	}

	public void novo() {
		try {
			ImoveisDAO idao = new ImoveisDAO();
			idao.salvar(imoveis);

			itens = idao.listar();

			JSFUtil.adicionarMensagemSucesso("Imovel salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ImoveisDAO idao = new ImoveisDAO();
			idao.excluir(imoveis);
			itens = idao.listar();
			JSFUtil.adicionarMensagemSucesso("Imovel excluido com sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("N�o � poss�vel excluir um Imovel que tenha um inquilino associado!");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ImoveisDAO idao = new ImoveisDAO();
			idao.editar(imoveis);
			itens = idao.listar();
			JSFUtil.adicionarMensagemSucesso("Imovel editado com sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
}
