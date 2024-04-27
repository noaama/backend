package org.sid.backendproject;

import org.sid.backendproject.entities.Payment;
import org.sid.backendproject.entities.PaymentType;
import org.sid.backendproject.entities.Student;
import org.sid.backendproject.repository.PaymentRepository;
import org.sid.backendproject.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        PaymentRepository paymentRepository){
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                            .firstName("Mohamed").code("11223355").programId("SIDA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                            .firstName("Amari").code("11223366").programId("SIDA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                            .firstName("Noaman").code("11223377").programId("GLSID")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                            .firstName("Jasmine").code("11223388").programId("MDC")
                    .build());

            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();

            studentRepository.findAll().forEach(st -> {
                for (int i = 0; i < 10; i++) {
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(1000+(int) (Math.random()*20000))//cette function permet de ne pas avoir les virgules (int)
                            .type(paymentTypes[index])
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });

        };
    }

}
