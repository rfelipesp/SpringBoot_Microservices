package com.rfelipe.pagamento.repository;

import com.rfelipe.pagamento.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository  extends JpaRepository<Venda, Long> {
}
