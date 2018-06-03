package com.anymk.controller;

import com.anymk.bean.*;
import com.anymk.controller.service.JianshuService;
import com.anymk.mongo.repository.*;
import com.anymk.mongo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.ArrayList;
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
    public ResponseBean blog_admin()throws Exception{
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
        /**保存专栏
          0 = {JianshuBean$OwnCollection@8711} "JianshuBean.OwnCollection(avatar=//upload.jianshu.io/collections/images/640102/ansible.png, id=640102, slug=9a3016454824, title=Ansible)"
         1 = {JianshuBean$OwnCollection@8712} "JianshuBean.OwnCollection(avatar=//upload.jianshu.io/collections/images/596155/docker.jpg, id=596155, slug=468a83a0c881, title=Docker Swarm)"
         2 = {JianshuBean$OwnCollection@8713} "JianshuBean.OwnCollection(avatar=//upload.jianshu.io/collections/images/594165/im_%281%29.png, id=594165, slug=f6d6dfdb8e03, title=轻量级微服务架构)"
         */
        final List<JianshuBean.OwnCollection> ownCollections = jianshuBeanAll.getOwnCollections();
        ownCollectionRepository.saveAll(ownCollections);
        /**
          0 = {JianshuBean$Article@8732} "JianshuBean.Article(id=9872db2e45e0, cover=//upload-images.jianshu.io/upload_images/3424642-5b8000fe5faf9887.png, title=Spring Boot 使用 gRPC 轻松调用远程方法, description=gRPC 简介 gRPC 是一个现代开源的高性能 RPC 框架，可以在任何环境下运行。它可以有效地将数据中心内和跨..., time=2018-04-23 17:56:07, likeCount=2)"

                    id,cover,title,description,time,likeCount
         */
        //List<JianshuBean.Article> articles = jianshuBeanAll.getArticles();

        List<ArticleBean> articleBeans = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            System.out.println("第"+i+"次爬");

            Thread.sleep(500);
            //爬取简书bean
            JianshuBean jianshuBeanArticle = jianshuService.parseArticleList(i);
            //获取简书bean中的文章列表
            List<JianshuBean.Article> articles = jianshuBeanArticle.getArticles();
            for (JianshuBean.Article article : articles) {
                //还需要爬取文章的正文等信息
                ArticleBean articleBeanContent = jianshuService.parseArticle(article.getId());
                ArticleBean articleBean=new ArticleBean();
                //将简书bean中的文章属性拷贝到独立的文章bean
                //id,cover,title,description,time,likeCount
                BeanUtils.copyProperties(article,articleBean);
                articleBean.setArticleId(article.getId());
                articleBean.setAuthorAvatar(author.getAvatar());
                articleBean.setAuthorName(author.getNickname());
                articleBean.setContent(articleBeanContent.getContent());
                articleBean.setUpdateTime(articleBeanContent.getUpdateTime());
                articleBean.setUserId(author.getUserId());
                articleBean.setWords(articleBeanContent.getWords());
                //如何获取分类和专题的id
                List<JianshuBean.Notebook> notebooksList = noteBooksRepository.findAll();
                for (JianshuBean.Notebook notebook : notebooksList) {
                    JianshuBean jianshuBean = jianshuService.parseNoteBooks(notebook.getId());
                    List<JianshuBean.Article> articles1 = jianshuBean.getArticles();
                    for (JianshuBean.Article article1 : articles1) {
                        if (article.getId().equals(article1.getId())){
                            article.setNoteBookId(notebook.getId());
                            System.out.println("找到了notebookid");
                        }
                    }
                }

                List<JianshuBean.OwnCollection> ownCollectionList = ownCollectionRepository.findAll();
                for (JianshuBean.OwnCollection ownCollection : ownCollectionList) {
                    JianshuBean jianshuBean = jianshuService.parseCollections(ownCollection.getId());
                    List<JianshuBean.Article> articles1 = jianshuBean.getArticles();
                    for (JianshuBean.Article article1 : articles1) {
                        if (article.getId().equals(article1.getId())) {
                            article.setOwnId(ownCollection.getId());
                            System.out.println("找到了ownid");
                        }
                    }
                }

                //更新简书文章的内容
                article.setAuthorId(author.getUserId());
                article.setWords(articleBeanContent.getWords());

                articleBeans.add(articleBean);
            }

            //保存简书文章列表
            articleJianshuRepository.saveAll(articles);
            System.out.println(articles);
            //执行保存独立文章列表
            articleRepository.saveAll(articleBeans);

        }
        return ResponseBean.success(jianshuBeanAll);
    }

    /**
     * 按分类id和专辑id爬取简书文章,并根据文章id将分类id和专辑id更新到文章表中
     * @return
     */
    @GetMapping("/admin/blog/crawlbyownandnote")
    @ResponseBody
    public String crawlByOwnAndNote(){
        return "success";
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

    @GetMapping("/admin/c/{id}")
    public String collectionAdmin(@PathVariable("id") String id,  Model model){
        Pageable pageable=new PageRequest(0,10);
        List<JianshuBean.Author> authors = authorRepository.findAll();
        List<JianshuBean.Notebook> notebooks = noteBooksRepository.findAll();
        List<JianshuBean.OwnCollection> ownCollections = ownCollectionRepository.findAll();

        //按ownid查询列表
        Page<JianshuBean.Article> articles = articleJianshuRepository.findByOwnId (id,pageable);
        JianshuBean jianshuBean=new JianshuBean();
        jianshuBean.setAuthor(authors.get(0));
        jianshuBean.setNotebooks(notebooks);
        jianshuBean.setOwnCollections(ownCollections);
        jianshuBean.setArticles(articles.getContent());
        model.addAttribute("jianshu", jianshuBean);
        return "admin/blog";
    }

    @GetMapping("/admin/c/{id}/{page}")
    @ResponseBody
    public ResponseBean collectionAdminPage(@PathVariable("id") String id, @PathVariable("page") int page){
        Pageable pageable=new PageRequest(page,10);
        List<JianshuBean.Author> authors = authorRepository.findAll();
        List<JianshuBean.Notebook> notebooks = noteBooksRepository.findAll();
        List<JianshuBean.OwnCollection> ownCollections = ownCollectionRepository.findAll();

        //按ownid查询列表
        Page<JianshuBean.Article> articles = articleJianshuRepository.findByOwnId (id,pageable);
        JianshuBean jianshuBean=new JianshuBean();
        jianshuBean.setAuthor(authors.get(0));
        jianshuBean.setNotebooks(notebooks);
        jianshuBean.setOwnCollections(ownCollections);
        jianshuBean.setArticles(articles.getContent());
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/admin/nb/{id}")
    public String notebookAdmin(@PathVariable("id") String id,  Model model){
        Pageable pageable=new PageRequest(0,10);
        List<JianshuBean.Author> authors = authorRepository.findAll();
        List<JianshuBean.Notebook> notebooks = noteBooksRepository.findAll();
        List<JianshuBean.OwnCollection> ownCollections = ownCollectionRepository.findAll();

        //按ownid查询列表
        Page<JianshuBean.Article> articles = articleJianshuRepository.findByNoteBookId (id,pageable);
        JianshuBean jianshuBean=new JianshuBean();
        jianshuBean.setAuthor(authors.get(0));
        jianshuBean.setNotebooks(notebooks);
        jianshuBean.setOwnCollections(ownCollections);
        jianshuBean.setArticles(articles.getContent());
        model.addAttribute("jianshu", jianshuBean);
        return "admin/blog";
    }

    @GetMapping("/admin/nb/{id}/{page}")
    @ResponseBody
    public ResponseBean notebookAdminPage(@PathVariable("id") String id, @PathVariable("page") int page){
        Pageable pageable=new PageRequest(page,10);
        List<JianshuBean.Author> authors = authorRepository.findAll();
        List<JianshuBean.Notebook> notebooks = noteBooksRepository.findAll();
        List<JianshuBean.OwnCollection> ownCollections = ownCollectionRepository.findAll();

        //按ownid查询列表
        Page<JianshuBean.Article> articles = articleJianshuRepository.findByNoteBookId (id,pageable);
        JianshuBean jianshuBean=new JianshuBean();
        jianshuBean.setAuthor(authors.get(0));
        jianshuBean.setNotebooks(notebooks);
        jianshuBean.setOwnCollections(ownCollections);
        jianshuBean.setArticles(articles.getContent());
        return ResponseBean.success(jianshuBean);
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
        List<JianshuBean.Article> articles = articleJianshuRepository.findAll();
        for (JianshuBean.Article article : articles) {
            if (article.getNoteBookId()==null)                {
                article.setNoteBookId("11953543");
            }
        }
        articleJianshuRepository.saveAll(articles);
        return ResponseBean.success(articles);
    }

    /**
     * 根据文章标题或内容搜索文章
     */
    @RequestMapping("/admin/blog_search")
    public String blogSearch(String title, Model model) throws Exception {
        if (StringUtils.isEmpty(title)) {
            return "redirect:/admin/blog";
        }
        URLEncoder.encode(title, "utf-8");
        List<JianshuBean.Article> articles=articleJianshuRepository.findByTitleLike(title);
        JianshuBean jianshuBean=new JianshuBean();
        jianshuBean.setArticles(articles);
        model.addAttribute("jianshu", jianshuBean);
        return "admin/blog";
    }

}
