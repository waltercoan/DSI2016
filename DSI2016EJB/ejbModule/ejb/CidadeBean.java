package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Cidade;

/**
 * Session Bean implementation class CidadeBean
 */
@Stateless
public class CidadeBean implements CidadeBeanLocal {

	@PersistenceContext(name="dsi2016context")
	private EntityManager em;
	
    public CidadeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Cidade> getAll() {
		Query q = em.createNamedQuery("getAllCidade");
		return q.getResultList();
	}

	@Override
	public List<Cidade> getCidadeByDesc(String desc) {
		Query q = em.createNamedQuery("getCidadeByDesc");
		q.setParameter("d", "%"+desc+"%");
		return q.getResultList();
	}

	@Override
	public void save(Cidade cidade) {
		if(em.find(Cidade.class, cidade.getOid()) == null){
			em.persist(cidade);
		}else{
			em.merge(cidade);
		}
	}

	@Override
	public void remove(Cidade cidade) {
		cidade = em.find(Cidade.class, cidade.getOid());
		em.remove(cidade);
	}

}
