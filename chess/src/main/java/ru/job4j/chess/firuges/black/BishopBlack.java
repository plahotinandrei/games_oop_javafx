package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        boolean isDiagonal = this.isDiagonal(position, dest);
        if (!isDiagonal) {
            throw new ImpossibleMoveException(
                    String.format("Could not way by diagonal from %s to %s", position, dest)
            );
        }
        int size = Math.abs(dest.getX() - position.getX());
        Cell[] steps = new Cell[size];
        boolean toRight = position.getX() - dest.getX() > 0;
        boolean toTop = position.getY() - dest.getY() > 0;
        int deltaX = toRight ? position.getX() - 1 : position.getX() + 1;
        int deltaY = toTop ? position.getY() - 1 : position.getY() + 1;
        for (int index = 0; index < size; index++) {
            int x = toRight ? deltaX - index : deltaX + index;
            int y = toTop ? deltaY - index : deltaY + index;
            steps[index] = Cell.findBy(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        int deltaX = Math.abs(source.getX() - dest.getX());
        int deltaY = Math.abs(source.getY() - dest.getY());
        return deltaX == deltaY;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
