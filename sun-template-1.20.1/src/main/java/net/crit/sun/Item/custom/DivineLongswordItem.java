package net.crit.sun.Item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.crit.sun.Sun;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Set;

public class DivineLongswordItem extends SwordItem {


    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


    public DivineLongswordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.attackDamage = attackDamage + material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    @Override
    public int getMaxUseTime(ItemStack stack) {



        return 1000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        double x =  user.getX();
        double y =  user.getY();
        double z =  user.getZ();
        float yaw = user.getYaw();
        float pitch = user.getPitch();


        if (!world.isClient()) {

            RegistryKey<World> sundimKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier("sun", "sundim"));
            RegistryKey<World> sundimKey2 = RegistryKey.of(RegistryKeys.WORLD, new Identifier("sun", "sundim2"));

            ServerWorld sun = Objects.requireNonNull(user.getServer()).getWorld(sundimKey);
            ServerWorld sun2 = user.getServer().getWorld(sundimKey2);


            boolean onSun = user.getWorld().getDimension().bedWorks() && user.getWorld().getDimension().respawnAnchorWorks();
            Set<PositionFlag> flags = Set.of(PositionFlag.X);

            if (user.hasStatusEffect(Sun.EMPOWERED_EFFECT)) {
                if (user.isCreative()) {
                    for (int i = 0; i < 10; i++) {
                        ((ServerWorld) world).spawnParticles(ParticleTypes.CLOUD,
                                x + 0, y + 0,
                                z + 0, 100, .3, 1, .3, 0.1);

                        ((ServerWorld) world).spawnParticles(ParticleTypes.ASH,
                                x + 0, y + 0,
                                z + 0, 10, .5, 1.5, .5, 0.1);

                    }

                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                    world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 10.0F, -1.0F);

                    if (onSun) {
                        user.teleport(sun2, x+0, y+0, z+0, flags, yaw+0,pitch+0);
                        user.addStatusEffect(new StatusEffectInstance(Sun.BLANKETED_EFFECT, 250, 0));

                    } else {



                        user.teleport(sun, x+0, y+0, z+0, flags, yaw+0,pitch+0);
                        user.removeStatusEffect(Sun.BLANKETED_EFFECT);
                    }

                } else {
                    for (int i = 0; i < 10; i++) {
                        ((ServerWorld) world).spawnParticles(ParticleTypes.CLOUD,
                                x + 0, y + 0,
                                z + 0, 100, .3, 1, .3, 0.1);

                        ((ServerWorld) world).spawnParticles(ParticleTypes.ASH,
                                x + 0, y + 0,
                                z + 0, 10, .5, 1.5, .5, 0.1);

                    }

                    world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 10.0F, -1.0F);

                    if (onSun) {
                        user.teleport(sun2, x+0, y+0, z+0, flags, yaw+0,pitch+0);
                        user.addStatusEffect(new StatusEffectInstance(Sun.BLANKETED_EFFECT, 250, 0));
                        user.getItemCooldownManager().set(this, 50);

                    } else {
                        user.teleport(sun, x+0, y+0, z+0, flags, yaw+0,pitch+0);
                        user.removeStatusEffect(Sun.BLANKETED_EFFECT);
                        user.getItemCooldownManager().set(this, 200);
                    }



                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));

                }
            //Charged ability
            } else if (user.hasStatusEffect(Sun.CHARGED_EFFECT)) {

                for (int i = 0; i < 6; i++) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.LAVA,
                            x + 0, y + 0,
                            z + 0, 100, 2, 0.1, 2, 0);

                    ((ServerWorld) world).spawnParticles(ParticleTypes.SMALL_FLAME,
                            x + 0, y + 0,
                            z + 0, 10, 2, 0.1, 2, 0);

                }

                double radius = 5.0;

                world.getEntitiesByClass(
                        LivingEntity.class,
                        user.getBoundingBox().expand(radius),
                        entity -> entity != user
                ).forEach(entity -> {
                    double ex = entity.getX();
                    double ey = entity.getY();
                    double ez = entity.getZ();

                    entity.teleport(sun, ex+0, 128, ez+0, flags, 10,10);
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 0));
                    entity.addStatusEffect(new StatusEffectInstance(Sun.TETHERED_EFFECT, 72000, 0));
                });


                user.removeStatusEffect(Sun.CHARGED_EFFECT);
                user.teleport(sun, 0+x, 128, 0+z, flags, 10,10);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 1));
                user.getItemCooldownManager().set(this, 200);
                user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION, SoundCategory.PLAYERS, 1.0F, 1.0F);
                user.addStatusEffect(new StatusEffectInstance(Sun.EMPOWERED_EFFECT, 36000, 0));
                user.addStatusEffect(new StatusEffectInstance(Sun.TETHERED_EFFECT, 72000, 0));

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
                    for (int i = 0; i < 21; i++) {

                        ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME,
                                x + 0, y + .2,
                                z + 0, 15, 2, 0.1, 2, 0);

                    }
                    ((ServerWorld) world).spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            x + 0, y + 0,
                            z + 0, 50, 2, 0.1, 2, 0.2);




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

                    for (int i = 0; i < 21; i++) {

                        ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME,
                                x + 0, y + .2,
                                z + 0, 15, 2, 0.1, 2, 0);

                    }
                    ((ServerWorld) world).spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            x + 0, y + 0,
                            z + 0, 50, 2, 0.1, 2, 0.2);

                    user.getItemCooldownManager().set(this, 150);
                    user.getStackInHand(hand).damage(1, user, (player) -> player.sendToolBreakStatus(hand));
                    world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                }
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    //After attack giving charged effect

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            int chargeChance = 10;
            if (attacker.getRandom().nextInt(50) <= chargeChance) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 150, 1), attacker);
            }
            if (target.isPlayer()) {
                if (attacker.getRandom().nextInt(100) <= chargeChance) {
                    attacker.addStatusEffect(new StatusEffectInstance(Sun.CHARGED_EFFECT, 36000, 0));

                }
            } else {
                if (attacker.getRandom().nextInt(1000) <= chargeChance) {
                    attacker.addStatusEffect(new StatusEffectInstance(Sun.CHARGED_EFFECT, 36000, 0));
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
    }

