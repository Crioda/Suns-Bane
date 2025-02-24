package net.crit.sun.Item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.crit.sun.Sun;
import net.minecraft.client.font.UnihexFont;
import net.minecraft.client.render.DimensionEffects;
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
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Set;

public class Coolitem extends SwordItem {


    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    private int charge = 0;



    public Coolitem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.attackDamage = attackDamage + material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }






    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        double x =  user.getX();
        double y =  user.getY();
        double z =  user.getZ();


        if (!world.isClient()) {
            ServerWorld nether = user.getServer().getWorld(ServerWorld.NETHER);
            ServerWorld overworld = user.getServer().getWorld(ServerWorld.OVERWORLD);

            String isinoverworld = "yes";

            Set<PositionFlag> flags = Set.of(PositionFlag.X);
            int t = 1;




            if (user.hasStatusEffect(Sun.EMPOWERED_EFFECT) | user.isCreative()) {

                if (user.isCreative()) {

                    if (t == 1){
                        user.sendMessage(Text.keybind(isinoverworld));
                    }





                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                } else {


                    user.getItemCooldownManager().set(this, 100);
                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));


                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                }

            } else if (user.hasStatusEffect(Sun.TATER_EFFECT)) {

                user.teleport(nether, 0+x, 128, 0+z, flags, 10,10);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 10, 1));

                double radius = 5.0;

                world.getEntitiesByClass(
                        LivingEntity.class,
                        user.getBoundingBox().expand(radius),
                        entity -> entity != user
                ).forEach(entity -> {
                    double ex = entity.getX();
                    double ey = entity.getY();
                    double ez = entity.getZ();

                    entity.teleport(nether, ex+0, 128, ez+0, flags, 10,10);
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1));
                });



                for (int i = 0; i < 6; i++) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.LAVA,
                            x + 0, y + 0,
                            z + 0, 100, 2, 0.1, 2, 0);

                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMALL_FLAME,
                            x + 0, y + 0,
                            z + 0, 10, 2, 0.1, 2, 0);

                }
                user.getItemCooldownManager().set(this, 200);
                user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION, SoundCategory.PLAYERS, 1.0F, 1.0F);
                user.addStatusEffect(new StatusEffectInstance(Sun.EMPOWERED_EFFECT, 10000, -1));

            } else {
                //No power

                if (user.isCreative()) {



                    double radius = 5.0;  // Define the radius around the user

                    world.getEntitiesByClass(
                            LivingEntity.class,  // Target all living entities
                            user.getBoundingBox().expand(radius),  // Expand around the user
                            entity -> entity != user  // Exclude the user themselves
                    ).forEach(entity -> {
                        // Set the entity on fire for 5 seconds
                        entity.setOnFireFor(5);

                    });

                    user.getItemCooldownManager().set(this, 1);
                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);




                } else {

                    double radius = 5.0;  // Define the radius around the user

                    world.getEntitiesByClass(
                            LivingEntity.class,  // Target all living entities
                            user.getBoundingBox().expand(radius),  // Expand around the user
                            entity -> entity != user  // Exclude the user themselves
                    ).forEach(entity -> {
                        // Set the entity on fire for 5 seconds
                        entity.setOnFireFor(5);

                    });

                    user.getItemCooldownManager().set(this, 600);
                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                }

            }
        }


        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            int chargeChance = 10;




            if (attacker.getRandom().nextInt(100) <= chargeChance) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 150, 1), attacker);
                target.setOnFireFor(3);

            }
        }

        return super.postHit(stack, target, attacker);
    }
    }

