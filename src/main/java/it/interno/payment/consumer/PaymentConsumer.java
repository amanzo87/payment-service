package it.interno.payment.consumer;

import it.interno.common.lib.model.OrderDto;
import it.interno.common.lib.util.Utility;
import it.interno.payment.entity.Payment;
import it.interno.payment.producer.PaymentProducer;
import it.interno.payment.repository.PaymentRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentConsumer {

    @Autowired
    private PaymentProducer paymentProducer;

    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics = "inventory-send", groupId = "payment-group")
    public void consumeInventorySend(ConsumerRecord<String, OrderDto> element) {

        OrderDto orderDto = element.value();

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
            }

            paymentProducer.sendToInventory(orderDto);

        } catch (Exception e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    private boolean simulaPagamento() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
