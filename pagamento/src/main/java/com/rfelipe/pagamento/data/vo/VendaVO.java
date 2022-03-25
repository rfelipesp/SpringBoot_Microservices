package com.rfelipe.pagamento.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rfelipe.pagamento.model.Produto;
import com.rfelipe.pagamento.model.ProdutoVenda;
import com.rfelipe.pagamento.model.Venda;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id","data", "produtos", "valorTotal"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
    private Date data;

    @JsonProperty("produtos")
    private List<ProdutoVendaVO> produtos;

    @JsonProperty("valorTotal")
    private Double valorTotal;

    public static VendaVO create(Venda venda){
        return new ModelMapper().map(venda, VendaVO.class);
    }

}



