package ua.com.alevel.api.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.model.AccountModel;
import ua.com.alevel.model.AccountStatementModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountApiServiceImpl implements AccountApiService {

    @Value("${finmanager.backend.api.url}")
    private String apiUrl;

    @Override
    public Collection<AccountModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccountModel[]> response = restTemplate.exchange(
                apiUrl + "/accounts",
                HttpMethod.GET,
                null,
                AccountModel[].class
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            AccountModel[] authorModels = response.getBody();
            if (authorModels != null) {
                return List.of(authorModels);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<AccountModel> findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<AccountModel> response = restTemplate.exchange(
                    apiUrl + "/accounts/" + id,
                    HttpMethod.GET,
                    null,
                    AccountModel.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                AccountModel accountModel = response.getBody();
                if (accountModel != null) {
                    return Optional.of(accountModel);
                }
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccountModel> findByAccountNumber(String accountNumber) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<AccountModel> response = restTemplate.exchange(
                    apiUrl + "/accounts/" + accountNumber,
                    HttpMethod.GET,
                    null,
                    AccountModel.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                AccountModel accountModel = response.getBody();
                if (accountModel != null) {
                    return Optional.of(accountModel);
                }
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<AccountStatementModel> getAccountStatement(Long accountId) {
        RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<AccountStatementModel[]> response = restTemplate.exchange(
                    apiUrl + "/users/statement/" + accountId,
                    HttpMethod.GET,
                    null,
                    AccountStatementModel[].class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                AccountStatementModel[] accountModel = response.getBody();
                if (accountModel != null) {
                    return List.of(accountModel);
                }
            }
        return Collections.emptyList();
    }
}
