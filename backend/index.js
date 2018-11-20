import * as THREE from 'three'
import * as Shaders from './shaders'
import saveAs from 'file-saver'

// controls: wasd + rf + qe + mouse
require('three-fly-controls')(THREE)

var container, camera, scene, renderer, controls

const MAX_IMG_PX = [512, 512] // Maximum size in pixels the heightmap can be

function getHeightMap(img) { // Extracts heightmap data
	if (img.width > MAX_IMG_PX[0] || img.height > MAX_IMG_PX[1]) {
		return `Image dimensions are too large. Max dimensions are ${MAX_IMG_PX[0]} by ${MAX_IMG_PX[1]}`
	}
	let canvas = document.createElement('canvas')
	canvas.width = img.width
	canvas.height = img.height
	let context = canvas.getContext('2d')
	context.drawImage(img, 0, 0)
	let data = context.getImageData(0, 0, img.width, img.height).data
	return data
}

/* Main function that creates everything
 * hmap - heightmap data
 * hmap_width, hmap_height - width and height of heightmap in pixels
 * scale_plane - multiplier to scale generated mesh by
 * scale_height - multiplier to scale height values by
 */
function init(hmap, hmap_width, hmap_height, scale_plane=0.01, scale_height=1) {
	container = document.getElementById('container')
	camera = new THREE.PerspectiveCamera(20, window.innerWidth / window.innerHeight, 1, 10000)
	camera.position.z = 1800
	scene = new THREE.Scene()
	scene.background = new THREE.Color(0xffffff)

	let plane = [hmap_width * scale_plane, hmap_height * scale_plane, hmap_width - 1, hmap_height - 1]
	let geom = new THREE.PlaneBufferGeometry(...plane)
	// let count = geom.attributes.position.count
	// geom.addAttribute( 'color', new THREE.BufferAttribute( new Float32Array( count * 3 ), 3 ) )
	let verts = geom.attributes.position.array

	let max_z = hmap[0]
	let min_z = hmap[0] 

	for (let i = 0, j = 2; i < hmap.length; i += 4, j += 3) { // Actually do the modelling
		let vert = hmap[i] * scale_height
		verts[j] = vert
		if (vert > max_z) {
			max_z = vert
		}
		if (vert < min_z) {
			min_z = vert
		}
	}

	console.log(`Max z: ${max_z}, min z: ${min_z}`)

	geom.computeFaceNormals()
	geom.computeVertexNormals()

	let material = new THREE.ShaderMaterial({
		uniforms: {
			max_z: { value: parseFloat(max_z) },
			min_z: { value: parseFloat(min_z) }
		},
		vertexShader: Shaders.vertShader,
		fragmentShader: Shaders.fragShader
	})

	var mesh = new THREE.Mesh(geom, material)
	mesh.position.y = -50
	mesh.rotation.x = Math.PI / 1.6
	mesh.rotation.y = Math.PI
	mesh.matrixAutoUpdate = false
	mesh.updateMatrix()
	scene.add(mesh)

	renderer = new THREE.WebGLRenderer({ antialias: true })
	renderer.setPixelRatio(window.devicePixelRatio)
	renderer.setSize(window.innerWidth, window.innerHeight)
	container.appendChild(renderer.domElement)

	controls = new THREE.FlyControls(camera, renderer.domElement)
	controls.enableDamping = true
	controls.dampingFactor = .15
	controls.rotateSpeed = .2

	camera.lookAt(scene.position)
}

function animate() {
	requestAnimationFrame(animate)
	renderer.render(scene, camera)

	controls.update()
}

window.saveOBJ = (filename) => {
	let OBJExporter = require('three-obj-exporter')
	let exporter = new OBJExporter()
	let result = exporter.parse(scene)
	let file = new File(result.split("\n"), "export.obj", {type: "text/plain;charset=utf-8"})
	saveAs(file)

}

window.onload = async () => {
	let IMG_URL = require('./mapfiles/heightmap_sthink.png')
	IMG_URL = await fetch(IMG_URL).then(r => r.blob())
	IMG_URL = URL.createObjectURL(IMG_URL)
	let img = new Image()
	img.src = IMG_URL

	img.onload = () => {
		let hmap = getHeightMap(img)
		if (typeof(hmap) === 'object') {
			init(hmap, img.width, img.height, 1, 1)
			animate()
			console.log("Ready to save!")
		} else {
			console.log(`ERROR: ${hmap}`)
		}
	}
}