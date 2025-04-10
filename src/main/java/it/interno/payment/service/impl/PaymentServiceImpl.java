package it.interno.payment.service.impl;

import it.interno.common.lib.model.OrderDto;
import it.interno.common.lib.util.Utility;
import it.interno.payment.entity.Payment;
import it.interno.payment.repository.PaymentRepository;
import it.interno.payment.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    @Override
    public OrderDto processaPagamentoOrdine(OrderDto orderDto) {

        try{

            orderDto.setPagamentoEffettuato( simulaPagamento() ); // 0 = Pagamento OK, 1 = Pagamento KO

            if(Boolean.TRUE.equals(orderDto.getPagamentoEffettuato())) {

                Payment payment = new Payment();

                payment.setIdPagamento( paymentRepository.getMaxIdPagamento() );
                payment.setTsInserimento(Utility.getTimestamp());
                payment.setIdUtenteInserimento(orderDto.getIdUteIns());
                payment.setIdRisultatoPagamento(0);
                payment.setIdTipologiaPagamento(orderDto.getIdTipologiaPagamento());
                payment.setIdOrdine(orderDto.getIdOrdine());
                payment.setTsInserimentoOrdine( Utility.convertStringToTimestamp(orderDto.getTsInserimento()) );

                paymentRepository.save(payment);

                orderDto.setPagamentoEffettuato(Boolean.TRUE);
                orderDto.setIdStato(3);
            }else{
                orderDto.setIdStato(5);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderDto;
    }

    private boolean simulaPagamento() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
