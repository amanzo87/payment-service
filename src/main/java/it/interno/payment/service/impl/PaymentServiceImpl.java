package it.interno.payment.service.impl;

import it.interno.common.lib.model.OrderDto;
import it.interno.common.lib.model.PaymentDto;
import it.interno.common.lib.model.ProductDto;
import it.interno.common.lib.model.ShippingDto;
import it.interno.common.lib.util.Utility;
import it.interno.payment.entity.Payment;
import it.interno.payment.entity.key.PaymentKey;
import it.interno.payment.exception.PaymentFallbackException;
import it.interno.payment.mapper.PaymentMapper;
import it.interno.payment.repository.PaymentRepository;
import it.interno.payment.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Transactional
    @Override
    public OrderDto processaPagamentoOrdine(OrderDto orderDto) {

//        try{

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

                orderDto.setPaymentDto( paymentMapper.toDto(payment) );
                orderDto.setPagamentoEffettuato(Boolean.TRUE);
                orderDto.setIdStato(3);
            }else{
                throw new PaymentFallbackException("PAGAMENTO NON ANDATO A BUON FINE");
                //orderDto.setIdStato(5);
            }

//        } catch (Exception e) {
//            throw e;
//            //e.printStackTrace();
//            //throw new PaymentFallbackException(e.getMessage());
//        }

        return orderDto;
    }

    private boolean simulaPagamento() {
        Random random = new Random();
        return random.nextBoolean();
    }

    @Transactional
    @Override
    public void fallimentoOrdine(OrderDto orderDto) {

        PaymentDto paymentDto = orderDto.getPaymentDto();

        PaymentKey paymentKey = new PaymentKey();

        paymentKey.setIdPagamento(paymentDto.getIdPagamento());
        paymentKey.setTsInserimento(Utility.convertStringToTimestamp(paymentDto.getTsInserimento()));

        paymentRepository.deleteById(paymentKey);

    }

}
