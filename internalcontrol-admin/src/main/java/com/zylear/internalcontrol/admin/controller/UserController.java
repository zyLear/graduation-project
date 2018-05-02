package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.domain.User;
import com.zylear.internalcontrol.admin.enums.Authority;
import com.zylear.internalcontrol.admin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * Created by xiezongyu on 2018/4/28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static char[] sequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};


    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/user/login");
    }

    @RequestMapping(value = "/register-admin")
    public ModelAndView registerSdmin() {
        return new ModelAndView("/user/register-admin");
    }

    @ResponseBody
    @RequestMapping(value = "/sure-login")
    public BasePageResult sureLogin(HttpServletRequest request,
                                    @Param("account") String account,
                                    @Param("password") String password,
                                    @Param("authority") Integer authority,
                                    @Param("captcha") String captcha) {
        String code = null;
        try {
            code = request.getSession().getAttribute("captcha").toString();
        } catch (Exception e) {
            logger.info("get captcha from session fail.", e);
            return BasePageResult.CAPTCHA_ERROR;
        }
        if (!captcha.equalsIgnoreCase(code)) {
            return BasePageResult.CAPTCHA_ERROR;
        }

        User user = userService.findByAccountAndPassword(account, password);
        if (user == null) {
            return BasePageResult.ERROR_RESPONSE;
        }

        request.getSession().setAttribute("authority", authority);
        request.getSession().setAttribute("account", account);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-register")
    public BasePageResult sureRegister(@Param("account") String account,
                                       @Param("password") String password,
                                       @Param("authority") Integer authority) {


        User user = userService.findByAccount(account);
        if (user != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setAuthority(authority);
        user.setIsDeleted(false);
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());
        userService.insert(user);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-logout")
    public BasePageResult sureLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("authority");
        request.getSession().removeAttribute("account");
        return BasePageResult.SUCCESS_RESPONSE;
    }


    @RequestMapping("/generateCaptcha")
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 在内存中创建图象
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字或字母)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = sequence[random.nextInt(sequence.length)] + "";
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }
        // 将认证码存入SESSION
        request.getSession().setAttribute("captcha", sRand);
        // 图象生效
        g.dispose();
        ServletOutputStream output = null;
        ImageOutputStream imageOut = null;
        try {
            output = response.getOutputStream();
            imageOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(image, "JPEG", imageOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            imageOut.close();
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
