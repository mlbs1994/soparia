package beans;
 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Sopa;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import persistencia.PedidoServico;
import persistencia.SopaServico;
 
@ManagedBean
@SessionScoped
public class TabbedView {
     
    private List<Sopa> sopas;
    private List<ItemPedido> itens;
    private ItemPedido item;
    private Pedido pedido;
    private int numero;
    
    public TabbedView(){
        this.sopas = new ArrayList<>();
        this.itens = new ArrayList<>();
        pedido = new Pedido();
    }
 
    @PostConstruct
    public void init() {
        SopaServico sopaServico = new SopaServico();
        sopas = sopaServico.findAll();
    }
     
    public List<Sopa> getSopas() {
        return sopas;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }  

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido ped) {
        this.pedido = ped;
    }
    
     
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
         
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String adicionaItem(String sopa, String destino){
        SopaServico ps = new SopaServico();
        Sopa p = ps.getSopa(sopa);
        ItemPedido ip = new ItemPedido(p,this.numero,pedido);
        this.itens.add(ip);
        pedido.addItem(ip);
        adicionaMensagem("Total atualizado: R$ " + pedido.getTotal(),"destinoAviso");
        return destino;
    }
    
    public String excluiItem(ItemPedido item, String destino){
        this.pedido.removeItem(item);
        this.itens.remove(item);
        adicionaMensagem("Item excluído! Total atualizado: R$ " + pedido.getTotal(),"destinoAviso");
        return destino;
    }
    
    public String fechaRedireciona(){
        adicionaMensagem("Faça login para fechar seu pedido!","destinoAviso");
        return "login";
    }
    
    public String fechaPedido(Cliente usu){
        this.pedido.setCliente((Cliente) usu);
        PedidoServico pedidoServico = new PedidoServico();
        
        pedidoServico.save(pedido);
        
        adicionaMensagem("Pedido feito! Total: R$ " + pedido.getTotal(),"destinoAviso");
        return "homeC";
    }
    
    private void adicionaMensagem(String mensagem, String destino){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.FACES_MESSAGES,mensagem);
        context.addMessage(destino, msg);
    }
}