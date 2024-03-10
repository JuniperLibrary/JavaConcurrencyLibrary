package org.uin.unsafe.test;

import java.util.Scanner;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/7:58 PM
 */
public class Main1 {

  public static void main(String[] args) {
    //小明在玩一款建造类的游戏。他需要为一段未开荒的地段设计路段的规划，以便起重机通过。
    //游戏里每段路径都有能承重的级别，小明现在希望尽可能让能承重更大的起重机通过，这样他就可以比较快地完成建造了。
    //游戏规定小明只能选一种起重机机型，小明想知道这个起重机最高的承重级别应该是多少，使得在该承重条件下，
    //起重机可以从任何一个点出发去向任何一个点而不会损坏道路（损坏道路指的是路段上行驶了超过承重能力的起重机）。
    //为了方便，我们将需要规划的建造点抽象成N个点，有M条边将他们相连。

    //第一行是两个空格隔开的正整数n,m。n代表点数，我们将点从1到n编号，m指边的数量
    //接下来m行，每行3个空格隔开的正整数u, v, p，代表节点u和节点v之间有一条承重能力为p的路径。
    Scanner sc = new Scanner(System.in);
    //n代表点数
    int n = sc.nextInt();
    //m指边的数量
    int m = sc.nextInt();
    sc.nextLine();
    int[] nums = new int[3];
    while (sc.hasNext()) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      //节点u和节点v之间有一条承重能力为p的路径
      int p = sc.nextInt();
      for (int i = 0; i < nums.length; i++) {
        nums[i++] = sc.nextInt();
        //int u = sc.nextInt();
        //int v = sc.nextInt();
        //节点u和节点v之间有一条承重能力为p的路径
        //int p = sc.nextInt();
      }
    }
  }

  public static int maxWeight(int n, int m, int[] nums) {

    return 0;
  }
}
