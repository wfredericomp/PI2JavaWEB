package br.com.model.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@ManagedBean
@ViewScoped
@SessionScoped
public class LoginBean implements Filter {
	private String login;
	private String senha;
	private boolean status;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LoginBean() {
		super();
	}

	public LoginBean(String login, String senha, boolean status) {
		super();
		this.login = login;
		this.senha = senha;
		this.status = status;
	}

	public boolean efetuarLogin() throws IOException {
		boolean status = false;
		System.out.println("Passou no login");
		String login = "admin";
		String senha = "123456";

		if (login.equals(this.login) && senha.equals(this.senha)
				&& status == false) {

			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/PIJava2Web/restricted/index.xhtml?faces-redirect=true");
		} else {
			addMessage("Login ou senha inválidos !");
		}

		return status;

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void sair() {
		this.login = null;
		this.senha = null;
		System.out.println("Logout...");
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("../login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		String userName = this.login;

		System.out.println("Login realizado: " + userName);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
