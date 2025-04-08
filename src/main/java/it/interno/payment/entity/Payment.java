package it.interno.payment.entity;

import it.interno.payment.entity.key.PaymentKey;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Entity
@Table(schema = "SHOP1", name = "PAYMENT")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(PaymentKey.class)
public class Payment implements Cloneable {

    @Id
    @Column(name = "ID_PAYMENT", columnDefinition = "INTEGER", nullable = false)
    Integer idPagamento ;

    @Id
    @Column(name = "TS_INSERIMENTO", columnDefinition = "TIMESTAMP", scale = 6, nullable = false)
    Timestamp tsInserimento;

    @Column(name = "ID_PAYMENT_TYPE", columnDefinition = "INTEGER", nullable = false)
    Integer idTipologiaPagamento;

    @Column(name = "ID_PAYMENT_RESULT", columnDefinition = "INTEGER", nullable = false)
    Integer idRisultatoPagamento;

    @Column(name = "ID_UTE_INS", columnDefinition = "CHAR", length = 8, nullable = false)
    String idUtenteInserimento;

    @Column(name = "TS_CANCELLAZIONE", columnDefinition = "TIMESTAMP", scale = 6)
    Timestamp tsCancellazione;

    @Column(name = "ID_UTE_CAN", columnDefinition = "CHAR", length = 8)
    String idUtenteCancellazione;

    @Column(name = "ID_ORDER", columnDefinition = "INTEGER")
    Integer idOrdine ;

    @Column(name = "TS_INSERIMENTO_ORDER", columnDefinition = "TIMESTAMP", scale = 6)
    Timestamp tsInserimentoOrdine;

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Timestamp getTsInserimento() {
        return tsInserimento;
    }

    public void setTsInserimento(Timestamp tsInserimento) {
        this.tsInserimento = tsInserimento;
    }

    public Integer getIdTipologiaPagamento() {
        return idTipologiaPagamento;
    }

    public void setIdTipologiaPagamento(Integer idTipologiaPagamento) {
        this.idTipologiaPagamento = idTipologiaPagamento;
    }

    public Integer getIdRisultatoPagamento() {
        return idRisultatoPagamento;
    }

    public void setIdRisultatoPagamento(Integer idRisultatoPagamento) {
        this.idRisultatoPagamento = idRisultatoPagamento;
    }

    public String getIdUtenteInserimento() {
        return idUtenteInserimento;
    }

    public void setIdUtenteInserimento(String idUtenteInserimento) {
        this.idUtenteInserimento = idUtenteInserimento;
    }

    public Timestamp getTsCancellazione() {
        return tsCancellazione;
    }

    public void setTsCancellazione(Timestamp tsCancellazione) {
        this.tsCancellazione = tsCancellazione;
    }

    public String getIdUtenteCancellazione() {
        return idUtenteCancellazione;
    }

    public void setIdUtenteCancellazione(String idUtenteCancellazione) {
        this.idUtenteCancellazione = idUtenteCancellazione;
    }

    public Integer getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Timestamp getTsInserimentoOrdine() {
        return tsInserimentoOrdine;
    }

    public void setTsInserimentoOrdine(Timestamp tsInserimentoOrdine) {
        this.tsInserimentoOrdine = tsInserimentoOrdine;
    }

    @Override
    public Payment clone() {
        try {
            Payment clone = (Payment) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
