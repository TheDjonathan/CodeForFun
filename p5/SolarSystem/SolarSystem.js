let sun, easycam, sunImg, earthImg, moonImg;
let astros = [];

function preload() {
  // Load images
  sunImg = loadImage("resource\\sun.jpg");
  earthImg = loadImage("resource\\earth.jpg");  
  moonImg = loadImage("resource\\moon.jpg");
}


function setup() {
  // Create a 3D canvas
  createCanvas(windowWidth, windowHeight, WEBGL);

  // Create sun object. (695.508 * 0.018) => sun is too big to show :P
  let sunPos = createVector(0, 0);
  sun = new Astro(sunPos, sunImg, (695.508 * 0.018), 0, 0.05);

  // Create earth object (6.371 km of radius)
  let earthPos = createVector(250, 50);
  astros[0] = new Astro(earthPos, earthImg, 6.371, 0.1, 0.1);

  // Create moon object (1.737 of radius)
  let moonPos = createVector(320, -10);
  astros[1] = new Astro(moonPos, moonImg, 1.737, 0.1, 0.1);

  // Create camera
  easycam = createEasyCam();
}


function draw() {
  background(1);

  // Show astros
  sun.show();
  for(astro of astros) {
    astro.show();
  }

  stroke(255);
  strokeWeight(4);

  
}

