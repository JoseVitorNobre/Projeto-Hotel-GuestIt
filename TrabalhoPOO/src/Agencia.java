/**
 * O enumerador Agencia serve para diferenciar o tipo de agência encarregado 
 * pelo turista, caso novas regras de negócio sejam adicionadas, como por 
 * exemplo ao invés de ser agências locais ou internacionais, serem agências 
 * classificadas por estados ou cidades, essas regras podem ser adicionadas 
 * aqui com mais facilidade.
 * @author Alexson Almeida e José Vitor
 */
public enum Agencia{
    LOCAL("local"), INTERNACIONAL("internacional");
    private String agencia;

    Agencia(String agencia){
        this.agencia = agencia;
    }

    public String getAgencia(){
        return this.agencia;
    }

    @Override
    public String toString() {
        String msg = "";
        switch (this.agencia) {
            case "local":
                msg += "Agencia Local";
                break;
            case "internacional":
                msg += "Agencia Internacional";
                break;
            default:
                msg += "Tipo de agencia não especificado";
                break;
        }
        return msg;
    }
}