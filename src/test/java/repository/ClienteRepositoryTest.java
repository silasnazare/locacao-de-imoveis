package repository;

import builder.ClienteBuilder;
import model.Cliente;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;

import java.time.LocalDate;

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
    void buscaPorId() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        Cliente silas = clienteBuilder.umCliente().comNome("Silas Nazare").constroi();
        silas.setId(100000);
        clientes.criaOuAtualiza(silas);
        manager.flush();
        manager.clear();

        assertThat(100000, is(equalTo(silas.getId())));
    }

    @Test
    void buscaPorNome() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        Cliente silas = clienteBuilder.umCliente().comNome("Silas Nazare").constroi();
        assertThrows(NoResultException.class, () -> clientes.buscaPorNome("Silas Nazare"), "Deveria lançar a exeção NoResultException");
        clientes.criaOuAtualiza(silas);
        manager.flush();
        manager.clear();

        assertThat("Silas Nazare", is(equalTo(silas.getNome())));
    }

    @Test
    void criaOuAtualiza() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        Cliente silas = clienteBuilder.umCliente().comNome("Silas Nazare").constroi();
        silas.setNome("Rita Teixeira");
        clientes.criaOuAtualiza(silas);
        manager.flush();
        manager.clear();

        assertThrows(NoResultException.class, () -> clientes.buscaPorNome("Silas Nazare"), "Deveria ter lançado a exceção NoResultException");
    }

    @Test
    void remove() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        Cliente silas = clienteBuilder.umCliente().comNome("Silas Nazare").constroi();
        clientes.criaOuAtualiza(silas);
        clientes.remove(silas);
        manager.flush();
        manager.clear();

        assertThrows(NoResultException.class, () -> clientes.buscaPorNome("Silas Nazare"), "Deveria ter lançado a exceção NoResultException");
    }
}