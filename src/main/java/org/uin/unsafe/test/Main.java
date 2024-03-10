package org.uin.unsafe.test;

import java.util.Scanner;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/7:21 PM
 */
public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    //有a辆坦克
    int a = sc.nextInt();
    //碉堡都有b点生命值
    int b = sc.nextInt();
    //每个碉堡可以炸毁c辆坦克
    int c = sc.nextInt();
    //战场上有 d 座敌方碉堡
    int d = sc.nextInt();

    //每个碉堡炸毁 k*c 辆坦克
    //k是剩余的碉堡的数量，每个碉堡可以炸毁c辆坦克。
    //输入 10 6 8 2
    //     a b c d
    //输出最小回合数 2
    System.out.println(minTimes(a, b, c, d));
  }

  public static int minTimes(int a, int b, int c, int d) {
    //坦克炸碉堡
    //a辆坦克 d 座敌方碉堡
    int count = 0;

    if (a != 0 && d != 0) {
      //第一回合 坦克先炸碉堡
      if ((a % b == 0)) {
        d -= 1;
        count += 1;
        minTimes(a, b, c, d);
      } else if (d == 0) {
        return count;
      }

      //第二回合 碉堡炸坦克
      if (d * c > a) {
        return -1;
      } else {
        count += 1;
        minTimes(a, b, c, d);
      }
    } else if ((a >= 0 && d == 0) || (a == 0 && b == 0)) {
      return count;
    } else {
      return -1;
    }
    return -1;
  }
}
