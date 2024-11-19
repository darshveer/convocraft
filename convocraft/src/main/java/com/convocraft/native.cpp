#include <jni.h>
#include "com_convocraft_NativeInterface.h"

JNIEXPORT jint JNICALL Java_com_convocraft_NativeInterface_add
  (JNIEnv* env, jobject obj, jint a, jint b) {
    return a + b;
}