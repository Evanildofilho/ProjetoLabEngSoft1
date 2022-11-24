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
public class Validade {
    
    private Produto fk_cod_barra;
    private String lote;
    private String validade;

    public Produto getfk_Cod_barra() {
        return fk_cod_barra;
    }

    public void setfk_Cod_barra(Produto fk_cod_barra) {
        this.fk_cod_barra = fk_cod_barra;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

  

    
}
