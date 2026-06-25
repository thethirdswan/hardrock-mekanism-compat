package com.thethirdswan.hardrock_mek_compat.mixin;

import mekanism.common.item.gear.ItemCanteen;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.NUTRIMIX;

@Mixin(ItemCanteen.class)
public abstract class ItemCanteenMixin {
    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    private void eatWithTFC(FoodData instance, int p_38708_, float p_38709_, ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            NUTRIMIX.get().getDefaultInstance().getCapability(FoodCapability.CAPABILITY).ifPresent(food -> ((TFCFoodData) player.getFoodData()).eat(food));
        }
    }
}