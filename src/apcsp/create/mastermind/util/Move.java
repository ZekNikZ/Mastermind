package apcsp.create.mastermind.util;

public class Move {
    public PegColor[] pegs;
    public PegColor[] responsePegs;

    public Move (PegColor[] pegs) {
        this(pegs, null);
    }

    private Move (PegColor[] pegs, PegColor[] responsePegs) {
        this.pegs = pegs;
        this.responsePegs = responsePegs;
    }
}