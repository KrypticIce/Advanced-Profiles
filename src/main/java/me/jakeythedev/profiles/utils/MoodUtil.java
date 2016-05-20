package me.jakeythedev.profiles.utils;

import me.jakeythedev.profiles.utils.skull.SkullCollection;

/**
 * C R E A T E D
 * B Y
 * J A K E Y T H E D E V
 * O N
 * 14/05/2016
 */

public enum MoodUtil
{

    DEFAULT(SkullCollection.DEFAULT),
    HAPPY(SkullCollection.BIG_GRIN),
    SAD(SkullCollection.SAD),
    CRYING(SkullCollection.FOREVER_CRYING),
    GRATEFUL(SkullCollection.ANGEL),
    ENTHUSIASTIC(SkullCollection.BIG_SMILE);

    private SkullCollection _skull;

    MoodUtil(SkullCollection skull)
    {
        this._skull = skull;
    }

    public SkullCollection getSkull()
    {
        return _skull;
    }
}
