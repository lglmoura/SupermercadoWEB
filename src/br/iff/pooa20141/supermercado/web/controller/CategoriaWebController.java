package br.iff.pooa20141.supermercado.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.iff.pooa20141.supermercado.model.controller.CategoriaPersistence;
import br.iff.pooa20141.supermercado.model.controller.SetorPersistence;
import br.iff.pooa20141.supermercado.model.entity.Categoria;
import br.iff.pooa20141.supermercado.model.entity.Setor;

@ManagedBean(name = "categoriaPrime")
@SessionScoped
public class CategoriaWebController {

	@EJB(lookup = "java:app/SupermercadoWEB/SetorPersistence!br.iff.pooa20141.supermercado.model.controller.SetorPersistence")
	private SetorPersistence jsetor;

	@EJB(lookup = "java:app/SupermercadoWEB/CategoriaPersistence!br.iff.pooa20141.supermercado.model.controller.CategoriaPersistence")
	private CategoriaPersistence jcategoria;

	private Categoria categoria;
	private Categoria categoriaSelecionado = new Categoria();

	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public List<Categoria> getAllCategoria() {
		return jcategoria.findAll();
	}

	public List<Setor> getAllSetor() {
		return jsetor.findAll();
	}

	public void salva() {
		
		if (jcategoria.find(categoria.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("categoria Ja Cadastrado"));

		} else {

			jcategoria.insert(categoria);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		
		RequestContext.getCurrentInstance().execute("cadastro.hide()");
		;
		
	}

	public void delete() {

		if (jcategoria.find(categoriaSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("categoria não Existe"));

		} else {
			jcategoria.delete(categoriaSelecionado.getUid());

		}
		RequestContext.getCurrentInstance().execute("deleta.hide()");
	}

	public Categoria getCategoriaSelecionado() {

		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;

	}

	public void update() {

		
		if (jcategoria.find(categoriaSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("categoria não Existe"));

		} else {

			jcategoria.update(categoriaSelecionado);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()");
		;
		
	}

	public CategoriaWebController() {

	}

}
