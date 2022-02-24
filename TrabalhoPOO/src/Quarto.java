import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * A classe quarto será onde iremos administrar os valores e a capacidade 
 * dos hóspedes, dependendo do tipo do quarto.
 * @author Alexson Almeida e José Vitor 
 */
public class Quarto implements Serializable{

    //Atributos
    private TipoDeQuarto tpQuarto;
    private List<Hospede> hospedes;
    private int maxHospedes;
    private float precoHosp;

    /**
     * No construtor iremos colocar o tipo do quarto e dependendo do tipo, 
     * os atributos maxHospedes e precoHosp irão mudar.
     * @param tpQuarto Tipo do quarto que será adicionado
     */
    public Quarto(TipoDeQuarto tpQuarto) {
        this.tpQuarto = tpQuarto;
        switch (this.tpQuarto) {
            case QUARTO_UNICO:
                this.maxHospedes = 1;
                this.precoHosp = 90;
                break;

            case QUARTO_CASAL:
                this.maxHospedes = 2;
                this.precoHosp = 160;
                break;

            case QUARTO_SOLTEIRO_DUPLO:
                this.maxHospedes = 2;
                this.precoHosp = 200;
                break;

            case QUARTO_FAMILIA:
                this.maxHospedes = 5;
                this.precoHosp = 400;
                break;

            default:
                this.maxHospedes = 0;
                this.precoHosp = 0;
                break;
        }
    }
    /**
     * Esse método será o responsável por adicionar os hospedes 
     * na List<Hospedes>
     * @param hospedes Lista dos hóspedes que serão adicionados no quarto
     */
    public void addHospedes(List<Hospede> hospedes){
        if(this.hospedes == null){
            this.hospedes = new ArrayList<Hospede>();
        }
        if(hospedes.size() > maxHospedes){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            this.hospedes = new ArrayList<Hospede>(hospedes);
        }
    }

    /**
     * Remove todos os hospedes que estão inseridos no quarto
     */
    public void desocuparHospedes(){
        this.hospedes = null;
    }
    /**
     * @return O preço de se hospedar naquele quarto
     */
    public float getPrecoHosp(){
        return this.precoHosp;
    }

    /**
     * @return A lista de hóspedes que estão no quarto
     */
    public List<Hospede> getHospedes(){
        return this.hospedes;
    }

    /**
     * @return A capacidade máxima de hóspedes do quarto
     */
    public int getMaxHospedes(){
        return this.maxHospedes;
    }

    /**
     * @return Uma versão comprimida do tipo de quarto
     */
    public String getTpQuarto() {
        String type = "";
        switch (this.tpQuarto) {
            case QUARTO_FAMILIA:
                type+= "QF";
                break;
        
            case QUARTO_CASAL:
                type+= "QC";
                break;
        
            case QUARTO_SOLTEIRO_DUPLO:
                type+= "QSD";
                break;
        
            case QUARTO_UNICO:
                type+= "QU";
                break;
        
            default:
                type += "N/A";
                break;
        }
        return type;
    }
    
    /**
     * @return O tipo do quarto e o nome dos hóspedes inseridos no quarto
     */
    @Override
    public String toString() {
        String resultado = String.format("Tipo do Quarto: %s \n", this.tpQuarto);
        if(this.hospedes == null){
            resultado += "Sem hóspedes no momento\n";
        }else{
            resultado += "Hóspesdes: ";
            int i = 0;
            for (Hospede hospede : this.hospedes) {
                if(i == this.hospedes.size() - 1){
                    resultado += hospede.getNome();
                }else{
                    resultado += hospede.getNome() + ", ";
                }
                i++;
            }
            resultado += "\n";
        }
        return resultado;
    }
}
