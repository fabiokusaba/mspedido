package meu.curso.microservicos.pedido.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import meu.curso.microservicos.pedido.model.Pedido;
import meu.curso.microservicos.pedido.service.PedidoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    // Injeção de dependência através do construtor
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    public String criarPedido(@RequestBody Pedido pedido) {
        pedidoService.salvarPedido(pedido);
        return "Pedido salvo e enviado para processamento: " + pedido.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }
    
}
