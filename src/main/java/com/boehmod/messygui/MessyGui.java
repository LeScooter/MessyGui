package com.boehmod.messygui;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod(modid = MessyGui.MODID, version = MessyGui.VERSION)
public final class MessyGui {

    /**
     * The ID of the mod
     */
    public static final String MODID = "mgui";
    /**
     * The version of the mod
     */
    public static final String VERSION = "0.1a";

    /**
     * On FML Initialization Event - Called when the FML is being initialized
     *
     * @param event - Given {@link FMLInitializationEvent} called
     */
    @EventHandler
    public void onFMLInitializationEvent(final FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * On Init Gui Event - Called when Minecraft opens a new GUI instance
     *
     * @param event - Given {@link GuiOpenEvent} called
     */
    @SubscribeEvent
    public void onInitGuiEvent(final GuiScreenEvent.InitGuiEvent.Post event) {

        //Create a new random instance
        Random random = new Random();

        //Set the max change value
        int maxChange = 45;

        //Iterate through each button
        event.buttonList.forEach(button -> {

            //Fetch the width and height of the button
            int buttonWidth = button.width;
            int buttonHeight = button.height;

            //Set the initial x/y positions of the button
            int xPosition = button.xPosition += getRandom(-maxChange, maxChange, random);
            int yPosition = button.yPosition += getRandom(-maxChange, maxChange, random);

            //While the x/y positions are out of the screen, re-organize
            while (xPosition < 0 || (xPosition + buttonWidth) > event.gui.width || yPosition < 0 || (yPosition + buttonHeight) > event.gui.height) {
                xPosition = button.xPosition += getRandom(-maxChange, maxChange, random);
                yPosition = button.yPosition += getRandom(-maxChange, maxChange, random);
            }

            //Change the X/Y positions of the button
            button.xPosition = xPosition;
            button.yPosition = yPosition;
        });
    }

    /**
     * Get Random - Fetches a random value between the two given values
     *
     * @param min - Minimum random value
     * @param max - Maximum random value
     * @return - Returns a new integer as the random value
     */
    private int getRandom(final int min, final int max, final Random random) {
        //Calculate the next random value using the random instance
        return random.nextInt(max - min) + min;
    }
}
