package repository;

import builder.ImovelBuilder;
import model.Imovel;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ImovelRepositoryTest {
    private EntityManager manager;
    private static EntityManagerFactory factory;
    private ImovelRepository imoveis;

    @BeforeAll
    static void start() {
        factory = Persistence.createEntityManagerFactory("br.edu.ifma.es2_lab3-locacaodeimoveis-integracao_1.0-SNAPSHOTPU_test");
    }

    @BeforeEach
    void setUp() {
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        imoveis = new ImovelRepositoryImplement(manager);
    }

    @AfterEach
    void tearDown() {
        manager.getTransaction().rollback();
    }

    @AfterAll
    static void end() {
        factory.close();
    }

    @Test
    void buscaPorId() {
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        Imovel apartamentoCalhau = imovelBuilder.umImovel().doTipo("Apartamento").noEndereco("Calhau").constroi();
        apartamentoCalhau.setId(100000);
        imoveis.criaOuAtualiza(apartamentoCalhau);
        manager.flush();
        manager.clear();

        assertThat(100000, is(equalTo(apartamentoCalhau.getId())));
    }

    @Test
    void criaOuAtualiza() {
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        Imovel imovelCalhau = imovelBuilder.umImovel().doTipo("Apartamento").noEndereco("Calhau").constroi();
        imovelCalhau.setTipo("Casa");
        imoveis.criaOuAtualiza(imovelCalhau);
        manager.flush();
        manager.clear();

        assertThat("Casa", is(equalTo(imovelCalhau.getTipo())));
    }

    @Test
    void remove() {
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        Imovel apartamentoCalhau = imovelBuilder.umImovel().doTipo("Apartamento").noEndereco("Calhau").constroi();
        imoveis.criaOuAtualiza(apartamentoCalhau);
        imoveis.remove(apartamentoCalhau);
        manager.flush();
        manager.clear();
    }
}