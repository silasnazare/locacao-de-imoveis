package repository;

import builder.ImovelBuilder;
import model.Imovel;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

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
        Imovel imovelCalhau = ImovelBuilder.umImovel().doTipo("apartamento").noEndereco("Calhau").constroi();
        imovelCalhau.setId(100000);
        imoveis.criaOuAtualiza(imovelCalhau);
        manager.flush();
        manager.clear();

        assertThat(100000, is(equalTo(imovelCalhau.getId())));
    }

    @Test
    void criaOuAtualiza() {
        Imovel imovelCalhau = ImovelBuilder.umImovel().doTipo("apartamento").noEndereco("Calhau").constroi();
        imovelCalhau.setTipo("casa");
        imoveis.criaOuAtualiza(imovelCalhau);
        manager.flush();
        manager.clear();

        assertThat("casa", is(equalTo(imovelCalhau.getTipo())));
    }

    @Test
    void remove() {
        Imovel imovelCalhau = ImovelBuilder.umImovel().doTipo("apartamento").noEndereco("Calhau").constroi();
        imoveis.criaOuAtualiza(imovelCalhau);
        imoveis.remove(imovelCalhau);
        manager.flush();
        manager.clear();
    }

    @Test
    void imoveisDisponiveisPorEndereco() {
        Imovel apartamentoCalhau1 = ImovelBuilder.umImovel().doTipo("apartamento").noEndereco("Calhau").constroi();
        Imovel apartamentoCalhau2 = ImovelBuilder.umImovel().doTipo("apartamento").noEndereco("Calhau").constroi();
        Imovel casaCalhau = ImovelBuilder.umImovel().doTipo("casa").noEndereco("Calhau").constroi();
        apartamentoCalhau1.setAtivo(true);
        apartamentoCalhau2.setAtivo(false);
        casaCalhau.setAtivo(false);

        imoveis.criaOuAtualiza(apartamentoCalhau1);
        imoveis.criaOuAtualiza(apartamentoCalhau2);
        imoveis.criaOuAtualiza(casaCalhau);
        List<Imovel> listaImoveis = imoveis.imoveisDisponiveisPorEndereco("apartamento", "Calhau");
        manager.flush();
        manager.clear();

        assertEquals(1, listaImoveis.size());
    }

    @Test
    void imoveisDisponiveisPorPreco() {
        Imovel imovel1 = ImovelBuilder.umImovel().comOValorSugeridoDe(2000.00).constroi();
        Imovel imovel2 = ImovelBuilder.umImovel().comOValorSugeridoDe(1849.90).constroi();
        Imovel imovel3 = ImovelBuilder.umImovel().comOValorSugeridoDe(1500.00).constroi();

        imoveis.criaOuAtualiza(imovel1);
        imoveis.criaOuAtualiza(imovel2);
        imoveis.criaOuAtualiza(imovel3);
        List<Imovel> listaImoveis = imoveis.imoveisDisponiveisPorPreco(1900);
        manager.flush();
        manager.clear();

        assertEquals(2, listaImoveis.size());
    }
}