package com.wongzl.spring.hellospring.transactiondemo;

import com.wongzl.spring.hellospring.exception.RollbackException;

/**
 * 。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2019-02-15 22:06
 */

public interface FooService {

    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;


}
