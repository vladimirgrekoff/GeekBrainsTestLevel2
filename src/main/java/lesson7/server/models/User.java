//Домашнее задание,уровень 2, урок 7: Владимир Греков
package lesson7.server.models;

import lombok.Data;

@Data
public class User {
    private final String login;
    private final String password;
    private final String username;

}
