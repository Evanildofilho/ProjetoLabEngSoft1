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
public class Produto {
    
    private String cod_barra;
    private Funcionario fk_id_funcionario;
    private String nome_produto;
    private double valor_produto;
    private int quantidade_produto;

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    public Funcionario getFk_id_funcionario() {
        return fk_id_funcionario;
    }

    public void setFk_id_funcionario(Funcionario fk_id_funcionario) {
        this.fk_id_funcionario = fk_id_funcionario;
    }


    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public double getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(double valor_produto) {
        this.valor_produto = valor_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }
    
    
    public String toString(){
        return this.cod_barra;
    }
    
    public boolean equals(Object objeto){
        Produto p = (Produto) objeto;
        return this.cod_barra.equals(p.getCod_barra());
    }
    
}
