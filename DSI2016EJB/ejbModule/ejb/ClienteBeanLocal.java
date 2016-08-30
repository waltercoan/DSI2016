package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Cliente;

@Local
public interface ClienteBeanLocal {
	public void save(Cliente c);
	public void remove(Cliente c);
	public List<Cliente> getAll();
}
