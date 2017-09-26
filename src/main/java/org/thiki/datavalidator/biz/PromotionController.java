package org.thiki.datavalidator.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sufangfang
 */
@RestController
@EnableAutoConfiguration
public class PromotionController {

    @Autowired
    private PromotionMapper promotionsMapper;

    @RequestMapping(value = "/abnormalPromotion", method = RequestMethod.GET)
    public Object abnormalPromotion() {
        List<Promotion> promotions = promotionsMapper.fetchPromotionWithoutProduction();
        Map<String, Object> result = new HashMap<>();
        result.put("desc", "打折活动的商品找不到");
        result.put("promotions", promotions);
        return result;
    }

}
