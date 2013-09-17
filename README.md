stunden
=======

Reads work hours via input plugin(s), processes them via process plugin(s) and ... outputs them out via output plugin(s).

TOC
===
 - [Current Plugins](#current-plugins-work-in-progress)
 - [Planned Plugins](#planned-plugins)
 - [How to build](#how-to-build)
 - [Configuration](#configuration)
 - [Example run](#example-run)

Current Plugins (work in progress)
==================================

Input
 - StundenTextfilePlugin: Reads data from human friendly text files
   - see https://github.com/sne11ius/StundenTextfilePlugin

Process
 - StundenValidatorPlugin: Makes sure all processed date is nice & clean
   - see https://github.com/sne11ius/StundenValidatorPlugin
 - StundenAutoTunePlugin: Makes sure you http://www.youtube.com/watch?v=T_AtWsA2cAM ;)
   - see https://github.com/sne11ius/StundenAutoTunePlugin
 - StundenRemoveBreaksPlugin: Removes entries based on their project names ("Lunch", "Mittag", "Kelloggs"...)
   - see https://github.com/sne11ius/StundenRemoveBreaksPlugin
 - StundenSimplifierPlugin: Merges entries to have each project max once per day
   - see https://github.com/sne11ius/StundenSimplifierPlugin

Output
 - StundenSTDOutPlugin: Prints nicely formatted data to stdout
   - see https://github.com/sne11ius/StundenSTDOutPlugin

Planned Plugins
===============

Output
 - StundenAutoHotkeyScriptForSAPGUIOutputPlugin: generates a AHK script to beat the data into SAP-GUI
   - see https://github.com/sne11ius/StundenAutoHotkeyScriptForSAPGUIOutputPlugin

How to build
============

 - Follow this to install jspf in your local repo: https://code.google.com/p/jspf/issues/detail?id=26
 - Clone stunden and all plugins you wish to use
 - run `mvn install && mvn assemly:single` for stunden
 - run `mvn assemly:single` for all plugins
 - create configuration file for stunden
 - run something like
```
java -jar target/stunden-0.0.1-SNAPSHOT-jar-with-dependencies.jar -c /path/to/my/config.json
```

Configuration
=============
Pointing stunden to a configuration file is mandatory.
The file must contain valid JSON (though you may omit the quotes around keys and may use `/*...*/` for comments):
```JSON
{
    "inputConfigs": [
        {
            "path": "/to/input/plugin.jar",
            "config": {
                "allEntriesInConfigObject": "depend on the plugin the path points to"
            }
        },
        {
            ...
        }
    ],
    "outputConfigs": [
        {
            "path": "/to/output/plugin.jar",
            "config": {
                "allEntriesInConfigObject": "depend on the plugin the path points to"
            }
        }, {
            ...
        }
    ]
}
```
Also see example @ https://github.com/sne11ius/stunden/blob/master/windows_example_config.json

Example run
===========

```
cornelius@homebox:~/src/stunden$ java -jar target/stunden-0.0.1-SNAPSHOT-jar-with-dependencies.jar -c default.json 
0    [main] INFO  nu.wasis.stunden.Stunden - Reading via `nu.wasis.stunden.plugins.StundenTextfilePlugin'...
1    [main] INFO  nu.wasis.stunden.plugins.StundenTextfilePlugin - Parsing `/home/cornelius/src/StundenTextfilePlugin/src/test/resources/examples' ...
552  [main] INFO  nu.wasis.stunden.plugins.StundenTextfilePlugin - ... done.
552  [main] INFO  nu.wasis.stunden.Stunden - ... done.

654  [main] INFO  nu.wasis.stunden.Stunden - Outputting via `nu.wasis.stunden.plugins.StundenSTDOutPlugin'...
Start of period	: 2013-01-01
End of period	: 2013-01-02
============================

2013-01-01
==========
10:00 - 10:45: Intern
10:45 - 12:00: Project 1
12:00 - 13:00: Project 2
13:00 - 14:15: Project 1
14:15 - 15:00: Project 3
15:00 - 20:00: Project 2
Summary:
	Intern: 00:45
	Project 2: 06:00
	Project 1: 02:30
	Project 3: 00:45
	Total: 10:00

2013-01-02
==========
10:00 - 10:30: Project 1
10:30 - 11:00: Intern
11:00 - 12:00: Project 2
12:00 - 13:00: Essen
13:00 - 13:45: Project 1
13:45 - 17:00: Project 2
17:00 - 20:00: Project 3
Summary:
	Essen: 01:00
	Intern: 00:30
	Project 2: 04:15
	Project 1: 01:15
	Project 3: 03:00
	Total: 09:00
669  [main] INFO  nu.wasis.stunden.Stunden - ... done.
669  [main] INFO  nu.wasis.stunden.Stunden - Outputting via `nu.wasis.stunden.plugins.StundenAutoHotkeyScriptForSAPGUIOutputPlugin'...
StundenAutoHotkeyScriptForSAPGUIOutputPlugin doing it's stuff...
669  [main] INFO  nu.wasis.stunden.Stunden - ... done.
```
