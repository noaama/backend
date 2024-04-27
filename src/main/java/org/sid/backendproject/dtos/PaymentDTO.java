package org.sid.backendproject.dtos;

import jakarta.persistence.*;
import lombok.*;
import org.sid.backendproject.entities.PaymentStatus;
import org.sid.backendproject.entities.PaymentType;
import org.sid.backendproject.entities.Student;

import java.time.LocalDate;


@NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter @ToString

public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private  double amount;
    private PaymentType type;
    private PaymentStatus status;
    //private String file;
   //private Student student;

}
