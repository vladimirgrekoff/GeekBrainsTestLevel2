package lesson8.server.services.impl;


import lesson8.server.services.AuthenticationService;

public class SqlAuthenticationServiceImpl implements AuthenticationService {
    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public boolean addNewUserEntry(String loginReg, String passwordReg, String usernameReg) {
        return false;
    }

    @Override
    public boolean isLoginBusy(String loginReg) {
        return false;
    }

    @Override
    public boolean isUsernameBusy(String usernameReg) {
        return false;
    }
}
