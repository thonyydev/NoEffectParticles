package net.thonyy.noeffectparticles.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class NoEffectParticlesConfig {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH =
            Path.of("config", "noeffectparticles.json");

    private static Data data = load();

    private NoEffectParticlesConfig() {
    }

    public static boolean removeOwnParticles() {
        return data.removeOwnParticles;
    }

    public static boolean removeOtherPlayerParticles() {
        return data.removeOtherPlayerParticles;
    }

    public static boolean removeMobParticles() {
        return data.removeMobParticles;
    }

    public static void setRemoveOwnParticles(boolean value) {
        data.removeOwnParticles = value;
        save();
    }

    public static void setRemoveOtherPlayerParticles(boolean value) {
        data.removeOtherPlayerParticles = value;
        save();
    }

    public static void setRemoveMobParticles(boolean value) {
        data.removeMobParticles = value;
        save();
    }

    private static Data load() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            if (Files.notExists(CONFIG_PATH)) {
                Data defaults = new Data();
                write(defaults);
                return defaults;
            }

            try (Reader reader = Files.newBufferedReader(
                    CONFIG_PATH,
                    StandardCharsets.UTF_8
            )) {
                Data loaded = GSON.fromJson(reader, Data.class);

                if (loaded != null) {
                    return loaded;
                }
            }
        } catch (Exception exception) {
            System.err.println(
                    "[No Effect Particles] Failed to load config: "
                            + exception.getMessage()
            );
        }

        return new Data();
    }

    public static void save() {
        try {
            write(data);
        } catch (Exception exception) {
            System.err.println(
                    "[No Effect Particles] Failed to save config: "
                            + exception.getMessage()
            );
        }
    }

    private static void write(Data config) throws Exception {
        Files.createDirectories(CONFIG_PATH.getParent());

        try (Writer writer = Files.newBufferedWriter(
                CONFIG_PATH,
                StandardCharsets.UTF_8
        )) {
            GSON.toJson(config, writer);
        }
    }

    private static final class Data {
        private boolean removeOwnParticles = true;
        private boolean removeOtherPlayerParticles = true;
        private boolean removeMobParticles = true;
    }
}