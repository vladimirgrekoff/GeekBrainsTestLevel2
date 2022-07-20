//Домашнее задание,уровень 2, урок 7: Владимир Греков
package lesson7.server.services.impl;

import lesson7.server.models.User;
import lesson7.server.services.AuthenticationService;

import java.util.List;

public class SimpleAuthenticationServiceImpl implements AuthenticationService {

    private static final List<User> clients = List.of(
            new User("martin", "1111", "Martin_Superstar"),
            new User("batman", "2222", "Брюс_Уэйн"),
            new User("gena", "3333", "Гендальф_Серый"),
            new User("mario", "4444", "Super_Mario"),
            new User("bender", "5555", "Bender"),
            new User("ezhik", "6666", "Super_Sonic")
    );

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User client : clients) {
            if (client.getLogin().equals(login) && client.getPassword().equals(password) ) {
                return client.getUsername();
            }
        }
        return null;
    }
}