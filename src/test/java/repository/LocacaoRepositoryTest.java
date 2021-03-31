package repository;

import builder.ClienteBuilder;
import builder.ImovelBuilder;
import builder.LocacaoBuilder;
import model.Imovel;
import model.Locacao;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

class LocacaoRepositoryTest {
    private EntityManager manager;
    private static EntityManagerFactory factory;
    private LocacaoRepository locacoes;
    private ImovelRepository imoveis;

    @BeforeAll
    static void start() {
        factory = Persistence.createEntityManagerFactory("br.edu.ifma.es2_lab3-locacaodeimoveis-integracao_1.0-SNAPSHOTPU_test");
    }

    @BeforeEach
    void setUp() {
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        locacoes = new LocacaoRepositoryImplement(manager);
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
    void imoveisDisponiveisPorEndereco() {
        Imovel apartamentoCalhau1 = ImovelBuilder.umImovel().doTipo("Apartamento").noEndereco("Calhau").constroi();
        Imovel apartamentoCalhau2 = ImovelBuilder.umImovel().doTipo("Apartamento").noEndereco("Calhau").constroi();
        Imovel casaCalhau = ImovelBuilder.umImovel().doTipo("Casa").noEndereco("Calhau").constroi();
        Locacao locacao1 = LocacaoBuilder.umaLocacao().deUmImovel(apartamentoCalhau1).constroi();
        Locacao locacao2 = LocacaoBuilder.umaLocacao().deUmImovel(apartamentoCalhau2).constroi();
        Locacao locacao3 = LocacaoBuilder.umaLocacao().deUmImovel(casaCalhau).constroi();

        imoveis.criaOuAtualiza(apartamentoCalhau1);
        imoveis.criaOuAtualiza(apartamentoCalhau2);
        imoveis.criaOuAtualiza(casaCalhau);
        locacoes.criaOuAtualiza(locacao1);
        locacoes.criaOuAtualiza(locacao2);
        locacoes.criaOuAtualiza(locacao3);
        locacoes.imoveisDisponiveisPorEndereco("Calhau");
        manager.flush();
        manager.clear();

        assertEquals("Calhau", casaCalhau.getEndereco());
    }
}