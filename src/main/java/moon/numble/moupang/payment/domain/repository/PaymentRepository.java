package moon.numble.moupang.payment.domain.repository;

import moon.numble.moupang.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
