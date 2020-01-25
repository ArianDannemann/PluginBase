# Version 1.2

- Added InventoryManager.java and ItemCreator.java
- Added ``getBlocksBetweenLocations(Location location1, Location location2)``, ``getSmallerLocation(Location location1, Location location2)``, ``getBiggerLocation(Location location1, Location location2)``, and ``isLocationUnderBlocks(Location location)``
- Added Darkness effect
- Added ``sendMessageToConsole(String pluginName, String message)``, ``sendWarningMessageToConsole(String pluginName, String message)`` and``sendErrorToConsole(String pluginName, String error, String explanation)``
- Added ``createItem(Material type, int amount, String name, String loreString)`` which will enable adding multiple lines of lore using ``\n`` as a divider
- Fixed bug: Validating a location that is at Y level 1 or below would lead to a server crash

# Version 1.1

- Added SoundEmitter.java
- Added broadcast to administrators when an error message is sent to the console
- Added ``broadcast``
- Added ``emitParticles(..., DustOptions dustOptions, ...)`` and ``emitParticles(..., BlockData blockData, ...)`` to add support for particles ``Particle.REDSTONE``, ``Particle.ITEM_CRACK``, ``Particle.BLOCK_CRACK`` and ``Particle.BLOCK_DUST``. There is also a new error message when trying to spawn these particles without the necessary information. NOTE: This does not yet work for continuous emission

# Version 1.0

- Initial release
