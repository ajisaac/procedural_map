package com.aaron.dfofin.algorithms;

public class BasicMath {
  private BasicMath(){}

  /**
   * Takes an int inside a range and normalizes it to between 1f and 0f
   *
   * @param num       the integer to be normalized
   * @param sourceMin the minimum integer of the source range
   * @param sourceMax the maximum integer of the source range
   * @return a float between 0 and 1
   */
  public static float normalizeIntToFloat(int num, int sourceMin, int sourceMax) {
    return (float) ((1 / ((double) sourceMax - (double) sourceMin)) * (double) num);
  }

  public static float normalizeFloatToFloat(float j, float sourceMin, float sourceMax, float destMin, float destMax) {
    return (destMax - destMin) / (sourceMax - sourceMin) * (j - sourceMax) + destMax;
  }

  public static double distanceBetweenTwoPoints(int i1, int j1, int i2, int j2) {
    return (Math.hypot(i2 - i1, j2 - j1));
  }

  public static float invert(float f) {
    return -f + 1;
  }
}
