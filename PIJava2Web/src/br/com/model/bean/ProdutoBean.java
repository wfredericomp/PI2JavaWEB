package br.com.model.bean;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.util.LixoPorCategoriaEnum;
import br.com.util.Utilidades;

@ManagedBean
@ViewScoped
@SessionScoped
public class ProdutoBean {

	private long codigo;
	private String nome;
	private String descricao;
	private String peso;
	private String categoria;

	static ArrayList<ProdutoBean> alterProduto = new ArrayList<ProdutoBean>();

	static ArrayList<ProdutoBean> listaProdutos = new ArrayList<ProdutoBean>();

	static ArrayList<String> categorias = new ArrayList<String>();
	
	static int contProdutos = 0;
	
	

	public static int getContProdutos() {
		return contProdutos;
	}

	public static void setContProdutos(int contProdutos) {
		ProdutoBean.contProdutos = contProdutos;
	}

	@PostConstruct
	public void posCriacao() {
		ProdutoBean pojo = new ProdutoBean();
		this.nome = pojo.getNome();
		this.descricao = pojo.getDescricao();
		this.peso = pojo.getPeso();
		this.categoria = pojo.getCategoria();
		this.codigo = Utilidades.gerarCodigo();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ProdutoBean() {
		super();
	}

	public ProdutoBean(long codigo, String nome, String descricao, String peso,
			String categoria) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.peso = peso;
		this.categoria = categoria;
	}

	public void cadastrarProduto() throws IOException {
		ProdutoBean produto = new ProdutoBean();

		long codigo = this.codigo;
		produto.setCodigo(codigo);
		System.out.println(codigo);
		System.out.println();

		String nome = this.nome;
		produto.setNome(nome);
		System.out.println(nome);
		if (produto.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("nome",
					new FacesMessage("Informe um nome para o produto."));
			return;
		}

		String descricao = this.descricao;
		produto.setDescricao(descricao);

		if (produto.getDescricao().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("descricao",
					new FacesMessage("Informe uma descrição para o produto."));
			return;
		}

		String peso = this.peso;
		produto.setPeso(peso);

		if (produto.getPeso().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("peso",
					new FacesMessage("Informe um peso para o produto."));
			return;
		}

		String categoria = this.categoria;
		produto.setCategoria(categoria);
		System.out.println(categoria);

		listaProdutos.add(produto);
		contProdutos++;
		addMessage("Produto de lixo eletônico cadastrado com sucesso!");
		
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("produto.xhtml");

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<ProdutoBean> getProdutos() {
		return listaProdutos;
	}

	public List<String> getCategorias() {
		if (categorias.size() < 6) {
			String computador = LixoPorCategoriaEnum.COMPUTADOR.getValor();
			categorias.add(computador);
			String notebook = LixoPorCategoriaEnum.NOTEBOOK.getValor();
			categorias.add(notebook);
			String telefone = LixoPorCategoriaEnum.TELEFONE.getValor();
			categorias.add(telefone);
			String baterias = LixoPorCategoriaEnum.BATERIAS.getValor();
			categorias.add(baterias);
			String perifericos = LixoPorCategoriaEnum.PERIFERICOS.getValor();
			categorias.add(perifericos);
			String outros = LixoPorCategoriaEnum.OUTROS.getValor();
			categorias.add(outros);
		}
		return categorias;
	}

	public void removerProduto(ProdutoBean produto) {
		System.out.println(produto.getCodigo());
		int indice = 0, aux = 0;
		for (ProdutoBean pojo : listaProdutos) {
			if (pojo.getCodigo() == produto.getCodigo()) {
				aux = indice;
			}
			indice++;
		}
		listaProdutos.remove(aux);
	}

	public void atulizarProduto(ProdutoBean produto) throws IOException {
		System.out.println("Atualizar passou");		
		
		System.out.println(produto.getCodigo());
		int indice = 0, aux = 0;
		for (ProdutoBean pojo : listaProdutos) {
			if (pojo.getCodigo() == produto.getCodigo()) {
				alterProduto.add(produto);
				aux = indice;
			}
		}

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("atualizarProduto.xhtml");
	}
	
	public List<ProdutoBean> getProdutoAlter() {
		return alterProduto;
	}
	
	public void alteraProduto(ProdutoBean produto) throws IOException{
		
		long codigo = produto.getCodigo();
		produto.setCodigo(codigo);
		
		String nome = produto.getNome();
		produto.setNome(nome);

		String descricao = produto.getDescricao();
		produto.setDescricao(descricao);

		String peso = produto.getPeso();
		produto.setPeso(peso);

		String categoria = produto.getCategoria();
		produto.setCategoria(categoria);
		
		int indice = 0, aux = 0;
		for (ProdutoBean pojo : listaProdutos) {
			if (pojo.getCodigo() == produto.getCodigo()) {
				aux = indice;
			}
			indice++;
		}
		listaProdutos.set(aux, produto);
		alterProduto.remove(0);
		alterProduto.clear();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaProdutos.xhtml");
	}

}
