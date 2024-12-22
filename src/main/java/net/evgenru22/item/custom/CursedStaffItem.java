package net.evgenru22.item.custom;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.effect.ModStatusEffects;
import net.evgenru22.util.IEntityDataSaver;
import net.evgenru22.util.IntegrityOfSoulData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CursedStaffItem extends Item {
    public CursedStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack wandStack = user.getStackInHand(hand);
        ItemStack offHandStack = user.getOffHandStack();

        if (!world.isClient()) {
            if (offHandStack.getItem() instanceof MagicTokenItem) {
                String spell = MagicTokenItem.getSpell(offHandStack);
                if (!spell.isEmpty()) {
                    CursedStaffItem.setCurrentSpell(wandStack, spell);
                    user.sendMessage(Text.literal("Set current spell to: " + spell), true);
                }
            } else {
                String spell = CursedStaffItem.getCurrentSpell(wandStack);

                switch (spell) {
                    case "protection" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 5) {
                            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 5);
                        }
                    }
                    case "fire" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 10) {
                            Vec3d playerPos = user.getEyePos();
                            Vec3d lookDir = user.getRotationVec(1.0f);

                            FireballEntity entity = new FireballEntity(world, user, lookDir.x, lookDir.y, lookDir.z, 2);

                            entity.setPos(playerPos.x + lookDir.x * 1.5,
                                    playerPos.y + lookDir.y * 1.5,
                                    playerPos.z + lookDir.z * 1.5);

                            world.spawnEntity(entity);
                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 10);
                        }
                    }
                    case "repulsion" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 8) {
                            Vec3d playerPos = user.getPos();

                            double radius = 3.0;
                            double pushStrength = 2.0;

                            List<Entity> nearbyEntities = world.getOtherEntities(user, user.getBoundingBox().expand(radius));

                            for (Entity entity : nearbyEntities) {
                                if (entity instanceof LivingEntity) {
                                    Vec3d direction = entity.getPos().subtract(playerPos).normalize();

                                    entity.setVelocity(direction.multiply(pushStrength));
                                    entity.velocityModified = true;
                                }
                            }

                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 8);
                        }
                    }
                    case "slowest falling" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 8) {
                            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 100, 3));

                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 8);
                        }
                    }
                    case "freezing" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 40) {
                            double maxDistance = 5;

                            EntityHitResult hitResult = raycastEntities(world, user, maxDistance);
                            if (hitResult != null && hitResult.getEntity() instanceof LivingEntity target) {
                                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 10));

                                for (int i = 0; i < 20; i++) {
                                    double offsetX = (world.random.nextDouble() - 0.5) * 0.5;
                                    double offsetY = world.random.nextDouble() * target.getHeight();
                                    double offsetZ = (world.random.nextDouble() - 0.5) * 0.5;

                                    world.addParticle(ParticleTypes.SNOWFLAKE,
                                            target.getX() + offsetX, target.getY() + offsetY, target.getZ() + offsetZ,
                                            0, 0, 0);
                                }
                            }

                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 40);
                        }
                    }
                    case "regenerate" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 10) {
                            user.heal(5);

                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 10);
                        }
                    }
                    case "ichor" -> {
                        if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 25) {
                            double maxDistance = 5;

                            EntityHitResult hitResult = raycastEntities(world, user, maxDistance);
                            if (hitResult != null && hitResult.getEntity() instanceof LivingEntity target) {
                                target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.ICHOR_EFFECT, 160, 1));
                            }

                            IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 25);
                        }
                    }
                    default -> user.sendMessage(Text.literal("Nothing to do"), true);
                }
            }
        }

        return TypedActionResult.success(wandStack);
    }

    private EntityHitResult raycastEntities(World world, PlayerEntity player, double maxDistance) {
        Vec3d playerEyePos = player.getEyePos();
        Vec3d lookDir = player.getRotationVec(1.0f);
        Vec3d targetPos = playerEyePos.add(lookDir.multiply(maxDistance));

        Box searchBox = new Box(playerEyePos, targetPos).expand(1.0);

        List<Entity> entities = world.getOtherEntities(player, searchBox);

        EntityHitResult closestHit = null;
        double closestDistance = maxDistance;

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity && entity != player) {
                Box entityBox = entity.getBoundingBox().expand(0.5);
                Optional<Vec3d> hit = entityBox.raycast(playerEyePos, targetPos);
                if (hit.isPresent()) {
                    double distance = playerEyePos.distanceTo(hit.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestHit = new EntityHitResult(entity, hit.get());
                    }
                }
            }
        }

        return closestHit;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
    }

    public static String getCurrentSpell(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt().contains("currentSpell")) {
            return stack.getNbt().getString("currentSpell");
        }
        return "";
    }

    public static void setCurrentSpell(ItemStack stack, String spell) {
        stack.getOrCreateNbt().putString("currentSpell", spell);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (getCurrentSpell(stack).isEmpty()) {
            tooltip.add(Text.literal("[EMPTY]").withColor(Colors.GRAY));
        } else {
            tooltip.add(Text.of(getCurrentSpell(stack)));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

}
