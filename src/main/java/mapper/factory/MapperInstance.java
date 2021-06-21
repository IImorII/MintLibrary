package mapper.factory;
import controller.command.Command;
import controller.command.CommandInstance;
import mapper.AccountMapper;
import mapper.AuthorMapper;
import mapper.BookMapper;
import mapper.GenreMapper;
import mapper.LanguageMapper;
import mapper.Mapper;
import mapper.RoleMapper;

public enum MapperInstance {
    ROLE(RoleMapper.getInstance()),
    GENRE(GenreMapper.getInstance()),
    LANGUAGE(LanguageMapper.getInstance()),
    AUTHOR(AuthorMapper.getInstance()),
    BOOK(BookMapper.getInstance()),
    ACCOUNT(AccountMapper.getInstance());

    private final Mapper mapper;

    MapperInstance(Mapper mapper) {
        this.mapper = mapper;
    }
    public static Mapper of(String name) {
        for (MapperInstance m: values()) {
            if (m.name().equalsIgnoreCase(name)) {
                return m.mapper;
            }
        }
        return null;
    }
}
