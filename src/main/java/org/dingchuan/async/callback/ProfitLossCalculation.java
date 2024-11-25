package org.dingchuan.async.callback;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.*;

@Slf4j
public class ProfitLossCalculation {

  public static void main(String[] args) {
    // 模拟数据：持仓数量、开盘汇率、收盘汇率等
    BigDecimal currencyAmount = new BigDecimal("10000");
    BigDecimal openingCurrencyRate = new BigDecimal("6.8");
    BigDecimal closingCurrencyRate = new BigDecimal("6.7");

    BigDecimal metalAmount = new BigDecimal("50");  // 50盎司黄金
    BigDecimal openingMetalPrice = new BigDecimal("1800");  // 黄金开盘价
    BigDecimal closingMetalPrice = new BigDecimal("1820");  // 黄金收盘价

    BigDecimal forexAmount = new BigDecimal("20000");
    BigDecimal openingForexRate = new BigDecimal("1.2");
    BigDecimal closingForexRate = new BigDecimal("1.25");

    // 创建回调实例
    ProfitLossCallback callback = new ProfitLossCallback() {
      @Override
      public void onCalculationComplete(BigDecimal result) {
        // 计算成功时打印结果
        log.info("计算结果: {}" , result);
      }

      @Override
      public void onError(Exception e) {
        // 处理错误，打印异常信息
        log.error("任务执行出错: {}" , e.getMessage());
      }
    };

    // 创建线程池
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    // 创建并提交任务，传入回调实例
    Callable<BigDecimal> currencyTask = new CurrencyProfitLossTask(currencyAmount, openingCurrencyRate, closingCurrencyRate,
        callback);
    Callable<BigDecimal> metalTask = new PreciousMetalProfitLossTask(metalAmount, openingMetalPrice, closingMetalPrice, callback);
    Callable<BigDecimal> forexTask = new ForexProfitLossTask(forexAmount, openingForexRate, closingForexRate, callback);

    // 提交任务并返回 Future 对象
    Future<BigDecimal> currencyResult = executorService.submit(currencyTask);
    Future<BigDecimal> metalResult = executorService.submit(metalTask);
    Future<BigDecimal> forexResult = executorService.submit(forexTask);

    try {
      // 获取各个资产的损益结果
      BigDecimal currencyProfitLoss = currencyResult.get();
      BigDecimal metalProfitLoss = metalResult.get();
      BigDecimal forexProfitLoss = forexResult.get();

      // 汇总所有资产的损益
      BigDecimal totalProfitLoss = currencyProfitLoss.add(metalProfitLoss).add(forexProfitLoss);
      log.info("总损益: {}" , totalProfitLoss);
    } catch (InterruptedException | ExecutionException e) {
      // 捕获错误并处理
      log.error("任务执行失败: {}" , e.getMessage());
      e.printStackTrace();
    } finally {
      // 关闭线程池
      executorService.shutdown();
    }
  }
}


