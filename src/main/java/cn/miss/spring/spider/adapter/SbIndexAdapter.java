package cn.miss.spring.spider.adapter;

import cn.miss.spring.spider.bean.DownType;
import cn.miss.spring.spider.bean.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/11/3.
 */
public class SbIndexAdapter extends AbstractAdapter<Pharmacy, String> {

    @Autowired
    private MapAdapter mapAdapter;

    @Override
    public DownType getDownType() {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public List<Pharmacy> adapter(String s) {
        return null;
    }
}
