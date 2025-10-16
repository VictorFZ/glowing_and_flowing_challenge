package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TankCalculator {
    private final int[] tankCapacities;
    private final int flowRate;

    public TankCalculator(String[] inputs) throws IllegalArgumentException {

        if(inputs == null || inputs.length == 0)
            throw new IllegalArgumentException("Input not in correct format: Missing inputs");

        var tankCountAndFlowRate = inputs[0].split(" ");

        if(tankCountAndFlowRate.length != 2)
            throw new IllegalArgumentException("Input not in correct format: First line is not in correct format");

        var tankCount = Integer.parseInt(tankCountAndFlowRate[0]);
        var flowRate = Integer.parseInt(tankCountAndFlowRate[1]);

        var tankCapacities = Arrays.stream(inputs).skip(1).toArray(String[]::new);

        if(tankCapacities.length != tankCount)
            throw new IllegalArgumentException("Input not in correct format: Tank count mismatch");

        this.tankCapacities = Arrays.stream(tankCapacities)
                .mapToInt(Integer::parseInt)
                .toArray();

        this.flowRate = flowRate;
    }

    public String doCalculation() {
        ArrayList<Double> tanksTimeToFill = new ArrayList<>();
        TreeSet<Double> sortedTimes = new TreeSet<>();

        var firstTankOverflowTimeInSeconds = (double) tankCapacities[0] / flowRate;

        tanksTimeToFill.add(firstTankOverflowTimeInSeconds);
        sortedTimes.add(firstTankOverflowTimeInSeconds);

        for (int i = 1; i < tankCapacities.length; i++) {
            double tankCapacity = tankCapacities[i];
            double previousTankTimeToFill = tanksTimeToFill.get(i - 1);
            double timeToFill = (double) tankCapacity / flowRate;
            double currentTimeToFill;

            if (timeToFill > previousTankTimeToFill) {
                double aloneFillingInSeconds = previousTankTimeToFill;

                double fillingLeftTimeInSeconds = tankCapacity - (aloneFillingInSeconds * flowRate);

                int count = sortedTimes.headSet(timeToFill).size();

                currentTimeToFill = aloneFillingInSeconds + (fillingLeftTimeInSeconds / (flowRate * (count + 1)));
            } else {
                currentTimeToFill = (double) tankCapacity / flowRate;
            }

            tanksTimeToFill.add(currentTimeToFill);
            sortedTimes.add(currentTimeToFill);
        }

        var maxTankOverflowTimeInSeconds = sortedTimes.last();
        var lastTankOverflowInSeconds = tanksTimeToFill.getLast();

        return String.format("%d %d", (int)Math.floor(lastTankOverflowInSeconds), (int) Math.floor(maxTankOverflowTimeInSeconds));
    }
}
