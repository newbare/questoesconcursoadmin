package br.com.questoesconcursoadmin.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Usuario;
import br.com.questoesconcursoadmin.remote.UsuarioRemote;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = -2686051853223630767L;
	private Usuario usuario;

	@EJB
	UsuarioRemote usuarioRemote;

	public LoginMB() {

	}

	@PostConstruct
	public void init() {
		try {
			usuario = new Usuario();
			SecurityContext context = SecurityContextHolder.getContext();
			if (context instanceof SecurityContext) {
				Authentication authentication = context.getAuthentication();
				if (authentication instanceof Authentication) {
					usuario.setEmail(((User) authentication.getPrincipal())
							.getUsername());
					if (usuario.getEmail() != null) {
						usuario = usuarioRemote.recuperarPorEmail(usuario
								.getEmail());
					}
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}