/* **********************************************************************
 * Copyright 2022 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package org.rebalance.balls;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author olysenko
 */
public class Box {

   private static int boxCounter = 1;

   private final String name;
   private final Set<Ball> ballSet = new HashSet<>();

   public Box() {
      this.name = "Box" + boxCounter++;
   }

   public String getName() {
      return name;
   }

   public void addBall(Ball ball) {
      ballSet.add(ball);
   }

   public boolean removeBall(Ball ball) {
      return ballSet.remove(ball);
   }

   public int getBallCount() {
      return ballSet.size();
   }

   public Set<Ball> getBallSet() {
      return ballSet;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Box box = (Box) o;
      return Objects.equals(name, box.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Box{" + "name='" + name + '\'' + '}';
   }
}
