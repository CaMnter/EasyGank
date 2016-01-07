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

package com.camnter.easygank.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：BaseGankData ( Gank数据的类型，无论是Android，还是福利，都是这样的数据 )
 * Created by：CaMnter
 * Time：2016-01-03 17:28
 */
public class BaseGankData implements Serializable {

    // 发布人
    @SerializedName("who")
    public String who;

    // 发布时间
    @SerializedName("publishedAt")
    public Date publishedAt;

    // 标题
    @SerializedName("desc")
    public String desc;

    // 类型， 一般都是"福利"
    @SerializedName("type")
    public String type;

    // 图片url
    @SerializedName("url")
    public String url;

    // 是否可用
    @SerializedName("used")
    public Boolean used;

    // 对象id
    @SerializedName("objectId")
    public String objectId;

    // 创建时间
    @SerializedName("createdAt")
    public Date createdAt;

    // 更新时间
    @SerializedName("updatedAt")
    public Date updatedAt;

}
