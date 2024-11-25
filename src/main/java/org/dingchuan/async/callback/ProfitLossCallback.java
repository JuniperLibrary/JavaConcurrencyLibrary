package org.dingchuan.async.callback;

import java.math.*;


public interface ProfitLossCallback {
  void onCalculationComplete(BigDecimal result);  // 任务完成时的回调

  void onError(Exception e);  // 错误发生时的回调
}
