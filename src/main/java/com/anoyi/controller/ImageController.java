package com.anoyi.controller;

import com.alibaba.fastjson.JSON;
import com.anoyi.mongo.repository.ImageRepsotory;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.anoyi.bean.Image;

/**
 * Created by szz on 2018/4/23 22:06.
 * Email szhz186@gmail.com
 */
@RestController
@AllArgsConstructor
public class ImageController {

    private ImageRepsotory imageRepsotory;
    @GetMapping("/admin/image_all")
    public String imageAll(Model model){
        List<Image> images = imageRepsotory.findAll();
        model.addAttribute("images", images);
        return JSON.toJSON(images).toString();
    }
    @GetMapping("/admin/image_save")
    public String book() {
        String img1 = "https://upload.jianshu.io/collections/images/16/computer_guy.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img2 = "https://upload.jianshu.io/collections/images/264569/2.pic.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img3 = "https://upload.jianshu.io/collections/images/14/6249340_194140034135_2.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img4 = "https://upload.jianshu.io/collections/images/514184/WechatIMG959.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img5 = "https://cdn2.jianshu.io/assets/default_avatar/8-a356878e44b45ab268a3b0bbaaadeeb7.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img6 = "https://upload.jianshu.io/users/upload_avatars/5227845/aaec28a0-a44e-4990-9363-4cd6cbca1d79.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img7 = "https://upload.jianshu.io/collections/images/76/12.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img8 = "https://upload.jianshu.io/users/upload_avatars/2649104/23ee4091f4cd.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img9 = "https://upload.jianshu.io/users/upload_avatars/3301278/f9ec10522566?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
        String img10 = "https://upload.jianshu.io/users/upload_avatars/5659613/ca7881e4-7ccd-45f5-a7d2-f35dd7203dec.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";

        List<String> imgList = new ArrayList<>();
        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);
        imgList.add(img5);
        imgList.add(img6);
        imgList.add(img7);
        imgList.add(img8);
        imgList.add(img9);
        imgList.add(img10);
        for (int i = 1; i <= imgList.size(); i++) {
            String s =  imgList.get(i-1);
            Image image=new Image(i+"",s);
            imageRepsotory.save(image);
        }
        return "success";
    }
}
