//Домашнее задание,уровень 2, урок 3: Владимир Греков
package lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //////////////////////////СПИСОК УНИКАЛЬНЫХ СЛОВ//////////////////////////////////////
        String[] arrayAnimals = {"Кот", "Собака", "Слон", "Бегемот", "Носорог", "Кот", "Слон", "Бегемот", "Собака", "Бегемот", "Собака","Бегемот", "Кот", "Слон", "Кот","Слон", "Бегемот", "Жираф", "Антилопа"};

        duplicateWordCount(arrayAnimals); //подсчет повторов слов
        System.out.println();



        //////////////////////////ТЕЛЕФОННЫЙ СПРАВОЧНИК///////////////////////////////////////

        Phonebook pb = new Phonebook();

        String[][] arrayAbonents = {{"Иванов", "89139156324"},{"Иванов", "89008156324"},{"Сидоров", "89019111320"},{"Сергеев", "89009145327, 861717"},{"Иванов", "89118156324"},{"Антонов", "89059145377, 89139114124"},{"Александров", "89279145337"},{"Петров", "89238114024"}};

        recordAbonents(pb, arrayAbonents);//запись аббонентов в справочник

        String[] arrayNameAbonents = {"Петров","Сидоров","Борис Джонсон","Иванов","Сергеевич","Антонов","Александров"};

        System.out.println("Запрос одного абонента:");
        pb.abonentPhoneNumber("Александров");
        System.out.println();

        System.out.println("Запрос абонентов по списку:");
        for (int i = 0; i < arrayNameAbonents.length; i++) {
            pb.abonentPhoneNumber(arrayNameAbonents[i]);
        }
        System.out.println();

        System.out.println("Добавить еще № телефона абоненту по предыдущему номеру:");
        pb.addPhoneNumber("Иванов", "89008156324", "232323");
        System.out.println("Проверка сведений о №№ телефонов абонента");
        pb.abonentPhoneNumber("Иванов");


    }

    private static void recordAbonents(Phonebook pb, String[][] arrayAbonents) {
        //запись аббонентов в телефонный справочник
        for (int i = 0; i < arrayAbonents.length; i++) {
            pb.recordPhonebook(arrayAbonents[i][0], arrayAbonents[i][1]);
        }
    }
    private static void duplicateWordCount(String[] arrayAnimals) {
        //подсчет повторов слов
        ArrayList<String> animalsList = new ArrayList<>(Arrays.asList(arrayAnimals));
        LinkedList<String> listUniqueName = new LinkedList<>();
        String tempString;

        System.out.println(animalsList);

        for (int i = 0; i < animalsList.size(); i++) {
            int count = 0;
            for (int j = 0; j < animalsList.size(); j++) {
                if (animalsList.get(i).equals(animalsList.get(j))) {
                    count++;
                }
            }
            tempString = "\nКоличество слов " + animalsList.get(i) + ": " + count;

            if (!listUniqueName.contains(tempString)) {
                listUniqueName.add(tempString);
            }
        }
        Collections.sort(listUniqueName);
        System.out.println(listUniqueName);
    }
}
