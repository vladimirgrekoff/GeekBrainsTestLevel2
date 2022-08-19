package lesson8.server.services;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password);

    boolean addNewUserEntry(String loginReg, String passwordReg, String usernameReg);

    boolean isLoginBusy(String loginReg);

    boolean isUsernameBusy(String usernameReg);
}