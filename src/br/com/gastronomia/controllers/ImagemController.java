package br.com.gastronomia.controllers;

import br.com.gastronomia.bo.ImagemBO;
import br.com.gastronomia.dao.ImagemDAO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Imagem;
import br.com.gastronomia.util.EncryptUtil;
import br.com.gastronomia.util.WriteImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

@Path("imagens")
public class ImagemController {
	private ImagemBO imagemBO = new ImagemBO();
	private ImagemDAO imagemDAO = new ImagemDAO();
	private EncryptUtil encryptUtil = new EncryptUtil();

	@Context
	private HttpServletRequest request;
	private HttpSession session;

	@GET
	@Path("/")
	@Produces("image/jpeg")
	//@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(imagemBO.listImagens()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("image/jpeg")
	//@JWTTokenNeeded
	public Response create(Imagem imagem) throws PersistenciaException, ValidationException {

		System.out.println("Criando imagem");
		try {
//			BufferedImage bi = WriteImage.imageFromBytes(imagem.getImage());
//			if(bi == null) {
//				return Response.ok().entity(new StandardResponseDTO(false, "Conversão de bytes para bufferedImage falhou")).status(Response.Status.ACCEPTED).build();
//			}
//			String path = WriteImage.write(bi, String.valueOf(imagem.hashCode()));
			String path = WriteImage.write(imagem.getImagem(), String.valueOf(imagem.hashCode()));
			System.out.printf("Imagem em path %s\n", path);
			if(path == null) {
				return Response.ok().entity(new StandardResponseDTO(false, "Salvamento da imagem em path falhou")).status(Response.Status.ACCEPTED).build();
			}
			imagem.setFilePath(path);
			imagemBO.createImagem(imagem);
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

		return Response.ok().entity(new StandardResponseDTO(true, "Imagem criada com sucesso!")).status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			return Response.ok().entity(new StandardResponseDTO(false, "Não implementado no backend!")).status(Response.Status.ACCEPTED).build();

		} catch (Exception e) {

			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		//return Response.ok().entity(new StandardResponseDTO(true, "Atributo desativado com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			return Response.ok().entity(imagemBO.getImagemById(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {

			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@PUT
	@Path("/update")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	//@JWTTokenNeeded
	public Response update(Imagem imagem) throws PersistenciaException, ValidationException {

		try {
			imagemBO.updateImagem(imagem);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().entity(new StandardResponseDTO(true, "Imagem atualizada com sucesso!")).status(Response.Status.ACCEPTED).build();

	}
}
