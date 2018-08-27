package com.jorge.conductor;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jorge.conductor.ConductorServiceApp;
import com.jorge.conductor.domain.Analise;
import com.jorge.conductor.domain.Funcionario;
import com.jorge.conductor.domain.Portador;
import com.jorge.conductor.repository.AnaliseRepository;
import com.jorge.conductor.repository.FuncionarioRepository;
import com.jorge.conductor.repository.PortadorRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConductorServiceApp.class)
@WebAppConfiguration
public class ConductorServiceApplicationTests {

	// Invoca o repositório dos funcionários e seus respectivos comandos JPA
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// Invoca o service do portador para ser efetuado execuções geralmente
	// para o banco de dados
	@Autowired
	private PortadorRepository portadorRepository;

	// Invoca o repositório das análises e seus respectivos comandos JPA
	@Autowired
	private AnaliseRepository analiseRepository;

	private static List<Funcionario> funcionarios = new ArrayList<>();

	@Before
	public void setup() {
		
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testFuncionarioSave() {
		funcionarios.add(new Funcionario("eliane@gmail.com", "Eliane Lima", "123456", true));
		assertTrue(funcionarioRepository.save(funcionarios) != null);
	}

	@Test
	public void testPortadorSave() {
		Portador portador = new Portador("José", "jose@gmail.com", "Rua José Branco Ribeiro 840", "999888", "00011166644",
				"08330656112", new Date(), "prefeito", 15500.0, true);
		assertTrue(portadorRepository.save(portador) != null);
		Analise analise = new Analise();
		analise.setPortador(portador);
		analise.setAprovado(null);
		analise.setDataAnalise(null);
		assertTrue(analiseRepository.save(analise) != null);
	}

}
