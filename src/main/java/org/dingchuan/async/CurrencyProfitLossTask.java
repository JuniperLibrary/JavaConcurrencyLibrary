package org.dingchuan.async;

import java.math.*;
import java.util.concurrent.*;
import lombok.*;
import lombok.extern.slf4j.*;

/**
 * 本币损益计算任务
 */
@Slf4j
@RequiredArgsConstructor
public class CurrencyProfitLossTask implements Callable<BigDecimal> {

  private BigDecimal holdingAmount;  // 持仓数量
  private BigDecimal openingRate;    // 开盘汇率
  private BigDecimal closingRate;    // 收盘汇率

  public CurrencyProfitLossTask(BigDecimal holdingAmount, BigDecimal openingRate, BigDecimal closingRate) {
    this.holdingAmount = holdingAmount;
    this.openingRate = openingRate;
    this.closingRate = closingRate;
  }

  @Override
  public BigDecimal call() throws Exception {
    // 损益计算公式：损益 = (收盘汇率 - 开盘汇率) * 持仓数量
    return closingRate.subtract(openingRate).multiply(holdingAmount);
  }
}
