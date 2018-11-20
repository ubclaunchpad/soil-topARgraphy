# Terrain generator

Generates a terrain model from a heightmap and colours it.
Change the path in the IMG_URL require towards the end to change the heightmap file.

## What's included

- Generating terrain models from heightmaps
- Colouring said models using GLSL shaders
- Multiple heightmaps to try
- The beginnings of an API in Express
- OBJ export (almost)

## What's not included

- Running headlessly
- Completed API
- Non-invisible OBJ
- Specifying the parameters outside the code
	- Would be done via the API
- Optimizations
	- Each pixel has its own vertex, which is really not performant
	- Even a 256x256 height map can already put out ~130k triangles/a 16MB file
- Passing custom colours to the shader and/or a non-disgusting default colour scheme

## Installation

Run `yarn install` or `npm install`

## Usage

Run `yarn start` or `npm start`

Controls: WASD + RF + QE + mouse
