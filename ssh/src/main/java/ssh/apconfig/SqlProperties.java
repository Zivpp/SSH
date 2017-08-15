package ssh.apconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration("sqlProperties")
@PropertySource({"classpath:properties/sql-script/sql-config.properties"})
public class SqlProperties {
	
    @Autowired
    private Environment env;

    public String getProperty(String key) {
        return env.getProperty(key);
    }
}
