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
	
	public List<Cliente> getAll(){
		return clienteBean.getAll();
	}
	
	
}
