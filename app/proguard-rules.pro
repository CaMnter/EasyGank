# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/CaMnter/Android/adt-bundle-mac-x86_64-20140702/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*



# EasyGank
-keep class com.camnter.easygank.views.** { *; }
-keep class com.camnter.easygank.widget.** { *;}
-keep class com.camnter.easygank.bean.** { *; }
-keep class com.camnter.easygank.core.** { *; }

# EasyRecyclerView
-keep class com.camnter.easyrecyclerview..** {*;}

# 组件
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference


# 泛型
-keepattributes Signature
-keepattributes *Annotation*


# R
-keep public class com.camnter.easygank.R$*{
		public static final int *;
}

# Support library
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

# Retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# RxJava
# RxAndroid will soon ship with rules so this may not be needed in the future
# https://github.com/ReactiveX/RxAndroid/issues/219
-dontwarn sun.misc.Unsafe
-keep class rx.internal.util.unsafe.** { *; }

# Gson
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keepattributes *Annotation*

# JSONObject
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

# Serializable Parcelable
-keepnames class * implements java.io.Serializable
-keep public class * implements android.os.Parcelable {*;}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Glide
-keep class com.github.bumptech.glide.** {*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# Native
-keepclasseswithmembernames class * {
    native <methods>;
}

# AttributeSet
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Enum
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Exceptions InnerClasses
-keepattributes Exceptions,InnerClasses