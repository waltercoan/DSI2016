package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name="getAllCliente", 
		query="select c from Cliente c"),
		
	@NamedQuery(name="getClienteByNome", 
		query="select c from Cliente c where c.nome like :nome"),
		
})

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long oid;
	private String nome;
	@Column(length=2000)
	private String endereco;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataNascimento;
	private String sexo;
	private int idade;
	@Column(unique=true)
	private String email;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	private Cidade cidadeNasc;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Cliente_oid")
	private List<ItemBem> colItensBem =
			new ArrayList<ItemBem>();
	
	
	public List<ItemBem> getColItensBem() {
		return colItensBem;
	}
	public void setColItensBem(List<ItemBem> colItensBem) {
		this.colItensBem = colItensBem;
	}
	public Cidade getCidadeNasc() {
		return cidadeNasc;
	}
	public void setCidadeNasc(Cidade cidadeNasc) {
		this.cidadeNasc = cidadeNasc;
	}
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
