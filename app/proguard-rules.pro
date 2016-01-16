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

# 代码压缩级别
-optimizationpasses 5

# 使用大小写混合
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
# 混淆时预校验
-dontpreverify
# 记录日志
-verbose

# 混淆时所用的算法
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
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment


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

# 自定义View
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# Enum
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Exceptions InnerClasses
-keepattributes Exceptions,InnerClasses

# umeng
-libraryjars libs/umeng-analytics-v5.6.4.jar
-libraryjars libs/umeng-update-v2.6.0.1.jar
-keep class com.umeng.** { *; }
-keep class com.umeng.update.** { *; }
-keep public class * extends com.umeng.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep public class com.umeng.socialize.* {*;}
-keep class com.umeng.scrshot.**
-keep class com.umeng.socialize.sensor.**
-keep public class com.umeng.library.R$*{
    public static final int *;
}


# butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#umeng start
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-dontwarn com.tencent.**
-dontwarn com.ta.**
-dontwarn u.upd.**
-dontwarn u.aly.**

-dontwarn org.apache.**
-keep class org.apache.** { *; }
-keep interface org.apache.** { *; }


-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.anupcowkur.** { *; }
-keep class com.jakewharton.** { *; }
-keep class org.apache.** { *; }
-dontwarn org.apache.**
-dontwarn com.jakewharton.**
-dontwarn com.anupcowkur.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-keep public class cn.pocdoc.majiaxian.R$*{
    public static final int *;
}
#end umeng