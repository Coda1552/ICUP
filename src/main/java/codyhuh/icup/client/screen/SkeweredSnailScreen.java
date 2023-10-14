package codyhuh.icup.client.screen;

import codyhuh.icup.common.menu.SkeweredSnailMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SkeweredSnailScreen extends AbstractContainerScreen<SkeweredSnailMenu> {
   private static final ResourceLocation CONTAINER_LOCATION = new ResourceLocation("textures/gui/container/dispenser.png");

   public SkeweredSnailScreen(SkeweredSnailMenu p_98685_, Inventory p_98686_, Component p_98687_) {
      super(p_98685_, p_98686_, Component.translatable("menu.icup.skewered_snail"));
   }

   protected void init() {
      super.init();
      this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
   }

   @Override
   protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {
      RenderSystem.setShader(GameRenderer::getPositionTexShader);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShaderTexture(0, CONTAINER_LOCATION);

      int i = (this.width - this.imageWidth) / 2;
      int j = (this.height - this.imageHeight) / 2;
      p_283065_.blit(CONTAINER_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
   }

}