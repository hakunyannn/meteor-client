package minegame159.meteorclient.mixin;

//Created by squidoodly 21/05/2020

import minegame159.meteorclient.modules.ModuleManager;
import minegame159.meteorclient.modules.misc.BypassDeathScreen;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    protected DeathScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    protected void init(CallbackInfo ci) {
        if(ModuleManager.INSTANCE.get(BypassDeathScreen.class).isActive()) {
            this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 48, 200, 20, "Ghost Spectate", (buttonWidgetx) -> {
                Utils.sendMessage("#redYou gay nigga 3");
                ModuleManager.INSTANCE.get(BypassDeathScreen.class).shouldBypass = true;
                this.minecraft.openScreen(null);
            }));
        }
    }
}