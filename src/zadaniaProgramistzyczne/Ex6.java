package zadaniaProgramistzyczne;

import java.util.Arrays;

public class Ex6 {

    //TODO Znajdź JEDNO z rozwiązań problemu 8 hetmanów za pomocą algorytmu przeszukiwania z nawrotami (wyświetl kolejne kroki algorytmu):
    // stosując jego najprostszą wersję (wybierając kolejne zmienne i wartości w porządku rosnącym) [O].
    static int[] queens = new int[8];
    static int backtrack = 1;

    public static void main(String[] args) {
        solveQueens();
        System.out.println("Ostateczne rozwiazanie:");
        for (int i = 0; i < 8; i++) {
            System.out.print(STR."\{queens[i]} ");
        }
        System.out.println();
        displaySolution(queens);
    }
    static void displaySolution(int[] queens) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (queens[j] == i) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    static void solveQueens() {
        for (int i = 0; i < 8; i += backtrack) {
            for (int j = 0; j < i; j++) {
                while (queens[i] == queens[j] || queens[i] == queens[j] + i - j || queens[i] == queens[j] - i + j) {
                    queens[i]++;
                    System.out.println(Arrays.toString(queens));
                    j = 0;

                    if (queens[i] >= 7) {
                        if (queens[i - 1] >= 7) {
                            queens[i - 1] = 0;
                            i--;
                        }
                        queens[i] = 0;
                        queens[i - 1]++;
                        backtrack = -1;
                        j = i;
                        break;
                    }
                }

                if (queens[i] != queens[j] && queens[i] != queens[j] + i - j && queens[i] != queens[j] - i + j) {
                    backtrack = 1;
                }
            }
        }
    }
}


