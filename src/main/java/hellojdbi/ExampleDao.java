package hellojdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ExampleMapper.class)
abstract class ExampleDao {

    @SqlUpdate("INSERT INTO example (name) values (:name)")
    @GetGeneratedKeys
    abstract long create(@BindBean Example example);


    @SqlQuery("SELECT * FROM example WHERE id = :id")
    abstract Example get(@Bind("id") long id);
}
