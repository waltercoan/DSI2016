package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
