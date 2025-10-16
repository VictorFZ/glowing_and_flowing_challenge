package cint.challenge;

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
        TreeSet<Double> tanksTimeToFillSorted = new TreeSet<>();

        var lastTankOverflowInSeconds = (double) tankCapacities[0] / flowRate;

        tanksTimeToFillSorted.add(lastTankOverflowInSeconds);

        var maxTankOverflowTimeInSeconds = lastTankOverflowInSeconds;

        for (int i = 1; i < tankCapacities.length; i++) {
            double tankCapacity = tankCapacities[i];
            double timeToFill = (double) tankCapacity / flowRate;
            double currentTimeToFill;

            if (timeToFill > lastTankOverflowInSeconds) {
                double fillingLeftTimeInSeconds = tankCapacity - (lastTankOverflowInSeconds * flowRate);

                int tankCountWithSmallerTimeToFill = tanksTimeToFillSorted.headSet(timeToFill).size();

                currentTimeToFill = lastTankOverflowInSeconds + (fillingLeftTimeInSeconds / (flowRate * (tankCountWithSmallerTimeToFill + 1)));
            } else {
                currentTimeToFill = (double) tankCapacity / flowRate;
                tanksTimeToFillSorted = new TreeSet<>();
            }

            if(currentTimeToFill > maxTankOverflowTimeInSeconds) {
                maxTankOverflowTimeInSeconds = currentTimeToFill;
            }

            lastTankOverflowInSeconds = currentTimeToFill;
            tanksTimeToFillSorted.add(currentTimeToFill);
        }

        return String.format("%d %d", (int)Math.floor(lastTankOverflowInSeconds), (int) Math.floor(maxTankOverflowTimeInSeconds));
    }
}
