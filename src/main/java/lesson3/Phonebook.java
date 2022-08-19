//Домашнее задание,уровень 2, урок 3: Владимир Греков
package lesson3;


import java.util.HashMap;

public class Phonebook {
    private HashMap<String, String> phonebook = new HashMap<>();

    public void recordPhonebook(String abonentName, String phoneNumber) {
        if (phonebook.containsKey(abonentName)){
            //аббоненты однофамильцы
            String value = phonebook.get(abonentName);
            if (value.contains(phoneNumber)){
                System.out.printf("Запись с абонентом: %s c № телефона: %s, уже была занесена в справочник раньше\n",abonentName, phonebook.get(abonentName));
            } else {
                String newValue = (phonebook.get(abonentName) + "; " + phoneNumber);
                phonebook.put(abonentName, newValue);
            }
        } else {
            phonebook.put(abonentName, phoneNumber);
        }
    }

    public void abonentPhoneNumber(String abonentName){
        //
        if (phonebook.containsKey(abonentName)) {
            if (phonebook.get(abonentName).contains(";")){
                String[] phones = phonebook.get(abonentName).split(";");
                for (String p: phones) {
                    System.out.printf("Абонент: %s, № телефона: %s%n", abonentName, p.trim());
                }
            } else {
                System.out.printf("Абонент: %s, № телефона: %s%n", abonentName, phonebook.get(abonentName));
            }
        }else {
            System.out.printf("Сведения о абоненте с фамилией: %s отсутствуют%n", abonentName);
        }
    }
    public boolean compareTo(String key, String phone){
        //сравнить номера телефонов
        return phonebook.get(key).equals(phone);
    }
    public void addPhoneNumber(String abonentName, String previousPhoneNumber, String addPhoneNumber){
        //добавить номер телефона аббоненту
        if (phonebook.containsKey(abonentName) && phonebook.get(abonentName).contains(previousPhoneNumber)) {
            if (phonebook.get(abonentName).contains(";")) {
                String[] phones = phonebook.get(abonentName).split(";");
                for (int i = 0; i < phones.length; i++) {
                    if (phones[i].contains(previousPhoneNumber)) {
                        phones[i] = phones[i].trim() + ", " + addPhoneNumber;
                        break;
                    }
                }
                String newValue = String.join(";", phones);
                phonebook.put(abonentName, newValue);
            } else {
                String newValue = (phonebook.get(abonentName) + ", " + addPhoneNumber);
                phonebook.put(abonentName, newValue);
            }
            System.out.printf("Телефон: %s добавлен абоненту: %s%n", addPhoneNumber, abonentName);
        } else {
            System.out.printf("Телефон: %s не значится у абонента: %s.%nПожалуйста, проверьте правильность указанного номера.%n", previousPhoneNumber, abonentName);
        }
    }
}
