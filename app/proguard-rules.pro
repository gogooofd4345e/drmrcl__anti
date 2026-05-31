# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\J-T-A\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and force proguard to use other files.

# Keep Room database schemas and builders
-keepclassmembers class * extends androidx.room.RoomDatabase {
    <init>(...);
}

# Hilt rules
-keep class class_containing_hilt_modules { *; }

# Serialization rules
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod

# Keep Ktor Client classes
-keep class io.ktor.** { *; }
