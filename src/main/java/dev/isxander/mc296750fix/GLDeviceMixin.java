package dev.isxander.mc296750fix;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.opengl.GlDevice;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GlDevice.class)
public class GLDeviceMixin {
    @ModifyReturnValue(method = "getUniformOffsetAlignment", at = @At("RETURN"))
    private int fixUniformOffsetAlignment(int original) {
        return Math.max(original, 256);
    }
}