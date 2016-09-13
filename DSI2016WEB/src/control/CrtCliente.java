package control;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Cliente;
import ejb.ClienteBeanLocal;

@ManagedBean(name="crtcliente")
@ViewScoped
public class CrtCliente {
	@EJB
	private ClienteBeanLocal clienteBean;
	private Cliente model = new Cliente();
	
	
	public void create(){
		model = new Cliente();
	}
	public void save(){
		System.out.println(model.getNome());
		clienteBean.save(model);
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
	
	
}
