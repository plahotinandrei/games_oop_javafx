package ru.job4j.chess;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.white.BishopWhite;

public class LogicTest {

    @Test
    public void moveBishopBlackC1ToH6()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.move(Cell.C1, Cell.H6);
    }

    @Test (expected = ImpossibleMoveException.class)
    public void moveBishopBlackC1ToC6()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.move(Cell.C1, Cell.C6);
    }

    @Test (expected = OccupiedCellException.class)
    public void moveBishopBlackC3ToF6()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C3));
        logic.add(new BishopWhite(Cell.E5));
        logic.move(Cell.C3, Cell.F6);
    }

    @Test (expected = FigureNotFoundException.class)
    public void moveBishopBlackH8ToD4ifSourceC3()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C3));
        logic.move(Cell.H8, Cell.D4);
    }

    @Test
    public void testBishopBlackPosition() {
        Figure bishopBlack = new BishopBlack(Cell.C1);
        Cell ps = bishopBlack.position();
        Assert.assertEquals(ps, Cell.C1);
    }

    @Test
    public void testBishopBlackCopy() {
        Figure bishopBlack = new BishopBlack(Cell.C1);
        Figure copyBishopBlack = bishopBlack.copy(Cell.B2);
        Cell ps = copyBishopBlack.position();
        Assert.assertEquals(ps, Cell.B2);
    }

    @Test
    public void bishopBlackWayC1ToG5() {
        Figure bishopBlack = new BishopBlack(Cell.C1);
        Cell[] cells = bishopBlack.way(Cell.G5);
        Cell[] expected = new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Assert.assertEquals(cells, expected);
    }

    @Test
    public void bishopBlackWayH5ToD1() {
        Figure bishopBlack = new BishopBlack(Cell.H5);
        Cell[] cells = bishopBlack.way(Cell.D1);
        Cell[] expected = new Cell[]{Cell.G4, Cell.F3, Cell.E2, Cell.D1};
        Assert.assertEquals(cells, expected);
    }

    @Test
    public void bishopBlackWayA8ToC6() {
        Figure bishopBlack = new BishopBlack(Cell.A8);
        Cell[] cells = bishopBlack.way(Cell.C6);
        Cell[] expected = new Cell[]{Cell.B7, Cell.C6};
        Assert.assertEquals(cells, expected);
    }

    @Test
    public void bishopBlackWayF3ToA7() {
        Figure bishopBlack = new BishopBlack(Cell.E3);
        Cell[] cells = bishopBlack.way(Cell.A7);
        Cell[] expected = new Cell[]{Cell.D4, Cell.C5, Cell.B6, Cell.A7};
        Assert.assertEquals(cells, expected);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void bishopBlackWayException() throws ImpossibleMoveException {
        Figure bishopBlack = new BishopBlack(Cell.E3);
        bishopBlack.way(Cell.E6);
    }
}