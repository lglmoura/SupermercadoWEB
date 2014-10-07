package br.iff.pooa20141.supermercado.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.iff.pooa20141.supermercado.model.controller.SetorPersistence;
import br.iff.pooa20141.supermercado.model.entity.Setor;

@ManagedBean(name = "setorPrime")
@SessionScoped
public class SetorWebController {

	@EJB(lookup = "java:app/SupermercadoWEB/SetorPersistence!br.iff.pooa20141.supermercado.model.controller.SetorPersistence")
	private SetorPersistence jsetor;

	private Setor setor;
	private Setor setorSelecionado = new Setor();

	public Setor getSetor() {
		if (setor == null) {
			setor = new Setor();
		}
		return setor;
	}

	public List<Setor> getAllSetor() {
		return jsetor.findAll();
	}

	public void salva() {

		if (jsetor.find(setor.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("setor Ja Cadastrado"));

		} else {
			jsetor.insert(setor);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("cadastro.hide()");
		;
	}

	public void delete() {

		if (jsetor.find(setorSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("setor não Existe"));

		} else {
			jsetor.delete(setorSelecionado.getUid());

		}
		RequestContext.getCurrentInstance().execute("deleta.hide()");
	}

	public Setor getSetorSelecionado() {

		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;

	}

	public void update() {

		if (jsetor.find(setorSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("setor não Existe"));

		} else {

			jsetor.update(setorSelecionado);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()");
		;
	}

	public SetorWebController() {

	}

}
