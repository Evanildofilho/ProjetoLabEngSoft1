/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;

public class Fornece {
    
    private Produto fk_cod_barra;
    private Fornecedor fk_cnpj;
    private Date data_compra;

    public Produto getFk_cod_barra() {
        return fk_cod_barra;
    }

    public void setFk_cod_barra(Produto fk_cod_barra) {
        this.fk_cod_barra = fk_cod_barra;
    }

    public Fornecedor getFk_cnpj() {
        return fk_cnpj;
    }

    public void setFk_cnpj(Fornecedor fk_cnpj) {
        this.fk_cnpj = fk_cnpj;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

   
    
}
