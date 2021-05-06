package dao.impl;

import dao.BaseDao;
import dao.TicketTypeDao;
import dao.mapper.Mapper;
import dao.mapper.TicketTypeMapper;
import entity.TicketType;
import exception.ConnectionException;
import exception.DaoException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TicketTypeDaoImpl implements TicketTypeDao {

    private static TicketTypeDaoImpl INSTANCE;

    private TicketTypeDaoImpl() {

    }

    public static TicketTypeDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicketTypeDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from ticket_type where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from ticket_type where role = ?";
    private static final String GET_ALL = "select * from ticket_type";
    private static final String CREATE = "insert into ticket_type (name, amount_max) values (?, ?)";
    private static final String UPDATE = "update ticket_type set name = ?, amount_max = ? where id = ?";
    private static final String DELETE = "delete from ticket_type where id = ?";

    @Override
    public List<TicketType> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<TicketType> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Arrays.asList(id));
    }

    @Override
    public Optional<TicketType> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Arrays.asList(name));
    }

    @Override
    public void create(TicketType entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(entity.getName(), entity.getAmountMax()));
    }

    @Override
    public void update(TicketType entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getAmountMax(), entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public Mapper<TicketType> getMapper() {
        return new TicketTypeMapper();
    }
}
