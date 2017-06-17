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
@Table(name = "bandeira", catalog = "soparia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bandeira.findAll", query = "SELECT b FROM Bandeira b")
    , @NamedQuery(name = "Bandeira.findByIdBandeira", query = "SELECT b FROM Bandeira b WHERE b.idBandeira = :idBandeira")
    , @NamedQuery(name = "Bandeira.findByNome", query = "SELECT b FROM Bandeira b WHERE b.nome = :nome")})
public class Bandeira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idBandeira")
    private Integer idBandeira;
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBandeira")
    private List<Cartao> cartaoList;

    public Bandeira() {
    }

    public Bandeira(Integer idBandeira) {
        this.idBandeira = idBandeira;
    }

    public Integer getIdBandeira() {
        return idBandeira;
    }

    public void setIdBandeira(Integer idBandeira) {
        this.idBandeira = idBandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Cartao> getCartaoList() {
        return cartaoList;
    }

    public void setCartaoList(List<Cartao> cartaoList) {
        this.cartaoList = cartaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBandeira != null ? idBandeira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bandeira)) {
            return false;
        }
        Bandeira other = (Bandeira) object;
        if ((this.idBandeira == null && other.idBandeira != null) || (this.idBandeira != null && !this.idBandeira.equals(other.idBandeira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Bandeira[ idBandeira=" + idBandeira + " ]";
    }
    
}
