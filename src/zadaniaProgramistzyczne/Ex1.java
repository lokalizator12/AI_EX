package zadaniaProgramistzyczne;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex1 {

    //TODO Napisz program implementujący algorytm generujący wszystkie permutacje n cyfr (nie można korzystać z gotowych funkcji generujących permutacje) w następujący sposób:
    //    1) aby wygenerować permutacje program musi pamiętać permutacje z poprzednich kroków (metoda rekurencyjna) [O],
    //    2) program generuje kolejną permutację wyłącznie na podstawie ostatnio wygenerowanej permutacji (np. generując je w porządku leksykograficznym).
    static List<String> newPermutations = new ArrayList<>();
    static List<String> oldPermutations = new ArrayList<>();
    static int i = 0, n;

    // Метод для генерации перестановок
    static void permutuj(int n) {
        if (i == 0) {
            // Начальная перестановка
            newPermutations.add("1");
            i++;
            permutuj(n);
        } else if (i < n) {
            oldPermutations = newPermutations; //1
            newPermutations = new ArrayList<>();

            for (int x = 0; x < (i + 1) * oldPermutations.size(); x++) {//2
                newPermutations.add(oldPermutations.get(x / (i + 1)));
                newPermutations.set(x, newPermutations.get(x).substring(0, x % (i + 1)) + (i + 1) + newPermutations.get(x).substring(x % (i + 1)));
            }
            i++;
            permutuj(n);
        }
    }

    // Метод для проверки перестановок
    static void sprawdz() {
        boolean pole;
        for (String perm : newPermutations) {
            pole = false;
            for (int z = 0; z < n; z++) {
                int currentValue = perm.charAt(z) - '0';  // Переводим символ в число
                for (int j = 1; j < perm.length() - z; j++) {
                    if ((currentValue + j == perm.charAt(j + z) - '0') || (currentValue - j == perm.charAt(j + z) - '0')) {
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
            }
        }
    }

    // Главный метод
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wpisz n: ");
        n = scanner.nextInt();
        permutuj(n);
        sprawdz();

        for (String perm : newPermutations) {
            System.out.println(perm);
        }
        scanner.close();
    }
}
