package com.hencoder.hencoderpracticedraw1.practice;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/7/27 汪波 first commit
 */
public class PieData {
  private String name;
  private float value;
  private int color;
  private float angle;

  public PieData(String name, float value, int color) {
    this.name = name;
    this.value = value;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public float getAngle() {
    return angle;
  }

  public void setAngle(float angle) {
    this.angle = angle;
  }
}
