package ru.job4j.generics;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
public class RoleStore extends AbstractStore<Role> {
    public RoleStore(SimpleArray<Role> t) {
        super(t);
    }
}
