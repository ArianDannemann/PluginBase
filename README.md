# Plugin Base

## Introduction

'Plugin Base' is a collection of useful tools for your future plugins. Often times when creating multiple plugins I found myself reusing a lot of code. For example most plugins I code use some sort of particle system so it made sense to create a ParticleEmitter class that I could use in all future projects.

## Installation Instructions (for when you just want to use a plugin that depends on the API)

Using the API when you don't want to code with it is pretty simple:

   1. Download the .jar file from the link above
   2. Move the file into your plugins folder (i.e. C:\Desktop\Arian\MC Server 1.13.2\plugins)

## Installation Instructions (for when you want to use the API yourself)

How to make the server know that your plugin uses the plugin API:

   1. Download the .jar file from the link above
   2. Move the file into your plugins folder (i.e. C:\Desktop\Arian\MC Server 1.13.2\plugins)
   3. In the "plugin.yml" file for your new plugin add a dependency for the plugin API. Just add the following line above the commands section:
    depend: [PluginBase]

How to make Eclipse know that your plugin uses the plugin API (this will also enable neat things like tab completion):

   1. Right click on your project
   2. Go to "Build Path" -> "Configure Build Path..."
   3. In the menu that opens click on "Libraries"
   4. Click on "Add External JARs..."
   5. Find and select "Plugin Base.jar"
   6. Click on "Apply and Close"
