package ru.mishapp.spring_boot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;
import ru.mishapp.spring_boot.service.UserOAuthService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class LoginController {

    final UserOAuthService userOAuthService;

    @Autowired
    public LoginController(UserOAuthService userOAuthService) {
        this.userOAuthService = userOAuthService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/oauth2/authorization/google")
    public RedirectView oauth2() {
        return userOAuthService.getRedirectView();
    }

    @GetMapping(value = "/oauth2/code/google", produces = { "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String googleCallback(@RequestParam("code") String code,
                                 @RequestParam("state") String state) throws InterruptedException, ExecutionException, IOException {
        return userOAuthService.getJsonGoogleUser(code, state);
    }

}
