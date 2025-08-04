/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.pagamento.pagamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamento.pagamento.models.Cliente;
import com.pagamento.pagamento.services.ClienteService;

/**
 *
 * @author mogli
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {
    @Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
		clienteService.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}

    // Métodos adicionais para compra, pagamento, etc.
    @PostMapping("/{id}/compra/credito/{numero}/{valor}")
    public ResponseEntity<Void> compraCredito(@PathVariable Long id, @PathVariable String numero, @PathVariable double valor) {
        clienteService.compraCredito(id, numero, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/compra/debito/{numero}/{valor}")
    public ResponseEntity<Void> compraDebito(@PathVariable Long id, @PathVariable String numero, @PathVariable double valor) {
        clienteService.compraDebito(id, numero, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/receberPagamento/{valor}")
    public ResponseEntity<Void> receberPagamento(@PathVariable Long id, @PathVariable double valor) {
        clienteService.receberPagamento(id, valor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pagarFatura/{numero}/{valor}")
    public ResponseEntity<Void> pagarFatura(@PathVariable Long id, @PathVariable String numero, @PathVariable double valor) {
        clienteService.pagarFatura(id, numero, valor);
        return ResponseEntity.ok().build();
    }
}
