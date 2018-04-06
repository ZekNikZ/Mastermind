package apcsp.create.mastermind.util;

import apcsp.create.mastermind.util.PegColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MastermindGame {

    private PegColor[] solution;
    private  List<Move> previousMoves;
    public Move lastMove;

    public MastermindGame() {
        solution = getRandomSolution();
        previousMoves = new ArrayList<Move>();
        /*
        System.out.print("Solution: ");
        for (PegColor color : solution) {
            System.out.print(color.toString() + " ");
        }
        */
        System.out.println();
    }

    private apcsp.create.mastermind.util.PegColor[] getRandomSolution() {
        apcsp.create.mastermind.util.PegColor[] sol = new apcsp.create.mastermind.util.PegColor[4];
        apcsp.create.mastermind.util.PegColor[] values = apcsp.create.mastermind.util.PegColor.values();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            sol[i] = values[rand.nextInt(values.length)];
        }
        return sol;
    }

    public apcsp.create.mastermind.util.PegColor[] submitMove(Move move) {
        Move newMove = calculateResponsePegs(move);
        previousMoves.add(newMove);
        lastMove = newMove;
        return newMove.responsePegs;
    }

    public Move calculateResponsePegs(Move move) {
        if (move.responsePegs == null) {
            int num_correct = 0;
            int num_almost_correct = 0;
            boolean[] partOfSolution = new boolean[4];
            boolean[] checked = new boolean[4];
            // TODO: Check exactly correct first, then almost correct
            for (int i = 0; i < 4; i++) {
                if (move.pegs[i] == solution[i]) {
                    num_correct++;
                    partOfSolution[i] = true;
                    checked[i] = true;
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (j == i || checked[i] || partOfSolution[j]) {
                        continue;
                    } else if (move.pegs[i] == solution[j]) {
                        partOfSolution[j] = true;
                        checked[i] = true;
                        num_almost_correct++;
                        break;
                    }
                }
            }
            apcsp.create.mastermind.util.PegColor[] result = new apcsp.create.mastermind.util.PegColor[4];
            int index = 0;
            while (num_correct > 0) {
                result[index++] = apcsp.create.mastermind.util.PegColor.RED;
                num_correct--;
            }
            while (num_almost_correct > 0) {
                result[index++] = apcsp.create.mastermind.util.PegColor.BLACK;
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

    public static class Move {
        public apcsp.create.mastermind.util.PegColor[] pegs;
        public apcsp.create.mastermind.util.PegColor[] responsePegs;

        public Move (apcsp.create.mastermind.util.PegColor[] pegs) {
            this(pegs, null);
        }

        public Move (apcsp.create.mastermind.util.PegColor[] pegs, apcsp.create.mastermind.util.PegColor[] responsePegs) {
            this.pegs = pegs;
            this.responsePegs = responsePegs;
        }
    }
}
