package control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Cidade;
import ejb.CidadeBeanLocal;

@ManagedBean(name="crtcidade")
@ViewScoped
public class CrtCidade {
	@EJB
	private CidadeBeanLocal cidadeBean;
	
	private Cidade model = new Cidade();
	
	private List<Cidade> colCidades = new ArrayList<Cidade>();
	
	@PostConstruct
	public void init(){
		colCidades = cidadeBean.getAll();
	}
	
	
	
	public List<Cidade> getColCidades() {
		return colCidades;
	}

	public void setColCidades(List<Cidade> colCidades) {
		this.colCidades = colCidades;
	}



	public void create(){
		model = new Cidade();
	}
	public void save(){
		cidadeBean.save(model);
		init();
	}
	
	public void remove(){
		cidadeBean.remove(model);
		init();
	}
	
	public List<Cidade> getAll(){
		return cidadeBean.getAll();
	}

	public Cidade getModel() {
		return model;
	}
	public void setModel(Cidade model) {
		this.model = model;
	}
}
