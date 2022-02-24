/**
 * O enumerador ServicoDeQuarto serve para classificar os tipos de serviços de quarto que podem ser 
 * pedidos pelo hóspede, podendo ser adicionado mais serviços se necessário.
 * @author Alexson Almeida e José Vitor
 */
public enum ServicoDeQuarto {
    FAXINA("faxina"), BUFE("bufe"), LAVANDERIA("lavanderia");
    String nomeServico;

    private ServicoDeQuarto(String servico) {
        this.nomeServico = servico;
    }

    /**
     * @param servico Serviço a ser fornecido
     * @return O valor daquele serviço
     */
    public static float precoServico(ServicoDeQuarto servico){
        if(servico.equals(FAXINA)){
            return 30;
        }else if(servico.equals(BUFE)){
            return 50;
        }else if(servico.equals(LAVANDERIA)){
            return 25;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        String nome = "";
        switch (this.nomeServico) {
            case "faxina":
            nome += "Faxina";
            break;

            case "bufe":
            nome += "Bufe";
            break;

            case "lavanderia":
            nome += "Lavanderia";
            break;

            default:
            nome += "Servico nao identificado";
            break;
        }

        return nome;
    }

    public String getNomeServico() {
        return nomeServico;
    }
}
