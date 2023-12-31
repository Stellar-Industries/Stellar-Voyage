package lol.koblizek.stellarvoyage.screen.bloomery;

import com.mojang.blaze3d.systems.RenderSystem;
import lol.koblizek.stellarvoyage.util.References;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BloomeryScreen extends HandledScreen<BloomeryHandledScreen> implements References {

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/gui/bloomery.png");

    public BloomeryScreen(BloomeryHandledScreen handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        //vprave
        context.drawTexture(TEXTURE, x + backgroundWidth / 2 - 47, y + backgroundHeight / 4 - 2, 176, 0, 14, 14);
        //vlevo
        context.drawTexture(TEXTURE, x + backgroundWidth / 2 - 66, y + backgroundHeight / 4 - 2, 176, 0, 14, 14);


    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
