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

package com.camnter.easygank.constant;

import java.util.HashMap;

/**
 * Description：UrlMatch
 * Created by：CaMnter
 * Time：2016-01-07 17:44
 */
public class UrlMatch {

    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

    public static final String GITHUB_PREFIX = "github.com";
    public static final String GITHUB_CONTENT = "github";
    public static final int GITHUB_COLOR = 0xff000000;

    public static final String JCODECRAEER_PREFIX = "jcodecraeer.com";
    public static final String JCODECRAEER_CONTENT = "泡在网上的日子";
    public static final int JCODECRAEER_COLOR = 0xff95BA7A;

    public static final String CSDN_PREFIX = "csdn.net";
    public static final String CSDN_CONTENT = "CSDN";
    public static final int CSDN_COLOR = 0xffBE0000;

    public static final String OSCHINA_PREFIX = "oschina.net";
    public static final String OSCHINA_CONTENT = "开源中国";
    public static final int OSCHINA_COLOR = 0xff37AB4F;

    public static final String BOLE_PREFIX = "jobbole.com";
    public static final String BOLE_CONTENT = "伯乐";
    public static final int BOLE_COLOR = 0xff766964;

    public static final String WEIXIN_PREFIX = "weixin.qq.com";
    public static final String WEIXIN_CONTENT = "微信分享";
    public static final int WEIXIN_COLOR = 0xff70DC3A;

    public static final String JIANSHU_PREFIX = "jianshu.com";
    public static final String JIANSHU_CONTENT = "简书";
    public static final int JIANSHU_COLOR = 0xffE9816D;


    public static final String TUDOU_PREFIX = "tudou.com";
    public static final String TUDOU_CONTENT = "土豆";
    public static final int TUDOU_COLOR = 0xffFD6600;

    public static final String YOUKU_PREFIX = "youku.com";
    public static final String YOUKU_CONTENT = "优酷";
    public static final int YOUKU_COLOR = 0xff00A6E0;

    public static final String MIAOPAI_PREFIX = "miaopai.com";
    public static final String MIAOPAI_CONTENT = "秒拍";
    public static final int MIAOPAI_COLOR = 0xffFDD700;

    public static final String WEIBO_PREFIX = "weibo.com";
    public static final String WEIBO_CONTENT = "秒拍";
    public static final int WEIBO_COLOR = 0xffFDD700;

    public static final String M_WEIBO_PREFIX = "weibo.cn";
    public static final String M_WEIBO_CONTENT = "秒拍";
    public static final int M_WEIBO_COLOR = 0xffFDD700;

    public static final String V_SINA_PREFIX = "v.sina.cn";
    public static final String V_SINA_CONTENT = "秒拍";
    public static final int V_SINA_COLOR = 0xffFDD700;

    public static final String SINA_PREFIX = "sina.com.cn";
    public static final String SINA_CONTENT = "新浪";
    public static final int SINA_COLOR = 0xff0075DE;

    public static final String TENCENT_PREFIX = "qq.com";
    public static final String TENCENT_CONTENT = "腾讯视频";
    public static final int TENCENT_COLOR = 0xff7ED800;

    public static final String STATIC_TENCENT_PREFIX = "video.qq.com";
    public static final String STATIC_TENCENT_CONTENT = "腾讯视频";
    public static final int STATIC_TENCENT_COLOR = 0xff7ED800;

    public static final String BILIBILI_PREFIX = "bilibili.com";
    public static final String BILIBILI_CONTENT = "bilibili";
    public static final int BILIBILI_COLOR = 0xffFFAEC9;

    public static final String ACFUN_PREFIX = "acfun.tv";
    public static final String ACFUN_CONTENT = "Acfun";
    public static final int ACFUN_COLOR = 0xff67BDCD;

    public static final String NETEASE_PREFIX = "163.com";
    public static final String NETEASE_CONTENT = "网易";
    public static final int NETEASE_COLOR = 0xffC02E34;

    public static final String VMOVIER_PREFIX = "vmovier.com";
    public static final String VMOVIER_CONTENT = "V电影";
    public static final int VMOVIER_COLOR = 0xff363636;

    public static final String SOUHU_PREFIX = "sohu.com";
    public static final String SOUHU_CONTENT = "搜狐";
    public static final int SOUHU_COLOR = 0xffDC1C1E;

    public static final String TV_SOUHU_PREFIX = "tv.sohu.com";
    public static final String TV_SOUHU_CONTENT = "搜狐";
    public static final int TV_SOUHU_COLOR = 0xffDC1C1E;

    public static final String FIVE_SIX_PREFIX = "56.com";
    public static final String FIVE_SIX_CONTENT = "56";
    public static final int FIVE_SIX_COLOR = 0xffE93430;

    public static final String LETV_PREFIX = "letv.com";
    public static final String LETV_CONTENT = "乐视";
    public static final int LETV_COLOR = 0xffDA0206;


    public static final String OTHER_BLOG_CONTENT = "文章";
    public static final int OTHER_BLOG_COLOR = 0xff870D4F;

    public static final String OTHER_VIDEO_CONTENT = "其他";
    public static final int OTHER_VIDEO_COLOR = 0xff301B91;

    public static final HashMap<String, Integer> url2Color = new HashMap<>();
    public static final HashMap<String, String> url2Content = new HashMap<>();

    static {
        url2Color.put(GITHUB_PREFIX, GITHUB_COLOR);
        url2Color.put(JCODECRAEER_PREFIX, JCODECRAEER_COLOR);
        url2Color.put(CSDN_PREFIX, CSDN_COLOR);
        url2Color.put(OSCHINA_PREFIX, OSCHINA_COLOR);
        url2Color.put(BOLE_PREFIX, BOLE_COLOR);
        url2Color.put(WEIXIN_PREFIX, WEIXIN_COLOR);
        url2Color.put(JIANSHU_PREFIX, JIANSHU_COLOR);
        url2Color.put(TUDOU_PREFIX, TUDOU_COLOR);
        url2Color.put(YOUKU_PREFIX, YOUKU_COLOR);
        url2Color.put(MIAOPAI_PREFIX, MIAOPAI_COLOR);
        url2Color.put(WEIBO_PREFIX, WEIBO_COLOR);
        url2Color.put(M_WEIBO_PREFIX, M_WEIBO_COLOR);
        url2Color.put(V_SINA_PREFIX, V_SINA_COLOR);
        url2Color.put(TENCENT_PREFIX, TENCENT_COLOR);
        url2Color.put(BILIBILI_PREFIX, BILIBILI_COLOR);
        url2Color.put(STATIC_TENCENT_PREFIX, STATIC_TENCENT_COLOR);
        url2Color.put(ACFUN_PREFIX, ACFUN_COLOR);
        url2Color.put(NETEASE_PREFIX, NETEASE_COLOR);
        url2Color.put(SINA_PREFIX, SINA_COLOR);
        url2Color.put(VMOVIER_PREFIX, VMOVIER_COLOR);
        url2Color.put(SOUHU_PREFIX, SOUHU_COLOR);
        url2Color.put(FIVE_SIX_PREFIX, FIVE_SIX_COLOR);
        url2Color.put(LETV_PREFIX, LETV_COLOR);
        url2Color.put(TV_SOUHU_PREFIX, TV_SOUHU_COLOR);

        url2Content.put(GITHUB_PREFIX, GITHUB_CONTENT);
        url2Content.put(JCODECRAEER_PREFIX, JCODECRAEER_CONTENT);
        url2Content.put(CSDN_PREFIX, CSDN_CONTENT);
        url2Content.put(OSCHINA_PREFIX, OSCHINA_CONTENT);
        url2Content.put(BOLE_PREFIX, BOLE_CONTENT);
        url2Content.put(WEIXIN_PREFIX, WEIXIN_CONTENT);
        url2Content.put(JIANSHU_PREFIX, JIANSHU_CONTENT);
        url2Content.put(TUDOU_PREFIX, TUDOU_CONTENT);
        url2Content.put(YOUKU_PREFIX, YOUKU_CONTENT);
        url2Content.put(MIAOPAI_PREFIX, MIAOPAI_CONTENT);
        url2Content.put(WEIBO_PREFIX, WEIBO_CONTENT);
        url2Content.put(M_WEIBO_PREFIX, M_WEIBO_CONTENT);
        url2Content.put(V_SINA_PREFIX, V_SINA_CONTENT);
        url2Content.put(V_SINA_PREFIX, V_SINA_CONTENT);
        url2Content.put(TENCENT_PREFIX, TENCENT_CONTENT);
        url2Content.put(BILIBILI_PREFIX, BILIBILI_CONTENT);
        url2Content.put(STATIC_TENCENT_PREFIX, STATIC_TENCENT_CONTENT);
        url2Content.put(ACFUN_PREFIX, ACFUN_CONTENT);
        url2Content.put(NETEASE_PREFIX, NETEASE_CONTENT);
        url2Content.put(SINA_PREFIX, SINA_CONTENT);
        url2Content.put(VMOVIER_PREFIX, VMOVIER_CONTENT);
        url2Content.put(SOUHU_PREFIX, SOUHU_CONTENT);
        url2Content.put(FIVE_SIX_PREFIX, FIVE_SIX_CONTENT);
        url2Content.put(LETV_PREFIX, LETV_CONTENT);
        url2Content.put(TV_SOUHU_PREFIX, TV_SOUHU_CONTENT);
    }

    public static String processUrl(String url) {
        String temp;
        if (url.startsWith(HTTPS)) {
            temp = url.replace(HTTPS, "");
        } else {
            temp = url.replace(HTTP, "");
        }
        return temp.substring(temp.indexOf(".") + 1, temp.indexOf("/"));
    }

}
