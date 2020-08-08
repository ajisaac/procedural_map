# basis for procedural map engine

A pet project to create procedurally generated maps of continents for 2d games. wip. On hold until we read it and figure out what the 7 years ago us was thinking.

## Future - todo

- [ ] refactor out the GDX libraries
- [ ] isolate the generation of maps
- [ ] isolate the settings out of code
- [ ] create simple wrapping application to easily work with the input data
- [ ] rewrite in C for performance and better algorithms
- [ ] add level of detail
- [ ] determine a binary save format
- [ ] expand upon all of this with former ideas
- [ ] add an easy to use api

## How it works

Uses Kurt Spencer's implementation of open-simplex-noise in java to create maps. Will support infinite maps and fixed sizes. Can display to image or write the data directly into whatever format needed. This is the basis for a small 2d map engine and will work alongside a few other programs for tiling, physics, opengl etc. Intended to have level of detail included in a future version and incorporation with the tile map format we choose.

The actual code will create multiple layers of maps to interact with each other. Similar to other procedural generation code. The layers shouldn't be too set in stone, and the user will have lots of freedom to define layers and their interactions, possibly using javascript python or something other. 

For example, the temperature map and elevation determine weather, the fault lines can determine seismic activity, a map of "moral" values can determine how hostile npcs in the area are.  Layers are also used as filters, sometimes 5+ layers are used in the creation of a single layer.
