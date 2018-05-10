package br.com.gastronomia.controllers;

import br.com.gastronomia.bo.UsuarioBO;
import br.com.gastronomia.dao.UsuarioDAO;
import br.com.gastronomia.exception.ValidationException;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.EncryptUtil;
import br.com.gastronomia.util.SendMail;
import br.com.gastronomia.util.TipoDeUsuario;
import br.com.gastronomia.util.Util;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Path("/")
public class APIController {

	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	public Response index() {

		Usuario usuario = null;

		try {
		    usuario = (new UsuarioDAO()).findUserByMatricula("14201623");
        } finally {

		    if (usuario != null){
                return Response.ok().entity(usuario).build();
            }

            String encryptedPassword = null;
            try {
                encryptedPassword = EncryptUtil.encrypt2("080396");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            usuario = new Usuario();
            usuario.setNome("Paul");
            usuario.setStatus(false);
            usuario.setEmail("gpaulbr@gmail.com");
            usuario.setCpf("02458544010");
            usuario.setMatricula("14201623");
            usuario.setTipo(TipoDeUsuario.USER);
            usuario.setSenha(encryptedPassword);

            System.out.println(usuario.getId());
            System.out.println("-------------");
            try {
                String encryptedId = EncryptUtil.encrypt2(String.valueOf(usuario.getId()));
                System.out.println(encryptedId);
                System.out.println("-------------");

                System.out.println(EncryptUtil.decrypt(encryptedId));
                System.out.println("-------------");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }

            try {
                UsuarioBO usuarioBO = new UsuarioBO();
                try {
                    usuarioBO.createUser(usuario);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return Response.ok().entity(usuario).build();
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            return Response.ok().entity(Response.Status.BAD_REQUEST).build();
        }
	}

	@GET
	@Path("/version")
	@Produces("application/text; charset=UTF-8")
	public String getVersion() {
		return Util.getVersion();
	}
}
