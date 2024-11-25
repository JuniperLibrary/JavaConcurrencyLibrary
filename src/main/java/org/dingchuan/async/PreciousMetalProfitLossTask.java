package org.dingchuan.async;

import java.math.*;
import java.util.concurrent.*;
import lombok.extern.slf4j.*;

/**
 * 贵金属损益计算任务
 */
@Slf4j
public class PreciousMetalProfitLossTask implements Callable<BigDecimal> {

  private BigDecimal holdingAmount;  // 持仓数量
  private BigDecimal openingPrice;   // 开盘金价
  private BigDecimal closingPrice;   // 收盘金价

  public PreciousMetalProfitLossTask(BigDecimal holdingAmount, BigDecimal openingPrice, BigDecimal closingPrice) {
    this.holdingAmount = holdingAmount;
    this.openingPrice = openingPrice;
    this.closingPrice = closingPrice;
  }

  @Override
  public BigDecimal call() throws Exception {
    // 损益计算公式：损益 = (收盘金价 - 开盘金价) * 持仓数量
    return closingPrice.subtract(openingPrice).multiply(holdingAmount);
  }
}
