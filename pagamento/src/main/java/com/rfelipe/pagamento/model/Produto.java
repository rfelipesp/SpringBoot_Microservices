package com.rfelipe.pagamento.model;

import com.rfelipe.pagamento.data.vo.ProdutoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto create(ProdutoVO produtoVO) {
        return new ModelMapper().map(produtoVO, Produto.class);
    }

}
