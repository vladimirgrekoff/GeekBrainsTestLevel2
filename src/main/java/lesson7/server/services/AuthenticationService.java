//Домашнее задание,уровень 2, урок 7: Владимир Греков
package lesson7.server.services;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password);
}