package br.com.gastronomia.controllers;

import br.com.gastronomia.bo.ReceitaBO;
import br.com.gastronomia.dto.StandardResponseDTO;
import br.com.gastronomia.exception.PersistenciaException;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Receita;
import br.com.gastronomia.model.ReceitaIngrediente;
import br.com.gastronomia.util.EncryptUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("receitas")
public class ReceitaController {
    private ReceitaBO receitaBO = new ReceitaBO();
    private EncryptUtil encryptUtil = new EncryptUtil();

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @GET
    @Path("/")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response list() throws PersistenciaException, SQLException {
        try {
            return Response.ok().entity(receitaBO.listReceita()).status(Response.Status.ACCEPTED).build();

        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response create(Receita receita) throws PersistenciaException, ValidationException {

        try {
            System.out.println(receita);
            receitaBO.createReceita(receita);
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "Receita "+receita.getNome()+", criado com sucesso!")).status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

        try {
            receitaBO.inactiveReceita(id);
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "Receita deletado com sucesso!")).status(Response.Status.ACCEPTED).build();

    }
    @GET
    @Path("/{id}")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

        try {
            return Response.ok().entity(receitaBO.getReceitaById(id)).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

    }

    @PUT
    @Path("/update")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    //@JWTTokenNeeded
    public Response update(Receita receita) throws PersistenciaException, ValidationException {
        try {
            receitaBO.updateReceita(receita);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().entity(new StandardResponseDTO(true, "Receita "+receita.getNome()+ " editado com sucesso!")).status(Response.Status.ACCEPTED).build();

    }
}
