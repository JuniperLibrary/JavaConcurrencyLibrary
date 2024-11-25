package org.dingchuan.async;

import java.math.*;

/**
 * 定义资产损益计算任务（Callable）
 */
public interface AssetProfitLossCalculator {

  BigDecimal calculate() throws Exception;
}
