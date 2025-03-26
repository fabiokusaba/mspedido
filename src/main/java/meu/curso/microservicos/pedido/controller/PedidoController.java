package meu.curso.microservicos.pedido.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import meu.curso.microservicos.pedido.model.Pedido;
import meu.curso.microservicos.pedido.service.PedidoService;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    // Configuração RabbitMQ
    private final RabbitTemplate rabbitTemplate;

    private final PedidoService pedidoService;

    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    // Injeção de dependência através do construtor
    public PedidoController(PedidoService pedidoService, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    public String criarPedido(@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        rabbitTemplate.convertAndSend("", routingKey, pedidoSalvo);
        return "Pedido salvo e enviado para processamento: " + pedidoSalvo.getDescricao();
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }
    
}
