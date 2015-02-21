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

import br.com.util.Utilidades;

@ManagedBean
@ViewScoped
@SessionScoped 
public class AgendaBean {
	private long codigo;
	private String data;
	private String descricao;
	
	static ArrayList<AgendaBean> listaAgendas = new ArrayList<AgendaBean>();
	static ArrayList<AgendaBean> alterAgenda = new ArrayList<AgendaBean>();
	static int contAgendamentos = 0;
	
	

	public static int getContAgendamentos() {
		return contAgendamentos;
	}

	public static void setContAgendamentos(int contAgendamentos) {
		AgendaBean.contAgendamentos = contAgendamentos;
	}

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AgendaBean() {
		super();
	}

	public AgendaBean(long codigo, String data, String descricao) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.descricao = descricao;
	}
	
	public void cadastrarAgenda() throws IOException {
		AgendaBean agenda = new AgendaBean();

		long codigo = this.codigo;
		agenda.setCodigo(codigo);
		System.out.println(codigo);

		String data = this.data;
		agenda.setData(data);;
		System.out.println(data);
		if (agenda.getData().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("data",
					new FacesMessage("Informe uma data."));
			return;
		}
		
		String descricao = this.descricao;
		agenda.setDescricao(descricao);;
		System.out.println(descricao);
		if (agenda.getDescricao().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("descricao",
					new FacesMessage("Informe uma descrição para esse agendamendo, exemplo: Local, hora etc ..."));
			return;
		}

		listaAgendas.add(agenda);
		contAgendamentos++;
		addMessage("Agendamento para o dia "+data+" realizado com sucesso!");
		
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("agenda.xhtml");

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<AgendaBean> getAgendas() {
		return listaAgendas;
	}
	
	public void removerAgenda(AgendaBean agenda){ 
		System.out.println(agenda.getCodigo());
		int indice = 0, aux = 0;
		for(AgendaBean pojo : listaAgendas){
			if(pojo.getCodigo() == agenda.getCodigo()){
				aux = indice;
			}
			indice++;
		}
		listaAgendas.remove(aux);
	}
	
	public void atulizarAgenda(AgendaBean agenda) throws IOException {
		System.out.println("Atualizar passou");		
		
		System.out.println(agenda.getCodigo());
		int indice = 0, aux = 0;
		for (AgendaBean pojo : listaAgendas) {
			if (pojo.getCodigo() == agenda.getCodigo()) {
				alterAgenda.add(agenda);
				aux = indice;
			}
		}

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("atualizarAgenda.xhtml");
	}
	
	public List<AgendaBean> getAgendaAlter() {
		return alterAgenda;
	}
	
	public void alteraPosto(AgendaBean agenda) throws IOException{
		
		long codigo = agenda.getCodigo();
		agenda.setCodigo(codigo);
		
		String data = agenda.getData();
		agenda.setData(data);

		String descricao = agenda.getDescricao();
		agenda.setDescricao(descricao);
		
		int indice = 0, aux = 0;
		for (AgendaBean pojo : listaAgendas) {
			if (pojo.getCodigo() == agenda.getCodigo()) {
				aux = indice;
			}
			indice++;
		}
		listaAgendas.set(aux, agenda);
		alterAgenda.remove(0);
		alterAgenda.clear();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaAgenda.xhtml");
	}

}
