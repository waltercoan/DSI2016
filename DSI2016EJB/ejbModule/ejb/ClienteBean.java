package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Cliente;

/**
 * Session Bean implementation class ClienteBean
 */
@Stateless
public class ClienteBean implements ClienteBeanLocal {

	/**
	 * Default constructor. 
	 */
	@PersistenceContext(name="dsi2016context")
	private EntityManager em;


	public ClienteBean() {
		// TODO Auto-generated constructor stub
	}

	//reflecion java
	@Override
	public void save(Cliente c) {
		if(em.find(Cliente.class, c.getOid()) == null){
			//Insert
			em.persist(c);
		}else{
			//Update
			em.merge(c);
		}
	}
	@Override
	public void remove(Cliente c) {
		c = em.find(Cliente.class, c.getOid());
		em.remove(c);
	}

	@Override
	public List<Cliente> getAll() {
		Query q = em.createNamedQuery("getAllCliente");
		return q.getResultList();
	}

	@Override
	public List<Cliente> getClienteByNome(String nome) {
		Query q = em.createNamedQuery("getClienteByNome");
		q.setParameter("nome", "%"+nome+"%");
		return q.getResultList();
	}
	



}






