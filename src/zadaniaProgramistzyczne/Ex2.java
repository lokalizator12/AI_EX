package zadaniaProgramistzyczne;

import java.util.ArrayList;
import java.util.List;

public class Ex2 {
    //TODO Napisz program sprawdzający, które permutacje 8 cyfr są rozwiązaniem problemu
    // 8-hetmanów tj. sprawdź dla każdej permutacji wygenerowanej w zad. 1 czy jest ona rozwiązaniem,
    // wyświetl wszystkie permutacje będące rozwiązaniami [O]. Przedstaw rozwiązania graficznie na
    // szachownicy. Spróbuj też znaleźć rozwiązania problemu n-hetmanów (n = 4,...,10).


    static List<String> newPermutations = new ArrayList<>();
    static List<String> oldPermutations = new ArrayList<>();
    static int i = 0;

    static void permutuj(int n) {
        if (i == 0) {
            // начальная перестановка
            newPermutations.add("1");
            i++;
            permutuj(n);
        } else if (i < n) {
            // сохранение текущих перестановок
            oldPermutations = newPermutations;
            newPermutations = new ArrayList<>();

            // генерация новых перестановок на основе предыдущих
            for (int x = 0; x < (i + 1) * oldPermutations.size(); x++) {
                // Создаем новую перестановку, вставляя (i + 1) в каждую позицию
                newPermutations.add(oldPermutations.get(x / (i + 1)));
                newPermutations.set(x, newPermutations.get(x).substring(0, x % (i + 1)) + (i + 1) + newPermutations.get(x).substring(x % (i + 1)));
            }
            i++;
            permutuj(n);
        }
    }

    static void sprawdz(int n) {
        int licznik = 0;
        boolean pole;
        for (String perm : newPermutations) {
            pole = false;
            for (int z = 0; z < n; z++) { // Перебор элементов перестановки

                int currentValue = perm.charAt(z) - '0'; // Переводим символ в число
                for (int j = 1; j < perm.length() - z; j++) {
                    // Проверка на диагональные конфликты
                    if (currentValue + j == perm.charAt(j + z) - '0' || currentValue - j == perm.charAt(j + z) - '0') {
                        pole = true;
                        break;
                    }
                }
                if (pole) {
                    break;
                }
            }
            if (!pole) {
                System.out.println(perm);
                displaySolution(perm);
                licznik++;
            }
        }
        System.out.println(STR."Ilosc rozwiazan: \{licznik}");
    }

    static void displaySolution(String perm) {
        int n = perm.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (perm.charAt(i) - '1' == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main() {
        permutuj(8);
        sprawdz(8);
    }
}
