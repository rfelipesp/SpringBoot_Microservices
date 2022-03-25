package com.rfelipe.pagamento.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rfelipe.pagamento.model.Produto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "estoque"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("estoque")
    private Integer estoque;

    public static  ProdutoVO create(Produto produto){
        return new ModelMapper().map(produto, ProdutoVO.class);
    }

}

