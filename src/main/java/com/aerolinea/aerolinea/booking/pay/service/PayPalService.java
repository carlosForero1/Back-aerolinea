package com.aerolinea.aerolinea.booking.pay.service;

import com.aerolinea.aerolinea.booking.pay.entity.PaymentEntity;
import com.aerolinea.aerolinea.booking.pay.repository.PaymentRepository;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PayPalService {

    @Autowired
    private APIContext apiContext;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws Exception {

        // Crear payment con PayPal
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = Collections.singletonList(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);


        String qrData = "PaymentID:" + createdPayment.getId() + ", SuccessURL:" + successUrl;
        String qrBase64 = QRCodeGenerator.generateQRCodeBase64(qrData, 200, 200);

        // Guardar en BD
        PaymentEntity entity = new PaymentEntity();
        entity.setPaymentId(createdPayment.getId());
        entity.setTotal(total);
        entity.setCurrency(currency);
        entity.setMethod(method);
        entity.setIntent(intent);
        entity.setDescription(description);
        entity.setCancelUrl(cancelUrl);
        entity.setSuccessUrl(successUrl);
        System.out.println();
        entity.setQrCodeBase64(qrBase64);
        System.out.println(qrBase64);

        paymentRepository.save(entity);

        return createdPayment;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }


}
