package com.thethirdswan.hardrock_mek_compat.mixin;

import mekanism.common.item.ItemNutritionalPasteBucket;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.NUTRIMIX;

@Mixin(ItemNutritionalPasteBucket.class)
public abstract class NutritionalPasteBucketMixin extends BucketItem {
    public NutritionalPasteBucketMixin(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    private void eatWithTFC(FoodData instance, int p_38708_, float p_38709_, ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            NUTRIMIX.get().getDefaultInstance().getCapability(FoodCapability.CAPABILITY).ifPresent(food -> ((TFCFoodData) player.getFoodData()).eat(food));
        }
    }
}