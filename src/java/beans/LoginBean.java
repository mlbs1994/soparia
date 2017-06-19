package beans;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import persistencia.UsuarioServico;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    private String telefone;
    private String senha;
    private String nome;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //metodo p/ verificar se o telefone Q pertence ao usuário é válido.
    //tb verifica se a senha fornecida é igual a senha gravada
    public boolean validaUsuario()throws SQLException{
        UsuarioServico ud = new UsuarioServico();
        try {
            String senhaRetornada = ud.retornaUsuario(this.telefone).getSenha();
            return this.senha.equals(senhaRetornada);
        } catch (Exception e) {
            return false;
        }
    }
    
    public Usuario retornaUsuario()throws SQLException{
        UsuarioServico ud = new UsuarioServico();
        try {
            Usuario usu = ud.retornaUsuario(this.telefone);
            return usu;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String doLogin() throws FacesException,ExceptionInInitializerError,SQLException{
        boolean valido;
        char tipo;
        Usuario usu;
        
        try {
            valido = this.validaUsuario();

            if (!valido) {
                adicionaMensagem("Login ou senha incorretos!", "destinoAviso");
                return "login";
            } else {
                UsuarioServico ud = new UsuarioServico();
                usu = ud.retornaUsuario(this.telefone);

                tipo = usu.tipo();
                this.setNome(usu.getNome());
                if (tipo == 'C') {
                    return "homeC";
                } else {
                    return "pedidos";
                }
            }
        } catch (Exception e) {
            adicionaMensagem("Login ou senha incorretos!", "destinoAviso");
            return "login";
        }
   
      }
    
    private void adicionaMensagem(String mensagem, String destino){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.FACES_MESSAGES,mensagem);
        context.addMessage(destino, msg);
    }
}
