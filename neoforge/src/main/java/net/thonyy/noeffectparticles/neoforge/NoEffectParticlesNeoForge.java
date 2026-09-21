package net.thonyy.noeffectparticles.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.thonyy.noeffectparticles.client.gui.NoEffectParticlesConfigScreen;

@Mod(
        value = "noeffectparticles",
        dist = Dist.CLIENT
)
public final class NoEffectParticlesNeoForge {

    public NoEffectParticlesNeoForge(ModContainer container) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (minecraft, parent) ->
                        new NoEffectParticlesConfigScreen(parent)
        );
    }
}