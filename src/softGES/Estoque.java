/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softGES;

import conexao.Conexao;

/**
 *
 * @author evani
 */
public class Estoque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Conexao conexao = new Conexao();
    	conexao.getConexao();
    	System.out.println("conexão aceita");
        
    }
    
}
