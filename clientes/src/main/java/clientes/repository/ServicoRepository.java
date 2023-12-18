package clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clientes.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
