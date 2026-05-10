package org.survivalcraft.zombie.utils;

import org.apache.logging.log4j.Level;
import cpw.mods.fml.common.FMLLog;

public class Logger {

	public static final String INIT_PREFIX = "[Initialization] ";
	
	public static void log(Level level, Object object) {
		FMLLog.log(SharedConstants.MOD_NAME, level, String.valueOf(object));
	}
	
    public static void all(Object object) {
        log(Level.ALL, object);
    }

    public static void debug(Object object) {
        log(Level.DEBUG, object);
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }

    public static void warn(Object object) {
        log(Level.WARN, object);
    }

    public static void error(Object object) {
        log(Level.ERROR, object);
    }

    public static void severe(Object object) {
        log(Level.FATAL, object);
    }
    
    public static void infoInit(Object object) {
    	info(INIT_PREFIX + object);
    }
}