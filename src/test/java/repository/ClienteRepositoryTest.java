package repository;

import model.Cliente;
import org.junit.jupiter.api.*;

import javax.persistence.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest {
    private EntityManager manager;
    private static EntityManagerFactory factory;
    private ClienteRepository clientes;

    @BeforeAll
    static void start() {
        factory = Persistence.createEntityManagerFactory("br.edu.ifma.es2_lab3-locacaodeimoveis-integracao_1.0-SNAPSHOTPU_test");
    }
    @BeforeEach
    void setUp() {
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        clientes = new ClienteRepositoryImplement(manager);
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
    void buscaPorNome() {
        assertThrows(NoResultException.class, () -> clientes.buscaPorNome("Silas Nazare"),"Deveria lançar a exceção NoResultException");

        clientes.salva(new Cliente("Silas Nazare"));
        manager.flush();
        manager.clear();

        Cliente clienteDoBanco = clientes.buscaPorNome("Silas Nazare");

        assertThat("Silas Nazare", is(equalTo(clienteDoBanco.getNome())));
    }

    @Test
    void salva() {
    }

    @Test
    void atualiza() {
    }

    @Test
    void exclui() {
    }
}