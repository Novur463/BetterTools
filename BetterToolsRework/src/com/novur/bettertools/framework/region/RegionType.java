package com.novur.bettertools.framework.region;

public enum RegionType {
    MINE("regions.mine"),
    LOG("regions.log");

    String configPath;

    RegionType(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}
