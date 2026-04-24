package com.besson.tutorial.sound;

import com.besson.tutorial.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MOD_ID);
    
    public static final RegistryObject<SoundEvent> FOUND_ORE = registerSoundEvent("found_ore");
    public static final RegistryObject<SoundEvent> BLOCK_BREAK = registerSoundEvent("block_break");
    public static final RegistryObject<SoundEvent> BLOCK_PLACE = registerSoundEvent("block_place");
    public static final RegistryObject<SoundEvent> BLOCK_HIT = registerSoundEvent("block_hit");
    public static final RegistryObject<SoundEvent> BLOCK_FALL = registerSoundEvent("block_fall");
    public static final RegistryObject<SoundEvent> BLOCK_STEP = registerSoundEvent("block_step");
    
    public static final ForgeSoundType BLOCK_SOUNDS = new ForgeSoundType(1.0f, 1.0f,
            BLOCK_BREAK, BLOCK_STEP, BLOCK_PLACE, BLOCK_HIT, BLOCK_FALL);
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
