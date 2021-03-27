package builder;

import model.Imovel;

public class ImovelBuilder {
    private Imovel imovel;

    public ImovelBuilder() { }

    public ImovelBuilder umImovel() {
        ImovelBuilder imovelBuilder = new ImovelBuilder();
        imovelBuilder.imovel = new Imovel();
        imovelBuilder.imovel.setTipo("tipo");
        return imovelBuilder;
    }

    public ImovelBuilder doTipo(String tipo) {
        imovel.setTipo(tipo);
        return this;
    }

    public ImovelBuilder noEndereco(String endereco) {
        imovel.setEndereco(endereco);
        return this;
    }

    public Imovel constroi() {
        return imovel;
    }
}
