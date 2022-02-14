package org.rebalance.balls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author olysenko
 */
public class BallTest {

   public static void main(String[] args) {

      Box box1 = new Box();
      Box box2 = new Box();
      Box box3 = new Box();

      Ball ball1 = new Ball(Arrays.asList(box1, box2, box3));
      Ball ball2 = new Ball(Arrays.asList(box1));
      Ball ball3 = new Ball(Arrays.asList(box1));
      Ball ball4 = new Ball(Arrays.asList(box1, box2));
      Ball ball5 = new Ball(Arrays.asList(box1, box2));
      Ball ball6 = new Ball(Arrays.asList(box1, box3));
      Ball ball7 = new Ball(Arrays.asList(box2));
      Ball ball8 = new Ball(Arrays.asList(box2));
      Ball ball9 = new Ball(Arrays.asList(box2, box3));
      Ball ball10 = new Ball(Arrays.asList(box3));
      Ball ball11 = new Ball(Arrays.asList(box3));
      Ball ball12 = new Ball(Arrays.asList(box1));

      Ball ball13 = new Ball(Arrays.asList(box1));
      Ball ball14 = new Ball(Arrays.asList(box1));
      Ball ball15 = new Ball(Arrays.asList(box1));
      Ball ball16 = new Ball(Arrays.asList(box1));
      Ball ball17 = new Ball(Arrays.asList(box1));
      Ball ball18 = new Ball(Arrays.asList(box1));
      Ball ball19 = new Ball(Arrays.asList(box1));
      Ball ball20 = new Ball(Arrays.asList(box1));


      Ball ball21 = new Ball(Arrays.asList(box2, box3));
      Ball ball22 = new Ball(Arrays.asList(box2, box3));
      Ball ball23 = new Ball(Arrays.asList(box2, box3));
      Ball ball24 = new Ball(Arrays.asList(box2, box3));
      Ball ball25 = new Ball(Arrays.asList(box2, box3));

      List<Box> boxes = Arrays.asList(box1, box2, box3);

      List<Ball> balls =
            Arrays.asList(ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8, ball9, ball10, ball11, ball12,
                  ball13, ball14, ball15, ball16, ball17, ball18, ball19, ball20,
                  ball21, ball22, ball23, ball24, ball25);

      initialDistribution(balls);

      for (Box box : boxes) {
         System.out.println(box + " : " + box.getBallCount() + " : " + box.getBallSet());
      }

      System.out.println("==============================================================================");

      rebalance(boxes);

      for (Box box : boxes) {
         System.out.println(box + " : " + box.getBallCount() + " : " + box.getBallSet());
      }

      System.out.println("==============================================================================");

      swapToInitialBox(balls);

      for (Box box : boxes) {
         System.out.println(box + " : " + box.getBallCount() + " : " + box.getBallSet());
      }


   }


   public static void initialDistribution(Collection<Ball> balls) {
      for (Ball ball : balls) {
         ArrayList<Box> boxes = new ArrayList<>(ball.getEligibleBoxes());
         boxes.sort(Comparator.comparingInt(Box::getBallCount).reversed());
         ball.putToBox(boxes.get(0));
         ball.setInitialBox(boxes.get(0));
//         ball.putToBox(ball.getEligibleBoxes().iterator().next());
      }
   }

   public static void rebalance(List<Box> boxes) {

      for (int i = 0; i < boxes.size(); i++) {

         //1 Select any machine k for which C partiat<k l = c* partiat .
         boxes.sort(Comparator.comparingInt(Box::getBallCount).reversed());
         Box boxMax = boxes.get(i);

         while (true) {
            //2 Search for job j, where j E Ak and l E M j' such that c partial(/)+ pjl < c* partial for
            //some machine l (l -:f. k).
            Ball ballToMove = null;
            Box newBox = null;
            BALL:
            for (Ball ball : boxMax.getBallSet()) {
               for (Box eligibleBox : ball.getEligibleBoxes()) {
                  if (boxMax == eligibleBox) {
                     continue;
                  }
                  if (eligibleBox.getBallCount() + 1 < boxMax.getBallCount()) {
                     ballToMove = ball;
                     newBox = eligibleBox;
                     break BALL;
                  }
               }
            }

            if (ballToMove == null) {
               //3  If no such job j and machine l are found, then c* partial is the final solution.
               break;
            }

            //4 Ifj and l are found, thenjobj is assigned to machine l.
            //5 Update At= AI UU}, cparial(l) = cparial(l)+ pjland Ak =Ak -U}.
            ballToMove.moveToBox(newBox);
         }
      }

   }

   private static void swapToInitialBox(List<Ball> ballList) {
      for (Ball ball : ballList) {
         if (ball.getBox() == ball.getInitialBox()) {
            continue;
         }
         BOXES: for (Box eligibleBox : ball.getEligibleBoxes()) {
            if (eligibleBox == ball.getInitialBox()) {
               //Search for ball that can be swapped
               for (Ball ballToSwap : eligibleBox.getBallSet()) {
                  if (ball == ballToSwap) {
                     continue;
                  }
                  if (ballToSwap.getInitialBox() != eligibleBox) {
                     Box currentBox = ball.getBox();
                     ball.moveToBox(eligibleBox);
                     ballToSwap.moveToBox(currentBox);
                     break BOXES;
                  }
               }
            }
         }
      }


   }


}
