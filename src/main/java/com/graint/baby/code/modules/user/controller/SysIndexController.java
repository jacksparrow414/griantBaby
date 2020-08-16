package com.graint.baby.code.modules.user.controller;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.graint.baby.code.vo.TransactionListVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jacksparrow414
 * @date 2020-03-14
 * @description: TODO
 */
@RestController
@RequestMapping(value = "transaction")
public class SysIndexController {

    @PostMapping(value = "list")
    public List<TransactionListVO> list(){
        List<TransactionListVO> items = Lists.newArrayList();
        // 构造假数据d
        double min = 4593.12;
        TransactionListVO transactionListVO1 = new TransactionListVO(RandomUtil.randomDouble(15000),"success", RandomUtil.randomString(23));
        TransactionListVO transactionListVO2 = new TransactionListVO(RandomUtil.randomDouble(15000),"success",RandomUtil.randomString(23));
        TransactionListVO transactionListVO3 = new TransactionListVO(RandomUtil.randomDouble(15000),"pending",RandomUtil.randomString(23));
        TransactionListVO transactionListVO4 = new TransactionListVO(RandomUtil.randomDouble(15000),"pending",RandomUtil.randomString(23));
        TransactionListVO transactionListVO5 = new TransactionListVO(RandomUtil.randomDouble(15000),"success",RandomUtil.randomString(23));
        TransactionListVO transactionListVO6 = new TransactionListVO(RandomUtil.randomDouble(15000),"pending",RandomUtil.randomString(23));
        TransactionListVO transactionListVO7 = new TransactionListVO(RandomUtil.randomDouble(15000),"success",RandomUtil.randomString(23));
        TransactionListVO transactionListVO8 = new TransactionListVO(RandomUtil.randomDouble(15000),"pending",RandomUtil.randomString(23));

        items.add(transactionListVO1);
        items.add(transactionListVO2);
        items.add(transactionListVO3);
        items.add(transactionListVO4);
        items.add(transactionListVO5);
        items.add(transactionListVO6);
        items.add(transactionListVO7);
        items.add(transactionListVO8);

        return items;
    }
}
