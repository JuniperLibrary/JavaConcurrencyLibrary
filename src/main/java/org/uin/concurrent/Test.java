package org.uin.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/5:25 PM
 */
public class Test implements Delayed {

  private final long time;   //延迟时间，这里以毫秒为单位
  private final int priority;
  private final long startTime;
  private final String data;

  public Test(long time, int priority, String data) {
    this.time = TimeUnit.SECONDS.toMillis(time);//转换成毫秒
    this.priority = priority;
    this.startTime = System.currentTimeMillis();
    this.data = data;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    long leftTime =
        time - (System.currentTimeMillis() - startTime); //计算剩余时间 = 设定时间 - 已度过时间(= 当前时间 - 开始时间)
    return unit.convert(leftTime, TimeUnit.MILLISECONDS);   //注意进行单位转换，单位由队列指定（默认是纳秒单位）
  }

  @Override
  public int compareTo(Delayed o) {
    if (o instanceof Test) {
      return priority - ((Test) o).priority;//优先级越小越优先
    }
    return 0;
  }

  @Override
  public String toString() {
    return data;
  }

}
