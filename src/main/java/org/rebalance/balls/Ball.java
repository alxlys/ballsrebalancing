package org.rebalance.balls;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author olysenko
 */
public class Ball {

   private static int ballCounter = 1;

   private String name;
   private Set<Box> eligibleBoxes;
   private Box box;
   private Box initialBox;

   public Ball(Collection<Box> eligibleBoxes) {
      this.name = "ball" + ballCounter++;
      this.eligibleBoxes = new HashSet<>(eligibleBoxes);
   }

   public void putToBox(Box box) {
      this.box = box;
      this.box.addBall(this);
   }

   public void setInitialBox(Box box) {
      this.initialBox = box;
   }

   public void moveToBox(Box newBox) {
      this.box.removeBall(this);
      newBox.addBall(this);
      this.box = newBox;
   }

   public Box getBox() {
      return box;
   }

   public Box getInitialBox() {
      return initialBox;
   }

   public Set<Box> getEligibleBoxes() {
      return eligibleBoxes;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Ball ball = (Ball) o;
      return name.equals(ball.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Ball{" + "name='" + name + '\'' + '}';
   }
}
