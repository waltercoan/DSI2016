package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long oid;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataPedido;
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	private Cliente cliente;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="pedido_oid")
	private List<ItemPedido> colItens = 
			new ArrayList<ItemPedido>();
	
	
	
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemPedido> getColItens() {
		return colItens;
	}
	public void setColItens(List<ItemPedido> colItens) {
		this.colItens = colItens;
	}
	
	
	
}
