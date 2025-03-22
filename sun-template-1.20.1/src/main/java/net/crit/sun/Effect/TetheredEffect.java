package net.crit.sun.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;

import java.util.Set;

public class TetheredEffect extends StatusEffect {
    public TetheredEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {

        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        Set<PositionFlag> flags = Set.of(PositionFlag.X);
        double x =  entity.getX();
        double y =  entity.getY();
        double z =  entity.getZ();
        float yaw = entity.getYaw();
        float pitch = entity.getPitch();
        ServerWorld overworld = entity.getServer().getWorld(ServerWorld.OVERWORLD);

        entity.teleport(overworld, x+0, y+0, z+0, flags, yaw+0,pitch+0);


        super.onRemoved(entity, attributes, amplifier);
    }
}