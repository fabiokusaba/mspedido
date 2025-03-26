package meu.curso.microservicos.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import meu.curso.microservicos.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
