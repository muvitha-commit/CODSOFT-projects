import java.util.Scanner;

public class TicTacToeAI {

    static char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    static final char HUMAN = 'X';
    static final char AI = 'O';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printBoard();
            humanMove(sc);

            if (isWinner(HUMAN)) {
                printBoard();
                System.out.println("You win!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }

            aiMove();

            if (isWinner(AI)) {
                printBoard();
                System.out.println("AI wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }
        }
        sc.close();
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("--+---+--");
        }
        System.out.println();
    }

    static void humanMove(Scanner sc) {
        while (true) {
            System.out.print("Enter position (1-9): ");
            int move = sc.nextInt();

            int row = (move - 1) / 3;
            int col = (move - 1) % 3;

            if (move >= 1 && move <= 9 && board[row][col] != HUMAN && board[row][col] != AI) {
                board[row][col] = HUMAN;
                break;
            } else {
                System.out.println("Invalid move!");
            }
        }
    }

    static void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = 0, bestCol = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != HUMAN && board[i][j] != AI) {
                    char temp = board[i][j];
                    board[i][j] = AI;

                    int score = minimax(false);

                    board[i][j] = temp;

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        board[bestRow][bestCol] = AI;
    }

    static int minimax(boolean isMaximizing) {
        if (isWinner(AI)) return 1;
        if (isWinner(HUMAN)) return -1;
        if (isBoardFull()) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != HUMAN && board[i][j] != AI) {
                        char temp = board[i][j];
                        board[i][j] = AI;

                        int score = minimax(false);

                        board[i][j] = temp;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != HUMAN && board[i][j] != AI) {
                        char temp = board[i][j];
                        board[i][j] = HUMAN;

                        int score = minimax(true);

                        board[i][j] = temp;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    static boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    static boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell != HUMAN && cell != AI) return false;
            }
        }
        return true;
    }
}