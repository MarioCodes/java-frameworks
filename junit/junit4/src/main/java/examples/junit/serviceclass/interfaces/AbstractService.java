package examples.junit.serviceclass.interfaces;

public interface AbstractService<TYPE> {

    TYPE create(final TYPE type);

    TYPE update(final TYPE type);

    TYPE remove(final TYPE type);

}
