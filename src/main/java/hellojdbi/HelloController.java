package hellojdbi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HelloController {

    private ExampleDao exampleDao;

    @Autowired
    public void setExampleDao(ExampleDao exampleDao) {
        this.exampleDao = exampleDao;
    }

    @RequestMapping("/")
    public String index() {

        Example example = new Example();
        example.setName("My Example");

        long exampleId = exampleDao.create(example);
        Example createdExample = exampleDao.get(exampleId);

        return String.format("Created example '%s' with id '%d'", createdExample.getName(), createdExample.getId());
    }
}
