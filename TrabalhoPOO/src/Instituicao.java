/**
 * O enumerador Instituicao serve para diferenciar o tipo de instituição 
 * encarregado pelo estudante, caso novas regras de negócio sejam 
 * adicionadas, como por exemplo ao invés de ser instituições de escolas ou 
 * universidades, serem instituições classificadas por públicas ou 
 * particulares, essas regras podem ser adicionadas aqui com mais facilidade.
 * @author Alexson Almeida e José Vitor
 */
public enum Instituicao {
    ESCOLA("escola"), UNIVERSIDADE("universidade");
    private String instituicao;

    Instituicao(String instituicao){
        this.instituicao = instituicao;
    }
    
    public String getInstituicao(){
        return this.instituicao;
    }

    @Override
    public String toString() {
        String msg = "";
        switch (this.instituicao) {
            case "escola":
                msg += "Escola";
                break;

            case "universidade":
                msg += "Universidade";
                break;
                
            default:
                msg += "Tipo de instituição não especificado";
                break;
        }
        return msg;
    }
}
