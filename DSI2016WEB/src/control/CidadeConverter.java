package control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name="cidadeConverter")
@RequestScoped
public class CidadeConverter implements Converter, Serializable{
	@ManagedProperty(value="#{crtcliente}")
	private CrtCliente crtCliente;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		int posicao = Integer.parseInt(arg2);
		
		return crtCliente.getColCidades().get(posicao);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		return String.valueOf(
				crtCliente.getColCidades().indexOf(arg2));
	}

	public void setCrtCliente(CrtCliente crtCliente) {
		this.crtCliente = crtCliente;
	}

}
