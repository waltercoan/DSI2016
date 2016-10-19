package control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Cliente;
import ejb.ClienteBeanLocal;

@ManagedBean(name="crtcliente")
@ViewScoped
public class CrtCliente {
	@EJB
	private ClienteBeanLocal clienteBean;
	private Cliente model = new Cliente();
	private String valorBusca;
	
	private List<Cliente> colClientes = new ArrayList<Cliente>();
	
	@PostConstruct
	public void init(){
		colClientes = clienteBean.getAll();
	}
	
	
	
	public void create(){
		model = new Cliente();
	}
	public void save(){
		System.out.println(model.getNome());
		clienteBean.save(model);
		init();
	}
	
	public void remove(){
		clienteBean.remove(model);
		init();
	}
	public void busca(){
		colClientes = clienteBean.getClienteByNome(valorBusca);
		if(colClientes.size() == 0){
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("ERRO",  "Nenhum registro foi encontrado...") );
		}
	}
	
	public List<Cliente> getAll(){
		return clienteBean.getAll();
	}

	public Cliente getModel() {
		return model;
	}
	public void setModel(Cliente model) {
		this.model = model;
	}
	public String getValorBusca() {
		return valorBusca;
	}
	public void setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
	}
	public List<Cliente> getColClientes() {
		return colClientes;
	}
	public void setColClientes(List<Cliente> colClientes) {
		this.colClientes = colClientes;
	}
	
	
}
