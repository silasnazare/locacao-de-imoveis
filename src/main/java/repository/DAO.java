package repository;

import model.Cliente;
import model.Entidade;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.Objects;

public class DAO<T extends Entidade>{
    private final EntityManager manager;

    DAO(EntityManager manager) {
        this.manager = manager;
    }

    Cliente buscaPorNome(Class<T> cClass, String nome) {
            return manager.createQuery("select c from Cliente c where c.nome = :pNome", Cliente.class)
                    .setParameter("pNome", nome)
                    .getSingleResult();
    }

    T buscaPorId(Class<T> cClass, Integer id) {
        return manager.find(cClass, id);
    }

    T salvaOuAtualiza(@NotNull T t) {
        if (Objects.isNull(t.getId())) {
            this.manager.persist(t);
        }
        else {
            t = this.manager.merge(t);
        }
        return t;
    }

    void remove(T t) {
        manager.remove(t);
        manager.flush();
    }
}
