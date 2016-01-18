/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package com.camnter.easygank.gank;

import com.camnter.easygank.EasyApplication;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Description：EasyGank
 * Created by：CaMnter
 * Time：2016-01-03 18:24
 */
public class EasyGank {

    private static EasyGank ourInstance;

    private GankService gankService;

    public static EasyGank getInstance() {
        if (ourInstance == null) ourInstance = new EasyGank();
        return ourInstance;
    }

    private EasyGank() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(7676, TimeUnit.MILLISECONDS);

        /*
         * 查看网络请求发送状况
         */
        if (EasyApplication.getInstance().log) {
            okHttpClient.interceptors().add(chain -> {
                Response response = chain.proceed(chain.request());
                com.orhanobut.logger.Logger.d(chain.request().urlString());
                return response;
            });
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApi.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(EasyApplication.getInstance().gson))
                .client(okHttpClient)
                .build();
        this.gankService = retrofit.create(GankService.class);
    }

    public GankService getGankService() {
        return gankService;
    }


}
