package org.example;

public class TankCalculatorResult {
    public int LastTankOverflowInSeconds;
    public int MaxTankOverflowTimeInSeconds;

    public TankCalculatorResult(int lastTankOverflowInSeconds, int maxTankOverflowTimeInSeconds) {
        LastTankOverflowInSeconds = lastTankOverflowInSeconds;
        MaxTankOverflowTimeInSeconds = maxTankOverflowTimeInSeconds;
    }
}
