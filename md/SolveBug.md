EasyGank solve bug
==



### 1.Caused by: java.lang.IllegalArgumentException: Unable to create call adapter for rx.Observable

**fix :**  

`compile 'com.squareup.retrofit:converter-gson:2.0.0-beta2'`

```java
this.retrofit = new Retrofit.Builder()
        .baseUrl(GankApi.BASE_URL)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(this.gson))
        .client(okHttpClient)
        .build();
```

---

### 2.retrofit.HttpException: HTTP 404 Not Found

**fix :**  retrofit2.0后：BaseUrl要以/结尾，@GET 等请求不要以/开头。

---

###3.java.lang.AbstractMethodError: abstract method "retrofit.CallAdapter retrofit.CallAdapter$Factory.get

**fix :**   

`'com.squareup.retrofit:adapter-rxjava:2.0.0-beta1'`
换为`'com.squareup.retrofit:adapter-rxjava:2.0.0-beta2'`

---

### 4.遇到一个坑爹的事情（SwipeLayout刷新不了也关不了、Toolbar设置不了，明明眼睁睁断点看着设置上了）

**fix :**  
我的Base类里多setContentView一次了，真是手贱封装的时候多写了一次，解决方法是判断HashCode：我断点走进去的时候发现设置listener的SwipeLayout和执行关闭的SwipeLayout的HashCode不一样，就知道布局重复渲染了一次，不同的SwipeLayout。哎，血的教训。




