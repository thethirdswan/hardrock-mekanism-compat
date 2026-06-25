package com.thethirdswan.hardrock_mek_compat.mixin;

import mekanism.api.gear.IModule;
import mekanism.common.content.gear.mekasuit.ModuleNutritionalInjectionUnit;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.capabilities.food.TFCFoodData;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.NUTRIMIX;

@Mixin(ModuleNutritionalInjectionUnit.class)
public class NutritionalInjectionMixin {
    @Inject(method = "tickServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    private void onInjection(IModule<ModuleNutritionalInjectionUnit> module, Player player, CallbackInfo ci) {
        NUTRIMIX.get().getDefaultInstance().getCapability(FoodCapability.CAPABILITY).ifPresent(food -> ((TFCFoodData) player.getFoodData()).eat(food));
    }
}
