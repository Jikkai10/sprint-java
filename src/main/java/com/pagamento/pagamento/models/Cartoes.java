/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pagamento.pagamento.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;



/**
 *
 * @author mogli
 */
@Entity
public class Cartoes {
    @Id
    private String numero;
    @Column(nullable = false)
    private double limite;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    @Override
    public String toString() {
        return "Cartoes{" + "numero=" + numero + ", limite=" + limite + '}';
    }

}
