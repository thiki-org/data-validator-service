package org.thiki.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.List;

/**
 * represetation for an entity of any type
 * use ResourceSupport just for build the links
 * Created by joeaniu on 10/18/16.
 */
public class GeneralResource<E> extends HashMap<String, Object> implements Identifiable<Link>
{
    
    private ResourceSupport rs;

    public GeneralResource() {
        this(new ResourceSupport());
    }

    public GeneralResource(ResourceSupport rs){
        this.rs = rs;
        this.put("links", rs.getLinks());
    }


    /**
     * copy all valid properties from entity into hashmap
     * @param entity
     */
    public GeneralResource<E> copyPropertiesFromEntity(E entity) {
        PropertiesUtil.copy(this, entity);
        return this;
    }


    /* proxy the method of ResourceSupport*/

    /**
     * Returns the {@link Link} with a rel of {@link Link#REL_SELF}.
     */
    @JsonIgnore
    @Override
    public Link getId() {
    return rs.getLink(Link.REL_SELF);
    }

    /**
     * Adds the given link to the resource.
     *
     * @param link
     */
    public void add(Link link) {
        rs.add(link);
    }

    /**
     * Adds all given {@link Link}s to the resource.
     *
     * @param links
     */
    public void add(Iterable<Link> links) {
        rs.add(links);
    }

    /**
     * Adds all given {@link Link}s to the resource.
     *
     * @param links must not be {@literal null}.
     */
    public void add(Link... links) {
        rs.add(links);
    }

    /**
     * Returns whether the resource contains {@link Link}s at all.
     *
     * @return
     */
    public boolean hasLinks() {
        return rs.hasLinks();
    }

    /**
     * Returns whether the resource contains a {@link Link} with the given rel.
     *
     * @param rel
     * @return
     */
    public boolean hasLink(String rel) {
        return rs.hasLink(rel);
    }

    /**
     * Returns all {@link Link}s contained in this resource.
     *
     * @return
     */
    @XmlElement(name = "link", namespace = Link.ATOM_NAMESPACE)
    @JsonProperty("links")
    public List<Link> getLinks() {
        return rs.getLinks();
    }

    /**
     * Removes all {@link Link}s added to the resource so far.
     */
    public void removeLinks() {
        rs.removeLinks();
    }

    /**
     * Returns the link with the given rel.
     *
     * @param rel
     * @return the link with the given rel or {@literal null} if none found.
     */
    public Link getLink(String rel) {
        return rs.getLink(rel);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return rs.toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        }

        GeneralResource that = (GeneralResource) obj;

        return this.rs.equals(that.rs);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.rs.hashCode();
    }

	public String getDomainObjectKey() {
		return "domainObject";
		
	}
}
