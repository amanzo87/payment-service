//package it.interno.payment.producer;
//
//import it.interno.common.lib.model.OrderDto;
//import it.interno.common.lib.model.OrderFailedEvent;
//import it.interno.common.lib.model.OrderSuccessEvent;
//import org.springframework.beans.BeanUtils;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PaymentProducer {
//
//    private static final String TOPIC = "inventory-receive";
//    private final KafkaTemplate<String, OrderDto> kafkaTemplate;
//
//    public PaymentProducer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendToInventory(OrderDto orderDto) {
//
//        try{
//
//            if(Boolean.TRUE.equals(orderDto.getPagamentoEffettuato())) {
//                orderDto.setIdStato(3); //"PAGAMENTO ORDINE " + orderDto.getIdOrdine() +" EFFETTUATO CON SUCCESSO");
//                OrderSuccessEvent orderSuccessDto = new OrderSuccessEvent();
//                BeanUtils.copyProperties(orderDto, orderSuccessDto);
//                kafkaTemplate.send(TOPIC, orderSuccessDto);
//            }else{
//                orderDto.setIdStato(5); //("PAGAMENTO ORDINE " + orderDto.getIdOrdine() +" NON ANDATO A BUON FINE");
//                OrderFailedEvent orderFailureDto = new OrderFailedEvent() ;
//                BeanUtils.copyProperties(orderDto, orderFailureDto);
//                kafkaTemplate.send(TOPIC, orderFailureDto);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//}
