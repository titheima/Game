package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by szz on 2018/4/23 22:05.
 * Email szhz186@gmail.com
 */
@Data
public class Image {
    @Id
    private String id;
    private String imgUrl;

    public Image() {
    }

    public Image(String id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }

}
