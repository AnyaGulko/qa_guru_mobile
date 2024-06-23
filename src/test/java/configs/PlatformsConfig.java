package configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${platform}.properties"})
public interface PlatformsConfig extends Config {
    @Key("app")
    String getApp();

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOS();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();
}
