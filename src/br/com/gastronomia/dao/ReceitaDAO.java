package br.com.gastronomia.dao;

import br.com.gastronomia.db.GenericHibernateDAO;
import br.com.gastronomia.db.HibernateUtil;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Receita;
import br.com.gastronomia.model.Usuario;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class ReceitaDAO extends GenericHibernateDAO<Receita> {

//    public List<Receita> listForName(String q) {
//        try {
//            Session receitas = HibernateUtil.getFactory();
//            List<Receita> receitas = new List<Receita>getNamedQuery("findUserByName").list();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return receitas;
//    }



    public List<Receita> listAllReceitas(){
        List<Receita> Receitas = listAll(Receita.class);
        return Receitas;
    }

    public Receita findReceitaByName(String nome) {
        return (Receita) findSingleObject("Nome", Receita.class, nome);
    }

    public Receita findReceitaById(long id) {
        return (Receita) findSingleObject("id", Receita.class, id);
    }

    public long updateReceita(Receita receita) throws ValidationException {
        return merge(receita);
    }

    public long alterStatus(long id, boolean statusBool) throws ValidationException {
        Receita receita = findReceitaById(id);
        receita.setStatus(statusBool);
        return merge(receita);
    }

    public List<Receita> findReceitaByIdUsuario(Long id) {
        return findMultipleObjects("professor", Receita.class, id);
    }
}
