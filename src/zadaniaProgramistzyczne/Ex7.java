package zadaniaProgramistzyczne;

import java.util.Scanner;

public class Ex7 {
    //TODO Korzystając z algorytmu MINIMAX (lub jego ulepszonej wersji alpha-beta pruning) zaimplementuj grę w kółko i krzyżyk [O].

    private static final char bot = 'X';
    private static final char player = 'O';

    public static void main(String[] args) {
        char[][] board = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };

        boolean gameOver = false;

        while (!gameOver) {
            drawTheBoard(board);

            System.out.println("\nPodaj współrzędne następnego ruchu:");
            System.out.println("\nWprowadź numer wiersza:");
            int row = new Scanner(System.in).nextInt();
            System.out.println("\nWprowadź numer kolumny:");
            int column = new Scanner(System.in).nextInt();

            if (board[row][column] == '-') {
                board[row][column] = player;

                if (checkWinner(board, player)) {
                    drawTheBoard(board);
                    System.out.println("\n***************************************");
                    System.out.println("               WYGRAŁEŚ");
                    System.out.println("***************************************");
                    break;
                }

                if (!isMovesLeft(board)) {
                    drawTheDraw();
                    break;
                }
            } else {
                continue;
            }

            Move bestMove = findBestMove(board);
            board[bestMove.row][bestMove.col] = bot;

            if (checkWinner(board, bot)) {
                drawTheBoard(board);
                System.out.println("\n***************************************");
                System.out.println("              PRZEGRAŁEŚ");
                System.out.println("***************************************");
                break;
            }

            if (!isMovesLeft(board)) {
                drawTheDraw();
                break;
            }
        }
    }

    static class Move {
        int row, col;

        Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static Move findBestMove(char[][] board) {
        int bestVal = -100;
        Move bestMove = new Move(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = bot;
                    int moveVal = minimax(board, false);
                    board[i][j] = '-';

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    static int minimax(char[][] board, boolean isMax) {
        int score = evaluate(board);

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (!isMovesLeft(board))
            return 0;

        if (isMax) {
            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = bot;
                        best = Math.max(best, minimax(board, !isMax));
                        board[i][j] = '-';
                    }
                }
            }

            return best;
        } else {
            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = player;
                        best = Math.min(best, minimax(board, !isMax));
                        board[i][j] = '-';
                    }
                }
            }

            return best;
        }
    }

    static int evaluate(char[][] b) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == bot)
                    return +10;
                else if (b[row][0] == player)
                    return -10;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == bot)
                    return +10;
                else if (b[0][col] == player)
                    return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == bot)
                return +10;
            else if (b[0][0] == player)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == bot)
                return +10;
            else if (b[0][2] == player)
                return -10;
        }

        return 0;
    }

    static boolean checkWinner(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        return board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol;
    }

    static boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return true;
                }
            }
        }
        return false;
    }

    static void drawTheBoard(char[][] board) {
        System.out.println();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(STR."\t\{board[i][j]} ");
            }
            System.out.println();
            System.out.println();
        }
    }

    static void drawTheDraw() {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("               Remis                    ");
        System.out.println("***************************************");
        System.out.println();
    }
}
