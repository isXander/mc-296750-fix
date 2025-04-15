# MC-296750 fix

This fixes the startup crash on AMD GPUs with Linux.

This mod increases the minimum size of UBO offset alignment to a minimum 256 (or larger if your system asks for it), which is typical on Windows.
On Mesa Linux, the minimum size is 4.

The unofficial word from Dinnerbone is:
> Somewhere I must have a UBO be too small and it's being masked by the giant alignment rules on windows
> If you want to try to mod it better, there's a method in GLDevice that returns this value. Change it to 256 (or 8 or 16 or whatever, lower is better but 256 works for sure) 

This mod does precisely that.

This gets the game to launch, and not crash, BUT there are severe rendering artifacts in-game.

## How to use

```kt
dependencies {
    modRuntimeOnly("dev.isxander:mc-296750-fix:1.0.0")
}
```

This will allow you to port your mods to the new snapshots and test them.