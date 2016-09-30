g++ -shared -o costlow_raspicam.so -I$JAVA_HOME/include \
  -I"$JAVA_HOME/include/linux" \
  -I"/usr/local/lib" \
  -lraspicam \
  Camera.cpp
