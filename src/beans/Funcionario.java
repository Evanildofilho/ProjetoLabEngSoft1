/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

public class Funcionario {
    
    private int id_funcionario;
    private String nome_funcionario;
    private String cargo;

 

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id_funcionario=" + id_funcionario + ", nome_funcionario=" + nome_funcionario + ", cargo=" + cargo + '}';
    }
    
    
    public boolean equals(Object objeto){
        Funcionario f = (Funcionario) objeto;
        if(this.id_funcionario == f.getId_funcionario()){
            return true;
        }else{
            return false;
        }
    }
}
