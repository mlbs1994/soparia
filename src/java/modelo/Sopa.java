/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saulo
 */
@Entity
@Table(name = "sopa", catalog = "soparia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sopa.findAll", query = "SELECT s FROM Sopa s")
    , @NamedQuery(name = "Sopa.findByIdMenu", query = "SELECT s FROM Sopa s WHERE s.idMenu = :idMenu")
    , @NamedQuery(name = "Sopa.findByNome", query = "SELECT s FROM Sopa s WHERE s.nome = :nome")
    , @NamedQuery(name = "Sopa.findByDescricao", query = "SELECT s FROM Sopa s WHERE s.descricao = :descricao")
    , @NamedQuery(name = "Sopa.findByPreco", query = "SELECT s FROM Sopa s WHERE s.preco = :preco")
    , @NamedQuery(name = "Sopa.findByImgSopa", query = "SELECT s FROM Sopa s WHERE s.imgSopa = :imgSopa")})
public class Sopa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMenu")
    private Integer idMenu;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
    private Float preco;
    @Column(name = "imgSopa")
    private String imgSopa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu")
    private List<ItemPedido> itemPedidoList;

    public Sopa() {
    }

    public Sopa(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getImgSopa() {
        return imgSopa;
    }

    public void setImgSopa(String imgSopa) {
        this.imgSopa = imgSopa;
    }

    @XmlTransient
    public List<ItemPedido> getItemPedidoList() {
        return itemPedidoList;
    }

    public void setItemPedidoList(List<ItemPedido> itemPedidoList) {
        this.itemPedidoList = itemPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sopa)) {
            return false;
        }
        Sopa other = (Sopa) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Sopa[ idMenu=" + idMenu + " ]";
    }
    
}
