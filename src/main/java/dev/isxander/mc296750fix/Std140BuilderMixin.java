package dev.isxander.mc296750fix;

import com.mojang.blaze3d.buffers.Std140Builder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

@Mixin(value = Std140Builder.class, remap = false)
public class Std140BuilderMixin {
    @Shadow @Final private ByteBuffer buffer;
    @Unique private int blockStart;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void trackBufferStart(ByteBuffer buf, CallbackInfo ci) {
        this.blockStart = buf.position();
    }

    @Overwrite
    public Std140Builder align(int alignment) {
        int absPos = buffer.position();
        int relPos = absPos - blockStart;
        int paddedRel = (relPos + alignment - 1) / alignment * alignment;
        buffer.position(blockStart + paddedRel);
        return (Std140Builder) (Object) this;
    }
}
