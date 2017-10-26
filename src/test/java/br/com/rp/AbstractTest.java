package br.com.rp;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import br.com.rp.domain.Log;
import br.com.rp.repository.LogRepositoryTest;
import br.com.rp.repository.Repository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.service.proposta.PropostaServiceTest;
import br.com.rp.services.LogService;
import br.com.rp.services.proposta.PropostaService;

@RunWith(Arquillian.class)
public abstract class AbstractTest {

	@PersistenceContext
	EntityManager em;

	@Before
	public void setup() {

	}

	@After
	public void tearDown() {
	}

	@Deployment(testable = true)
	public static WebArchive createTestArchive() {

		File[] deps = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies().resolve()
				.withTransitivity().asFile();

		WebArchive archive = ShrinkWrap.create(WebArchive.class, "vbank.war")
				.addPackages(true, Log.class.getPackage())
				.addPackages(true, Repository.class.getPackage())
				.addPackages(true, AbstractRepositoryImpl.class.getPackage())
				.addPackages(true, AbstractTest.class.getPackage())
				.addPackages(true, LogRepositoryTest.class.getPackage())
				.addPackages(true, LogService.class.getPackage())
				.addPackages(true, PropostaService.class.getPackage())
				.addPackages(true, PropostaServiceTest.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("vbank-ds.xml")
				.addAsLibraries(deps);

		return archive;
	}

}
