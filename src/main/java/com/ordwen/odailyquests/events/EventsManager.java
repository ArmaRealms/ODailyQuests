package com.ordwen.odailyquests.events;

import com.ordwen.odailyquests.ODailyQuests;
import com.ordwen.odailyquests.configuration.essentials.UseCustomFurnaceResults;
import com.ordwen.odailyquests.configuration.integrations.ItemsAdderEnabled;
import com.ordwen.odailyquests.configuration.integrations.OraxenEnabled;
import com.ordwen.odailyquests.events.listeners.customs.CustomFurnaceExtractListener;
import com.ordwen.odailyquests.events.listeners.entity.EntityBreedListener;
import com.ordwen.odailyquests.events.listeners.entity.EntityDeathListener;
import com.ordwen.odailyquests.events.listeners.entity.EntityTameListener;
import com.ordwen.odailyquests.events.listeners.entity.PlayerInteractEntityListener;
import com.ordwen.odailyquests.events.listeners.entity.ShearEntityListener;
import com.ordwen.odailyquests.events.listeners.entity.SpawnerSpawnListener;
import com.ordwen.odailyquests.events.listeners.entity.custom.mobs.EliteMobDeathListener;
import com.ordwen.odailyquests.events.listeners.entity.custom.mobs.MythicMobDeathListener;
import com.ordwen.odailyquests.events.listeners.entity.custom.stackers.EntityUnstackListener;
import com.ordwen.odailyquests.events.listeners.global.BucketFillListener;
import com.ordwen.odailyquests.events.listeners.global.PlayerDeathListener;
import com.ordwen.odailyquests.events.listeners.global.PlayerExpChangeListener;
import com.ordwen.odailyquests.events.listeners.global.PlayerInteractListener;
import com.ordwen.odailyquests.events.listeners.global.PlayerLevelChangeListener;
import com.ordwen.odailyquests.events.listeners.global.PlayerRespawnListener;
import com.ordwen.odailyquests.events.listeners.integrations.itemsadder.CustomBlockBreakListener;
import com.ordwen.odailyquests.events.listeners.integrations.itemsadder.ItemsAdderLoadDataListener;
import com.ordwen.odailyquests.events.listeners.integrations.oraxen.OraxenItemsLoadedListener;
import com.ordwen.odailyquests.events.listeners.inventory.InventoryClickListener;
import com.ordwen.odailyquests.events.listeners.inventory.InventoryCloseListener;
import com.ordwen.odailyquests.events.listeners.item.BlockBreakListener;
import com.ordwen.odailyquests.events.listeners.item.BlockDropItemListener;
import com.ordwen.odailyquests.events.listeners.item.BlockPlaceListener;
import com.ordwen.odailyquests.events.listeners.item.CraftItemListener;
import com.ordwen.odailyquests.events.listeners.item.EnchantItemListener;
import com.ordwen.odailyquests.events.listeners.item.FurnaceExtractListener;
import com.ordwen.odailyquests.events.listeners.item.PickupItemListener;
import com.ordwen.odailyquests.events.listeners.item.PlayerDropItemListener;
import com.ordwen.odailyquests.events.listeners.item.PlayerFishListener;
import com.ordwen.odailyquests.events.listeners.item.PlayerHarvestBlockListener;
import com.ordwen.odailyquests.events.listeners.item.PlayerItemConsumeListener;
import com.ordwen.odailyquests.events.listeners.item.ProjectileLaunchListener;
import com.ordwen.odailyquests.events.listeners.item.SmithItemListener;
import com.ordwen.odailyquests.events.listeners.item.StructureGrowListener;
import com.ordwen.odailyquests.events.listeners.vote.VotifierListener;
import com.ordwen.odailyquests.externs.hooks.mobs.EliteMobsHook;
import com.ordwen.odailyquests.externs.hooks.mobs.MythicMobsHook;
import com.ordwen.odailyquests.externs.hooks.stackers.WildStackerHook;
import org.bukkit.Bukkit;

public class EventsManager {

    private final ODailyQuests oDailyQuests;

    public EventsManager(final ODailyQuests oDailyQuests) {
        this.oDailyQuests = oDailyQuests;
    }

    /**
     * Registers all events.
     */
    public void registerListeners() {

        // entity events
        Bukkit.getPluginManager().registerEvents(new EntityBreedListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new EntityTameListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new ShearEntityListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new SpawnerSpawnListener(), oDailyQuests);
        //Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), oDailyQuests);

        if (EliteMobsHook.isEliteMobsSetup()) {
            Bukkit.getPluginManager().registerEvents(new EliteMobDeathListener(), oDailyQuests);
        }

        if (MythicMobsHook.isMythicMobsSetup()) {
            Bukkit.getPluginManager().registerEvents(new MythicMobDeathListener(), oDailyQuests);
        }

        if (WildStackerHook.isWildStackerSetup()) {
            Bukkit.getPluginManager().registerEvents(new EntityUnstackListener(), oDailyQuests);
        }

        // global events
        Bukkit.getPluginManager().registerEvents(new BucketFillListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerExpChangeListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerLevelChangeListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), oDailyQuests);

        // item events
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new CraftItemListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new SmithItemListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new EnchantItemListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new FurnaceExtractListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PickupItemListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerFishListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerItemConsumeListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new ProjectileLaunchListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new BlockDropItemListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerHarvestBlockListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), oDailyQuests);
        Bukkit.getPluginManager().registerEvents(new StructureGrowListener(), oDailyQuests);

        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Votifier")) {
            Bukkit.getPluginManager().registerEvents(new VotifierListener(), oDailyQuests);
        }

        // inventory events
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), oDailyQuests);

        // custom events
        if (ItemsAdderEnabled.isEnabled()
                || OraxenEnabled.isEnabled()
                || UseCustomFurnaceResults.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new CustomFurnaceExtractListener(), oDailyQuests);
        }

        // other plugins events
        if (ItemsAdderEnabled.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new ItemsAdderLoadDataListener(oDailyQuests), oDailyQuests);
            Bukkit.getPluginManager().registerEvents(new CustomBlockBreakListener(), oDailyQuests);
        }

        if (OraxenEnabled.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new OraxenItemsLoadedListener(oDailyQuests), oDailyQuests);
        }
    }
}
