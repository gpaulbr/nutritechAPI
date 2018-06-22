package br.com.gastronomia.tests;

import static br.com.gastronomia.util.TipoDeUsuario.ADMIN;
import static br.com.gastronomia.util.TipoDeUsuario.PROFESSOR;
import static br.com.gastronomia.util.TipoDeUsuario.USUARIO;
import static org.junit.Assert.assertEquals;

import br.com.gastronomia.bo.UsuarioBO;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.Usuario;
import br.com.gastronomia.util.TipoDeUsuario;
import org.junit.Test;

import br.com.gastronomia.util.Validator;
import br.com.gastronomia.util.Util;

public class TestUsuarioBO {


	// Testes do semestre anterior da classe Validator

	@Test
	public void testCPFTrue() {
		assertEquals(true, Validator.validaCpf.fazConta(("61642096091")));
	}
	@Test
	public void testCPFFalse() {
		assertEquals(false, Validator.validaCpf.fazConta(("85172596021")));
	}

	// Testes a partir da Sprint 3 - 2018
	// Testes da Classe Validator
	@Test
	public void testCpfNaoAceitaLetras() {assertEquals(false, Validator.validaCpf.fazConta("abcdefghijk"));}

	@Test
	public void testCpfNaoAceitaMenosDeOnzeNumeros(){assertEquals(false, Validator.validaCpf.fazConta("12345678910"));}

	@Test
	public void testCpfNaoAceitaMaisDeOnzeNumeros(){assertEquals(false, Validator.validaCpf.fazConta("123456789011"));}

	// Testes da Classe util

	@Test
	public void testNaoAceitaSequenciaDeNumerosIguais () {

		assertEquals(false, Util.isCPF("1111111111"));
	}

	// Testes da Classe Usuario - model

	@Test
	public void testUsuarioNome(){
	Usuario aluno = new Usuario();
	aluno.setNome("Testando Usuario");
		{assertEquals("Testando Usuario", aluno.getNome());}
	}

	@Test
	public void testUsuarioCpf(){
		Usuario aluno = new Usuario();
		aluno.setCpf("0987654321");
		{assertEquals("0987654321", aluno.getCpf());}
	}

	@Test
	public void testUsuarioSenha(){
		Usuario aluno = new Usuario();
		aluno.setSenha("teste123");
		{assertEquals("teste123", aluno.getSenha());}
	}

	@Test
	public void testUsuarioStatus(){
		Usuario aluno = new Usuario();
		aluno.setStatus(true);
		{assertEquals(true, aluno.isStatus());}
	}

	@Test
	public void testUsuarioEmail(){
		Usuario aluno = new Usuario();
		aluno.setEmail("aluno@ufcspa.com.br");
		{assertEquals("aluno@ufcspa.com.br", aluno.getEmail());}
	}

	@Test
	public void testUsuarioTipoADMIN(){
		Usuario aluno = new Usuario();
		aluno.setTipo(ADMIN);
		{assertEquals(ADMIN, aluno.getTipo());}
	}
	@Test
	public void testUsuarioTipoPROFESSOR(){
		Usuario aluno = new Usuario();
		aluno.setTipo(PROFESSOR);
		{assertEquals(PROFESSOR, aluno.getTipo());}
	}

	@Test
	public void testUsuarioTipoUSUARIO(){
		Usuario aluno = new Usuario();
		aluno.setTipo(USUARIO);
		{assertEquals(USUARIO, aluno.getTipo());}
	}


}

