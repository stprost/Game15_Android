package com.example.pk.game15;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class MyBot {

    private Logic logic;
    final private Graphic graphic;
    private int[] tiles;
    private int blankPos;
    private int side;
    private int numTiles;

    private Graph graph;

    public MyBot(Logic logic, Graphic graphic) {
        this.logic = logic;
        this.graphic = graphic;
        tiles = logic.getTiles();
        blankPos = logic.getBlankPos();
        side = logic.getSide();
        numTiles = logic.getNumTiles();

        graph = new Graph(side);
    }


    //Сам алгоритм передвижения фишек на свои места
    public void bot() throws InterruptedException {
        for (int i = 0; i < (side - 3) * side + side; i++) {
            if (i % side == side - 2) {
                moveTileTo(i + 1, i + 1);
            } else if (i % side == side - 1) {
                if (findNode(i + 1) == i - 1 + side || findNode(i + 1) == i - 1) {
                    moveTileTo(i + 1, i - 2 + side);
                    moveTileTo(i, i);
                }
                moveTitleToWithout(i + 1, i + side, i);
                moveTitleToWithout(i, i - 1, i + side);
                graph.deleteNode(graph.getNodeList()[i - 1]);
                moveTileTo(i + 1, i);
                graph.deleteNode(graph.getNodeList()[i]);
            } else {
                moveTileTo(i + 1, i);
                graph.deleteNode(graph.getNodeList()[i]);
            }
        }
        for (int i = (side - 3) * side + side; i < (side - 3) * side + side * 2 - 1; i++) {
            if (i != (side - 3) * side + side * 2 - 2) {
                moveTileTo(i + 1 + side, i);
                if (findNode(i + 1) == i + side || findNode(i + 1) == i + 1 + side) {
                    moveTileTo(i + 1, i + 2 + side);
                }
                moveTileTo(i + 1 + side, i);
                moveTitleToWithout(i + 1, i + 1, i);
                moveTitleToWithout(i + 1 + side, i + side, i + 1);
                moveTileTo(i + 1, i);
            } else {
                moveTileTo(side * side - 1, side * side - 2);
                moveTileTo(side * side - 1 - side, side * side - 2 - side);
                moveTileTo(side * side - side, side * side - 1 - side);
            }
        }
    }

    //Передвижение фишки по пути определенному с помощью А*
    private void moveTileTo(int start, int goal) throws InterruptedException {
        Node nodeStart = graph.getNodeList()[findNode(start)];
        Node nodeGoal = graph.getNodeList()[goal];
        Node blankNode = graph.getNodeList()[blankPos];
        ArrayDeque<Node> mainPath = graph.aStar(nodeStart, nodeGoal);
        for (Node mainNode : mainPath) {
            graph.deleteNode(nodeStart);
            ArrayDeque<Node> localPath = graph.aStar(blankNode, mainNode);
            for (Node localNode : localPath) {
                logic.move(localNode.getKey());
                graphic.update(logic);
                TimeUnit.SECONDS.sleep(1);
            }
            graph.cancelDeleteNode(nodeStart);
            logic.move(nodeStart.getKey());
            graphic.update(logic);
            TimeUnit.SECONDS.sleep(1);
            nodeStart = graph.getNodeList()[findNode(start)];
            blankNode = graph.getNodeList()[blankPos];
        }
    }

    //Тоже самое, что и moveTileTo, только не использую определенный узел графа
    private void moveTitleToWithout(int start, int goal, int statikNode) throws InterruptedException {
        graph.deleteNode(graph.getNodeList()[statikNode]);
        moveTileTo(start, goal);
        graph.cancelDeleteNode(graph.getNodeList()[statikNode]);
    }

    //Поиск места определенноой фишки
    private int findNode(int k) {
        int r = -1;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == k) r = i;
        }
        return r;
    }
}
