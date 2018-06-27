
/**
 * Created by szz on 2018/4/20 21:16.
 * Email szhz186@gmail.com
 */
public class Test {

    public static void main(String[] args) {

        String request = HttpClientUtils.sendGetRequest("http://localhost/api/user/admin");
        System.out.println(request);
    }

}
