package net.crit.sun.Item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LongswordItem extends SwordItem {


    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;




    public LongswordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.attackDamage = attackDamage + material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }




    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            double radius = 5.0;  // Define the radius around the user

            world.getEntitiesByClass(
                    LivingEntity.class,  // Target all living entities
                    user.getBoundingBox().expand(radius),  // Expand around the user
                    entity -> entity != user  // Exclude the user themselves
            ).forEach(entity -> {
                // Set the entity on fire for 5 seconds
                entity.setOnFireFor(5);



            });
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 10, 0));


            // Apply cooldown and item damage
            user.getItemCooldownManager().set(this, 400);
            user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));

            // Play sound for ability use
            world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }



    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            // Set the chance for the effect to occur
            int chargeChance = 10; // 10% chance

            // Check if the random roll allows applying the effect
            if (attacker.getRandom().nextInt(100) <= chargeChance) {
                // Apply Levitation effect to the target
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1), attacker);
            }
        }

        // Call the parent method to ensure standard behavior
        return super.postHit(stack, target, attacker);
    }



    }

