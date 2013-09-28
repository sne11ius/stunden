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
 - StundenTagBreaksPlugin: Tags entries as break based on their project names ("Lunch", "Mittag", "Kelloggs"...)
   - see https://github.com/sne11ius/StundenTagBreaksPlugin
 - StundenTagEntriesPlugin: Tags entries based on their project names.
   - see https://github.com/sne11ius/StundenTagEntriesPlugin   
 - StundenSimplifierPlugin: Merges entries to have each project max once per day
   - see https://github.com/sne11ius/StundenSimplifierPlugin

Output
 - StundenSTDOutPlugin: Prints nicely formatted data to stdout
   - see https://github.com/sne11ius/StundenSTDOutPlugin
 - StundenExcelWriterPlugin: Fills out a template Excel file.
   - see https://github.com/sne11ius/StundenExcelWriterPlugin

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
    "processConfigs": [
        {
            "path": "/to/process/plugin.jar",
            "config": {
                "allEntriesInConfigObject": "depend on the plugin the path points to"
            }
        }, {
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

Obviously, the outcome can be quite different depending on the plugins you use
and how they are configured.

```
1    [main] INFO  nu.wasis.stunden.Stunden - Reading via `nu.wasis.stunden.plugins.StundenTextfilePlugin'...
2    [main] INFO  nu.wasis.stunden.plugins.StundenTextfilePlugin - Parsing `C:/Users/coli/Documents/src/StundenTextfilePlugin/src/test/resources/examples/2013/2013-08' ...
601  [main] INFO  nu.wasis.stunden.plugins.StundenTextfilePlugin - ... done.
601  [main] INFO  nu.wasis.stunden.Stunden - ... done.
907  [main] INFO  nu.wasis.stunden.Stunden - Processing via `nu.wasis.stunden.plugins.StundenTagBreaksPlugin'...
907  [main] INFO  nu.wasis.stunden.plugins.StundenTagBreaksPlugin - Tagging breaks...
913  [main] INFO  nu.wasis.stunden.plugins.StundenTagBreaksPlugin - ...done
913  [main] INFO  nu.wasis.stunden.Stunden - ...done.
913  [main] INFO  nu.wasis.stunden.Stunden - Processing via `nu.wasis.stunden.plugins.validator.StundenValidatorPlugin'...
914  [main] INFO  nu.wasis.stunden.plugins.validator.StundenValidatorPlugin - Validating the stuff...
922  [main] INFO  nu.wasis.stunden.plugins.validator.StundenValidatorPlugin - ...done
922  [main] INFO  nu.wasis.stunden.Stunden - ...done.
922  [main] INFO  nu.wasis.stunden.Stunden - Processing via `nu.wasis.stunden.plugins.StundenSimplifierPlugin'...
922  [main] INFO  nu.wasis.stunden.plugins.StundenSimplifierPlugin - Simplifying entries...
926  [main] INFO  nu.wasis.stunden.plugins.StundenSimplifierPlugin - ...done
926  [main] INFO  nu.wasis.stunden.Stunden - ...done.
999  [main] INFO  nu.wasis.stunden.Stunden - Outputting via `nu.wasis.stunden.plugins.StundenSTDOutPlugin'...
Start of period	: 2013-08-01
End of period	: 2013-08-30
============================

2013-08-01
==========
09:00 - 10:00: Project J
10:00 - 11:00: Essen (break)
11:00 - 13:15: ProjectZ
13:15 - 18:00: ProjectX
Summary:
	Total work time: 08:00

2013-08-02
==========
09:00 - 09:30: Project J
09:30 - 10:30: Essen (break)
10:30 - 12:30: Meeting Some People ProjectX
12:30 - 13:00: ProjectZ & ProjectX
13:00 - 15:30: Meeting ProjectX
Summary:
	Total work time: 05:30

2013-08-05
==========
09:00 - 09:45: Essen (break)
09:45 - 10:15: Project Q
10:15 - 11:00: Project P
11:00 - 13:00: ProjectX
13:00 - 17:30: ProjectZ
17:30 - 18:00: ProjectW
Summary:
	Total work time: 08:15

2013-08-06
==========
09:00 - 10:00: Essen (break)
10:00 - 10:45: Project W
10:45 - 12:00: ProjectZ
12:00 - 17:15: ProjectX
Summary:
	Total work time: 07:15

2013-08-07
==========
09:00 - 10:00: Essen (break)
10:00 - 12:30: ProjectZ
12:30 - 17:15: ProjectX
Summary:
	Total work time: 07:15

2013-08-08
==========
09:00 - 10:00: Essen (break)
10:00 - 18:00: ProjectZ
Summary:
	Total work time: 08:00

2013-08-09
==========
09:00 - 10:00: Essen (break)
10:00 - 12:45: Project R
12:45 - 16:30: ProjectZ
16:30 - 17:15: Project T
Summary:
	Total work time: 07:15

2013-08-12
==========
09:00 - 09:30: Essen (break)
09:30 - 09:45: Intern
09:45 - 16:45: ProjectX
16:45 - 17:45: ProjectZ
17:45 - 18:00: ProjectW
Summary:
	Total work time: 08:30

2013-08-13
==========
09:00 - 10:00: Essen (break)
10:00 - 13:45: ProjectX
13:45 - 15:15: ProjectZ
15:15 - 15:30: Project U
15:30 - 17:30: Project S
Summary:
	Total work time: 07:30

2013-08-14
==========
09:00 - 10:00: Essen (break)
10:00 - 10:30: ProjectX
10:30 - 11:00: ProjectZ (Telko)
11:00 - 17:30: Project U
Summary:
	Total work time: 07:30

2013-08-15
==========
09:00 - 10:00: Essen (break)
10:00 - 11:30: ProjectV
11:30 - 13:30: ProjectX
13:30 - 16:45: ProjectZ
Summary:
	Total work time: 06:45

2013-08-16
==========
09:00 - 16:45: ProjectX
Summary:
	Total work time: 07:45

2013-08-19
==========
09:00 - 11:45: Reise nach CityTown
11:45 - 14:30: Rückfahrt
14:30 - 15:15: ProjectW
15:15 - 19:30: Besprechung ProjectX
Summary:
	Total work time: 10:30

2013-08-20
==========
09:00 - 09:30: Pause (break)
09:30 - 17:15: ProjectX
Summary:
	Total work time: 07:45

2013-08-21
==========
09:00 - 09:15: Intern
09:15 - 09:45: Pause (break)
09:45 - 10:15: ProjectZ
10:15 - 17:00: ProjectX
Summary:
	Total work time: 07:30

2013-08-22
==========
09:00 - 09:30: Pause (break)
09:30 - 10:00: ProjectX
10:00 - 16:00: ProjectZ
16:00 - 18:00: ProjectY
Summary:
	Total work time: 08:30

2013-08-23
==========
09:00 - 09:15: ProjectXY
09:15 - 15:30: ProjectX
15:30 - 18:00: ProjectY
Summary:
	Total work time: 09:00

2013-08-26
==========
09:00 - 17:00: Urlaub
Summary:
	Total work time: 08:00

2013-08-27
==========
09:00 - 10:00: Intern
10:00 - 10:30: Pause (break)
10:30 - 18:45: ProjectX - Doing stuff A
Summary:
	Total work time: 09:15

2013-08-28
==========
09:00 - 17:00: Urlaub
Summary:
	Total work time: 08:00

2013-08-29
==========
09:00 - 10:00: Essen (break)
10:00 - 16:30: ProjectX - Doing stuff A
Summary:
	Total work time: 06:30

2013-08-30
==========
09:00 - 10:00: Essen (break)
10:00 - 14:45: ProjectZ
14:45 - 18:00: ProjectX - Doing stuff A
Summary:
	Total work time: 08:00
TOTAL
=====
Work duration:	172:30
Days:		22
Work/day:	7:50
1012 [main] INFO  nu.wasis.stunden.Stunden - ... done.
```
