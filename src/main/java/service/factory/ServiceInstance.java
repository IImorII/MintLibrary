package service.factory;

import service.Service;
import service.impl.*;

public enum ServiceInstance {
    ROLE(RoleServiceImpl.getInstance()),
    GENRE(GenreServiceImpl.getInstance()),
    LANGUAGE(LanguageServiceImpl.getInstance()),
    AUTHOR(AuthorServiceImpl.getInstance()),
    BOOK(BookServiceImpl.getInstance()),
    ACCOUNT(AccountServiceImpl.getInstance());

    private final Service service;

    ServiceInstance(Service service) {
        this.service = service;
    }

    public static Service of(String name) {
        for (ServiceInstance s: values()) {
            if (s.name().equalsIgnoreCase(name)) {
                return s.service;
            }
        }
        return null;
    }
}
