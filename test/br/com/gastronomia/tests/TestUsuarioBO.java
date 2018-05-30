package br.com.gastronomia.tests;

import static org.junit.Assert.assertEquals;

import br.com.gastronomia.bo.UsuarioBO;
import br.com.gastronomia.model.Ingrediente;
import br.com.gastronomia.model.Usuario;
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
}
