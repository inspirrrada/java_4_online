package ua.com.alevel.api.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.api.TransactionApiService;
import ua.com.alevel.model.TransactionForm;
import ua.com.alevel.model.UserFinanceModel;
import ua.com.alevel.model.UserModel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionApiServiceImpl implements TransactionApiService {

    @Value("${finmanager.backend.api.url}")
    private String apiUrl;

    @Override
    public Optional<TransactionForm> create(TransactionForm transactionForm, Long id, Long accountId) {
        RestTemplate restTemplate = new RestTemplate();
//        try {
//            ResponseEntity<TransactionForm> response = restTemplate
//                    .exchange(
//                            apiUrl + "/users/" + id + "/" + accountId + "/transaction",
//                            HttpMethod.POST,
//                            null,
//                            TransactionForm.class);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                TransactionForm transactionForm1 = response.getBody();
//                if (transactionForm1 != null) {
//                    return Optional.of(transactionForm1);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Optional.empty();
//        }
//        return Optional.empty();
        try{
        HttpEntity<TransactionForm> request = new HttpEntity<>(transactionForm);
        ResponseEntity<TransactionForm> response = restTemplate
                .exchange(
                        apiUrl + "/users/" + id + "/" + accountId + "/transaction",
                            HttpMethod.POST,
                            request,
                            TransactionForm.class);

        if (response.getStatusCode().is2xxSuccessful()) {
                TransactionForm transactionForm1 = response.getBody();
                if (transactionForm1 != null) {
                    return Optional.of(transactionForm1);
                }
//            return response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public String create2(TransactionForm transactionForm, Long accountId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        // Create the request body by wrapping
        // the object in HttpEntity
        try {
            HttpEntity<TransactionForm> request =
                    new HttpEntity<TransactionForm>(
                            transactionForm, requestHeaders);

            ResponseEntity<Object> productCreateResponse =
                    restTemplate
                            .exchange(
                                    apiUrl + "/users/" + accountId + "/transaction",
                                    HttpMethod.POST,
                                    request,
                                    Object.class);

            System.out.println(productCreateResponse);
            return productCreateResponse.getBody().toString();
        } catch (HttpStatusCodeException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "cool";
    }
}
