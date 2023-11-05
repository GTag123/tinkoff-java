package edu.project2;

import java.util.Random;

public interface Generator {
    Maze generate(int height, int width, Random random);
}
