package hellojdbi;

import org.skife.jdbi.v2.ReflectionBeanMapper;

public class ExampleMapper extends ReflectionBeanMapper<Example> {
    public ExampleMapper() {
        super(Example.class);
    }
}
