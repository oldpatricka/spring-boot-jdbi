package hellojdbi;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ExampleDao {

    private ExampleDb db;

    @Autowired
    public void setDbi(DBI dbi) {
        this.db = dbi.onDemand(ExampleDb.class);
    }

    public long create(Example example) {
        return db.create(example);
    }

    public Example get(long id) {
        return db.get(id);
    }

    public List<Example> getWhere(String name, long minimumId) {
        String where = String.format("name = '%s' AND id >= %s", name, minimumId);
        return db.getWhere(where);
    }


    @UseStringTemplate3StatementLocator
    @RegisterMapper(ExampleMapper.class)
    public static abstract class ExampleDb {

        @SqlUpdate("INSERT INTO example (name) values (:name)")
        @GetGeneratedKeys
        abstract long create(@BindBean Example example);


        @SqlQuery("SELECT * FROM example WHERE id = :id")
        abstract Example get(@Bind("id") long id);

        @SqlQuery("SELECT * FROM example WHERE <where>")
        abstract List<Example> getWhere(@Define("where") String where);
    }
}

