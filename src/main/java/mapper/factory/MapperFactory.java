package mapper.factory;

import dao.BaseDao;
import dao.impl.*;
import entity.BaseEntity;
import mapper.*;

public abstract class MapperFactory {
    public static Mapper get(Class<? extends BaseEntity> entity) {
        return switch (entity.getSimpleName().toLowerCase()) {
            case "account" -> AccountMapper.getInstance();
            case "author" -> AuthorMapper.getInstance();
            case "book" -> BookMapper.getInstance();
            case "genre" -> GenreMapper.getInstance();
            case "language" -> LanguageMapper.getInstance();
            case "role" -> RoleMapper.getInstance();
            default -> null;
        };
    }
}
