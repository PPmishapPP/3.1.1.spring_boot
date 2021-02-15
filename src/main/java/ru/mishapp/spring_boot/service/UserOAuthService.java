package ru.mishapp.spring_boot.service;

import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;



public interface UserOAuthService {
    RedirectView getRedirectView();
    String getJsonGoogleUser(String code, String state) throws InterruptedException, ExecutionException, IOException;
}
