package com.rfelipe.pagamento.service;

import com.rfelipe.pagamento.data.vo.VendaVO;
import com.rfelipe.pagamento.exception.ResourceNotFoundException;
import com.rfelipe.pagamento.model.ProdutoVenda;
import com.rfelipe.pagamento.model.Venda;
import com.rfelipe.pagamento.repository.ProdutoRepository;
import com.rfelipe.pagamento.repository.ProdutoVendaRepository;
import com.rfelipe.pagamento.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaVO create(VendaVO vendaVO){
        Venda venda = vendaRepository.save(Venda.create(vendaVO));

        List<ProdutoVenda> produtoSalvos = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda produtoVenda = ProdutoVenda.create(p);
            produtoVenda.setVenda(venda);
            produtoSalvos.add(produtoVendaRepository.save(produtoVenda));
        });
        venda.setProdutos(produtoSalvos);
        return VendaVO.create(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable){
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaVO);
    }

    public VendaVO findById(Long id){
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return VendaVO.create(entity);
    }

    public VendaVO update(VendaVO vendaVO){
        final Optional<Venda> optionalVenda = vendaRepository.findById(vendaVO.getId());

        if(!optionalVenda.isPresent()){
            new ResourceNotFoundException("No records found for this ID");
        }

        List<ProdutoVenda> produtoSalvos = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda produtoVenda = ProdutoVenda.create(p);
            produtoVenda.setVenda(optionalVenda.get());
            produtoSalvos.add(produtoVendaRepository.save(produtoVenda));
        });
        optionalVenda.get().setProdutos(produtoSalvos);

        return VendaVO.create(vendaRepository.save(Venda.create(vendaVO)));
    }

    public void delete(Long id){
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        vendaRepository.delete(entity);
    }

    private VendaVO convertToVendaVO(Venda venda) {
        return VendaVO.create(venda);
    }

}
