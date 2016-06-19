package hellojdbi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Example> wheres = exampleDao.getWhere("My Example", 2);
        String wheresJoined = wheres.stream()
                .map(Example::getId)
                .map(i -> i.toString())
                .collect(Collectors.joining(", "));

        return String.format("Created example '%s' with id '%d'\nFound %s", createdExample.getName(), createdExample.getId(), wheresJoined);

    }
}
