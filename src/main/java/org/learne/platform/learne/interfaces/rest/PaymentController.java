package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Saves a Payment Method",
            description = "Saves a Payment Method for a certain User identified by their studentId"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment Method Saved for User"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping("/savePaymentMethod")
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

    @Operation(
            summary = "Retrieve a User Payment Method Information",
            description = "Retrieves a User Payment Method Information using their studentId"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment Method Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @GetMapping("/getPaymentByStudentId/{studentId}")
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
