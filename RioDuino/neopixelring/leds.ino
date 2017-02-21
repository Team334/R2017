//Team 334 Led Controls - Joseph Babbitt
#include <Adafruit_NeoPixel.h>
#include <Wire.h>
#ifdef __AVR__
  #include <avr/power.h>
#endif
Adafruit_NeoPixel ring = Adafruit_NeoPixel(24, 9, NEO_GRB + NEO_KHZ800);
Adafruit_NeoPixel strip1 = Adafruit_NeoPixel(300, 11, NEO_BGR + NEO_KHZ800);
Adafruit_NeoPixel strip2 = Adafruit_NeoPixel(300, 10, NEO_BGR + NEO_KHZ800);

void setup() {
  // Setup for strips and ring
  ring.begin();
  strip1.begin();
  strip2.begin();
  Wire.begin(8);
  Wire.onReceive(receiveEvent);
}

void loop() {
  delay(10);
}
void receiveEvent() {
  int vals[9];
  int i = 0;
  while (Wire.available()) {
    vals[i++] = Wire.read();
  }
  switch(vals[0]){
    case 0:
      setRing(vals[1],vals[2],vals[3],vals[4],vals[5],vals[6]);
      break;
    case 1:
      switch(vals[2]){
        case 0:
          if(vals[1] == 0) chase1(vals[3],vals[4],vals[5],vals[6],vals[7],vals[8],vals[9]);
          else if(vals[1] == 1) marquee1(vals[3],vals[4],vals[5],vals[6]);
          break;
        case 1:
          if(vals[1] == 0) chase2(vals[3],vals[4],vals[5],vals[6],vals[7],vals[8],vals[9]);
          else if(vals[1] == 1) marquee2(vals[3],vals[4],vals[5],vals[6]);
          break;
      }
      break;
    case 2:
      if(vals[1] == 0) badfade(vals[2],vals[3],vals[4],vals[5]);
      if(vals[1] == 1) badsetColor(vals[2],vals[3],vals[4]);
  }
}

// Animation Functions

//Bad led strip
void badfade(int r,int g,int b,int dly){
  for(int fv = 0; fv<=255; fv+=5){
    analogWrite(3,r);
    analogWrite(4,g);
    analogWrite(2,b);
    delay(dly);
  }
  for(int fv = 255; fv>=255; fv-=5){
    analogWrite(3,r);
    analogWrite(4,g);
    analogWrite(2,b);
    delay(dly);
  }
}
void badsetColor(int r, int g, int b){
  analogWrite(3,r);
  analogWrite(4,g);
  analogWrite(2,b);
}

//Neopixel strip1
void chase1(int numStreaks,int trail, int space, int dly, int r, int g, int b) {
  for(int i=0;i<strip1.numPixels(); i++){
   for(int j=0;j<numStreaks;j++) {
     for(int s=0;s<trail;s++) {
      strip1.setPixelColor(i+j*space+trail, strip1.Color(r,g,b));
     }
      strip1.setPixelColor(i+j*space, strip1.Color(r,g,b));
      strip1.setPixelColor(i-1+j*space, strip1.Color(0,0,0));
    }
    strip1.show();
    delay(dly);
  }
  strip1.setPixelColor(strip1.numPixels()-1,strip1.Color(0,0,0));
}
void marquee1(int dly, int r, int g, int b) {
  for(int i=0;i<strip1.numPixels();i+=2){
      strip1.setPixelColor(i, strip1.Color(r,g,b));
      strip1.setPixelColor(i-1, strip1.Color(0,0,0));
  }
  strip1.show();
  delay(dly);
  for(int i=1;i<strip1.numPixels();i+=2){
      strip1.setPixelColor(i-1, strip1.Color(0,0,0));
      strip1.setPixelColor(i, strip1.Color(r,g,b));
  }
  strip1.show();
  delay(dly);
}
//Neopixel strip2
void chase2(int numStreaks,int trail, int space, int dly, int r, int g, int b) {
  for(int i=0;i<strip2.numPixels(); i++){
   for(int j=0;j<numStreaks;j++) {
     for(int s=0;s<trail;s++) {
      strip2.setPixelColor(i+j*space+trail, strip2.Color(r,g,b));
     }
      strip2.setPixelColor(i+j*space, strip2.Color(r,g,b));
      strip2.setPixelColor(i-1+j*space, strip2.Color(0,0,0));
    }
    strip2.show();
    delay(dly);
  }
  strip2.setPixelColor(strip2.numPixels()-1,strip2.Color(0,0,0));
}
void marquee2(int dly, int r, int g, int b) {
  for(int i=0;i<strip2.numPixels();i+=2){
      strip2.setPixelColor(i, strip2.Color(r,g,b));
      strip2.setPixelColor(i-1, strip2.Color(0,0,0));
  }
  strip2.show();
  delay(dly);
  for(int i=1;i<strip2.numPixels();i+=2){
      strip2.setPixelColor(i-1, strip2.Color(0,0,0));
      strip2.setPixelColor(i, strip2.Color(r,g,b));
  }
  strip2.show();
  delay(dly);
}

//Ring
void setRing(int start, int stp, int r,int g,int b, int dly) {
  for(int i=start;i<=stp;i++) {
    ring.setPixelColor(i, ring.Color(r,g,b));
    delay(dly);
    ring.show();
  }
}
