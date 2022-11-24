/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author evani
 */
public class Vende_Produto {
    
    private Produto fk_cod_barra;
    private Venda fk_id_venda;

    public Produto getFk_cod_barra() {
        return fk_cod_barra;
    }

    public void setFk_cod_barra(Produto fk_cod_barra) {
        this.fk_cod_barra = fk_cod_barra;
    }

    public Venda getFk_id_venda() {
        return fk_id_venda;
    }

    public void setFk_id_venda(Venda fk_id_venda) {
        this.fk_id_venda = fk_id_venda;
    }
    
}
