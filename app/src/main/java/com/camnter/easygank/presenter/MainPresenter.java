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

package com.camnter.easygank.presenter;

import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.constant.Constant;
import com.camnter.easygank.core.mvp.BasePresenter;
import com.camnter.easygank.gank.GankApi;
import com.camnter.easygank.gank.GankType;
import com.camnter.easygank.gank.GankTypeDict;
import com.camnter.easygank.presenter.iview.MainView;
import com.camnter.easygank.utils.DateUtils;
import com.camnter.easygank.utils.ReservoirUtils;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Subscriber;

/**
 * Description：MainPresenter
 * Created by：CaMnter
 * Time：2016-01-03 18:09
 */
public class MainPresenter extends BasePresenter<MainView> {

    private EasyDate currentDate;
    private int page;

    private ReservoirUtils reservoirUtils;

    /**
     * 查每日干货需要的特殊的类
     */
    public class EasyDate implements Serializable {
        private Calendar calendar;

        public EasyDate(Calendar calendar) {
            this.calendar = calendar;
        }

        public int getYear() {
            return calendar.get(Calendar.YEAR);
        }

        public int getMonth() {
            return calendar.get(Calendar.MONTH) + 1;
        }

        public int getDay() {
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public List<EasyDate> getPastTime() {
            List<EasyDate> easyDates = new ArrayList<>();
            for (int i = 0; i < GankApi.DEFAULT_DAILY_SIZE; i++) {
                /*
                 * - (page * DateUtils.ONE_DAY) 翻到哪页再找 一页有DEFAULT_DAILY_SIZE这么长
                 * - i * DateUtils.ONE_DAY 往前一天一天 找呀找
                 */
                long time = this.calendar.getTimeInMillis() - ((page - 1) * GankApi.DEFAULT_DAILY_SIZE * DateUtils.ONE_DAY) - i * DateUtils.ONE_DAY;
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(time);
                EasyDate date = new EasyDate(c);
                easyDates.add(date);
            }
            return easyDates;
        }

    }


    public MainPresenter() {
        this.reservoirUtils = new ReservoirUtils();
        long time = System.currentTimeMillis();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        this.currentDate = new EasyDate(mCalendar);
        this.page = 1;
    }

    /**
     * 设置查询第几页
     *
     * @param page page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取当前页数量
     *
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * 查询每日数据
     *
     * @param refresh 是否是刷新
     * @param oldPage olaPage==GankTypeDict.DONT_SWITCH表示不是切换数据
     */
    public void getDaily(final boolean refresh, final int oldPage) {
        /*
         * 切换数据源的话,尝试页数1
         */
        if (oldPage != GankTypeDict.DONT_SWITCH) {
            this.page = 1;
        }
        this.mCompositeSubscription.add(this.mDataManager.getDailyDataByNetwork(this.currentDate)
                .subscribe(new Subscriber<List<GankDaily>>() {
                    @Override
                    public void onCompleted() {
                        if (MainPresenter.this.mCompositeSubscription != null)
                            MainPresenter.this.mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Logger.d(e.getMessage());
                        } catch (Throwable e1) {
                            e1.getMessage();
                        } finally {
                            if (refresh) {
                                Type resultType = new TypeToken<List<GankDaily>>() {
                                }.getType();
                                MainPresenter.this.reservoirUtils.get(GankType.daily + "", resultType, new ReservoirGetCallback<List<GankDaily>>() {
                                    @Override
                                    public void onSuccess(List<GankDaily> object) {
                                        // 有缓存显示缓存数据
                                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                                            if (MainPresenter.this.getMvpView() != null)
                                                MainPresenter.this.getMvpView().onSwitchSuccess(GankType.daily);
                                        }
                                        if (MainPresenter.this.getMvpView() != null)
                                            MainPresenter.this.getMvpView().onGetDailySuccess(object, refresh);
                                        if (MainPresenter.this.getMvpView() != null)
                                            MainPresenter.this.getMvpView().onFailure(e);
                                    }

                                    @Override
                                    public void onFailure(Exception e) {
                                        MainPresenter.this.switchFailure(oldPage, e);
                                    }
                                });
                            } else {
//                                MainPresenter.this.switchFailure(oldPage, e);
                                // 加载更多失败
                                MainPresenter.this.getMvpView().onFailure(e);
                            }
                        }

                    }

                    @Override
                    public void onNext(List<GankDaily> dailyData) {
                        /*
                         * 如果是切换数据源
                         * page=1加载成功了
                         * 即刚才的loadPage
                         */
                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                            if (MainPresenter.this.getMvpView() != null)
                                MainPresenter.this.getMvpView().onSwitchSuccess(GankType.daily);
                        }
                        // 刷新缓存
                        if (refresh)
                            MainPresenter.this.reservoirUtils.refresh(GankType.daily + "", dailyData);
                        if (MainPresenter.this.getMvpView() != null)
                            MainPresenter.this.getMvpView().onGetDailySuccess(dailyData, refresh);
                    }
                }));
    }

    /**
     * * 查询 ( Android、iOS、前端、拓展资源、福利、休息视频 )
     *
     * @param type    GankType
     * @param refresh 是否是刷新
     * @param oldPage olaPage==GankTypeDict.DONT_SWITCH表示不是切换数据
     */
    public void getData(final int type, final boolean refresh, final int oldPage) {
        /*
         * 切换数据源的话,尝试页数1
         */
        if (oldPage != GankTypeDict.DONT_SWITCH) {
            this.page = 1;
        }
        String gankType = GankTypeDict.type2UrlTypeDict.get(type);
        if (gankType == null) return;
        this.mCompositeSubscription.add(this.mDataManager.getDataByNetWork(gankType, GankApi.DEFAULT_DATA_SIZE, this.page)
                .subscribe(new Subscriber<ArrayList<BaseGankData>>() {
                    @Override
                    public void onCompleted() {
                        if (MainPresenter.this.mCompositeSubscription != null)
                            MainPresenter.this.mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Logger.d(e.getMessage());
                        } catch (Throwable e1) {
                            e1.getMessage();
                        } finally {
                            if (refresh) {
                                Type resultType = new TypeToken<ArrayList<BaseGankData>>() {
                                }.getType();
                                MainPresenter.this.reservoirUtils.get(type + "", resultType, new ReservoirGetCallback<ArrayList<BaseGankData>>() {
                                    @Override
                                    public void onSuccess(ArrayList<BaseGankData> object) {
                                        // 有缓存显示缓存数据
                                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                                            if (MainPresenter.this.getMvpView() != null)
                                                MainPresenter.this.getMvpView().onSwitchSuccess(type);
                                        }
                                        if (MainPresenter.this.getMvpView() != null)
                                            MainPresenter.this.getMvpView().onGetDataSuccess(object, refresh);
                                        if (MainPresenter.this.getMvpView() != null)
                                            MainPresenter.this.getMvpView().onFailure(e);
                                    }

                                    @Override
                                    public void onFailure(Exception e) {
                                        MainPresenter.this.switchFailure(oldPage, e);
                                    }
                                });
                            } else {
//                                MainPresenter.this.switchFailure(oldPage, e);
                                // 加载更多失败
                                MainPresenter.this.getMvpView().onFailure(e);
                            }
                        }
                    }

                    @Override
                    public void onNext(ArrayList<BaseGankData> baseGankData) {
                        /*
                         * 如果是切换数据源
                         * page=1加载成功了
                         * 即刚才的loadPage
                         */
                        if (oldPage != GankTypeDict.DONT_SWITCH) {
                            if (MainPresenter.this.getMvpView() != null)
                                MainPresenter.this.getMvpView().onSwitchSuccess(type);
                        }
                        // 刷新缓存
                        if (refresh)
                            MainPresenter.this.reservoirUtils.refresh(type + "", baseGankData);
                        if (MainPresenter.this.getMvpView() != null)
                            MainPresenter.this.getMvpView().onGetDataSuccess(baseGankData, refresh);


                    }
                }));

    }

    public void switchType(int type) {
        /*
         * 记录没切换前的数据的页数
         * 以防切换数据后，网络又跪导致的page对不上问题
         */
        int oldPage = this.page;
        switch (type) {
            case GankType.daily:
                this.getDaily(true, oldPage);
                break;
            case GankType.android:
            case GankType.ios:
            case GankType.js:
            case GankType.resources:
            case GankType.welfare:
            case GankType.video:
            case GankType.app:
                this.getData(type, true, oldPage);
                break;
        }
    }


    public void getDailyDetail(final GankDaily.DailyResults results) {
        this.mCompositeSubscription.add(this.mDataManager.getDailyDetailByDailyResults(results)
                .subscribe(new Subscriber<ArrayList<ArrayList<BaseGankData>>>() {
                    @Override
                    public void onCompleted() {
                        if (MainPresenter.this.mCompositeSubscription != null)
                            MainPresenter.this.mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Logger.d(e.getMessage());
                        } catch (Throwable e1) {
                            e1.getMessage();
                        }
                    }

                    @Override
                    public void onNext(ArrayList<ArrayList<BaseGankData>> detail) {
                        if (MainPresenter.this.getMvpView() != null) {
                            // 相信福利一定有
                            MainPresenter.this.getMvpView().getDailyDetail(
                                    DateUtils.date2String(results.welfareData.get(0).publishedAt.getTime(),
                                            Constant.DAILY_DATE_FORMAT), detail
                            );
                        }
                    }
                }));
    }

    /**
     * 切换分类失败
     *
     * @param oldPage oldPage
     */
    private void switchFailure(int oldPage, Throwable e) {
        /*
         * 如果是切换数据源
         * 刚才尝试的page＝1失败了的请求
         * 加载失败
         * 会影响到原来页面的page
         * 在这里执行复原page操作
         */
        if (oldPage != GankTypeDict.DONT_SWITCH)
            MainPresenter.this.page = oldPage;
        if (MainPresenter.this.getMvpView() != null)
            MainPresenter.this.getMvpView().onFailure(e);
    }


}
