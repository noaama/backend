package org.sid.backendproject.web;

import org.sid.backendproject.entities.Payment;
import org.sid.backendproject.entities.PaymentStatus;
import org.sid.backendproject.entities.PaymentType;
import org.sid.backendproject.entities.Student;
import org.sid.backendproject.repository.PaymentRepository;
import org.sid.backendproject.repository.StudentRepository;
import org.sid.backendproject.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private PaymentService paymentService;
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentRestController(PaymentService paymentService, StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    @GetMapping(path="/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping(path="/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping(path=" /payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }
    @GetMapping(path=" /payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }
    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
      return paymentRepository.findById(id).get();
    }
    @GetMapping(path = "/students")
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }
    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code){

    return studentRepository.findByCode(code);

    }
    @GetMapping("/studentsByProgramId")
    public  List<Student> getStudentsByProgramId(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }
    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        return  paymentService.updatePaymentStatus(status,id);
    }
@PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date,
                               double amount ,
                               PaymentType type
                             , String studentcode) throws IOException {

            return  this.paymentService.savePayment(file,date , amount,type,studentcode);

}
        @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
        public byte[] getPaymentFile(@PathVariable  Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
    }
}
