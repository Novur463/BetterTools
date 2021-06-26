package com.novur.bettertools.framework.math.gems;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.math.MathUtil;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Random;

public class GemHandler {
    private final BetterTools betterTools;
    private int minGems, maxGems;
    private float gemChance;
    private float explosiveMultiplier, laserMultiplier;
    private final Random random;

    public GemHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.random = new Random();
        init();
    }

    private void init() {
        this.minGems = betterTools.getConfig().getInt("gems.min");
        this.maxGems = betterTools.getConfig().getInt("gems.max");
        this.gemChance = (float) betterTools.getConfig().getDouble("gems.chance");
        this.explosiveMultiplier = (float) betterTools.getConfig().getDouble("gems.multiplier.explosive");
        this.laserMultiplier = (float) betterTools.getConfig().getDouble("gems.multiplier.laser");
    }

    public void reload() {
        init();
    }

    public int getGems() {
        return MathUtil.getThreadRandom().nextInt(minGems, maxGems + 1);
    }

    public boolean shouldDiscover() {
        return getRandomChance() < getGemChance();
    }

    public boolean shouldDiscover(ItemStack inHand, HashSet<Block> blocksToAccount) {
        int blocks = blocksToAccount.size();

        Tools tools = betterTools.getToolUtility().getFromItem(inHand);
        float multiplier = 0;
        float overallChance;
        if(tools != null) {

            if(tools == Tools.LASER_PICKAXE) {
                multiplier = laserMultiplier * blocks;
            }

            else if(tools == Tools.EXPLOSIVE_PICKAXE) {
                multiplier = explosiveMultiplier * blocks;
            }

            overallChance = getGemChance() + multiplier;
            int randomChance = getRandomChance();

            return randomChance < overallChance;
        }

        return false;
    }

    public int getMinGems() {
        return minGems;
    }

    public int getMaxGems() {
        return maxGems;
    }

    public float getGemChance() {
        return gemChance;
    }

    public float getExplosiveMultiplier() {
        return explosiveMultiplier;
    }

    public float getLaserMultiplier() {
        return laserMultiplier;
    }

    private int getRandomChance() {
        return random.nextInt(100);
    }

    public void giveGems(Player player) {
        int gems = getGems();
        betterTools.getTokenManager().addTokens(player, gems);
        player.sendMessage(betterTools.getStringUtil().color(betterTools.getConfig().getString("messages.gems.gemsAwarded").replace("{gems}", String.valueOf(gems))));
    }
}
