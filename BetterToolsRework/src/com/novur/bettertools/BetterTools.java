package com.novur.bettertools;

import com.novur.bettertools.command.CommandHandler;
import com.novur.bettertools.events.gem.Gems;
import com.novur.bettertools.events.ice.IceListener;
import com.novur.bettertools.events.item.ItemDamage;
import com.novur.bettertools.events.tool.block.BlockPickaxe;
import com.novur.bettertools.events.tool.bountiful.BountifulPickaxe;
import com.novur.bettertools.events.tool.explosive.ExplosivePickaxe;
import com.novur.bettertools.events.tool.laser.LaserPickaxe;
import com.novur.bettertools.events.tool.lj.LumberjackAxe;
import com.novur.bettertools.events.tool.smelters.SmeltersPickaxe;
import com.novur.bettertools.framework.crate.CrateUtil;
import com.novur.bettertools.framework.effect.EffectHandler;
import com.novur.bettertools.framework.ice.Ice;
import com.novur.bettertools.framework.item.ItemUtility;
import com.novur.bettertools.framework.item.ToolItems;
import com.novur.bettertools.framework.math.FortuneUtil;
import com.novur.bettertools.framework.math.experience.ExperienceUtil;
import com.novur.bettertools.framework.math.gems.GemHandler;
import com.novur.bettertools.framework.region.RegionHandler;
import com.novur.bettertools.framework.sound.SoundHandler;
import com.novur.bettertools.framework.string.StringUtil;
import com.novur.bettertools.framework.tool.ToolUtility;
import com.novur.bettertools.framework.tool.tools.*;
import com.novur.bettertools.framework.tool.tools.aoe.AOEUtil;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterTools extends JavaPlugin {
    //Tool handlers
    private BlockPickHandler blockPickHandler;
    private SmelterPickHandler smelterPickHandler;
    private LumberjackAxeHandler lumberjackAxeHandler;
    private LaserPickHandler laserPickHandler;
    private BountifulPickHandler bountifulPickHandler;

    //Util
    private ToolUtility toolUtility;
    private RegionHandler regionHandler;
    private ExperienceUtil experienceUtil;
    private FortuneUtil fortuneUtil;
    private CrateUtil crateUtil;
    private StringUtil stringUtil;
    private ToolItems toolItems;
    private SoundHandler soundHandler;
    private EffectHandler effectHandler;
    private AOEUtil aoeUtil;
    private ItemUtility itemUtility;
    private Ice ice;
    private CommandHandler commandHandler;
    private TokenManager tokenManager;
    private GemHandler gemHandler;

    @Override
    public void onEnable() {
        //Tool handlers
        blockPickHandler = new BlockPickHandler();
        smelterPickHandler = new SmelterPickHandler();
        lumberjackAxeHandler = new LumberjackAxeHandler(this);
        laserPickHandler = new LaserPickHandler(this);
        bountifulPickHandler = new BountifulPickHandler(this);

        //Util
        toolUtility = new ToolUtility(this);
        regionHandler = new RegionHandler(this);
        experienceUtil = new ExperienceUtil();
        fortuneUtil = new FortuneUtil();
        crateUtil = new CrateUtil();
        stringUtil = new StringUtil(this);
        toolItems = new ToolItems(this);
        soundHandler = new SoundHandler(this);
        effectHandler = new EffectHandler(this);
        aoeUtil = new AOEUtil(this);
        itemUtility = new ItemUtility(this);
        ice = new Ice(this);
        commandHandler = new CommandHandler(this);
        tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        gemHandler = new GemHandler(this);

        //getCommand("bettertools").setExecutor(new BTools(this));

        commandHandler.registerCommands();

        getServer().getPluginManager().registerEvents(new SmeltersPickaxe(this),this);
        getServer().getPluginManager().registerEvents(new BlockPickaxe(this), this);
        getServer().getPluginManager().registerEvents(new LumberjackAxe(this), this);
        getServer().getPluginManager().registerEvents(new LaserPickaxe(this), this);
        getServer().getPluginManager().registerEvents(new ExplosivePickaxe(this), this);
        getServer().getPluginManager().registerEvents(new BountifulPickaxe(this), this);

        getServer().getPluginManager().registerEvents(new IceListener(this), this);
        getServer().getPluginManager().registerEvents(new Gems(this), this);
        getServer().getPluginManager().registerEvents(new ItemDamage(this), this);

        getCommandHandler().registerCommands();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public void reload() {
        reloadConfig();
        regionHandler.reload();
        toolItems.reload();
        soundHandler.reload();
        effectHandler.reload();
        laserPickHandler.reload();
        gemHandler.reload();
    }

    public BlockPickHandler getBlockPickHandler() {
        return blockPickHandler;
    }

    public SmelterPickHandler getSmelterPickHandler() {
        return smelterPickHandler;
    }

    public LumberjackAxeHandler getLumberjackAxeHandler() {
        return lumberjackAxeHandler;
    }

    public LaserPickHandler getLaserPickHandler() {
        return laserPickHandler;
    }

    public BountifulPickHandler getBountifulPickHandler() {
        return bountifulPickHandler;
    }

    //Util
    public ToolUtility getToolUtility() {
        return toolUtility;
    }

    public RegionHandler getRegionHandler() {
        return regionHandler;
    }

    public ExperienceUtil getExperienceUtil() {
        return experienceUtil;
    }

    public FortuneUtil getFortuneUtil() {
        return fortuneUtil;
    }

    public CrateUtil getCrateUtil() {
        return crateUtil;
    }

    public StringUtil getStringUtil() {
        return stringUtil;
    }

    public ToolItems getToolItems() {
        return toolItems;
    }

    public SoundHandler getSoundHandler() {
        return soundHandler;
    }

    public EffectHandler getEffectHandler() {
        return effectHandler;
    }

    public AOEUtil getAOEUtil() {
        return aoeUtil;
    }

    public ItemUtility getItemUtility() {
        return itemUtility;
    }

    public Ice getIce() {
        return ice;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public GemHandler getGemHandler() {
        return gemHandler;
    }
}
