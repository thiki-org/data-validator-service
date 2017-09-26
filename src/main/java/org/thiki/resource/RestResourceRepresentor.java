package org.thiki.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joeaniu on 10/19/16.
 * E: entity type
 */
public abstract class RestResourceRepresentor<E> extends ResourceAssemblerSupport<E, ResourceSupport> {


    private Map<String, Object> requestParams;

    public RestResourceRepresentor(Class<?> controllerClass){
        super(controllerClass, ResourceSupport.class);
        requestParams = new HashMap<>();
    }


    public RestResourceRepresentor<E> addParam(String key, Object value){
        this.requestParams.put(key, value);
        return this;
    }

    public Object getParam(String key) {
        return requestParams.get(key);
    }

    /**
     * to be overrided by children type.
     * convert to an resource object which support links
     * would not be a ResourceSupport object
     * @param entity
     * @return
     */
    abstract public  GeneralResource<E> toRestResource(E entity);


    public GeneralResource<E> fillData(E entity) {
        ResourceSupport links= toResource(entity);
        GeneralResource<E> resource = new GeneralResource<E>(links).copyPropertiesFromEntity(entity);
        return resource;
    }

    @Override
    public ResourceSupport toResource(E entity) {
        return instantiateResource(entity);
    }

    /**
     *
     * @param entities
     * @return GeneralResource: {"domainObject": List<GeneralResource<E>>, links: ... }
     */
    public GeneralResource toRestResources(Iterable<E> entities) {
        Assert.notNull(entities);
        List<GeneralResource> list = new ArrayList<>();
        for (E entity : entities) {
            list.add(toRestResource(entity));
        }

        GeneralResource result = new GeneralResource();
        result.put("domainObject", list);
        return result;
    }
}
