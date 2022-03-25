package com.rfelipe.pagamento.repository;

import com.rfelipe.pagamento.model.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
