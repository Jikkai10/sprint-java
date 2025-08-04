/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pagamento.pagamento.services;

import com.pagamento.pagamento.models.Cliente;

/**
 *
 * @author mogli
 */
public interface ClienteService {

	Iterable<Cliente> buscarTodos();

	Cliente buscarPorId(Long id);

	void inserir(Cliente cliente);

	void atualizar(Long id, Cliente cliente);

	void deletar(Long id);

    void compraCredito(Long id, String numero, double valor);

    void compraDebito(Long id, String numero, double valor);

    void receberPagamento(Long id, double valor);
    
    void pagarFatura(Long id, String numero, double valor);

}
