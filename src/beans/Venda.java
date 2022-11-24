package beans;

import java.sql.Date;

/**
*
* @author Evanildo
*/
public class Venda {

    private int id_venda;
    private Funcionario id_funcionario;
    private double valor_total;
    private Date data_venda;
    
    /**
    * Construtor
    */
    public Venda(){}

    /**
    * seta o valor de id_venda
    * @param pId_venda
    */
    public void setId_venda(int pId_venda){
        this.id_venda = pId_venda;
    }
    /**
    * @return pk_id_venda
    */
    public int getId_venda(){
        return this.id_venda;
    }
    
    public Funcionario getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Funcionario id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    /**
    * seta o valor de valor_total
    * @param pValor_total
    */
    public void setValor_total(double pValor_total){
        this.valor_total = pValor_total;
    }
    /**
    * @return valor_total
    */
    public double getValor_total(){
        return this.valor_total;
    }

    /**
    * seta o valor de data_venda
    * @param pData_venda
    */
    public void setData_venda(Date pData_venda){
        this.data_venda = pData_venda;
    }
    /**
    * @return data_venda
    */
    public Date getData_venda(){
        return this.data_venda;
    }

}