package org.example;

import java.util.ArrayList;

public class TankCalculator {
    private final int[] tankCapacities;
    private final int flowRate;

    public TankCalculator(int[] tankCapacities, int flowRate){
        this.tankCapacities = tankCapacities;
        this.flowRate = flowRate;
    }

    public TankCalculatorResult doCalculation() {
        ArrayList<Double> tanksTimeToFill = new ArrayList<>();

        double maxTankOverflowTimeInSeconds = (double) tankCapacities[0] / flowRate;
        tanksTimeToFill.add(maxTankOverflowTimeInSeconds);

        double lastTankOverflowInSeconds = 0;

        for (int i = 1; i < tankCapacities.length; i++) {
            double tankCapacity = tankCapacities[i];
            double previousTankTimeToFill = tanksTimeToFill.get(i - 1);
            double timeToFill = (double) tankCapacity / flowRate;
            double currentTimeToFill;

            if (timeToFill > previousTankTimeToFill) {
                double aloneFillingInSeconds = previousTankTimeToFill;
                double fillingLeftTimeInSeconds = tankCapacity - (aloneFillingInSeconds * flowRate);
                int count = 0;

                for (int j = 0; j < i; j++) {
                    if (tanksTimeToFill.get(j) < timeToFill) {
                        count++;
                    }
                }
                currentTimeToFill = aloneFillingInSeconds + (fillingLeftTimeInSeconds / (flowRate * (count + 1)));
            } else {
                currentTimeToFill = (double) tankCapacity / flowRate;
            }

            tanksTimeToFill.add(currentTimeToFill);

            if (i == tankCapacities.length - 1) {
                lastTankOverflowInSeconds = currentTimeToFill;
            }

            if (currentTimeToFill > maxTankOverflowTimeInSeconds) {
                maxTankOverflowTimeInSeconds = currentTimeToFill;
            }
        }

        return new TankCalculatorResult((int) Math.floor(lastTankOverflowInSeconds), (int) Math.floor(maxTankOverflowTimeInSeconds));
    }
}
