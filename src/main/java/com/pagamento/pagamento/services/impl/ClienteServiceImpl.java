/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pagamento.pagamento.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.pagamento.models.Cartoes;
import com.pagamento.pagamento.models.CartoesRepository;
import com.pagamento.pagamento.models.Cliente;
import com.pagamento.pagamento.models.ClienteRepository;
import com.pagamento.pagamento.services.ClienteService;

/**
 *
 * @author mogli
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CartoesRepository cartoesRepository;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Cliente> buscarTodos() {
		// Buscar todos os Clientes.
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// Buscar Cliente por ID.
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarCliente(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		// Buscar Cliente por ID, caso exista:
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarCliente(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Cliente por ID.
		clienteRepository.deleteById(id);
	}

	private void salvarCliente(Cliente cliente) {
		
        List<Cartoes> cartoes = cliente.getCartoes();

        for (Cartoes cartao : cartoes) {
            cartoesRepository.save(cartao);
        }
		clienteRepository.save(cliente);
	}

    @Override
    public void compraCredito(Long id, String numero, double valor) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        Cartoes cartaoBd = null;
        for (var cartao : clienteBd.get().getCartoes()) {
            if (cartao.getNumero().equals(numero)) {
                cartaoBd = cartao;
                break;
            }
        }
        if (clienteBd.isPresent() && cartaoBd != null) {
            Cliente cliente = clienteBd.get();
            if (cartaoBd.getLimite() >= valor) {
                cartaoBd.setLimite(cartaoBd.getLimite() - valor);
                cartoesRepository.save(cartaoBd);
                clienteRepository.save(cliente);
            } else {
                throw new RuntimeException("Limite insuficiente");
            }
            
        }else{
            throw new RuntimeException("Cliente ou cartão não encontrado");
        }
    }

    @Override
    public void compraDebito(Long id, String numero, double valor) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        Cartoes cartaoBd = null;
        for (var cartao : clienteBd.get().getCartoes()) {
            if (cartao.getNumero().equals(numero)) {
                cartaoBd = cartao;
                break;
            }
        }
        if (clienteBd.isPresent() && cartaoBd != null) {
            Cliente cliente = clienteBd.get();
            if (cliente.getSaldo() >= valor) {
                cliente.setSaldo(cliente.getSaldo() - valor);
                clienteRepository.save(cliente);
            } else {
                throw new RuntimeException("Saldo insuficiente");
            }
            
        }else{
            throw new RuntimeException("Cliente ou cartão não encontrado");
        }
    }

    @Override
    public void receberPagamento(Long id, double valor) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            Cliente cliente = clienteBd.get();
            cliente.setSaldo(cliente.getSaldo() + valor);
            clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    @Override
    public void pagarFatura(Long id, String numero, double valor) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        Optional<Cartoes> cartaoBd = cartoesRepository.findById(numero);
        if (clienteBd.isPresent() && cartaoBd.isPresent()) {
            Cliente cliente = clienteBd.get();
            Cartoes cartao = cartaoBd.get();
            if (cliente.getSaldo() >= valor) {
                cliente.setSaldo(cliente.getSaldo() - valor);
                cartao.setLimite(cartao.getLimite() + valor);
                clienteRepository.save(cliente);
                cartoesRepository.save(cartao);
            } else {
                throw new RuntimeException("Saldo insuficiente para pagar a fatura");
            }
        } else {
            throw new RuntimeException("Cliente ou cartão não encontrado");
        }
    }

}
