package org.sid.backendproject.repository;



import org.sid.backendproject.entities.Payment;
import org.sid.backendproject.entities.PaymentStatus;
import org.sid.backendproject.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
 List<Payment> findByStudentCode(String code);
 List<Payment> findByStatus(PaymentStatus status);
 List<Payment> findByType(PaymentType type);


}
