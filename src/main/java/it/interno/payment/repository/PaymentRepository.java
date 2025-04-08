package it.interno.payment.repository;

import it.interno.payment.entity.Payment;
import it.interno.payment.entity.key.PaymentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentKey> {

    @Query(value =
            "SELECT COALESCE(MAX(ID_PAYMENT), 0) + 1 AS ID_PAYMENT " +
            "FROM SHOP1.PAYMENT ", nativeQuery = true)
    int getMaxIdPagamento();

}
