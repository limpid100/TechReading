package com.dxl.techreading.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author dxl
 * @date 2018/11/12 13:37
 */
public class CategoryPresenter implements CategoryContract.ICategoryPresenter {

    private CategoryContract.ICategoryView mICategoryView;

    public CategoryPresenter(CategoryContract.ICategoryView iCategoryView) {
        mICategoryView = iCategoryView;
    }

    @Override
    public void getCategoryItems() {
        Gson gson = new Gson();
        TestGsons testGsons = gson.fromJson("{\"error\":false,\"results\":[{\"_id\":\"5bc49bb99d2122791c972ca9\",\"createdAt\":\"2018-10-15T13:52:57.103Z\"," +
                "\"desc\":\"\\u65b0\\u7248youtube\\u89c6\\u9891\\u6548\\u679c\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8dcpt0g308w0fse83\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8gpdc4g308w0fsnpg\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/moyokoo/YoutubeVideoSample\",\"used\":true,\"who\":\"miaoyj\"},{\"_id\":\"5bd013769d21220315c66403\",\"createdAt\":\"2018-10-24T06:38:46.734Z\",\"desc\":\"Kotlin\\u5b9e\\u73b0\\u7684\\u5706\\u89d2ImageView\\uff0c\\u652f\\u6301\\u586b\\u5145color\\u8d44\\u6e90\\u548cGlide\\u7684GifDrawable\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8icvxjg309a0giwgi\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/howshea/RoundCornerImageView\",\"used\":true,\"who\":\"howshea\"},{\"_id\":\"5bd3eb7e9d2122031e666793\",\"createdAt\":\"2018-10-27T04:37:18.843Z\",\"desc\":\"A highlight & guide library for android Edit\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8jtmkej307i0dcmxj\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8k1541g307i0dc414\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8kuzk5g307i0dc7wh\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/samlss/Lighter\",\"used\":true,\"who\":\"samlss\"},{\"_id\":\"5bdbaa529d21223d7a1b0aa9\",\"createdAt\":\"2018-11-02T01:37:22.247Z\",\"desc\":\"\\u4e00\\u4e2a\\u7528\\u4e8e\\u5feb\\u901f\\u5f00\\u53d1\\u5e95\\u90e8\\u5bfc\\u822a\\u7684\\u5e93\\uff0c\\u652f\\u6301\\u7ea2\\u70b9\\u63d0\\u9192\\uff0c\\u652f\\u6301\\u5206\\u5272\\u7ebf\\u81ea\\u5b9a\\u4e49\\u3002\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8l7k2fj30910fzq37\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/wintonBy/BottomNavigationView\",\"used\":true,\"who\":\"Winton\"},{\"_id\":\"5bdd6ebe9d21223ddba8c9ec\",\"createdAt\":\"2018-11-03T09:47:42.594Z\",\"desc\":\"\\u517c\\u5bb9\\u5fae\\u4fe1 v6.7.3 \\u7684\\u591a\\u56fe\\u5206\\u4eab\\u5e93\\u3002\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8ld5ctj30kp083wfe\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/shichaohui/WXShareMultiImage\",\"used\":true,\"who\":\"\\u77f3\\u671d\\u8f89\"},{\"_id\":\"5bdf1a5b9d21223ddba8c9ef\",\"createdAt\":\"2018-11-06T08:18:17.802Z\",\"desc\":\" \\u4e00\\u4e2aAndroid\\u6587\\u5b57\\u8f6e\\u64ad\\u63a7\\u4ef6\\uff0c\\u5b9e\\u73b0\\u4e86\\u53ef\\u5782\\u76f4\\u8dd1\\u3001\\u53ef\\u6c34\\u5e73\\u8dd1\\u7684\\u8dd1\\u9a6c\\u706f\\u3002\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8ly98mg30990gj4qp\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/ChessLuo/TextBannerView\",\"used\":true,\"who\":\"ChessLuo\"},{\"_id\":\"5bdfcb489d21223dd88989cb\",\"createdAt\":\"2018-11-05T04:47:04.719Z\",\"desc\":\"\\u7528 kotlin \\u7f16\\u5199\\u7684\\u5e26\\u8fdb\\u5ea6\\u6761\\u7684\\u81ea\\u5b9a\\u4e49 View \\uff0c\\u9002\\u7528\\u4e8e\\u63d0\\u4ea4\\u6216\\u4e0a\\u4f20\\u56fe\\u7247\\u3002\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8mghkhg30mi05kk9f\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8mty6og30mi05kdsm\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8ngtcdg30mi05ktow\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/foreveronly/SubmitButton\",\"used\":true,\"who\":\"foreveronly\"},{\"_id\":\"5be145d79d21223dd50660f1\",\"createdAt\":\"2018-11-06T07:42:15.458Z\",\"desc\":\"Android\\u5f39\\u7a97\\u5f0f\\u83dc\\u5355\\uff0c\\u652f\\u6301\\u591a\\u884c\\u53ef\\u6eda\\u52a8\\u7684\\u5b50\\u83dc\\u5355\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf93vukwg307i0dcb29\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf953noeg307i0dc1d2\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf95qikvg307i0dcqi6\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"https://github.com/samlss/TimoMenu\",\"used\":true,\"who\":\"samlss\"},{\"_id\":\"5be14b7e9d21223dd50660f2\",\"createdAt\":\"2018-11-06T08:06:22.851Z\",\"desc\":\"\\u5f39\\u6027\\u89c6\\u56fe\\u662f\\u4e00\\u79cd\\u5e38\\u89c4\\u7684CardView\\uff0c\\u53ef\\u4ee5\\u4ece\\u7528\\u6237\\u89e6\\u6478\\u4e2d\\u8fdb\\u884c\\u5f2f\\u66f2\\u3002\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf977r0tg30860eju0y\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf994xfug30860ej1ky\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"chrome\",\"type\":\"Android\",\"url\":\"https://github.com/armcha/ElasticView\",\"used\":true,\"who\":\"lijinshanmx\"},{\"_id\":\"5be14be29d21223dd50660f3\",\"createdAt\":\"2018-11-06T08:08:02.150Z\",\"desc\":\"Android native BottomSheet on steroids.\",\"images\":[\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf99mhzzg308w0ft11t\",\"https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf99rx73j30dr0ls3yj\"],\"publishedAt\":\"2018-11-06T00:00:00.0Z\",\"source\":\"chrome\",\"type\":\"Android\",\"url\":\"https://github.com/andrefrsousa/SuperBottomSheet\",\"used\":true,\"who\":\"lijinshanmx\"}]}", TestGsons.class);
        List<CategoryResult> results = testGsons.getResults();
        mICategoryView.setCategoryItems(results);

    }

    @Override
    public void subscribe() {
        getCategoryItems();
    }

    @Override
    public void unSubscribe() {

    }
}
