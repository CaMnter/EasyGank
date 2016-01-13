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

package com.camnter.easygank.data;

import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.model.impl.DailyModel;
import com.camnter.easygank.model.impl.DataModel;
import com.camnter.easygank.presenter.MainPresenter;
import com.camnter.easygank.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Description：DataManager
 * Created by：CaMnter
 * Time：2016-01-13 13:45
 */
public class DataManager {

    private static DataManager dataManager;

    private DailyModel dailyModel;
    private DataModel dataModel;

    public synchronized static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private DataManager() {
        this.dataModel = DataModel.getInstance();
        this.dailyModel = DailyModel.getInstance();
    }

    public Observable<List<GankDaily>> getDailyDataByNetwork(MainPresenter.EasyDate currentDate) {
        return Observable.just(currentDate)
                .flatMapIterable(MainPresenter.EasyDate::getPastTime)
                .flatMap(easyDate -> {
                    /*
                     * 感觉Android的数据应该不会为null
                     * 所以以Android的数据为判断是否当天有数据
                     */
                    return this.dailyModel
                            .getDaily(easyDate.getYear(), easyDate.getMonth(), easyDate.getDay())
                            .filter(dailyData -> dailyData.results.androidData != null);
                })
                .toSortedList((dailyData, dailyData2) -> {
                    return dailyData2.results.androidData.get(0).publishedAt.compareTo(dailyData.results.androidData.get(0).publishedAt);
                }).compose(RxUtils.applyIOToMainThreadSchedulers());
    }

    public Observable<ArrayList<BaseGankData>> getDataByNetWork(String type, int size, int page) {
        return this.dataModel.getData(type, size, page)
                .map(gankData -> gankData.results)
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }

    public Observable<ArrayList<ArrayList<BaseGankData>>> getDailyDetailByDailyResults(GankDaily.DailyResults results) {
        return Observable.just(results)
                .map(dailyResults -> {
                    ArrayList<ArrayList<BaseGankData>> cardData = new ArrayList<>();
                    if (dailyResults.welfareData != null && dailyResults.welfareData.size() > 0)
                        cardData.add(dailyResults.welfareData);
                    if (dailyResults.androidData != null && dailyResults.androidData.size() > 0)
                        cardData.add(dailyResults.androidData);
                    if (dailyResults.iosData != null && dailyResults.iosData.size() > 0)
                        cardData.add(dailyResults.iosData);
                    if (dailyResults.jsData != null && dailyResults.jsData.size() > 0)
                        cardData.add(dailyResults.jsData);
                    if (dailyResults.videoData != null && dailyResults.videoData.size() > 0)
                        cardData.add(dailyResults.videoData);
                    if (dailyResults.resourcesData != null && dailyResults.resourcesData.size() > 0)
                        cardData.add(dailyResults.resourcesData);
                    if (dailyResults.appData != null && dailyResults.appData.size() > 0)
                        cardData.add(dailyResults.appData);
                    if (dailyResults.recommendData != null && dailyResults.recommendData.size() > 0)
                        cardData.add(dailyResults.recommendData);
                    return cardData;
                })
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }

}
