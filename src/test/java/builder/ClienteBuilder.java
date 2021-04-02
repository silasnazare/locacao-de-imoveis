package builder;

import model.Cliente;

public class ClienteBuilder {
    private Cliente cliente;

    public ClienteBuilder() { }

    public static ClienteBuilder umCliente() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        clienteBuilder.cliente = new Cliente();
        clienteBuilder.cliente.setNome("...");
        return clienteBuilder;
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public Cliente constroi() {
        return cliente;
    }
}
