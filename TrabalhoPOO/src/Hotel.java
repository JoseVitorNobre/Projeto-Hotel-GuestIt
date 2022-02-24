import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * A classe Hotel representa um hotel com o nome “Guest It” irá 
 * administrar um conjunto de Hospedes(e suas classes filhas) e Quartos(sendo 
 * 20 quartos no total), onde nessa classe podemos adicionar hóspedes em 
 * seus quartos e fornecer serviços de quarto, como um bufê com comidas, 
 * serviços de limpeza e lavanderia, onde essas ações irão aumentar seu cachê. 
 * @author Alexson Almeida e José Vitor
 */
public class Hotel implements Serializable {

    //Atributos
    public static final String nomeHotel = "Guest It";
    public static final int qtdMaxQuartos = 20;
    private List<Hospede> hospedes;
    private List<Quarto> quartos;

    //Construtores
    /**
     * <p>Esse primeiro construtor irá servir para inicializar todos os valores 
     * da Lista de Hóspedes como nulo, e inicializar todos os quartos com 
     * Hóspedes nulos, porém os quartos já terão seus tipos já definidos, a 
     * ordem dos quartos são:</p>
     * <p>0 - 4 Quartos para casais(Capacidade máxima de 2 hóspedes)</p>
     * <p>5 - 9 Quarto para família(Capacidade máxima de 5 hóspedes)</p>
     * <p>10 - 14 Quarto solteiro duplo(Capacidade máxima de 2 hóspedes)</p>
     * 15 - 19 Quarto único(Capacidade máxima de 1 hóspede)
     */
    public Hotel() {
        this.hospedes = new ArrayList<Hospede>();
        for (int i = 0; i < qtdMaxQuartos; i++) {
            this.hospedes.add(null);
        }
        this.quartos = new ArrayList<Quarto>();
        for (int i = 0; i < qtdMaxQuartos; i++) {
            if(i < 5){
                this.quartos.add(new Quarto(TipoDeQuarto.QUARTO_CASAL));
            }else if(i < 10){
                this.quartos.add(new Quarto(TipoDeQuarto.QUARTO_FAMILIA));
            }else if(i < 15){
                this.quartos.add(new Quarto(TipoDeQuarto.QUARTO_SOLTEIRO_DUPLO));
            }else if(i < qtdMaxQuartos){
                this.quartos.add(new Quarto(TipoDeQuarto.QUARTO_UNICO));
            }
        }
    }

    /**
     * Aqui será inserido uma lista de hóspedes em um quarto, caso esse 
     * quarto já possua algum hóspede, os hóspedes apenas serão adicionados 
     * se a capacidade do quarto suportar, caso não tenha nenhum hóspede 
     * inserido no quarto, os hóspedes serão inseridos, novamente apenas se 
     * a capacidade do quarto suportar, e o primeiro hóspede da lista será o 
     * responsável pelo quarto, dessa forma ele será adicionado na lista 
     * hospedes e o cache daquele quarto e do serviço de quarto será 
     * atribuído apenas a ele.
     * @param hospedes Lista de hospedes que serão inseridos no quarto
     * @param numQuarto Número do quarto em que os hospedes serão inseridos
     */
    public void addHospedes(List<Hospede> hospedes, int numQuarto) {
        if(this.quartos.get(numQuarto).getHospedes() == null){
            this.quartos.get(numQuarto).addHospedes(hospedes);
            this.hospedes.set(numQuarto, hospedes.get(0));
            this.hospedes.get(numQuarto).addCache(this.quartos.get(numQuarto).getPrecoHosp());
            for (Hospede hospede : hospedes) {
                hospede.setNumQuarto(numQuarto);
            }
        }else{
            List<Hospede> Allhospedes = new ArrayList<Hospede>();
            for (Hospede hospede : this.quartos.get(numQuarto).getHospedes()) {
                Allhospedes.add(hospede);
            }
            for (Hospede hospede : hospedes) {
                Allhospedes.add(hospede);
            }
            for (Hospede hospede : Allhospedes) {
                hospede.setNumQuarto(numQuarto);
            }
            this.quartos.get(numQuarto).addHospedes(Allhospedes);
        }
    }
    
    /**
     * Aqui é onde será fornecido os serviços de bufê, faxina ou lavanderia, 
     * após executar esse método o cache do hóspede será incrementado dependendo 
     * do serviço requisitado.
     * @param numQuarto Número do quarto que requisitou o serviço de quarto
     * @param servicoQuarto Serviço de quarto a ser oferecido
     */
    public void servicoQuarto(int numQuarto, ServicoDeQuarto servicoQuarto) {
        this.hospedes.get(numQuarto).addCache(ServicoDeQuarto.precoServico(servicoQuarto));
    }

    /**
     * Aqui no desocupar iremos retornar o hóspede responsável pelo quarto, 
     * em seguida iremos remover todos os hóspedes que estavam contidos naquele 
     * quarto e depois remover o hóspede da lista hospedes(no caso iremos 
     * definir o valor onde o hóspede ocupava como nulo, pois se removermos 
     * usando o remove, iremos ter um quarto a menos e não é isso que queremos).
     * @param numQuarto Número do quarto ao qual o(s) hóspede(s) irão desocupar
     * @return Hóspede responavel pelo quarto
     */
    public Hospede desocupar(int numQuarto) {
        if(this.hospedes.get(numQuarto).equals(null)){
            throw new NullPointerException();
        }else{
            Hospede hospede = this.hospedes.get(numQuarto);
            this.hospedes.set(numQuarto, null);
            this.quartos.get(numQuarto).desocuparHospedes();
            return hospede;
        }
    }


    /**
     * Aqui será exibido os dados do hóspede de um quarto em específico.
     * @param numQuarto Número do quarto do hospede desejado
     * @return Hóspede responsavel pelo quarto
     */
    public Hospede getHospede(int numQuarto) {
        return this.hospedes.get(numQuarto);
    }

    /**
     * Aqui será exibido os dados do quarto, esses dados consistem no tipo 
     * do quarto e nos hóspedes que estão inseridos nele.
     * @param numQuarto Número do quarto que se deseja ver as informações
     * @return Quarto com as informações a respeito dele
     */
    public Quarto getQuarto(int numQuarto) {
        return this.quartos.get(numQuarto);
    }

    /**
     * Aqui serão exibidos todos os quartos e hóspedes, porém ficaria muito
     * poluído colocar todos os dados de todos os hóspedes e quartos de uma
     * vez só, dessa forma os hóspedes serão apenas listados pelo seu nome e 
     * o número do quarto e os quartos serão listados pelo tipo do quarto e 
     * se eles estão ou não ocupados.
     * @return Todos os hóspedes e quartos de maneira resumida
     */
    @Override
    public String toString() {
        String msg = "Hospedes:\n[";
        int i = 0;
        for (Hospede hospede : this.hospedes) {
            if(hospede == null){
                if(i == qtdMaxQuartos - 1){
                    msg += " - ";
                }else{
                    msg += " -";
                }
            }else{
                if(i == qtdMaxQuartos - 1){
                    msg += " " + i + ":" + hospede.getNome();
                }else{
                    msg += " " + i + ":" + hospede.getNome() + " ";
                }
            }
            i++;
        }
        i = 0;
        msg += "]\nQuartos:\n[";
        for (Quarto quarto : this.quartos) {
            if(quarto.getHospedes() == null){
                if(i == qtdMaxQuartos - 1){
                    msg += " " + quarto.getTpQuarto() + ":De ";
                }else{
                    msg += " " + quarto.getTpQuarto() + ":De";
                }
            }else{
                if(i == qtdMaxQuartos - 1){
                    msg += " " + quarto.getTpQuarto() + ":Oc ";
                }else{
                    msg += " " + quarto.getTpQuarto() + ":Oc";
                }
            }
            i++;
        }
        msg += "]";
        return msg;
    }
}
