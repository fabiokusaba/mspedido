package meu.curso.microservicos.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import meu.curso.microservicos.pedido.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
