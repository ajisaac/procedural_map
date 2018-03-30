# basis for procedural map engine

A pet project to create procedurally generated maps of continents for 2d games. wip.

## Future

Will likely rewrite in C in the future and remove some functionality in order to add other functionality.

Will want to add LOD, some format for tiling, an easy to use api and to package as a library.

## How it works

Uses Kurt Spencer's implementation of open-simplex-noise in java to create maps. Will support infinite maps and fixed sizes. Can display to image or write the data directly into whatever format needed. This is the basis for a small 2d map engine and will work alongside a few other programs for tiling, physics, opengl etc. Intended to have level of detail included in a future version and incorporation with the tile map format we choose.

The actual code will create multiple layers of maps to interact with each other. Similar to other procedural generation code. The layers shouldn't be too set in stone, and the user will have lots of freedom to define layers and their interactions, possibly using javascript python or something other. 

For example, the temperature map and elevation determine weather, the fault lines can determine seismic activity, a map of "moral" values can determine how hostile npcs in the area are.  Layers are also used as filters, sometimes 5+ layers are used in the creation of a single layer.

## Examples
A live editor for redrawing using a variety of layer settings.
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map1.jpg)
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map2.png)
A nice way to display weather.
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map3.jpg)
Sample Elevation map.
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map4.jpg)
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map5.jpg)
Sometimes the maps are more artistic in nature
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map6.jpg)
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map7.png)
Scale can be determined, and layer of detail can be added.
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map8.jpg)
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map9.jpg)
![alt text](https://s3-us-west-2.amazonaws.com/github-ajisaac/pgc_map/pgc_map10.jpg)
