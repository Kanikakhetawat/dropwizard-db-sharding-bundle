package io.appform.dropwizard.sharding.dao.listeners;

import io.appform.dropwizard.sharding.listeners.TransactionListenerContext;
import io.appform.dropwizard.sharding.listeners.TransactionListener;
import org.junit.Assert;

public class TestListener implements TransactionListener {

    private final Class<?> daoClass;
    private final Class<?> entityClass;
    private final String shardName;

    public TestListener(final Class<?> daoClass,
                        final Class<?> entityClass,
                        final String shardName) {
        this.daoClass = daoClass;
        this.entityClass = entityClass;
        this.shardName = shardName;
    }

    @Override
    public void beforeExecute(TransactionListenerContext listenerContext) {
        validateContext(listenerContext);
    }

    @Override
    public void afterExecute(TransactionListenerContext listenerContext) {
        validateContext(listenerContext);
    }

    @Override
    public void afterException(TransactionListenerContext listenerContext, Throwable e) {
        validateContext(listenerContext);
    }

    private void validateContext(final TransactionListenerContext listenerContext) {
        Assert.assertEquals(shardName, listenerContext.getShardName());
        Assert.assertNotNull(listenerContext.getOpType());
        Assert.assertEquals(daoClass, listenerContext.getDaoClass());
        Assert.assertEquals(entityClass, listenerContext.getEntityClass());
    }
}
