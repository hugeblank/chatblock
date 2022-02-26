package me.hugeblank.chatblock.config;

public class CBConfig {
    private final boolean debug;
    private final boolean jackgrass;

    public CBConfig(boolean debug, boolean jackgrass) {
        this.debug = debug;
        this.jackgrass = jackgrass;
    }

    public boolean getDebug() {
        return debug;
    }

    public boolean getJackgrass() {
        return jackgrass;
    }
}
