package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemBem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long oid;
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	private Propriedade propriedade;
	private float valorReal;
	
	
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public Propriedade getPropriedade() {
		return propriedade;
	}
	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}
	public float getValorReal() {
		return valorReal;
	}
	public void setValorReal(float valorReal) {
		this.valorReal = valorReal;
	}
	
	
}
