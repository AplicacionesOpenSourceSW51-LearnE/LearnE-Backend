package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.commands.DeletePaymentCommand;
import org.learne.platform.learne.domain.model.queries.GetPaymentByStudentIdQuery;
import org.learne.platform.learne.domain.services.Payment.PaymentCommandService;
import org.learne.platform.learne.domain.services.Payment.PaymentQueryService;
import org.learne.platform.learne.interfaces.rest.resources.Payment.CreatePaymentResource;
import org.learne.platform.learne.interfaces.rest.resources.Payment.PaymentResource;
import org.learne.platform.learne.interfaces.rest.resources.Payment.UpdatePaymentResource;
import org.learne.platform.learne.interfaces.rest.transform.Payment.CreatePaymentCommandFromResourceAssembler;
import org.learne.platform.learne.interfaces.rest.transform.Payment.PaymentResourceFromEntityAssembler;
import org.learne.platform.learne.interfaces.rest.transform.Payment.UpdatePaymentCommandFromResourceAssembler;
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

    @Operation(
            summary = "Update a User Payment Method Information",
            description = "Updates a User Payment Method Information using the payment ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment Information Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PutMapping("/updatePaymentMethod/{paymentId}")
    public ResponseEntity<PaymentResource> updatePayment(@PathVariable Long paymentId, @RequestBody UpdatePaymentResource resource) {
        var updatePaymentCommand = UpdatePaymentCommandFromResourceAssembler.toCommandFromResource(paymentId, resource);
        var updatedPayment = paymentCommandService.handle(updatePaymentCommand);
        if (updatedPayment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updatedPaymentEntity = updatedPayment.get();
        var updatedPaymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(updatedPaymentEntity);
        return ResponseEntity.ok(updatedPaymentResource);
    }

    @Operation(
            summary = "Delete a User Payment Method",
            description = "Deletes a User Payment Method Information using the payment ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment Deleted Successfully "),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @DeleteMapping("/deletePaymentMethod/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable Long paymentId) {
        var deletePaymentCommand = new DeletePaymentCommand(paymentId);
        paymentCommandService.handle(deletePaymentCommand);
        return ResponseEntity.ok("Payment method deleted successfully");
    }
}
