package nu.wasis.stunden.plugin;

import java.util.Map;

import nu.wasis.stunden.model.WorkPeriod;

public interface InputPlugin {

    WorkPeriod read(Map<String, String> args);

}
