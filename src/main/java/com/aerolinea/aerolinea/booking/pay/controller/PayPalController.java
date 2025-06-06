package com.aerolinea.aerolinea.booking.pay.controller;

import com.aerolinea.aerolinea.booking.pay.service.PayPalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
@CrossOrigin(origins = "http://localhost:3000")
public class PayPalController {

    @Autowired
    private PayPalService service;

    @PostMapping("/pay")
    public ResponseEntity<?> payment(@RequestBody Map<String, Object> data) {
        try {
            Double total = Double.parseDouble(data.get("total").toString());

            Payment payment = service.createPayment(
                    total,
                    "USD",
                    "paypal",
                    "sale",
                    "Pago de vuelo",
                    "http://localhost:4200/cancel",   // URL de cancelación
                    "http://localhost:8080/api/paypal/success"  // URL de éxito
            );

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return ResponseEntity.ok(link.getHref());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/success")
    public ResponseEntity<Map<String, Object>> simulateSuccess() {
        Map<String, Object> response = Map.of(
                "status", "success",
                "message", "El pago fue  exitosamente.",
                "redirectUrl", "http://localhost:3000/payment-success"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/failure")
    public ResponseEntity<Map<String, Object>> failure() {
        Map<String, Object> response = Map.of(
                "status", "failure",
                "message", "El pago fue rechazado debido a saldo insuficiente.",
                "reason", "saldo-insuficiente",
                "redirectUrl", "http://localhost:3000/payment-error?reason=saldo-insuficiente"
        );
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
    }

    @GetMapping("/failure/technical")
    public ResponseEntity<Map<String, Object>> technical() {
        Map<String, Object> response = Map.of(
                "status", "failure",
                "message", "El pago fue rechazado debido a errores tecnicos comuniquese con su administrador.",
                "reason", "saldo-insuficiente",
                "redirectUrl", "http://localhost:3000/payment-error?reason=error=tecnico"
        );
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
    }

}
