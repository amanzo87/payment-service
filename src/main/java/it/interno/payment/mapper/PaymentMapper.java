package it.interno.payment.mapper;

import it.interno.common.lib.model.PaymentDto;
import it.interno.payment.entity.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "tsInserimento", expression = "java(paymentDto.getTsInserimento() != null ? it.interno.common.lib.util.Utility.convertStringToTimestamp(paymentDto.getTsInserimento()) : null)")
    @Mapping(target = "tsCancellazione", expression = "java(paymentDto.getTsCancellazione() != null ? it.interno.common.lib.util.Utility.convertStringToTimestamp(paymentDto.getTsCancellazione()) : null)")
    Payment toEntity(PaymentDto paymentDto);

    @InheritInverseConfiguration
    @Mapping(target = "tsInserimento", expression = "java(payment.getTsInserimento() != null ? it.interno.common.lib.util.Utility.convertTimestampToString(payment.getTsInserimento()) : null)")
    @Mapping(target = "tsCancellazione", expression = "java(payment.getTsCancellazione() != null ? it.interno.common.lib.util.Utility.convertTimestampToString(payment.getTsCancellazione()) : null)")
    PaymentDto toDto(Payment payment);

}
