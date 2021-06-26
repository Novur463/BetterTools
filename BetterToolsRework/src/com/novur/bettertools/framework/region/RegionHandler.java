package com.novur.bettertools.framework.region;

import com.novur.bettertools.BetterTools;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.block.Block;

import java.util.*;

public class RegionHandler {
    private final BetterTools betterTools;
    private final Map<RegionType, List<String>> regionMap;
    private final Map<RegionType, String> regionString;
    public RegionHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.regionMap = new HashMap<>();
        this.regionString = new HashMap<>();

        init();
    }

    private void init() {
        for(RegionType regionType : RegionType.values()) {
            List<String> set = betterTools.getConfig().getStringList(regionType.getConfigPath());

            regionMap.put(regionType, set);

            StringBuilder stringBuilder = new StringBuilder();
            for(String string : set) {
                if(!set.get(set.size()-1).equalsIgnoreCase(string)) {
                    stringBuilder.append(string).append(", ");
                } else {
                    stringBuilder.append(string);
                }
            }

            regionString.put(regionType, stringBuilder.toString());
        }
    }

    public void reload() {
        regionMap.clear();
        init();
    }


    public boolean inRegion(RegionType regionType, Block block) {
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(block.getWorld()));
        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(block.getLocation()));

        for(ProtectedRegion protectedRegion : applicableRegionSet.getRegions()) {
            if(regionMap.get(regionType).contains(protectedRegion.getId())) {
                return true;
            }
        }

        return false;
    }


    public Map<RegionType, List<String>> getRegionMap() {
        return regionMap;
    }

    public String getRegionList(RegionType regionType) {
        return regionString.get(regionType);
    }

}
