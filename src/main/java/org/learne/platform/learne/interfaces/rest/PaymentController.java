package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.queries.GetPaymentByStudentIdQuery;
import org.learne.platform.learne.domain.services.Payment.PaymentCommandService;
import org.learne.platform.learne.domain.services.Payment.PaymentQueryService;
import org.learne.platform.learne.interfaces.rest.resources.CreatePaymentResource;
import org.learne.platform.learne.interfaces.rest.resources.PaymentResource;
import org.learne.platform.learne.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import org.learne.platform.learne.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payments API")
public class PaymentController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        var payment = paymentCommandService.handle(createPaymentCommand);
        if(payment.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var createPayment = payment.get();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(createPayment);
        return new ResponseEntity<>(paymentResource, HttpStatus.CREATED);
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<PaymentResource> getPaymentByStudentId(@PathVariable Long studentId) {
        var payment = paymentQueryService.handle(new GetPaymentByStudentIdQuery(studentId));
        if (payment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var paymentEntity = payment.get();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(paymentEntity);
        return ResponseEntity.ok(paymentResource);
    }




}
