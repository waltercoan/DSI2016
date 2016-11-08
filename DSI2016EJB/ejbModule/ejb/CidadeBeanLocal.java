package ejb;

import java.util.List;

import javax.ejb.Local;

import model.Cidade;

@Local
public interface CidadeBeanLocal {
	public void save(Cidade cidade);
	public void remove(Cidade cidade);
	public List<Cidade> getAll();
	public List<Cidade> getCidadeByDesc(String desc);

}
