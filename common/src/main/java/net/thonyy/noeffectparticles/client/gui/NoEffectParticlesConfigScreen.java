package net.thonyy.noeffectparticles.client.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.thonyy.noeffectparticles.config.NoEffectParticlesConfig;

public final class NoEffectParticlesConfigScreen extends Screen {

    private final Screen parent;

    public NoEffectParticlesConfigScreen(Screen parent) {
        super(Component.translatable("screen.noeffectparticles.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 110;
        int y = this.height / 2 - 50;

        this.addRenderableWidget(
                Checkbox.builder(
                                Component.translatable(
                                        "screen.noeffectparticles.remove_own"
                                ),
                                this.font
                        )
                        .pos(x, y)
                        .maxWidth(220)
                        .selected(
                                NoEffectParticlesConfig.removeOwnParticles()
                        )
                        .onValueChange((checkbox, value) ->
                                NoEffectParticlesConfig.setRemoveOwnParticles(value)
                        )
                        .build()
        );

        this.addRenderableWidget(
                Checkbox.builder(
                                Component.translatable(
                                        "screen.noeffectparticles.remove_other_players"
                                ),
                                this.font
                        )
                        .pos(x, y + 30)
                        .maxWidth(220)
                        .selected(
                                NoEffectParticlesConfig.removeOtherPlayerParticles()
                        )
                        .onValueChange((checkbox, value) ->
                                NoEffectParticlesConfig.setRemoveOtherPlayerParticles(value)
                        )
                        .build()
        );

        this.addRenderableWidget(
                Checkbox.builder(
                                Component.translatable(
                                        "screen.noeffectparticles.remove_mobs"
                                ),
                                this.font
                        )
                        .pos(x, y + 60)
                        .maxWidth(220)
                        .selected(
                                NoEffectParticlesConfig.removeMobParticles()
                        )
                        .onValueChange((checkbox, value) ->
                                NoEffectParticlesConfig.setRemoveMobParticles(value)
                        )
                        .build()
        );

        this.addRenderableWidget(
                Button.builder(
                                Component.translatable("gui.done"),
                                button -> this.onClose()
                        )
                        .bounds(
                                this.width / 2 - 100,
                                y + 105,
                                200,
                                20
                        )
                        .build()
        );
    }

    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {
        graphics.centeredText(
                this.font,
                this.title,
                this.width / 2,
                this.height / 2 - 90,
                0xFFFFFFFF
        );

        super.extractRenderState(
                graphics,
                mouseX,
                mouseY,
                delta
        );
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(this.parent);
    }
}