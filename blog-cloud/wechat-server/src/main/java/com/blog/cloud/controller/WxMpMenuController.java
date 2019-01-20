package com.blog.cloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxw
 * @date 2019/1/10
 * @description
 */
@Slf4j
@RestController
@RequestMapping(value = "/wx/mp/menu")
@Api(value = "WxMpMenuController", description = "微信公众号MenuController")
public class WxMpMenuController extends WxBaseController {

    @PutMapping("/updateMenu")
    @ApiOperation(value = "更新微信菜单", notes = "更新微信菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "appId", value = "appId", required = true, dataType = "int"),
    })
    public void updateMenu () throws WxErrorException {
        WxMpService wxMpService = getWxMpService();

        String json = "{\n" +
                " 	\"button\":[\n" +
                " 	{	\n" +
                "    	\"type\":\"click\",\n" +
                "    	\"name\":\"今日歌曲\",\n" +
                "     	\"key\":\"V1001_TODAY_MUSIC\" \n" +
                "	},\n" +
                "	{ \n" +
                "		\"name\":\"菜单\",\n" +
                "		\"sub_button\":[\n" +
                "		{	\n" +
                "			\"type\":\"view\",\n" +
                "			\"name\":\"搜索\",\n" +
                "			\"url\":\"http://www.soso.com/\"\n" +
                "		},\n" +
                "		{\n" +
                "			\"type\":\"view\",\n" +
                "			\"name\":\"视频\",\n" +
                "			\"url\":\"http://v.qq.com/\"\n" +
                "		},\n" +
                "		{\n" +
                "			\"type\":\"click\",\n" +
                "			\"name\":\"赞一下我们\",\n" +
                "			\"key\":\"V1001_GOOD\"\n" +
                "		}]\n" +
                " }],\n" +
                "\"matchrule\":{\n" +
                "  \"tag_id\":\"2\",\n" +
                "  \"sex\":\"1\",\n" +
                "  \"country\":\"中国\",\n" +
                "  \"province\":\"广东\",\n" +
                "  \"city\":\"广州\",\n" +
                "  \"client_platform_type\":\"2\",\n" +
                "  \"language\":\"zh_CN\"\n" +
                "  }\n" +
                "}";

        WxMenu wxMenu = new WxMenu();

        wxMpService.getMenuService().menuCreate(json);

        // 设置菜单
        //wxMpService.getMenuService().menuCreate(wxMenu);
    }


}
