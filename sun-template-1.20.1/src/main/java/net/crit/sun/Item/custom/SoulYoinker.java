package net.crit.sun.Item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Set;

public class SoulYoinker extends SwordItem {
    public SoulYoinker(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {

        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            ServerWorld nether = user.getServer().getWorld(ServerWorld.NETHER);
            Set<PositionFlag> flags = Set.of(PositionFlag.X);

            user.teleport(nether, 10, 128, 10, flags, 10,10);
        }

        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!attacker.getWorld().isClient){
                final int chargeChance = 50;

            if (target.getRandom().nextInt(100) <= chargeChance) {

                target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 1), attacker);
            }

        }

        return super.postHit(stack, target, attacker);
    }
}
