package zadaniaProgramistzyczne;

import java.util.Random;

public class Ex4 {

    //TODO Znajdź JEDNO z rozwiązań problemu 8 hetmanów za pomocą wybranego algorytmu przeszukiwania lokalnego (np. steepest-ascent/descent) [O].*/

    private static final Random random = new Random();

    // Генерация случайной доски
    private static int[] generateRandomBoard(int n) {
        int[] board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = random.nextInt(n);
        }
        return board;
    }

    // Подсчёт конфликтов на доске
    private static int countConflicts(int[] board) {
        int conflicts = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] == board[j] || Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    // Перемещение ферзя
    private static void moveQueen(int[] board, int column, int newRow) {
        board[column] = newRow;
    }

    // Метод локального поиска
    private static int[] localSearch(int n) {
        int[] currentBoard = generateRandomBoard(n);
        int currentConflicts = countConflicts(currentBoard);

        while (currentConflicts > 0) {
            int[] bestBoard = currentBoard.clone();
            int bestConflicts = currentConflicts;

            for (int col = 0; col < n; col++) {
                for (int row = 0; row < n; row++) {
                    if (currentBoard[col] != row) {
                        moveQueen(currentBoard, col, row);
                        int conflicts = countConflicts(currentBoard);

                        if (conflicts < bestConflicts) {
                            bestBoard = currentBoard.clone();
                            bestConflicts = conflicts;
                        }

                        moveQueen(currentBoard, col, bestBoard[col]);
                    }
                }
            }

            if (bestConflicts >= currentConflicts) {
                break;
            }

            currentBoard = bestBoard.clone();
            currentConflicts = bestConflicts;
        }

        return currentBoard;
    }

    // Метод для отображения решения на шахматной доске
    private static void displaySolution(int[] board) {
        int n = board.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[col] == row) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Главный метод
    public static void main(String[] args) {
        int n = 8;  // Размер доски
        int[] solution = localSearch(n);
        System.out.println("Rozwiązanie:");
        displaySolution(solution);
    }
}


/*Начать с первой строки (row = 0) и попытаться разместить ферзя в каждом столбце этой строки.
Проверить, не создаёт ли это расположение конфликты с ранее размещёнными ферзями.
Если конфликтов нет, перейти к следующей строке и повторить процесс.
Если конфликт есть или все столбцы текущей строки испробованы безуспешно, вернуться на предыдущую строку и переместить ферзя в следующий столбец.
Продолжать этот процесс, пока не будет найдено решение или не будут исчерпаны все возможности.*/