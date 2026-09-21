package net.thonyy.noeffectparticles.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.thonyy.noeffectparticles.client.gui.NoEffectParticlesConfigScreen;

public final class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return NoEffectParticlesConfigScreen::new;
    }
}