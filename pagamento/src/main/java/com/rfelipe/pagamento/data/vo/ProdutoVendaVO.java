package com.rfelipe.pagamento.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rfelipe.pagamento.model.ProdutoVenda;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "idProduto", "quantidade"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduto")
    private Long idProduto;

    @JsonProperty("quantidade")
    private Long quantidade;

    public static  ProdutoVendaVO create(ProdutoVenda produtoVenda){
        return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
    }

}
