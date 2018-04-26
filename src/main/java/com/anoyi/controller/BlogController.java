package com.anoyi.controller;

import com.anoyi.bean.*;
import com.anoyi.controller.service.JianshuService;
import com.anoyi.mongo.repository.*;
import com.anoyi.mongo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class BlogController {

    private final JianshuService jianshuService;

    private final CommentService commentService;

    private final JianshuRepository jianshuRepository;

    @GetMapping("/p/{articleId}")
    public String article(@PathVariable String articleId, Model model){
        ArticleBean article= jianshuService.parseArticle(articleId);
        article.setArticleId(articleId);
        model.addAttribute("article", article);
        List<Comment> comments = commentService.listByArticle(articleId);
        model.addAttribute("comments", comments);
        return "article";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        JianshuBean jianshuBean = jianshuService.parseUserAndBlogs();
        model.addAttribute("jianshu", jianshuBean);
        return "blog";
    }

    //所有文章列表
    @GetMapping("/blog/{page}")
    @ResponseBody
    public ResponseBean blog(@PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseArticleList(page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/nb/{id}")
    public String notebook(@PathVariable("id") String id,  Model model){
        JianshuBean jianshuBean = jianshuService.parseNoteBooks(id);
        model.addAttribute("jianshu", jianshuBean);
        return "blog";
    }

    @GetMapping("/nb/{id}/{page}")
    @ResponseBody
    public ResponseBean notebook(@PathVariable("id") String id, @PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseArticleListInNotebook(id, page);
        return ResponseBean.success(jianshuBean);
    }

    //分类
    @GetMapping("/nbs/{page}")
    @ResponseBody
    public ResponseBean notebooks(@PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseNotebookList(page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/c/{id}")
    public String collection(@PathVariable("id") String id,  Model model){
        JianshuBean jianshuBean = jianshuService.parseCollections(id);
        model.addAttribute("jianshu", jianshuBean);
        return "blog";
    }

    @GetMapping("/c/{id}/{page}")
    @ResponseBody
    public ResponseBean collection(@PathVariable("id") String id, @PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseArticleListInCollection(id, page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/cs/{page}")
    @ResponseBody
    public ResponseBean collections(@PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseCollectionList(page);
        return ResponseBean.success(jianshuBean);
    }

    /**
     * **************************************后台管理功能****************************************
     * 博客只需爬取下来存入数据库即可
     */

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    private final NoteBookRepository noteBooksRepository;
    private final OwnCollectionRepository ownCollectionRepository;
    private final ArticleJianshuRepository articleJianshuRepository;

    @GetMapping("/admin/blog/crawl")
    @ResponseBody
    public ResponseBean blog_admin(@PathVariable("page") int page)throws Exception{
        JianshuBean jianshuBeanAll = jianshuService.parseUserAndBlogs();
        /**保存作者
         * JianshuBean.Author(userId=3424642, nickname=Anoyi, avatar=//upload.jianshu.io/users/upload_avatars/3424642/fb55f16faaf6.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240, description=朝如青丝暮成雪 I just cannot push you away)
         */
        JianshuBean.Author author = jianshuBeanAll.getAuthor();
        authorRepository.save(author);

        /**保存分类
          0 = {JianshuBean$Notebook@8683} "JianshuBean.Notebook(book=false, id=23179817, name=数据结构)"
         1 = {JianshuBean$Notebook@8684} "JianshuBean.Notebook(book=false, id=19940308, name=架构)"
         2 = {JianshuBean$Notebook@8685} "JianshuBean.Notebook(book=false, id=11953543, name=DevOps)"
         3 = {JianshuBean$Notebook@8686} "JianshuBean.Notebook(book=false, id=7916985, name=Docker)"
         4 = {JianshuBean$Notebook@8687} "JianshuBean.Notebook(book=false, id=7549029, name=Spring Boot)"
         5 = {JianshuBean$Notebook@8688} "JianshuBean.Notebook(book=false, id=8347242, name=Spring Security)"
         6 = {JianshuBean$Notebook@8689} "JianshuBean.Notebook(book=false, id=17203156, name=Database)"
         7 = {JianshuBean$Notebook@8690} "JianshuBean.Notebook(book=false, id=13211261, name=Any Code Support)"
         8 = {JianshuBean$Notebook@8691} "JianshuBean.Notebook(book=false, id=12088211, name=Java)"
         9 = {JianshuBean$Notebook@8692} "JianshuBean.Notebook(book=false, id=7095933, name=网络爬虫)"
         */
        List<JianshuBean.Notebook> notebooks = jianshuBeanAll.getNotebooks();
        noteBooksRepository.saveAll(notebooks);
        /**保存专辑
          0 = {JianshuBean$OwnCollection@8711} "JianshuBean.OwnCollection(avatar=//upload.jianshu.io/collections/images/640102/ansible.png, id=640102, slug=9a3016454824, title=Ansible)"
         1 = {JianshuBean$OwnCollection@8712} "JianshuBean.OwnCollection(avatar=//upload.jianshu.io/collections/images/596155/docker.jpg, id=596155, slug=468a83a0c881, title=Docker Swarm)"
         2 = {JianshuBean$OwnCollection@8713} "JianshuBean.OwnCollection(avatar=//upload.jianshu.io/collections/images/594165/im_%281%29.png, id=594165, slug=f6d6dfdb8e03, title=轻量级微服务架构)"
         */
        final List<JianshuBean.OwnCollection> ownCollections = jianshuBeanAll.getOwnCollections();
        ownCollectionRepository.saveAll(ownCollections);
        /**
          0 = {JianshuBean$Article@8732} "JianshuBean.Article(id=9872db2e45e0, cover=//upload-images.jianshu.io/upload_images/3424642-5b8000fe5faf9887.png, title=Spring Boot 使用 gRPC 轻松调用远程方法, description=gRPC 简介 gRPC 是一个现代开源的高性能 RPC 框架，可以在任何环境下运行。它可以有效地将数据中心内和跨..., time=2018-04-23 17:56:07, likeCount=2)"
         1 = {JianshuBean$Article@8733} "JianshuBean.Article(id=acb1587e8ccd, cover=//upload-images.jianshu.io/upload_images/3424642-db2fe15902f40b71.png, title=Cloud Native 世界顶级开源项目, description=CNCF 是什么？ CNCF 是一个开源软件基金会，致力于使云原生计算具有普遍性和可持续性。 云原生计算使用开源软..., time=2018-04-16 18:59:35, likeCount=16)"
         2 = {JianshuBean$Article@8734} "JianshuBean.Article(id=14c0b6c389f0, cover=//upload-images.jianshu.io/upload_images/3424642-10d6b9daed33ad40.png, title=Spring Boot 内嵌容器 Tomcat / Undertow / Jetty 优雅停机实现, description=Spring Boot 在关闭时，如果有请求没有响应完，在不同的容器会出现不同的结果，例如，在 Tomcat 和 ..., time=2018-04-13 19:35:45, likeCount=7)"
         3 = {JianshuBean$Article@8735} "JianshuBean.Article(id=9c43383e9563, cover=//upload-images.jianshu.io/upload_images/3424642-38011c617aa93141.png, title=Ansible 跨主机变量引用, description=示例：自动化将目标主机加入到 swarm 集群，成为工作节点 原理如下： ① 将任务执行的结果注册到变量，此变量的..., time=2018-04-11 22:00:59, likeCount=1)"
         4 = {JianshuBean$Article@8736} "JianshuBean.Article(id=62388a4fcbc6, cover=//upload-images.jianshu.io/upload_images/3424642-005d1034133ea139.png, title=前世今生：1 小时学会 Ansible, description=Inventory 配置详解 所有模块及文档 注意：并发会导致变量错乱 类似于 try catch finally, time=2018-04-10 21:59:07, likeCount=0)"
         5 = {JianshuBean$Article@8737} "JianshuBean.Article(id=a97d2efb23c5, cover=//upload-images.jianshu.io/upload_images/3424642-490aa0eca29c0253.png, title=顶尖 API 文档管理工具 (Yapi), description=前言介绍 Yapi 由 YMFE 开源，旨在为开发、产品、测试人员提供更优雅的接口管理服务，可以帮助开发者轻松创建..., time=2018-04-05 11:26:48, likeCount=57)"
         6 = {JianshuBean$Article@8738} "JianshuBean.Article(id=0d59bc614baa, cover=//upload-images.jianshu.io/upload_images/3424642-061da004152c69f9.png, title=Docker Swarm 进阶：NFS 共享数据卷, description=启动 NFS 服务（CentOS 7） 首先，安装 rpcbind 和 nfs-utils 然后，编辑 /etc/..., time=2018-04-02 17:48:18, likeCount=2)"
         7 = {JianshuBean$Article@8739} "JianshuBean.Article(id=f747046f7aa6, cover=//upload-images.jianshu.io/upload_images/3424642-32b64e716e688ae4.png, title=轻量级 HTTP(s) 代理 TinyProxy, description=CentOS 下安装 TinyProxy 启动、停止、重启 相关配置 默认配置文件路径 允许所有人使用代理，注释 ..., time=2018-03-28 10:23:48, likeCount=4)"
         8 = {JianshuBean$Article@8740} "JianshuBean.Article(id=735e21163081, cover=//upload-images.jianshu.io/upload_images/3424642-bf240f5fc0b05c78.png, title=查找 - 计算式查找法 - 哈希法, description=1、哈希函数的构造方法 > 数字分析法 假设关键字 Key 为 8 位十进制整数： ① 确定哈希表的长度，示例：1..., time=2018-03-14 13:44:35, likeCount=3)"
         "JianshuBean.Article(id=cb83d8b2d5df, cover=//upload-images.jianshu.io/upload_images/3424642-03ff70711bbcf965.png, title=Ansible Ad-Hoc Commands 介绍, description=基本语法 指定host信息 <module_name> 指定模块 指定参数 常用示例 1、并行执行 2、Shell..., time=2018-03-13 09:51:47, likeCount=1)"

                    id,cover,title,description,time,likeCount
         */
        //List<JianshuBean.Article> articles = jianshuBeanAll.getArticles();

        List<ArticleBean> articleBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            Thread.sleep(3000);
            page=i;
            //爬取简书bean
            JianshuBean jianshuBeanArticle = jianshuService.parseArticleList(page);
            //获取简书bean中的文章列表
            List<JianshuBean.Article> articles = jianshuBeanArticle.getArticles();
            articleJianshuRepository.saveAll(articles);
            for (JianshuBean.Article article : articles) {
                //还需要爬取文章的正文等信息
                ArticleBean articleBeanContent = jianshuService.parseArticle(article.getId());
                System.out.println(articleBeanContent);
                ArticleBean articleBean=new ArticleBean();
                //将简书bean中的文章属性拷贝到独立的文章bean
                //id,cover,title,description,time,likeCount
                BeanUtils.copyProperties(article,articleBean);
                articleBean.setArticleId(article.getId());
                articleBean.setAuthorAvatar(author.getAvatar());
                articleBean.setAuthorName(author.getNickname());
                articleBean.setContent(articleBeanContent.getContent());
                articleBean.setUpdateTime(articleBeanContent.getUpdateTime());
                //如何获取分类和专题的id
                articleBean.setNotebookId("1");
                articleBean.setOwnCollectionId("1");
                articleBean.setUserId(author.getUserId());
                articleBean.setWords(articleBeanContent.getWords());
                
                articleBeans.add(articleBean);
            }
            //执行保存独立文章列表
            articleRepository.saveAll(articleBeans);

        }
        return ResponseBean.success(jianshuBeanAll);
    }

    @GetMapping("/admin/blog")
    public String blog_admin(Model model){
        Pageable pageable=new PageRequest(0,10);
        List<JianshuBean.Author> authors = authorRepository.findAll();
        List<JianshuBean.Notebook> notebooks = noteBooksRepository.findAll();
        List<JianshuBean.OwnCollection> ownCollections = ownCollectionRepository.findAll();

        //Page<ArticleBean> pageData = articleRepository.findAll(pageable);
        Page<JianshuBean.Article> articles = articleJianshuRepository.findAll(pageable);
        JianshuBean jianshuBean=new JianshuBean();
        jianshuBean.setAuthor(authors.get(0));
        jianshuBean.setNotebooks(notebooks);
        jianshuBean.setOwnCollections(ownCollections);
        jianshuBean.setArticles(articles.getContent());

        //model.addAttribute("articleBeans", pageData.getContent());
        model.addAttribute("jianshu", jianshuBean);
        return "admin/blog";
    }

    //所有文章列表
    @GetMapping("/admin/blog/{page}")
    @ResponseBody
    public ResponseBean adminBlog(@PathVariable("page") int page){
        JianshuBean jianshuBean = new JianshuBean();
        Page<JianshuBean.Article> pageData = articleJianshuRepository.findAll(new PageRequest(page, 10));
        jianshuBean.setArticles(pageData.getContent());
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/admin/blogFindAll")
    @ResponseBody
    public ResponseBean blogFindAll(Model model){
        List<JianshuBean> jianshuBeans = jianshuRepository.findAll();
        jianshuRepository.deleteAll();
        return ResponseBean.success(jianshuBeans);
    }
}
