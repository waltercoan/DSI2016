package ejb;

import javax.ejb.Local;

import model.Usuario;

@Local
public interface UsuarioBeanLocal {
	public void criaUsuario(String usuario, String senha);
	public Usuario validaUsuario(String usuario, String senha);

}
