package com.rfelipe.pagamento.model;

import com.rfelipe.pagamento.data.vo.ProdutoVendaVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto_venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoVenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10)
    private Long idProduto;

    @Column(name = "quantidade", nullable = false, length = 10)
    private Long quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public static ProdutoVenda create(ProdutoVendaVO produtoVendaVO){
        return new ModelMapper().map(produtoVendaVO, ProdutoVenda.class);
    }
}
