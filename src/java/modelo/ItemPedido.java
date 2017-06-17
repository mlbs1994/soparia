/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saulo
 */
@Entity
@Table(name = "item_pedido", catalog = "soparia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i")
    , @NamedQuery(name = "ItemPedido.findByIdItemPedido", query = "SELECT i FROM ItemPedido i WHERE i.idItemPedido = :idItemPedido")
    , @NamedQuery(name = "ItemPedido.findByQuantidade", query = "SELECT i FROM ItemPedido i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "ItemPedido.findBySubtotal", query = "SELECT i FROM ItemPedido i WHERE i.subtotal = :subtotal")})
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItemPedido")
    private Integer idItemPedido;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Float subtotal;
    @JoinColumn(name = "idMenu", referencedColumnName = "idMenu")
    @ManyToOne(optional = false)
    private Menu idMenu;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedido idPedido;

    public ItemPedido() {
    }

    public ItemPedido(Integer idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Integer getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Integer idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemPedido != null ? idItemPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.idItemPedido == null && other.idItemPedido != null) || (this.idItemPedido != null && !this.idItemPedido.equals(other.idItemPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ItemPedido[ idItemPedido=" + idItemPedido + " ]";
    }
    
}
