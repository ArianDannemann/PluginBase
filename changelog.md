# Version 1.2

- Added InventoryManager.java

# Version 1.1

- Added SoundEmitter.java
- Added broadcast to administrators when an error message is sent to the console
- Added ``broadcast``
- Added ``emitParticles(..., DustOptions dustOptions, ...)`` and ``emitParticles(..., BlockData blockData, ...)`` to add support for particles ``Particle.REDSTONE``, ``Particle.ITEM_CRACK``, ``Particle.BLOCK_CRACK`` and ``Particle.BLOCK_DUST``. There is also a new error message when trying to spawn these particles without the necessary information. NOTE: This does not yet work for continuous emission

# Version 1.0

- Initial release
