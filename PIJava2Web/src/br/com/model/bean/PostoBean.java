package br.com.model.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.util.Utilidades;

@ManagedBean
@ViewScoped
public class PostoBean {
	private long codigo;
	private String nome;
	private String estado;
	private String cidade;
	private String numero;
	private String complemento;
	private boolean status = true;
	static ArrayList<PostoBean> listaPosto = new ArrayList<PostoBean>();
	static ArrayList<PostoBean> alterPosto = new ArrayList<PostoBean>();

	@PostConstruct
	public void posCriacao() {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public PostoBean() {
		super();
	}

	public PostoBean(long codigo, String nome, String estado, String cidade,
			String numero, String complemento, boolean status) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.estado = estado;
		this.cidade = cidade;
		this.numero = numero;
		this.complemento = complemento;
		this.status = status;
	}

	public void cadastrarPosto() throws IOException {
		PostoBean posto = new PostoBean();
		long codigo = this.codigo;
		posto.setCodigo(codigo);
		System.out.println(codigo);

		String nome = this.nome;
		posto.setNome(nome);
		System.out.println(nome);

		if (posto.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("nome",
					new FacesMessage("Informe um nome para o posto."));
			return;
		}

		String estado = this.estado;
		posto.setEstado(estado);
		System.out.println(estado);

		if (posto.getEstado().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("estado",
					new FacesMessage("Informe um estado para o posto."));
			return;
		}

		String cidade = this.cidade;
		posto.setCidade(cidade);
		System.out.println(cidade);

		if (posto.getCidade().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("cidade",
					new FacesMessage("Informe uma cidade para o posto."));
			return;
		}

		String numero = this.numero;
		posto.setNumero(numero);
		System.out.println(numero);

		if (posto.getNumero().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("numero",
					new FacesMessage("Informe um número para o posto."));
			return;
		}

		String complemento = this.complemento;
		posto.setComplemento(complemento);
		System.out.println(complemento);

		if (posto.getComplemento().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("complemento",
					new FacesMessage("Informe um complemento para o posto."));
			return;
		}

		boolean status = this.status;
		posto.setStatus(status);

		listaPosto.add(posto);

		addMessage("Posto de coletas de lixo cadastrado com sucesso!");
		
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("posto.xhtml");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<PostoBean> getPostos() {
		return listaPosto;
	}
	
	public void removerPosto(PostoBean posto){ 
		System.out.println(posto.getCodigo());
		int indice = 0, aux = 0;
		for(PostoBean pojo : listaPosto){
			if(pojo.getCodigo() == posto.getCodigo()){
				aux = indice;
			}
			indice++;
		}
		listaPosto.remove(aux);
	}
	
	public void atulizarPosto(PostoBean posto) throws IOException {
		System.out.println("Atualizar passou");		
		
		System.out.println(posto.getCodigo());
		int indice = 0, aux = 0;
		for (PostoBean pojo : listaPosto) {
			if (pojo.getCodigo() == posto.getCodigo()) {
				alterPosto.add(posto);
				aux = indice;
			}
		}

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("atualizarPosto.xhtml");
	}
	
	public List<PostoBean> getPostoAlter() {
		return alterPosto;
	}
	
	public void alteraPosto(PostoBean posto) throws IOException{
		
		long codigo = posto.getCodigo();
		posto.setCodigo(codigo);
		
		String nome = posto.getNome();
		posto.setNome(nome);

		String estado = posto.getEstado();
		posto.setEstado(estado);
		
		String cidade = posto.getCidade();
		posto.setCidade(cidade);
		
		String numero = posto.getNumero();
		posto.setNumero(numero);
		
		String complemento = posto.getComplemento();
		posto.setComplemento(complemento);
		
		int indice = 0, aux = 0;
		for (PostoBean pojo : listaPosto) {
			if (pojo.getCodigo() == posto.getCodigo()) {
				aux = indice;
			}
			indice++;
		}
		listaPosto.set(aux, posto);
		alterPosto.remove(0);
		alterPosto.clear();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaPostos.xhtml");
	}

}
