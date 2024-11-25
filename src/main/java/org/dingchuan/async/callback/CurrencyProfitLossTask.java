package org.dingchuan.async.callback;

import java.math.BigDecimal;
import java.util.concurrent.*;

public class CurrencyProfitLossTask implements Callable<BigDecimal> {
  private BigDecimal holdingAmount;  // 持仓数量
  private BigDecimal openingRate;    // 开盘汇率
  private BigDecimal closingRate;    // 收盘汇率
  private ProfitLossCallback callback; // 回调接口

  public CurrencyProfitLossTask(BigDecimal holdingAmount, BigDecimal openingRate, BigDecimal closingRate, ProfitLossCallback callback) {
    this.holdingAmount = holdingAmount;
    this.openingRate = openingRate;
    this.closingRate = closingRate;
    this.callback = callback;
  }

  @Override
  public BigDecimal call() throws Exception {
    try {
      // 损益计算公式：损益 = (收盘汇率 - 开盘汇率) * 持仓数量
      BigDecimal result = closingRate.subtract(openingRate).multiply(holdingAmount);

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


