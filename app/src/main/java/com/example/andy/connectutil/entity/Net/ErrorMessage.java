package com.example.andy.connectutil.entity.Net;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by andy on 2017/4/12.
 */

public class ErrorMessage {
    private int code;
    private Context context;
    public ErrorMessage(Context context,int code)
    {
        this.code=code;
        this.context=context;
        HandleError();
    }

    private void HandleError() {
        switch (code)
        {
            case 4001001:
                Toast.makeText(context,"请求数据字段验证不通过",Toast.LENGTH_SHORT).show();
                break;
            case 4001002:
                Toast.makeText(context,"请求数据必须字段不可为空",Toast.LENGTH_SHORT).show();
                break;
            case 4001003:
                Toast.makeText(context,"手机验证码不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001004:
                Toast.makeText(context,"手机验证码错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001005:
                Toast.makeText(context,"注册的手机号已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001006:
                Toast.makeText(context,"注册的邮箱已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001007:
                Toast.makeText(context,"密码错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001008:
                Toast.makeText(context,"帐号不合法",Toast.LENGTH_SHORT).show();
                break;
            case 4001009:
                Toast.makeText(context,"企业成员状态不合法",Toast.LENGTH_SHORT).show();
            case 4001010:
                Toast.makeText(context,"刷新token不合法",Toast.LENGTH_SHORT).show();
                break;
            case 4001011:
                Toast.makeText(context,"未知成员角色类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001012:
                Toast.makeText(context,"只有管理员才能邀请",Toast.LENGTH_SHORT).show();
                break;
             case 4001013:
                Toast.makeText(context,"不可修改其他成员信息",Toast.LENGTH_SHORT).show();
                break;
             case 4001014:
                Toast.makeText(context,"不能删除本人",Toast.LENGTH_SHORT).show();
                break;
             case 4001015:
                Toast.makeText(context,"未知的产品连接类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001016:
                Toast.makeText(context,"已发布的产品不可删除",Toast.LENGTH_SHORT).show();
                break;
            case 4001017:
                Toast.makeText(context,"固件版本已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001018:
                Toast.makeText(context,"数据端点未知数据类型",Toast.LENGTH_SHORT).show();
                break;
            case 4001019:
                Toast.makeText(context,"数据端点索引已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001020:
                Toast.makeText(context,"已发布的数据端点不可删除",Toast.LENGTH_SHORT).show();
                break;
            case 4001021:
                Toast.makeText(context,"该产品下设备MAC地址已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001022:
                Toast.makeText(context,"不能删除已激活的设备",Toast.LENGTH_SHORT).show();
                break;
            case 4001023:
                Toast.makeText(context,"扩展属性Key为预留字段",Toast.LENGTH_SHORT).show();
                break;
            case 4001024:
                Toast.makeText(context,"设备扩展属性超过上限",Toast.LENGTH_SHORT).show();
                break;
            case 4001025:
                Toast.makeText(context,"新增已存在的扩展属性",Toast.LENGTH_SHORT).show();
                break;
             case 4001026:
                Toast.makeText(context,"更新不存在的扩展属性",Toast.LENGTH_SHORT).show();
                break;
             case 4001027:
                Toast.makeText(context,"属性字段名不合法",Toast.LENGTH_SHORT).show();
                break;
             case 4001028:
                Toast.makeText(context,"邮件验证码错误",Toast.LENGTH_SHORT).show();
                break;
             case 4001029:
                Toast.makeText(context,"新增已存在的扩展属性",Toast.LENGTH_SHORT).show();
                break;
             case 4001030:
                Toast.makeText(context,"用户状态不合法",Toast.LENGTH_SHORT).show();
                break;
            case 4001031:
                Toast.makeText(context,"用户手机尚未认证",Toast.LENGTH_SHORT).show();
                break;
            case 4001032:
                Toast.makeText(context,"用户邮箱尚未认证",Toast.LENGTH_SHORT).show();
                break;
            case 4001033:
                Toast.makeText(context,"用户已经订阅设备",Toast.LENGTH_SHORT).show();
                break;
            case 4001034:
                Toast.makeText(context,"用户没有订阅该设备",Toast.LENGTH_SHORT).show();
                break;
            case 4001035:
                Toast.makeText(context,"自动升级任务名称已存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001036:
                Toast.makeText(context,"升级任务状态未知",Toast.LENGTH_SHORT).show();
                break;
             case 4001037:
                Toast.makeText(context,"已有相同的起始版本升级任务",Toast.LENGTH_SHORT).show();
                break;
             case 4001038:
                Toast.makeText(context,"设备激活失败",Toast.LENGTH_SHORT).show();
                break;
             case 4001039:
                Toast.makeText(context,"设备认证失败",Toast.LENGTH_SHORT).show();
                break;
             case 4001041:
                Toast.makeText(context,"订阅设备认证码错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001042:
                Toast.makeText(context,"授权名称已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001043:
                Toast.makeText(context,"该告警规则名称已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001045:
                Toast.makeText(context,"数据表名称已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001046:
                Toast.makeText(context,"产品固件文件超过大小限制",Toast.LENGTH_SHORT).show();
                break;
            case 4001047:
                Toast.makeText(context,"APN密钥文件超过大小限制",Toast.LENGTH_SHORT).show();
                break;
            case 4001048:
                Toast.makeText(context,"APP的APN功能未启用",Toast.LENGTH_SHORT).show();
                break;
            case 4001049:
                Toast.makeText(context,"产品未允许用户注册设备",Toast.LENGTH_SHORT).show();
                break;
            case 4001050:
                Toast.makeText(context,"该类型的邮件模板已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001051:
                Toast.makeText(context,"邮件模板正文内容参数缺失",Toast.LENGTH_SHORT).show();
                break;
            case 4001052:
                Toast.makeText(context,"该手机今日发送短信的次数已达上限",Toast.LENGTH_SHORT).show();
                break;
            case 4001053:
                Toast.makeText(context,"设备已经是最新版本",Toast.LENGTH_SHORT).show();
                break;
            case 4001054:
                Toast.makeText(context,"设备不在线",Toast.LENGTH_SHORT).show();
                break;
            case 4001055:
                Toast.makeText(context,"设备升级失败",Toast.LENGTH_SHORT).show();
                break;
            case 4001056:
                Toast.makeText(context,"模板审核中",Toast.LENGTH_SHORT).show();
                break;
            case 4001057:
                Toast.makeText(context,"应用类型错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001058:
                Toast.makeText(context,"数据表类型错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001059:
                Toast.makeText(context,"第三方用户验证失败",Toast.LENGTH_SHORT).show();
                break;
            case 4001060:
                Toast.makeText(context,"图片大小超过上限",Toast.LENGTH_SHORT).show();
                break;
            case 4001061:
                Toast.makeText(context,"用户由于多次输入错误密码已被锁定",Toast.LENGTH_SHORT).show();
                break;
            case 4001062:
                Toast.makeText(context,"企业成员账号已经激活",Toast.LENGTH_SHORT).show();
                break;
            case 4001063:
                Toast.makeText(context,"用户邮箱已经激活",Toast.LENGTH_SHORT).show();
                break;
            case 4001064:
                Toast.makeText(context,"访问设备超时",Toast.LENGTH_SHORT).show();
                break;
            case 4001065:
                Toast.makeText(context,"微信授权登录失败",Toast.LENGTH_SHORT).show();
                break;
              case 4001066:
                Toast.makeText(context,"微博授权登录失败",Toast.LENGTH_SHORT).show();
                break;
              case 4001067:
                Toast.makeText(context,"获取QQ用户信息失败",Toast.LENGTH_SHORT).show();
                break;
              case 4001068:
                Toast.makeText(context,"获取微信用户信息失败",Toast.LENGTH_SHORT).show();
                break;
              case 4001069:
                Toast.makeText(context,"获取微博用户信息失败",Toast.LENGTH_SHORT).show();
                break;
              case 4001070:
                Toast.makeText(context,"获取第三方用户信息失败",Toast.LENGTH_SHORT).show();
                break;
            case 4001071:
                Toast.makeText(context,"AccessKey不可用",Toast.LENGTH_SHORT).show();
                break;
            case 4001072:
                Toast.makeText(context,"AccessKey错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001073:
                Toast.makeText(context,"该Mac地址的设备已经授权",Toast.LENGTH_SHORT).show();
                break;
            case 4001074:
                Toast.makeText(context,"该Mac地址的设备未授权",Toast.LENGTH_SHORT).show();
                break;
            case 4001075:
                Toast.makeText(context,"无效的转发URL",Toast.LENGTH_SHORT).show();
                break;
            case 4001076:
                Toast.makeText(context,"用户不是该设备的管理员",Toast.LENGTH_SHORT).show();
                break;
            case 4001077:
                Toast.makeText(context,"微信授权配置参数无效",Toast.LENGTH_SHORT).show();
                break;
            case 4001078:
                Toast.makeText(context,"产品授权中",Toast.LENGTH_SHORT).show();
                break;
            case 4001079:
                Toast.makeText(context,"管理员账号密码错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001080:
                Toast.makeText(context,"配额达到上限",Toast.LENGTH_SHORT).show();
                break;
            case 4001081:
                Toast.makeText(context,"访问邮件服务商异常",Toast.LENGTH_SHORT).show();
                break;
            case 4001082:
                Toast.makeText(context,"云迁移任务不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001083:
                Toast.makeText(context,"云迁移任务已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001084:
                Toast.makeText(context,"云迁移任务取消",Toast.LENGTH_SHORT).show();
                break;
            case 4001085:
                Toast.makeText(context,"云迁移任务完成",Toast.LENGTH_SHORT).show();
                break;
             case 4001086:
                Toast.makeText(context,"产品连接类型并非PC类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001087:
                Toast.makeText(context,"产品不允许激活注册设备",Toast.LENGTH_SHORT).show();
                break;
             case 4001088:
                Toast.makeText(context,"邮件域名超过上限",Toast.LENGTH_SHORT).show();
                break;
             case 4001089:
                Toast.makeText(context,"未知的快照类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001090:
                Toast.makeText(context,"该应用插件类型已存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001091:
                Toast.makeText(context,"产品已经发布",Toast.LENGTH_SHORT).show();
                break;
             case 4001092:
                Toast.makeText(context,"应用插件已存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001093:
                Toast.makeText(context,"标签搜索运算符未知",Toast.LENGTH_SHORT).show();
                break;
             case 4001094:
                Toast.makeText(context,"手机号已被使用",Toast.LENGTH_SHORT).show();
                break;
             case 4001095:
                Toast.makeText(context,"非法URL",Toast.LENGTH_SHORT).show();
                break;
            case 4001096:
                Toast.makeText(context,"邮箱已被使用",Toast.LENGTH_SHORT).show();
                break;
            case 4001097:
                Toast.makeText(context,"设备授权码验证错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001098:
                Toast.makeText(context,"向设备发送probe包失败",Toast.LENGTH_SHORT).show();
                break;
            case 4001099:
                Toast.makeText(context,"自定义角色使用中",Toast.LENGTH_SHORT).show();
                break;
            case 4001100:
                Toast.makeText(context,"splashWnd图片数量达到上限",Toast.LENGTH_SHORT).show();
                break;
             case 4001101:
                Toast.makeText(context,"xfile最大数量",Toast.LENGTH_SHORT).show();
                break;
             case 4001102:
                Toast.makeText(context,"密码过于简单",Toast.LENGTH_SHORT).show();
                break;
             case 4001103:
                Toast.makeText(context,"生成二维码信息数量超过上限",Toast.LENGTH_SHORT).show();
                break;
             case 4001104:
                Toast.makeText(context,"广播消息类型未知",Toast.LENGTH_SHORT).show();
                break;
             case 4001105:
                Toast.makeText(context,"广播消息动作类型未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001106:
                Toast.makeText(context,"广播消息目标地址类型未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001107:
                Toast.makeText(context,"用户不是第三方用户",Toast.LENGTH_SHORT).show();
                break;
            case 4001108:
                Toast.makeText(context,"用户已初始化登录密码",Toast.LENGTH_SHORT).show();
                break;
            case 4001109:
                Toast.makeText(context,"用户已绑定第三方帐号",Toast.LENGTH_SHORT).show();
                break;
            case 4001110:
                Toast.makeText(context,"解除绑定第三方帐号失败,请保证至少保留一个第三方帐号信息或者Xlink帐号信息",Toast.LENGTH_SHORT).show();
                break;
             case 4001111:
                Toast.makeText(context,"设备地理位置信息不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001112:
                Toast.makeText(context,"未知的设备固件类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001113:
                Toast.makeText(context,"未知的固件升级任务类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001114:
                Toast.makeText(context,"设备ID与调用凭证不对应",Toast.LENGTH_SHORT).show();
                break;
             case 4001115:
                Toast.makeText(context,"未知的用户性别",Toast.LENGTH_SHORT).show();
                break;
            case 4001116:
                Toast.makeText(context,"用户区域设置错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001117:
                Toast.makeText(context,"经销商已被停用",Toast.LENGTH_SHORT).show();
                break;
            case 4001118:
                Toast.makeText(context,"大客户已被停用",Toast.LENGTH_SHORT).show();
                break;
            case 4001119:
                Toast.makeText(context,"推送任务不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001120:
                Toast.makeText(context,"企业云迁移任务不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001121:
                Toast.makeText(context,"设备未激活",Toast.LENGTH_SHORT).show();
                break;
            case 4001122:
                Toast.makeText(context,"设备区域设置错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001123:
                Toast.makeText(context,"推送任务已经开启，不可重复开启",Toast.LENGTH_SHORT).show();
                break;
            case 4001124:
                Toast.makeText(context,"用户未初始化密码",Toast.LENGTH_SHORT).show();
                break;
            case 4001125:
                Toast.makeText(context,"用户自定义的第三方账户已被绑定",Toast.LENGTH_SHORT).show();
                break;
             case 4001126:
                Toast.makeText(context,"QQ账户已被绑定",Toast.LENGTH_SHORT).show();
                break;
             case 4001127:
                Toast.makeText(context,"微信账户已被绑定",Toast.LENGTH_SHORT).show();
                break;
             case 4001128:
                Toast.makeText(context,"微博账户已被绑定",Toast.LENGTH_SHORT).show();
                break;
             case 4001129:
                Toast.makeText(context,"未识别的二维码",Toast.LENGTH_SHORT).show();
                break;
             case 4001130:
                Toast.makeText(context,"未知的Home邀请",Toast.LENGTH_SHORT).show();
                break;
            case 4001131:
                Toast.makeText(context,"Home邀请状态错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001133:
                Toast.makeText(context,"Home创建者不可退出",Toast.LENGTH_SHORT).show();
                break;
            case 4001132:
                Toast.makeText(context,"Home超级管理员不可删除",Toast.LENGTH_SHORT).show();
                break;
            case 4001134:
                Toast.makeText(context,"必须为Home邀请者",Toast.LENGTH_SHORT).show();
                break;
            case 4001135:
                Toast.makeText(context,"用户归属在不同Home下",Toast.LENGTH_SHORT).show();
                break;
            case 4001136:
                Toast.makeText(context,"Home成员已达到限制",Toast.LENGTH_SHORT).show();
                break;
            case 4001137:
                Toast.makeText(context,"未启用应用",Toast.LENGTH_SHORT).show();
                break;
            case 4001138:
                Toast.makeText(context,"未知升级任务类型",Toast.LENGTH_SHORT).show();
                break;
            case 4001139:
                Toast.makeText(context,"不允许多个管理员",Toast.LENGTH_SHORT).show();
                break;
            case 4001140:
                Toast.makeText(context,"设备不归属在Home中",Toast.LENGTH_SHORT).show();
                break;
             case 4001141:
                Toast.makeText(context,"设备因扫描策略原因订阅失败",Toast.LENGTH_SHORT).show();
                break;
             case 4001142:
                Toast.makeText(context,"固件在启动的升级任务中不能被修改",Toast.LENGTH_SHORT).show();
                break;
             case 4001143:
                Toast.makeText(context,"未知的经销商销售记录中客户类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001144:
                Toast.makeText(context,"应用未被授权",Toast.LENGTH_SHORT).show();
                break;
             case 4001145:
                Toast.makeText(context,"未知的应用状态",Toast.LENGTH_SHORT).show();
                break;
            case 4001146:
                Toast.makeText(context,"微信设备授权任务不是正在进行",Toast.LENGTH_SHORT).show();
                break;
            case 4001147:
                Toast.makeText(context,"导入的设备mac地址或者sn重复",Toast.LENGTH_SHORT).show();
                break;
            case 4001148:
                Toast.makeText(context,"设备的sn已存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001149:
                Toast.makeText(context,"快照统计规则统计粒度未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001150:
                Toast.makeText(context,"快照规则的统计规则超过限额",Toast.LENGTH_SHORT).show();
                break;
             case 4001151:
                Toast.makeText(context,"快照规则不包含数据端点",Toast.LENGTH_SHORT).show();
                break;
             case 4001152:
                Toast.makeText(context,"快照统计规则统计方式未知",Toast.LENGTH_SHORT).show();
                break;
             case 4001153:
                Toast.makeText(context,"智能互联的指令不合法",Toast.LENGTH_SHORT).show();
                break;
             case 4001154:
                Toast.makeText(context,"需要进行图片验证码",Toast.LENGTH_SHORT).show();
                break;
             case 4001155:
                Toast.makeText(context,"未知的图片验证码",Toast.LENGTH_SHORT).show();
                break;
            case 4001156:
                Toast.makeText(context,"错误的图片验证码",Toast.LENGTH_SHORT).show();
                break;
            case 4001157:
                Toast.makeText(context,"快照规则不支持有统计规则",Toast.LENGTH_SHORT).show();
                break;
            case 4001158:
                Toast.makeText(context,"排序方式类型未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001159:
                Toast.makeText(context,"快照统计规则未知类型",Toast.LENGTH_SHORT).show();
                break;
            case 4001160:
                Toast.makeText(context,"快照规则有统计规则",Toast.LENGTH_SHORT).show();
                break;
            case 4001161:
                Toast.makeText(context,"无效的oauth授权应用",Toast.LENGTH_SHORT).show();
                break;
            case 4001162:
                Toast.makeText(context,"无效的oauth回调URI",Toast.LENGTH_SHORT).show();
                break;
            case 4001163:
                Toast.makeText(context,"无效的oauth的scope列表",Toast.LENGTH_SHORT).show();
                break;
            case 4001164:
                Toast.makeText(context,"无效的oauth授权码",Toast.LENGTH_SHORT).show();
                break;
            case 4001165:
                Toast.makeText(context,"无效的oauth请求类型",Toast.LENGTH_SHORT).show();
                break;
            case 4001166:
                Toast.makeText(context,"oauth授权失败",Toast.LENGTH_SHORT).show();
                break;
            case 4001167:
                Toast.makeText(context,"快照统计规则未知状态",Toast.LENGTH_SHORT).show();
                break;
            case 4001168:
                Toast.makeText(context,"oauth access token无效",Toast.LENGTH_SHORT).show();
                break;
            case 4001169:
                Toast.makeText(context,"twitter用户已经存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001170:
                Toast.makeText(context,"facebook用户已经存在",Toast.LENGTH_SHORT).show();
                break;
            case 4001171:
                Toast.makeText(context,"oauth版本未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001172:
                Toast.makeText(context,"导出任务未知状态",Toast.LENGTH_SHORT).show();
                break;
            case 4001173:
                Toast.makeText(context,"导出任务不是处于已完成导出状态",Toast.LENGTH_SHORT).show();
                break;
            case 4001174:
                Toast.makeText(context,"导出任务已过期",Toast.LENGTH_SHORT).show();
                break;
            case 4001175:
                Toast.makeText(context,"未知的扩展属性",Toast.LENGTH_SHORT).show();
                break;
            case 4001176:
                Toast.makeText(context,"销售信息不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001177:
                Toast.makeText(context,"大客户用户名已存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001178:
                Toast.makeText(context,"数据端点来源类型未知",Toast.LENGTH_SHORT).show();
                break;
             case 4001179:
                Toast.makeText(context,"经销商账号已存在",Toast.LENGTH_SHORT).show();
                break;
             case 4001180:
                Toast.makeText(context,"无效的oauth请求类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001181:
                Toast.makeText(context,"手机区号错误",Toast.LENGTH_SHORT).show();
                break;
             case 4001182:
                Toast.makeText(context,"设备日志类型未知",Toast.LENGTH_SHORT).show();
                break;
             case 4001183:
                Toast.makeText(context,"非应用类型的数据端点",Toast.LENGTH_SHORT).show();
                break;
             case 4001184:
                Toast.makeText(context,"产品连接类型不是蓝牙类型",Toast.LENGTH_SHORT).show();
                break;
             case 4001185:
                Toast.makeText(context,"第三方用户账号URL校验失败",Toast.LENGTH_SHORT).show();
                break;
            case 4001186:
                Toast.makeText(context,"未设置子设备功能的相关配置",Toast.LENGTH_SHORT).show();
                break;
            case 4001187:
                Toast.makeText(context,"子设备网关的能力的配置项错误",Toast.LENGTH_SHORT).show();
                break;
            case 4001188:
                Toast.makeText(context,"网关能力类型未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001189:
                Toast.makeText(context,"耗材数据类型未知",Toast.LENGTH_SHORT).show();
                break;
            case 4001190:
                Toast.makeText(context,"设备授权的最大数量达到限制",Toast.LENGTH_SHORT).show();
                break;
            case 4001191:
                Toast.makeText(context,"设备已经设置所归属Home",Toast.LENGTH_SHORT).show();
                break;
            case 4031001:
                Toast.makeText(context,"禁止访问",Toast.LENGTH_SHORT).show();
                break;
            case 4031002:
                Toast.makeText(context,"禁止访问，需要Access-Token",Toast.LENGTH_SHORT).show();
                break;
            case 4031003:
                Toast.makeText(context,"无效的Access-Token",Toast.LENGTH_SHORT).show();
                break;
            case 4031004:
                Toast.makeText(context,"需要企业的调用权限",Toast.LENGTH_SHORT).show();
                break;
            case 4031005:
                Toast.makeText(context,"需要企业管理员权限",Toast.LENGTH_SHORT).show();
                break;
             case 4031006:
                Toast.makeText(context,"需要数据操作权限",Toast.LENGTH_SHORT).show();
                break;
             case 4031007:
                Toast.makeText(context,"禁止访问私有数据",Toast.LENGTH_SHORT).show();
                break;
             case 4031008:
                Toast.makeText(context,"分享已经被取消",Toast.LENGTH_SHORT).show();
                break;
             case 4031009:
                Toast.makeText(context,"分享已经接受",Toast.LENGTH_SHORT).show();
                break;
             case 4031010:
                Toast.makeText(context,"用户没有订阅设备，不能执行操作",Toast.LENGTH_SHORT).show();
                break;
            case 4031011:
                Toast.makeText(context,"应用插件认证不通过",Toast.LENGTH_SHORT).show();
                break;
            case 4031012:
                Toast.makeText(context,"分享已经失效",Toast.LENGTH_SHORT).show();
                break;
            case 4031013:
                Toast.makeText(context,"设备不能被订阅",Toast.LENGTH_SHORT).show();
                break;
            case 4031014:
                Toast.makeText(context,"需要home的管理员及以上的权限",Toast.LENGTH_SHORT).show();
                break;
            case 4031015:
                Toast.makeText(context,"需要home的创建者权限",Toast.LENGTH_SHORT).show();
                break;
             case 4031016:
                Toast.makeText(context,"Home访客过期",Toast.LENGTH_SHORT).show();
                break;
             case 4031017:
                Toast.makeText(context,"需要home的超级管理员权限",Toast.LENGTH_SHORT).show();
                break;
             case 4031018:
                Toast.makeText(context,"应用提供商身份签名无效",Toast.LENGTH_SHORT).show();
                break;
             case 4031019:
                Toast.makeText(context,"Oauth的token没有权限调用接口",Toast.LENGTH_SHORT).show();
                break;
             case 4031020:
                Toast.makeText(context,"需要大客户组织用户管理员权限",Toast.LENGTH_SHORT).show();
                break;
            case 4041001:
                Toast.makeText(context,"URL找不到",Toast.LENGTH_SHORT).show();
                break;
            case 4041002:
                Toast.makeText(context,"企业成员帐号不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041003:
                Toast.makeText(context,"企业成员不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041004:
                Toast.makeText(context,"激活的成员邮箱不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041005:
                Toast.makeText(context,"需要大客户组织用户管理员权限",Toast.LENGTH_SHORT).show();
                break;
             case 4041006:
                Toast.makeText(context,"产品固件不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041007:
                Toast.makeText(context,"数据端点不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041008:
                Toast.makeText(context,"设备不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041009:
                Toast.makeText(context,"设备扩展属性不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041010:
                Toast.makeText(context,"企业不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041011:
                Toast.makeText(context,"用户不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041012:
                Toast.makeText(context,"用户扩展属性不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041013:
                Toast.makeText(context,"升级任务不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041014:
                Toast.makeText(context,"第三方身份授权不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041015:
                Toast.makeText(context,"告警规则不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041016:
                Toast.makeText(context,"数据表不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041017:
                Toast.makeText(context,"数据不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041018:
                Toast.makeText(context,"分享资源不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041019:
                Toast.makeText(context,"企业邮箱不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041020:
                Toast.makeText(context,"APP不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041021:
                Toast.makeText(context,"产品转发规则不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041022:
                Toast.makeText(context,"邮件模板不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041023:
                Toast.makeText(context,"第三方用户验证URL不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041024:
                Toast.makeText(context,"AccessKey不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041025:
                Toast.makeText(context,"微信授权配置不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041026:
                Toast.makeText(context,"快照规则不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041027:
                Toast.makeText(context,"splashWnd图片不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041028:
                Toast.makeText(context,"splashWnd图片已经存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041029:
                Toast.makeText(context,"成员角色不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041030:
                Toast.makeText(context,"经销商不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041031:
                Toast.makeText(context,"大客户不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041032:
                Toast.makeText(context,"推送任务不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041033:
                Toast.makeText(context,"产品配额超过上限",Toast.LENGTH_SHORT).show();
                break;
            case 4041034:
                Toast.makeText(context,"导入授权记录不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041035:
                Toast.makeText(context,"home的成员不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041036:
                Toast.makeText(context,"home不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041037:
                Toast.makeText(context,"应用提供商不存在",Toast.LENGTH_SHORT).show();
                break;
             case 4041038:
                Toast.makeText(context,"推送消息在推送中或者已推送,不允许删除",Toast.LENGTH_SHORT).show();
                break;
             case 4041039:
                Toast.makeText(context,"经销商下不存在该设备",Toast.LENGTH_SHORT).show();
                break;
             case 4041040:
                Toast.makeText(context,"快照统计规则不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041041:
                Toast.makeText(context,"授权记录不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041042:
                Toast.makeText(context,"home邀请记录不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041043:
                Toast.makeText(context,"虚拟设备不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041044:
                Toast.makeText(context,"快照统计规则不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041045:
                Toast.makeText(context,"大客户用户名不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041046:
                Toast.makeText(context,"指定的大客户组织不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041047:
                Toast.makeText(context,"用户不是邀请者",Toast.LENGTH_SHORT).show();
                break;
            case 4041048:
                Toast.makeText(context,"产品扩展属性不存在",Toast.LENGTH_SHORT).show();
                break;
            case 4041049:
                Toast.makeText(context,"产品不支持子设备",Toast.LENGTH_SHORT).show();
                break;
            case 4041050:
                Toast.makeText(context,"上级经销商代码不存在",Toast.LENGTH_SHORT).show();
                break;
             case 5031001:
                Toast.makeText(context,"服务端发生异常",Toast.LENGTH_SHORT).show();
                break;
             default:
                 break;
        }
    }
}
