package com.example.examplemod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID)
public class RespawnHandler {
    private static final List<ScheduledBlock> blocksToRespawn = new ArrayList<>();

    public static void queueBlock(BlockPos pos, IBlockState state, int delay) {
        blocksToRespawn.add(new ScheduledBlock(pos, state, delay));
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Iterator<ScheduledBlock> it = blocksToRespawn.iterator();
            while (it.hasNext()) {
                ScheduledBlock sb = it.next();
                sb.ticks--;
                if (sb.ticks <= 0) {
                    World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
                    if (world.isAirBlock(sb.pos)) {
                        world.setBlockState(sb.pos, sb.state);
                    }
                    it.remove();
                }
            }
        }
    }

    private static class ScheduledBlock {
        BlockPos pos;
        IBlockState state;
        int ticks;

        ScheduledBlock(BlockPos pos, IBlockState state, int ticks) {
            this.pos = pos;
            this.state = state;
            this.ticks = ticks;
        }
    }
}
