/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.NoResultException;
import modelo.Cliente;

public class ClienteServico extends UsuarioServico{

    public ClienteServico() {
        super();
    }
   
    public boolean salvar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
