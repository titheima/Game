package com.anoyi.tools;

import com.anoyi.bean.MessageBean;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * 钉钉工具类
 */
@Log4j2
public class DingTalkTools {

    private final static String API = "https://oapi.dingtalk.com/robot/send?access_token=b1586fba8caf2c98bf6f1174b4ec57c75941553a15a75c437422f55fc1b76cd1";

    private final static String TYPE_TEXT = "text";

    /**
     * 文本留言到钉钉
     */
    public static void textMessage(MessageBean messageBean) {
        log.info("leave a message");
        String content = "{\"msgtype\": \"" + TYPE_TEXT + "\", \"text\": {\"content\": \"" + messageBean.getNickname() + "\n" + messageBean.getEmail() + "\n" + messageBean.getContent() + "\"}}";
        try {
            Jsoup.connect(API).header("Content-Type", "application/json").requestBody(content).ignoreContentType(true).post();
        } catch (IOException e) {
            log.error("Leave a message fail! " + messageBean.toString());
        }
    }

}
