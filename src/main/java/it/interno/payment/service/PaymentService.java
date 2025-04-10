package it.interno.payment.service;

import it.interno.common.lib.model.OrderDto;

public interface PaymentService {

    OrderDto processaPagamentoOrdine(OrderDto orderDto);

}
