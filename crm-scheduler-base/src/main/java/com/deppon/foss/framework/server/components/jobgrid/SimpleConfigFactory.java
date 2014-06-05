package com.deppon.foss.framework.server.components.jobgrid;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;

import org.quartz.impl.jdbcjobstore.oracle.OracleDelegate;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.deppon.foss.framework.server.components.jobgrid.impl.GridJobLoaderPlugin;
import com.deppon.foss.framework.server.components.jobgrid.impl.NOPAcquireStrategy;
import com.deppon.foss.framework.server.components.jobgrid.impl.PlannedAcquireStrategy;

public class SimpleConfigFactory implements InitializingBean, FactoryBean<Properties> {
    private String instanceId;
    private String jndiURL;
    private Class<?> jdbcDriver;
    private String jdbcURL;
    private String jdbcUser;
    private String jdbcPassword;
    private Properties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceId", instanceId);
        ThreadPool threadPool = new ThreadPool();
        threadPool.store(properties);
        JobStore jobStore = new JobStore();

        jobStore.acquireStrategyClass = PlannedAcquireStrategy.class;
        jobStore.store(properties);
        if (null != jndiURL && jndiURL.length() > 0) {
            JNDIDataSource jndiDS = new JNDIDataSource();
            jndiDS.jndiURL = updateJndiURL(jndiURL);
            jndiDS.store(properties);
        } else {
            JDBCDataSource jdbcDS = new JDBCDataSource();
            jdbcDS.driver = jdbcDriver;
            jdbcDS.URL = jdbcURL;
            jdbcDS.user = jdbcUser;
            jdbcDS.password = jdbcPassword;
            jdbcDS.store(properties);
        }
        Plugin jobLoader = new Plugin("jobLoader", GridJobLoaderPlugin.class);
        jobLoader.paramMap = new HashMap<String, String>();
        jobLoader.paramMap.put("dataSource", jobStore.dataSource);
        jobLoader.store(properties);
    }

    /**
     * <p>兼容jboss与tomcat中的quartz使用jndiURL调用方式</p>
     * tomcat 只能用 java:comp/env/jdbc/foss
     * foss 只能用jdbc/foss
     * @param jndiURL
     * @return
     */
    private String updateJndiURL(String jndiURL) {
        String prefix = "java:comp/env/";
        try {
            InitialContext cxt = new InitialContext();
            cxt.lookup(jndiURL);
            return jndiURL;
        } catch (Exception e) {
            if (jndiURL.startsWith(prefix)) {
                return updateJndiURL(jndiURL.substring(prefix.length()));
            } else {
                return updateJndiURL(prefix + jndiURL);
            }
        }
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getJndiURL() {
        return jndiURL;
    }

    public void setJndiURL(String jndiURL) {
        this.jndiURL = jndiURL;
    }

    public Class<?> getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(Class<?> jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    @Override
    public Properties getObject() throws Exception {
        return properties;
    }

    @Override
    public Class<?> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public abstract static class SettingPart {
        protected String prefix;

        public SettingPart(String prefix) {
            this.prefix = prefix;
        }

        public String getPropertyKey(String key) {
            return prefix + key;
        }

        protected Class<?> getClass(Properties props, String key) {
            String value = props.getProperty(getPropertyKey(key));
            try {
                return Class.forName(value);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        protected String getString(Properties props, String key) {
            return props.getProperty(getPropertyKey(key));
        }

        protected boolean getBoolean(Properties props, String key) {
            String value = props.getProperty(getPropertyKey(key));
            return Boolean.valueOf(value);
        }

        protected int getInteger(Properties props, String key) {
            String value = props.getProperty(getPropertyKey(key));
            return Integer.valueOf(value);
        }

        protected long getLong(Properties props, String key) {
            String value = props.getProperty(getPropertyKey(key));
            return Long.valueOf(value);
        }

        protected void setProperty(Properties props, String key, boolean value) {
            String text = Boolean.toString(value);
            props.setProperty(getPropertyKey(key), text);
        }

        protected void setProperty(Properties props, String key, String text) {
            props.setProperty(getPropertyKey(key), text);
        }

        protected void setProperty(Properties props, String key, long value) {
            String text = Long.toString(value);
            props.setProperty(getPropertyKey(key), text);
        }

        protected void setProperty(Properties props, String key, int value) {
            String text = Integer.toString(value);
            props.setProperty(getPropertyKey(key), text);
        }

        protected void setProperty(Properties props, String key, Class<?> value) {
            String text = value.getName();
            props.setProperty(getPropertyKey(key), text);
        }

        public abstract void store(Properties props);

        public abstract void load(Properties props);
    }

    public static class ThreadPool extends SettingPart {
        public Class<?> clazz = SimpleThreadPool.class;
        public int threadCount = 25;
        public int threadPriority = 5;

        public ThreadPool() {
            super("org.quartz.threadPool.");
        }

        @Override
        public void store(Properties props) {
            setProperty(props, "class", clazz);
            setProperty(props, "threadCount", threadCount);
            setProperty(props, "threadPriority", threadPriority);
        }

        @Override
        public void load(Properties props) {
            clazz = getClass(props, "class");
            threadCount = getInteger(props, "threadCount");
            threadPriority = getInteger(props, "threadPriority");
        }

    }

    public static class JobStore extends SettingPart {
        public int misfireThreshold = 6000;
        public Class<?> clazz = GridJobStoreTX.class;
        public Class<?> driverDelegateClass = OracleDelegate.class;
        public Class<?> acquireStrategyClass = NOPAcquireStrategy.class;
        public boolean useProperties = true;
        public String dataSource = "myDS";
        public String tablePrefix = "CRM_QRTZ_";
        public boolean isClustered = true;
        public int clusterCheckinInterval = 10000;
        public int maxMisfiresToHandleAtATime = 20;

        public JobStore() {
            super("org.quartz.jobStore.");
        }

        @Override
        public void store(Properties props) {
            setProperty(props, "misfireThreshold", misfireThreshold);
            setProperty(props, "class", clazz);
            setProperty(props, "driverDelegateClass", driverDelegateClass);
            setProperty(props, "useProperties", useProperties);
            setProperty(props, "dataSource", dataSource);
            setProperty(props, "tablePrefix", tablePrefix);
            setProperty(props, "isClustered", isClustered);
            setProperty(props, "clusterCheckinInterval", clusterCheckinInterval);
            setProperty(props, "acquireStrategyClass", acquireStrategyClass);
            setProperty(props, "maxMisfiresToHandleAtATime", maxMisfiresToHandleAtATime);
        }

        @Override
        public void load(Properties props) {
            misfireThreshold = getInteger(props, "misfireThreshold");
            clazz = getClass(props, "class");
            driverDelegateClass = getClass(props, "driverDelegateClass");
            acquireStrategyClass = getClass(props, "acquireStrategyClass");
            useProperties = getBoolean(props, "useProperties");
            dataSource = getString(props, "dataSource");
            tablePrefix = getString(props, "tablePrefix");
            isClustered = getBoolean(props, "isClustered");
            clusterCheckinInterval = getInteger(props, "clusterCheckinInterval");
            maxMisfiresToHandleAtATime = getInteger(props, "maxMisfiresToHandleAtATime");
        }

    }

    public static class JDBCDataSource extends SettingPart {
        public String name = "myDS";
        public Class<?> driver;
        public String URL;
        public String user;
        public String password;
        public int maxConnections = 5;

        // public String validationQuery = "select 0";

        public JDBCDataSource() {
            super("org.quartz.dataSource.");
        }

        @Override
        public String getPropertyKey(String key) {
            return prefix + name + "." + key;
        }

        @Override
        public void store(Properties props) {
            setProperty(props, "driver", driver);
            setProperty(props, "URL", URL);
            setProperty(props, "user", user);
            setProperty(props, "password", password);
            setProperty(props, "maxConnections", maxConnections);
            // setProperty(props, "validationQuery", validationQuery);
        }

        @Override
        public void load(Properties props) {
            driver = getClass(props, "driver");
            URL = getString(props, "URL");
            user = getString(props, "user");
            password = getString(props, "password");
            maxConnections = getInteger(props, "maxConnections");
            // validationQuery = getString(props, "validationQuery");
        }

    }

    public static class JNDIDataSource extends SettingPart {
        public String name = "myDS";
        public String jndiURL;

        public JNDIDataSource() {
            super("org.quartz.dataSource.");
        }

        @Override
        public String getPropertyKey(String key) {
            return prefix + name + "." + key;
        }

        @Override
        public void store(Properties props) {
            setProperty(props, "jndiURL", jndiURL);
        }

        @Override
        public void load(Properties props) {
            jndiURL = getString(props, "jndiURL");
        }

    }

    public static class Plugin extends SettingPart {
        public Map<String, String> paramMap;
        public String pluginName;
        public Class<?> clazz;

        public Plugin(String pluginName, Class<?> pluginClass) {
            super("org.quartz.plugin.");
            this.paramMap = new HashMap<String, String>();
            this.pluginName = pluginName;
            this.clazz = pluginClass;
        }

        @Override
        public String getPropertyKey(String key) {
            return prefix + pluginName + "." + key;
        }

        @Override
        public void store(Properties props) {
            setProperty(props, "class", clazz);
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                setProperty(props, entry.getKey(), entry.getValue());
            }

        }

        @Override
        public void load(Properties props) {
            this.clazz = getClass(props, "class");
        }

    }
}
