package meu.curso.microservicos.pedido.service;

import org.springframework.stereotype.Service;

import meu.curso.microservicos.pedido.model.ItemPedido;
import meu.curso.microservicos.pedido.model.Pedido;
import meu.curso.microservicos.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    // Injeção de dependência através do construtor
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Salvar pedidos -> por convenção, quando recebemos uma requisição do tipo POST em nossa API e salvamos um dado
    // no banco de dados, a gente sempre retorna o mesmo conteúdo do POST com o atributo ID para manipulação futura.
    public Pedido salvarPedido(Pedido pedido) {

        // Validando se a minha lista de itens não está vazia
        if (pedido.getItens() != null) {
            // Processando os ItemPedido antes de salvar no banco
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
            }
        }

        // Salvando o pedido no banco de dados
        return pedidoRepository.save(pedido);   
    }
    
}
