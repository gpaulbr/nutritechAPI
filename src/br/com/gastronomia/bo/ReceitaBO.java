package br.com.gastronomia.bo;

import br.com.gastronomia.dao.ReceitaDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Receita;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceitaBO {

    private ReceitaDAO receitaDAO;

    public ReceitaBO() {
        receitaDAO = new ReceitaDAO();
    }

    public long inactiveReceita(long id) throws ValidationException  {
        return receitaDAO.alterStatus(id, false);
    }

    public long activateReceita(long id) throws ValidationException  {
        return receitaDAO.alterStatus(id, true);
    }

    public long updateReceita(Receita receita) throws ValidationException {
        if (receita != null) {
            return receitaDAO.updateReceita(receita);
        }
        throw new ValidationException("invalido");

    }

    public boolean createReceita(Receita receita) throws ValidationException, NoSuchAlgorithmException {
        if (receita != null) {
            System.out.println(receita);
            receitaDAO.save(receita);
            return true;
        }
        throw new ValidationException("invalido");

    }

    public HashMap<String, List<Receita>> listReceita() {
        ArrayList<Receita> receitas = null;
        HashMap<String, List<Receita>> listReceitas = new HashMap<String, List<Receita>>();
        receitas = (ArrayList<Receita>) receitaDAO.listAllReceitas();
        listReceitas.put("Receitas", receitas);
        return listReceitas;
    }

    public Receita getReceitaById(Long id) throws ValidationException {
        if (id > 0) {
            return receitaDAO.findReceitaById(id);
        }
        throw new ValidationException("invalido");

    }

    public List<Receita> getReceitaByIdUsuario(Long id) throws ValidationException {
        if (id > 0) {
            return receitaDAO.findReceitaByIdUsuario(id);
        }
        throw new ValidationException("invalido");

    }

//    public List<Receita> listReceitaName(String nome) throws ValidationException {
//        if (nome != null) {
//            return receitaDAO.listForName(nome);
//        }
//        throw new ValidationException("invalido");
//    }

}
