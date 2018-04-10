package apcsp.create.mastermind.util;

import java.util.Random;

public class MastermindGame {

    private PegColor[] solution;
    public Move lastMove;

    public MastermindGame() {
        solution = getRandomSolution();
        System.out.println();
    }

    private PegColor[] getRandomSolution() {
        PegColor[] sol = new PegColor[4];
        PegColor[] values = PegColor.values();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            sol[i] = values[rand.nextInt(values.length)];
        }
        return sol;
    }

    public PegColor[] submitMove(Move move) {
        Move newMove = calculateResponsePegs(move);
        lastMove = newMove;
        assert newMove != null;
        return newMove.responsePegs;
    }


    private Move calculateResponsePegs(Move move) {
        if (move.responsePegs == null) {
            int num_correct = 0;
            int num_almost_correct = 0;
            boolean[] partOfSolution = new boolean[4];
            boolean[] checked = new boolean[4];
            for (int i = 0; i < 4; i++) {
                if (move.pegs[i] == solution[i]) {
                    num_correct++;
                    partOfSolution[i] = true;
                    checked[i] = true;
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (j != i && !checked[i] && !partOfSolution[j]) {
                        if (move.pegs[i] == solution[j]) {
                            partOfSolution[j] = true;
                            checked[i] = true;
                            num_almost_correct++;
                            break;
                        }
                    }
                }
            }
            PegColor[] result = new PegColor[4];
            int index = 0;
            while (num_correct > 0) {
                result[index++] = PegColor.RED;
                num_correct--;
            }
            while (num_almost_correct > 0) {
                result[index++] = PegColor.BLACK;
                num_almost_correct--;
            }
            move.responsePegs = result;
            return move;
        }
        return null;
    }

    public MastermindGame restart() {
        MastermindGame game = new MastermindGame();
        game.solution = this.solution;
        return game;
    }

    public boolean wasWon() {
        for (int i = 0; i < 4; i++) {
            if (lastMove.pegs[i] != solution[i]) return false;
        }
        return true;
    }
}
