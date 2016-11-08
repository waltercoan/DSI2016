package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name="getAllCidade", 
			query="Select c from Cidade c"),
	@NamedQuery(name="getCidadeByDesc",
		query="Select c from Cidade c where c.descricao like :d"
				+ " order by c.descricao")
})


@Entity
public class Cidade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long oid;
	@Column(length=1000)
	private String descricao;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (oid ^ (oid >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (oid != other.oid)
			return false;
		return true;
	}
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
