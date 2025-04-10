package it.interno.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.interno.common.lib.model.OrderDto;
import it.interno.payment.dto.ResponseDto;
import it.interno.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Gestisce la procedura di pagamento")
    @PostMapping(path = "/order")
    public ResponseEntity<ResponseDto<OrderDto>> processaPagamentoOrdine(@RequestBody OrderDto orderDto) {

        OrderDto dto = paymentService.processaPagamentoOrdine(orderDto);

        ResponseDto<OrderDto> response = new ResponseDto<>(
                HttpStatus.OK.value(), orderDto, null, null
        );

        return ResponseEntity.ok(response);
    }

}
