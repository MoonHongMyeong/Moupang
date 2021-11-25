package moon.numble.moupang.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.payment.domain.entity.Payment;
import moon.numble.moupang.payment.domain.entity.PaymentType;

@Getter
@NoArgsConstructor
public class PaymentResponseDto {

    private Long paymentId;

    private ProductOrder order;

    private double paymentPrice;

    private double ShippingFee;

    private double totalPrice;

    private PaymentType paymentType;

    @Builder
    public PaymentResponseDto(Long paymentId, ProductOrder order, double paymentPrice, double shippingFee, PaymentType paymentType, double totalPrice) {
        this.paymentId = paymentId;
        this.order = order;
        this.paymentPrice = paymentPrice;
        this.ShippingFee = shippingFee;
        this.paymentType = paymentType;
        this.totalPrice = totalPrice;
    }

    public static PaymentResponseDto of(Payment payment){
        return PaymentResponseDto.builder()
                .paymentId(payment.getId())
                .order(payment.getOrder())
                .paymentPrice(payment.getOrder().getTotalPrice())
                .shippingFee(payment.getOrder().getTotalFee())
                .totalPrice(payment.getOrder().getTotalPrice() + payment.getOrder().getTotalFee())
                .paymentType(payment.getPaymentType())
                .build();
    }
}
