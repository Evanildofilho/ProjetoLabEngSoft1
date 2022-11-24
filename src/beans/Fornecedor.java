/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

public class Fornecedor {
    
    private String cnpj;
    private Funcionario fk_id_funcionario;
    private String nomeFantasia;
    private String telefone;
    private String email;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Funcionario getFk_id_funcionario() {
        return fk_id_funcionario;
    }

    public void setFk_id_funcionario(Funcionario fk_id_funcionario) {
        this.fk_id_funcionario = fk_id_funcionario;
    }
    
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "nomeFantasia=" + nomeFantasia + '}';
    }
   
    
}
