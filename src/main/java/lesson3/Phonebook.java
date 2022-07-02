//Домашнее задание,уровень 2, урок 3: Владимир Греков
package lesson3;

import java.util.HashMap;

public class Phonebook {
    private HashMap<String, String> phonebook = new HashMap<>();

    public void recordPhonebook(String abonentName, String phoneNumber) {
        phonebook.put(abonentName,phoneNumber);
    }

    public void abonentPhoneNumber(String abonentName){
        //
        if (phonebook.containsKey(abonentName)) {
            System.out.printf("Абонент: %s\t № телефона: %s%n", abonentName, phonebook.get(abonentName));
        }else {
            System.out.printf("Сведения о абоненте с фамилией: %s отсутствуют %n", abonentName);
        }
    }
    public boolean compareTo(String key, String phone){
        //сравнить номера телефонов
        return phonebook.get(key).equals(phone);
    }
    public void addPhoneNumber(String abonentName, String addPhoneNumber){
        //добавить номер телефона аббоненту
        if (phonebook.containsKey(abonentName)) {
            String newValue = (phonebook.get(abonentName) + ", " + addPhoneNumber);
            phonebook.put(abonentName, newValue);
            System.out.printf("Телефон: %s добавлен абоненту: %s%n", addPhoneNumber, abonentName);
        }
    }
}

