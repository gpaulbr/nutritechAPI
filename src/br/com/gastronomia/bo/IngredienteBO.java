package br.com.gastronomia.bo;

import br.com.gastronomia.dao.IngredienteDAO;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.dto.IngredienteCadastroDTO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.Usuario;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IngredienteBO {
	private IngredienteDAO ingredienteDAO;
	private UsuarioDAO usuarioDAO;

	public IngredienteBO() {
		ingredienteDAO = new IngredienteDAO();
		usuarioDAO = new UsuarioDAO();
	}


	public long inactiveIngrediente(long id) throws ValidationException  {
		return ingredienteDAO.alterStatus(id, false);
	}

	public long activateIngrediente(long id) throws ValidationException  {
		return ingredienteDAO.alterStatus(id, true);
	}

	public long updateIngrediente(Ingrediente ingrediente) throws ValidationException {
		if (ingrediente != null) {
			return ingredienteDAO.updateIngrediente(ingrediente);
		}
		throw new ValidationException("invalido");

	}

	public boolean createIngrediente(IngredienteCadastroDTO ingredienteDto) throws ValidationException, NoSuchAlgorithmException {
		if (ingredienteDto != null) {
			Usuario usuario = usuarioDAO.findUserByID(ingredienteDto.getIdCriador());
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setIngredienteInfoCadastro(ingredienteDto, usuario);
			ingredienteDAO.save(ingrediente);
			return true;
		}
		throw new ValidationException("invalido");

	}

	public HashMap<String, List<Ingrediente>> listIngrediente() {
		ArrayList<Ingrediente> ingredientes = null;
		HashMap<String, List<Ingrediente>> listIngredientes = new HashMap<String, List<Ingrediente>>();
		ingredientes = (ArrayList<Ingrediente>) ingredienteDAO.listAllIngredientes();
		listIngredientes.put("Ingredientes", ingredientes);
		return listIngredientes;
	}

	public Ingrediente getIngredienteById(long id) throws ValidationException {
		if (id > 0) {
			return ingredienteDAO.findIngredienteById(id);
		}
		throw new ValidationException("invalido");

	}

}
