package br.iff.pooa20141.supermercado.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.iff.pooa20141.supermercado.model.controller.FabricantePersistence;
import br.iff.pooa20141.supermercado.model.entity.Fabricante;

@ManagedBean(name = "fabricantePrime")
@SessionScoped
public class FabricanteWebController {

	@EJB(lookup = "java:app/SupermercadoWEB/FabricantePersistence!br.iff.pooa20141.supermercado.model.controller.FabricantePersistence")
	private FabricantePersistence jfabricante;

	private Fabricante fabricante;
	private Fabricante fabricanteSelecionado = new Fabricante();

	public Fabricante getFabricante() {
		if (fabricante == null) {
			fabricante = new Fabricante();
		}
		return fabricante;
	}

	public List<Fabricante> getAllFabricante() {
		return jfabricante.findAll();
	}

	public void salva() {

		if (jfabricante.find(fabricante.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("fabricante Ja Cadastrado"));

		} else {
			jfabricante.insert(fabricante);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("cadastro.hide()");
		;
	}

	public void delete() {

		if (jfabricante.find(fabricanteSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("fabricante não Existe"));

		} else {
			jfabricante.delete(fabricanteSelecionado.getUid());

		}
		RequestContext.getCurrentInstance().execute("deleta.hide()");
	}

	public Fabricante getFabricanteSelecionado() {

		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;

	}

	public void update() {

		if (jfabricante.find(fabricanteSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("fabricante não Existe"));

		} else {

			jfabricante.update(fabricanteSelecionado);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()");
		;
	}

	public FabricanteWebController() {

	}

}
