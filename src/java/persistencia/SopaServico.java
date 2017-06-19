package persistencia;

import java.sql.SQLException;
import java.util.List;
import modelo.Sopa;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SopaServico extends DAOGenericoJPA<Long, Sopa>{

    public SopaServico() {
        super();
    }
    
    public boolean deletar(Sopa sopa) throws Exception,SQLException{
        super.getEm().getTransaction().begin();
        Query query = super.getEm().createNamedQuery("Sopa.RetornaId");
        query.setParameter("nome", sopa.getNome());
        
        Long id = (Long) query.getSingleResult();
        
        Sopa p = super.getEm().find(Sopa.class,id);
        
        try{
            super.getEm().remove(p);
            super.getEm().getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     *
     * @param pr
     */
    @Override
    public void update(Sopa pr){
        super.getEm().getTransaction().begin();
        Sopa sopa = getSopa(pr.getNome());
        sopa = super.getEm().find(Sopa.class, sopa.getId());
        sopa.setDescricao(pr.getDescricao());
        System.out.println("Descricao: " + pr.getDescricao() + "  PREÇOOOOO: " + pr.getPreco());
        sopa.setPreco(pr.getPreco());
        super.getEm().merge(sopa);
        super.getEm().getTransaction().commit();
    }

    public Sopa getById(long pk) {
        return super.getById(pk);
    }
    
    public Sopa getSopa(String pr){
        String query = "select e from Sopa e";
        List<Sopa> sopas = super.getEm().createQuery(query, Sopa.class).getResultList();
        try{
            for(Sopa sopa : sopas){
                if(sopa.getNome().equals(pr)) return sopa;
            }
            return null;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public boolean existeSopa(Sopa p){
        String query = "select e from Sopa e";
        List<Sopa> sopas = super.getEm().createQuery(query, Sopa.class).getResultList();
        try{
            for(Sopa sopa : sopas){
                if(sopa.equals(p))return true;
            }
            return false;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @Override
    public List<Sopa> findAll() {
        String query = "SELECT e FROM Sopa e ORDER BY e.nome";
        List<Sopa> sopas = super.getEm().createQuery(query, Sopa.class).getResultList();
        return sopas;
    }
    
    @Override
    public void save(Sopa b) {
        if(!existeSopa(b)){
            super.getEm().getTransaction().begin();
            super.getEm().persist(b);
            super.getEm().getTransaction().commit();
        }
    }
    
    
    
}

