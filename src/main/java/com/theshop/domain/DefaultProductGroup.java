package com.theshop.domain;

public final class DefaultProductGroup {
    private static DefaultProductGroup defaultProductGroupInstance = null;
    private String name = "group unassigned";

    private DefaultProductGroup() {
    }

}
/*
private SettingsFileEngine() {
    }

    public static SettingsFileEngine getInstance() {
        if (settingsFileEngineInstance == null) {
            synchronized (SettingsFileEngine.class) {
                if (settingsFileEngineInstance == null) {
                    settingsFileEngineInstance = new SettingsFileEngine();
                }
            }
        }
        return settingsFileEngineInstance;
 */