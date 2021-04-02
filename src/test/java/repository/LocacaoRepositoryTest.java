package repository;

import builder.LocacaoBuilder;
import model.Locacao;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LocacaoRepositoryTest {
    private EntityManager manager;
    private static EntityManagerFactory factory;
    private LocacaoRepository locacoes;

    @BeforeAll
    static void start() {
        factory = Persistence.createEntityManagerFactory("br.edu.ifma.es2_lab3-locacaodeimoveis-integracao_1.0-SNAPSHOTPU_test");
    }

    @BeforeEach
    void setUp() {
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        locacoes = new LocacaoRepositoryImplement(manager);
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
        Locacao locacao = LocacaoBuilder.umaLocacao().constroi();
        locacao.setId(100);
        locacoes.criaOuAtualiza(locacao);
        manager.flush();
        manager.clear();

        assertThat(100, is(equalTo(locacao.getId())));
    }
}