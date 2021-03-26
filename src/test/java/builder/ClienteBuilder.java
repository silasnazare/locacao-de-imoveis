package builder;

import model.Cliente;

public class ClienteBuilder {
    private Cliente cliente;
    private static int contadorId = 1;

    public ClienteBuilder() {
    }

    public static ClienteBuilder umCliente() {
        ClienteBuilder builder = new ClienteBuilder();
        builder.cliente = new Cliente();
        builder.cliente.setNome("Cliente 01");
        builder.cliente.setId(contadorId++);
        return builder;
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public Cliente constroi() {
        return cliente;
    }
}
