package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.bean.MapBean;
import cn.miss.spring.spider.bean.Pharmacy;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/11/3.
 */
public class MapAdapter extends AbstractAdapter<MapBean, Pharmacy> {
    @Override
    public DownType getDownType() {
        return null;
    }

    @Override
    public String getPrefix() {
        return "https://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=bt&c=131&sn=1$$58099ba07af2be0baf080fde$$12959763.95,4846797.61$$%E7%AB%8B%E6%B0%B4%E6%A1%A5$$0$$$$&en=1$$2435c41e54ffab581cf74dde$$12965337.03,4829449.03$$%E5%9B%A2%E7%BB%93%E6%B9%96$$0$$$$&sc=131&ec=131&pn=0&rn=5&exptype=dep&exptime=2018-11-03%2011:52&version=5&da_src=&da_src=pcmappg.searchBox.button&tn=B_NORMAL_MAP&nn=0&auth=LB85v1K1vvHQ%40eDda5PXPZZZ272BC%4036uxHExzxNxETt1qo6DF%3D%3DCy1uVt1GgvPUDZYOYIZuxtEdwKv7ucvY1SGpuHt300b0z8yPWv3GuEtXzljPaVjyBDEHKOQUWYxcEWe1GD8zv7u%40ZPuVteuztghxehwzJGzBz4PBDv0kMMxA7wIookK7&u_loc=12960312,4847406&ie=utf-8&l=12&b=(12939524.297444444,4825210.7391111115;12970232.424066667,4851025.600888888)&t=1541217154149";
    }

    @Override
    public List<MapBean> adapter(Pharmacy pharmacy) {
        final String download = getDownLoader().download(getUrl(pharmacy.getAddress()));
        return getParser().parse(download);
    }
}
