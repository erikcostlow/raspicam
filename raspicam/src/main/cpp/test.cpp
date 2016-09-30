#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <unistd.h>
#include <stdio.h>
#include "raspicam-0.1.3/raspicam.h"
#include "raspicam-0.1.3/raspicam_still.h"
#include "raspicam-0.1.3/raspicamtypes.h"
using namespace std;

raspicam::RaspiCam Camera;

int main ( int argc, char *argv[] ) {
    //Open camera 
    cout<<"Opening Camera..."<<endl;
    raspicam::RASPICAM_FORMAT format = raspicam::RASPICAM_FORMAT_RGB;
    Camera.setFormat(format);
    if ( !Camera.open()) {cerr<<"Error opening camera"<<endl;return -1;}
    //wait a while until camera stabilizes
    cout<<"Sleeping for 3 secs"<<endl;
    sleep(3);
    cout<<"Done sleeping, capturing now..."<<endl;

    //capture
    Camera.grab();
    //allocate memory
    unsigned int length=Camera.getImageTypeSize ( Camera.getFormat() );
    unsigned char *data=new unsigned char[  length];
    //extract the image in rgb format
    Camera.retrieve ( data,raspicam::RASPICAM_FORMAT_IGNORE );//get camera image
    //save
    std::ofstream outFile ( "raspicam_image.ppm",std::ios::binary );
    outFile<<"P6\n"<<Camera.getWidth() <<" "<<Camera.getHeight() <<" 255\n";
    outFile.write ( ( char* ) data, length );
    cout<<"Image saved at raspicam_image.ppm"<<endl;
    
    std::string prefix="P6\n";
    unsigned int width=Camera.getWidth();
    unsigned int height=Camera.getHeight();
    std::string space=" ";
    std::string suffix = " 255\n";
    
    std::ostringstream ss;
    ss << prefix << width << space << height << suffix;
    ss.write ( ( char* ) data, length );
    std::string full = ss.str();
    
    unsigned int total=full.length();
    
    //unsigned int total=length+additional;
    cout<<"Total is " << total << endl;
    std::ofstream outFile2 ( "raspicam_image2.ppm",std::ios::binary );
    //outFile2<<full;
    outFile2.write ( ( char* ) full.c_str(), total );
    
    delete data;
    return 0;
}

int OLDmain ( int argc, char *argv[] ) {
  raspicam::RaspiCam_Still still;
  still.setEncoding ( raspicam::RASPICAM_ENCODING_JPEG );
  int length=still.getImageBufferSize();
  unsigned char *data=new unsigned char[length];

  cout<<"Capturing image at size " << length <<endl;
  if(!still.grab_retrieve(data, length)) {
    cerr<<"Error in grab"<<endl;
    return -1;
  }
  still.release();
  cout<< "Got it" << endl;
  ofstream file ( "picture.jpg",ios::binary );
  file.write ( ( char* ) data,   length );
  delete[] data;
  return 0;
}
