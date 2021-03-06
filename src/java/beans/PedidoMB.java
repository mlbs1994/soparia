package beans;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Sopa;
import persistencia.ClienteServico;
import persistencia.PedidoServico;

@ManagedBean(name = "pedidoMB")
@SessionScoped
public class PedidoMB{
    private Pedido pedido;
    private int quantidade;
    private Sopa Sopa;
    private List<ItemPedido> itens;
    
    private String mensagem;
    
    public PedidoMB(){
        this.itens = new ArrayList<>();
        this.pedido = new Pedido();
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItem(ItemPedido item) {
        this.itens.add(item);
    }
    
    public Sopa getSopa() {
        return Sopa;
    }

    public void setSopa(Sopa p) {
        this.Sopa = p;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public String adicionaItem(){
        ItemPedido ip = new ItemPedido(this.Sopa,this.quantidade,pedido);
        
        pedido.addItem(ip);
        this.addItem(ip);
        
        
        setMensagem("Item adicionado ao pedido!");
        return "homeC";
    }
    
    public String fechaPedido(long id_cliente){
        PedidoServico pedidoDAO = new PedidoServico();
        this.pedido.setCliente(new ClienteServico().getById(id_cliente));
        pedidoDAO.save(pedido);
        
        setMensagem("Pedido realizado!");
        this.itens = new ArrayList<>();
        this.pedido = new Pedido();
        return "homeC";
    }
    
    public List<Pedido> getPedidosNAtendidos(){
        return new PedidoServico().pedidosNAtendidos();
    }
    
    public List<Pedido> getPedidosAtendidos(){
        return new PedidoServico().pedidosAtendidos();
    }
    
    public String atendePedido(Long id){
        PedidoServico pd = new PedidoServico();
        pd.pedidoAtende(id);
        return "pedidos";
    }
    
}
