/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pagamento.pagamento.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 *
 * @author mogli
 */
@Entity
public class Cliente {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String nome;
    @Column(nullable = false)
    private double saldo;
    @OneToMany
    private List<Cartoes> cartoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cartoes> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartoes> cartoes) {
        this.cartoes = cartoes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", cartoes=" + cartoes + '}';
    }

}
