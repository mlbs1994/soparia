package beans;


import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@NamedQuery(name = "Sopa.RetornaId",query= " SELECT max(u.id) FROM Sopa u WHERE u.nome = :nome")
@Table(name = "TB_SOPA")
public class Sopa implements Serializable, BaseEntity {
    private static final long serialVersionUID = 1L;
    
    public Sopa(){
        this.sopas = new ArrayList<>();
    }
    
    
    public Sopa(String nome, double preco, String descricao, String imagem){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOPA")
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 30)
    @Column(name = "SOPA_NOME")
    private String nome;
    
    @NotNull
    @Min(0)
    @Column(name = "SOPA_PRECO")
    private Double preco;
    
    @NotBlank
    @Size(min = 3, max = 300)
    @Column(name = "SOPA_DESCRICAO")
    private String descricao;
    
    //Nome da imagem, no diretório imagens
    @Column(name = "SOPA_IMAGEM")
    private String imagem;
    
    @Transient
    private ArrayList<Sopa> sopas;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public ArrayList<Sopa> getSopas() {
        return sopas;
    }

    public void setSopas(ArrayList<Sopa> sopas) {
        this.sopas = sopas;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

    public boolean equals(Sopa outro) {
        return this.nome.equals(outro.nome); 
    }
    
    
    
}
