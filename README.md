stunden
=======

Reads work hours via input plugin(s) and puts them out via output plugin(s).

Current Plugins (work in progress)
==================================

Input
 - StundenTextfilePlugin: Reads data from human friendly text files
   - see https://github.com/sne11ius/StundenTextfilePlugin

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
 - run `mvn assemly:single` for stunden and all plugins
 - create configuration file for stunden
 - run something like
```
java -jar target/stunden-0.0.1-SNAPSHOT-jar-with-dependencies.jar -c /path/to/my/config.json
```

Configuration
=============
Pointing stunden to a configuration file is mandatory.
The file must contain valid JSON (though you may omit the quotes around keys):
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
Also see example @ https://github.com/sne11ius/stunden/blob/master/default.json
