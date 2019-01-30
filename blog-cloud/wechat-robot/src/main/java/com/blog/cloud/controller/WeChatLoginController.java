package com.blog.cloud.controller;

import com.blog.cloud.domain.response.LoginResponse;
import com.blog.cloud.enums.LoginCode;
import com.blog.cloud.event.WechatRobotApplicationEvent;
import com.blog.cloud.http.RestResultBuilder;
import com.blog.cloud.service.LoginService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/wechat/login")
@Api(value = "WeChatLoginController", description = "微信登陆控制器")
public class WeChatLoginController extends WeChatBaseController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(value = "/createLoginQRCode")
    @ApiOperation(value = "创建微信机器人登陆二维码", notes = "创建微信机器人登陆二维码")
    public void createLoginQRCode() {
        ServletOutputStream stream = null;
        try {
            String result = loginService.createLoginQRCode();
            String loginQRCode = result.split(",")[0];
            String uuid = result.split(",")[1];

            response.setHeader("uuid", uuid);
            stream = response.getOutputStream();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bm = qrCodeWriter.encode(loginQRCode, BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageWriter.writeToStream(bm, "png", stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                stream.flush();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping(value = "/checkScanQRCodeLogin")
    @ApiOperation(value = "校验微信机器人是否登陆", notes = "校验微信机器人是否登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "uuid", value = "登陆的UUID", required = true)
    })
    public RestResultBuilder<LoginResponse> checkScanQRCodeLogin() {
        String uuid = request.getHeader("uuid");
        long startTime = System.currentTimeMillis();
        LoginResponse loginResponse = loginService.checkScanQRCodeLogin(uuid);
        log.info("响应的数据为 ============> {}", loginResponse);
        long endTime = System.currentTimeMillis();
        log.info("响应时间为 ==============> {}", (endTime - startTime) / 1000);
        return successBuild(loginResponse);
    }

    @PutMapping(value = "/wechatRobotLogin")
    @ApiOperation(value = "微信机器人登陆", notes = "微信机器人登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "uuid", value = "登陆的UUID", required = true)
    })
    public RestResultBuilder<String> wechatRobotLogin(@RequestBody LoginResponse loginResponse) {
        String uuid = request.getHeader("uuid");
        if (LoginCode.SUCCESS.getCode().equals(loginResponse.getCode())) {
            try {
                loginService.wechatRobotLogin(loginResponse, uuid);
//                publisher.publishEvent(new WechatRobotApplicationEvent(uuid));
            } catch (Exception e) {
                e.printStackTrace();
                return failBuild("登陆失败");
            }
        } else {
            return failBuild("登陆失败");
        }
        return successBuild();
    }

}
