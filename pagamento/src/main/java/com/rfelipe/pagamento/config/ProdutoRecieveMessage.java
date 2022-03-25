package com.rfelipe.pagamento.config;

import com.rfelipe.pagamento.data.vo.ProdutoVO;
import com.rfelipe.pagamento.model.Produto;
import com.rfelipe.pagamento.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRecieveMessage {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoRecieveMessage(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoVO produtoVO){
        produtoRepository.save(Produto.create(produtoVO));
    }

}
