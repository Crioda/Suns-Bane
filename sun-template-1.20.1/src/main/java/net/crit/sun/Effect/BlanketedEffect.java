package net.crit.sun.Effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Set;

public class BlanketedEffect extends StatusEffect {
    public BlanketedEffect() {
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
        RegistryKey<World> sundimKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier("sun", "sundim"));
        ServerWorld sun = entity.getServer().getWorld(sundimKey);


        entity.teleport(sun, x+0, y+0, z+0, flags, yaw+0,pitch+0);


        super.onRemoved(entity, attributes, amplifier);
    }
}