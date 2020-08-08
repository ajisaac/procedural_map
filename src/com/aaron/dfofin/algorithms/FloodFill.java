package com.aaron.dfofin.algorithms;

import java.util.LinkedList;

import com.aaron.dfofin.S;
import com.aaron.dfofin.data.Pair;

public class FloodFill {

  int size = S.size;

  private void fillInternal(int x, int y, float[][] map) {

    Pair currentCell = new Pair(x, y);
    System.out.println(currentCell.getX());
    LinkedList<Pair> stack = new LinkedList<Pair>();

    //find our start point
    currentCell = findLeftWall(currentCell, map);
    System.out.println(currentCell.getX());

    //paint right till wall, picking up ranges as we go
    paintRight(currentCell, map, stack);

    while (!stack.isEmpty()) {
      paintRight(stack.removeFirst(), map, stack);
    }
  }

  private void paintRight(Pair p, float[][] map, LinkedList<Pair> stck) {
    //paint initial cell
    Pair currentCell = getRightCell(p);

    //if the cell above us isn't a wall
    if (!isCellWall(getUpCell(currentCell), map)) {
      stck.push(findLeftWall(getUpCell(currentCell), map));
    }
    //if the cell below us isnt a wall
    if (!isCellWall(getDownCell(currentCell), map)) {
      stck.push(findLeftWall(getDownCell(currentCell), map));
    }
    //while current cell isn't a wall
    while (!isCellWall(currentCell, map)) {
      map[currentCell.getX()][currentCell.getY()] = .5f;
      currentCell = getRightCell(currentCell);
      //if the cell above us isn't a wall
      if (!isCellWall(getUpCell(currentCell), map)) {
        stck.push(findLeftWall(getUpCell(currentCell), map));
      }
      //if the cell below us isnt a wall
      if (!isCellWall(getDownCell(currentCell), map)) {
        stck.push(findLeftWall(getDownCell(currentCell), map));
      }
    }
  }

  /**
   * @param p   - initial cell
   * @param map - array we're checking
   * @return the left wall
   */
  private Pair findLeftWall(Pair p, float[][] map) {
    Pair r = getLeftCell(p);
    //while we haven't found the left wall
    while (!isCellWall(r, map) && r.getX() != p.getX()) {
      //go left
      r = getLeftCell(r);
    }
    return r;
  }

  /**
   * @param p   - the cell we are checking
   * @param map - the 2d array we're checking
   * @return true if the cell is a wall
   */
  private boolean isCellWall(Pair p, float[][] map) {
    boolean b;
    if (map[p.getX()][p.getY()] == 0f)
      b = false;
    else
      b = true;
    return b;
  }

  /**
   * @param p - initial cell
   * @return the cell above initial cell
   */
  private Pair getUpCell(Pair p) {
    int x = p.getX(), y = p.getY();
    Pair r = new Pair(x, y);

    if (y == size - 1)
      y = 0;
    else
      y++;

    r.setY(y);
    return r;
  }

  /**
   * @param p - initial cell
   * @return the cell above initial cell
   */
  private Pair getDownCell(Pair p) {
    int x = p.getX(), y = p.getY();
    Pair r = new Pair(x, y);

    if (y == 0)
      y = size - 1;
    else
      y--;

    r.setY(y);
    return r;
  }

  /**
   * @param p - initial cell
   * @return the cell above initial cell
   */
  private Pair getRightCell(Pair p) {
    int x = p.getX(), y = p.getY();
    Pair r = new Pair(x, y);

    if (x == size - 1)
      x = 0;
    else
      x++;

    r.setX(x);
    return r;
  }

  /**
   * @param p - initial cell
   * @return the cell above initial cell
   */
  private Pair getLeftCell(Pair p) {
    int x = p.getX(), y = p.getY();
    Pair r = new Pair(x, y);

    if (x == 0)
      x = size - 1;
    else
      x--;

    r.setX(x);
    return r;
  }

  public float[][] thickenLines(float[][] f) {
    for (int i = 1; i < f.length - 1; i++) {
      for (int j = 1; j < f.length - 1; j++) {
        if (f[i][j] == 0 && f[i - 1][j - 1] == 0 && f[i - 1][j] != 0 && f[i][j - 1] != 0) {
          f[i][j] = .75f;
        }
        if (f[i][j] != 0 && f[i - 1][j - 1] != 0 && f[i - 1][j] == 0 && f[i][j - 1] == 0) {
          f[i - 1][j] = .75f;
        }
      }
    }
    return f;
  }

}
