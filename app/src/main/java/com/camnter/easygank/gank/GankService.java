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


import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.bean.GankData;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Description：GankService
 * Created by：CaMnter
 * Time：2016-01-03 16:18
 */
public interface GankService {

    /**
     * @param year  year
     * @param month month
     * @param day   day
     * @return Observable<GankDaily>
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getDaily(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day
    );

    /**
     * 找妹子、Android、iOS、前端、扩展资源、休息视频
     *
     * @param type 数据类型
     * @param size 数据个数
     * @param page 第几页
     * @return Observable<GankWelfare>
     */
    @GET("data/{type}/{size}/{page}")
    Observable<GankData> getData(
            @Path("type") String type,
            @Path("size") int size,
            @Path("page") int page
    );

}

