package com.danli.config;

/**
 * Redis cache key
 *Redis (Remote Dictionary Server) is an open source, network-enabled,
 * memory-based and persistent logging, key-value database written in ANSI C language,
 * and provides multiple language APIS.
 * @author: Yicong Wang
 * @date: 2022
 */
public class RedisKeyConfig {
    //Home Blog Introduction List Page object key：homeBlogInfoList : {{1," first page cache "},{2," second page cache "}}
    public static final String BLOG_INFO_CACHE = "BlogInfoCache";

    //Category name list key
    public static final String CATEGORY_NAME_CACHE = "categoryNameCache";


    //Classified blog list key
    public static final String CATEGORY_BLOG_CACHE = "categoryBlogCache";





    //About my page key
    public static final String ABOUT_INFO_CACHE = "aboutInfoCache";

    //Friendlink information key
    public static final String FRIEND_INFO_CACHE = "friendInfoCache";

    //Friendchain page blog information key
    public static final String FRIEND_BLOG_CACHE = "friendBlogCache";

    //Blog Archive Key
    public static final String ARCHIVE_INFO_CACHE = "archiveInfoCache";

    //Blog Visit Key
    public static final String BLOG_VIEWS_MAP = "blogViewsMap";

    //Visitor ID key
    public static final String IDENTIFICATION_SET = "identificationSet";

    //No paging key
    public static final String All = "all";

    //PVUV
    public static final String PV_UV = "pv_uv";

    //Access to the key
    public static final String VISIT_API = "visitApi";


}



