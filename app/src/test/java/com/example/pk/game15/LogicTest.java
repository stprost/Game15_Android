package com.example.pk.game15;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class LogicTest {
    private Logic logic;

    @Test
    public void checkEasy() throws Exception {
        check(4);
    }

    @Test
    public void checkNormal() throws Exception {
        check(5);
    }

    @Test
    public void checkHard() throws Exception {
        check(6);
    }

    private void check (int side) {
        logic = new Logic(side);
        int moves = 100;
        ArrayList<Integer> neigh = new ArrayList<Integer>();
        for (int i = 0; i < moves; i++) {
            int blank = logic.getBlankPos();
            if (blank % side < (side - 1)) neigh.add(blank + 1);
            if (blank % side > 0) neigh.add(blank - 1);
            if (blank / side < (side - 1)) neigh.add(blank + side);
            if (blank / side > 0) neigh.add(blank - side);
            int random = new Random().nextInt(neigh.size());
            int blankCell = logic.getTiles()[blank];
            int randomCell = logic.getTiles()[neigh.get(random)];
            logic.move(neigh.get(random));
            assertEquals(logic.getTiles()[neigh.get(random)], blankCell);
            assertEquals(logic.getTiles()[blank], randomCell);
            neigh.clear();
        }
    }
}
