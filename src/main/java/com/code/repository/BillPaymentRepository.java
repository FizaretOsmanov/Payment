package com.code.repository;

import com.code.dto.response.billPayment.BillPaymentResponse;
import com.code.model.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillPaymentRepository extends JpaRepository<BillPayment, Long> {

}
