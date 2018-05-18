package br.com.gastronomia.bo;

import br.com.gastronomia.dao.AtributoDAO;
import br.com.gastronomia.dao.IngredienteDAO;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.dto.IngredienteAtributoDto;
import br.com.gastronomia.dto.IngredienteCadastroDTO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.IngredienteAtributo;
import br.com.gastronomia.model.Usuario;
import org.hibernate.exception.ConstraintViolationException;

import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class IngredienteBO {
	private IngredienteDAO ingredienteDAO;
	private UsuarioDAO usuarioDAO;
	private AtributoDAO atributoDAO;

	public IngredienteBO() {
		ingredienteDAO = new IngredienteDAO();
		usuarioDAO = new UsuarioDAO();
		atributoDAO = new AtributoDAO();
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
			try {
				Usuario usuario = usuarioDAO.findUserByID(ingredienteDto.getIdCriador());
				Ingrediente ingrediente = new Ingrediente();
				ingrediente.setIngredienteInfoCadastro(ingredienteDto, usuario);

				for (IngredienteAtributoDto ingAtr: ingredienteDto.getAtributos()) {
					ingrediente.addIngredienteAtributo(atributoDAO.findAtributoByID(ingAtr.getIdAtributo()), ingAtr.getValor());
				}
				ingredienteDAO.save(ingrediente);
				return true;
			} catch (ValidationException | NullPointerException e) {
				if(e instanceof ValidationException) {
					throw new ValidationException("Ingrediente já existente.");
				} else {
					throw new ValidationException("Erro no cadastro.");
				}
			}
		}
		return false;
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
