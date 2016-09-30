rm a.out
g++ -I$JAVA_HOME/include \
  -I"$JAVA_HOME/include/linux" \
  -I"/usr/local/lib" \
  -lraspicam \
  test.cpp
