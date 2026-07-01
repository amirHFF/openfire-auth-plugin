package io.amirHFF.projectz.plugin;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 6/23/2026 - 2:07 AM
*/

import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.util.JiveGlobals;

import java.io.File;

public class JwtAuthPlugin implements Plugin {
    @Override
    public void initializePlugin(PluginManager pluginManager, File file) {
        JiveGlobals.setProperty(
                "provider.auth.className",
                JwtAuthProvider.class.getName());

        System.out.println("JWT Auth Plugin Loaded");
    }

    @Override
    public void destroyPlugin() {

    }
}

