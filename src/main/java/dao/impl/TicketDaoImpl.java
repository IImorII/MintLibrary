package dao.impl;

import dao.BaseDao;
import dao.TicketDao;
import dao.mapper.Mapper;
import dao.mapper.TicketMapper;
import entity.Ticket;
import exception.ConnectionException;
import exception.DaoException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TicketDaoImpl implements TicketDao {

    private static TicketDaoImpl INSTANCE;

    private TicketDaoImpl() {
    }

    public static TicketDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicketDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from ticket where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from ticket where name = ?";
    private static final String GET_ALL = "select * from ticket";
    private static final String CREATE = "insert into ticket (amount_current, type_id_fk) values (?, ?)";
    private static final String UPDATE = "update ticket set amount_current = ?, type_id_fk = ? where id = ?";
    private static final String DELETE = "delete from ticket where id = ?";

    @Override
    public List<Ticket> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<Ticket> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Arrays.asList(id));
    }

    @Override
    public Optional<Ticket> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Arrays.asList(name));
    }

    @Override
    public void create(Ticket entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(entity.getName(), entity.getAmountCurrent(), entity.getType().getId()));
    }

    @Override
    public void update(Ticket entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getAmountCurrent(), entity.getType().getId(), entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public Mapper<Ticket> getMapper() {
        return new TicketMapper();
    }
}
