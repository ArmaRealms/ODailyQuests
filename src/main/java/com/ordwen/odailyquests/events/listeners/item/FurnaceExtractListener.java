package com.ordwen.odailyquests.events.listeners.item;

import com.ordwen.odailyquests.configuration.essentials.Debugger;
import com.ordwen.odailyquests.configuration.essentials.UseCustomFurnaceResults;
import com.ordwen.odailyquests.quests.player.progression.PlayerProgressor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

public class FurnaceExtractListener extends PlayerProgressor implements Listener {

    @EventHandler
    public void onFurnaceExtractEvent(FurnaceExtractEvent event) {
        if (UseCustomFurnaceResults.isEnabled()) {
            Debugger.addDebug("CustomFurnaceExtractEvent is enabled. Skipping FurnaceExtractEvent.");
            return;
        }

        Debugger.addDebug("=========================================================================================");
        Debugger.addDebug("FurnaceExtractListener: onFurnaceExtractEvent summoned by " + event.getPlayer().getName() + " for " + event.getItemType() + ".");

        setPlayerQuestProgression(event, event.getPlayer(), event.getItemAmount(), "COOK");
    }
}

