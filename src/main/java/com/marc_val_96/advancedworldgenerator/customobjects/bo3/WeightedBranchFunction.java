package com.marc_val_96.advancedworldgenerator.customobjects.bo3;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.customobjects.Branch;
import com.marc_val_96.advancedworldgenerator.customobjects.CustomObjectCoordinate;
import com.marc_val_96.advancedworldgenerator.exception.InvalidConfigException;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class WeightedBranchFunction extends BranchFunction implements Branch {

    /**
     * At the end of the loading process, this value is equal to the sum of
     * the individual branch chances
     */
    public double cumulativeChance = 0;

    public WeightedBranchFunction(BO3Config config, List<String> args) throws InvalidConfigException {
        super(config);
        branches = new TreeSet<BranchNode>();
        cumulativeChance = readArgs(args, true);
    }

    @Override
    public CustomObjectCoordinate toCustomObjectCoordinate(LocalWorld world, Random random, int x, int y, int z) {
        double randomChance = random.nextDouble() * (totalChance != -1
                ? totalChance
                : (cumulativeChance >= 100
                ? cumulativeChance
                : 100));
        AWG.log(LogMarker.TRACE, "W-Branch: chance_max - {}", randomChance);
        for (BranchNode branch : branches) {
            AWG.log(LogMarker.TRACE, "  {} trying to spawn! #{}", branch.getCustomObject().getName(), branch.getChance());
            if (branch.getChance() >= randomChance) {
                AWG.log(LogMarker.TRACE, "  Successful Spawn");
                return new CustomObjectCoordinate(branch.getCustomObject(), branch.getRotation(), x + this.x, y + this.y, z + this.z);
            }
        }
        AWG.log(LogMarker.TRACE, "  No Spawn");
        return null;
    }

    @Override
    protected String getConfigName() {
        return "WeightedBranch";
    }

}
