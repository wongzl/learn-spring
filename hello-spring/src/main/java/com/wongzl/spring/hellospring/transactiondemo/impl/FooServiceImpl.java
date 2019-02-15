package com.wongzl.spring.hellospring.transactiondemo.impl;

import com.wongzl.spring.hellospring.exception.RollbackException;
import com.wongzl.spring.hellospring.transactiondemo.FooService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2019-02-15 22:06
 */
@Component
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    /**
     * 我们知道声明式事务是通过AOP的方式实现的，当在本类中调用另一个函数时，因为没有走代理类因此事务是没有起效的
     *
     * @throws RollbackException
     */
    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        // insertThenRollback();
        // 要想同类中起效
        FooServiceImpl fooService = (FooServiceImpl) AopContext.currentProxy();
        fooService.insertThenRollback();
    }
}
