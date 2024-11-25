package org.dingchuan.async.callback;

import java.math.BigDecimal;
import java.util.concurrent.*;

public class PreciousMetalProfitLossTask implements Callable<BigDecimal> {
  private BigDecimal holdingAmount;  // 持仓数量
  private BigDecimal openingPrice;   // 开盘金价
  private BigDecimal closingPrice;   // 收盘金价
  private ProfitLossCallback callback; // 回调接口

  public PreciousMetalProfitLossTask(BigDecimal holdingAmount, BigDecimal openingPrice, BigDecimal closingPrice, ProfitLossCallback callback) {
    this.holdingAmount = holdingAmount;
    this.openingPrice = openingPrice;
    this.closingPrice = closingPrice;
    this.callback = callback;
  }

  @Override
  public BigDecimal call() throws Exception {
    try {
      // 损益计算公式：损益 = (收盘金价 - 开盘金价) * 持仓数量
      BigDecimal result = closingPrice.subtract(openingPrice).multiply(holdingAmount);

      // 计算完成后，调用回调方法
      if (callback != null) {
        callback.onCalculationComplete(result);  // 通知回调
      }

      return result;
    } catch (Exception e) {
      // 发生错误时，调用回调的 onError 方法
      if (callback != null) {
        callback.onError(e);  // 通知回调错误
      }
      throw e;  // 重新抛出异常以便处理
    }
  }
}


