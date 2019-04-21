package hibernate.lesson4.dao;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.*;
import hibernate.lesson4.model.Order;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.*;

public class RoomDAO extends GeneralDAO<Room> {
    private String hqlFindById = "from Room where id = ";

    private UserDAO userDAO = InstanceFactory.getInstanceUserDAO();
    private OrderDAO orderDAO = InstanceFactory.getInstanceOrderDAO();


    public List<Room> findRooms(Filter filter) throws Exception {
        List<Room> rooms = foundRoomsByHotelFilter(filter);
        if (rooms == null || rooms.size() == 0) {
            throw new BadRequestException("No matching rooms found.");
        }
        return rooms;
    }


    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws BadRequestException {
        Order order = new Order();
        order.setRoom(findById(roomId));
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setUserOrdered(userDAO.findById(userId));
        orderDAO.save(order);
    }


    public void cancelReservation(long orderId) {
        orderDAO.delete(orderId);
    }


    public Room save(Room room) throws BadRequestException {
        return saveEntity(room);
    }

    public Room update(Room room) {
        return updateEntity(room);
    }

    public void delete(long id) {
        deleteEntity(findById(id));
    }

    public Room findById(long id) {
        return findEntityBy(hqlFindById + id).get(0);
    }


    private List<Room> foundRoomsByHotelFilter(Filter filter) {
        Session session = InstanceFactory.getInstanceSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);
        Root<Room> rootRoom = criteriaQuery.from(Room.class);
        criteriaQuery.select(rootRoom);
        Join<Room, Hotel> hotelJoin = rootRoom.join("hotel");

        List<Predicate> restrictions = new ArrayList<>();

        if (filter.getDateAvailableFrom() != null
            || filter.isBreakfastIncluded() != null
            || filter.isPetsAllowed() != null
            || filter.getNumberOfGuests() != null
            || filter.getMaxPrice() != null
            || filter.getMinPrice() != null) {

            if (filter.getDateAvailableFrom() != null) {
                restrictions.add(builder.lessThan(rootRoom.get("dateAvailableFrom"), filter.getDateAvailableFrom()));
            }
            if (filter.isBreakfastIncluded() != null) {
                restrictions.add(builder.equal(rootRoom.get("breakfastIncluded"), filter.isBreakfastIncluded()));
            }
            if (filter.isPetsAllowed() != null) {
                restrictions.add(builder.equal(rootRoom.get("petsAllowed"), filter.isPetsAllowed()));
            }
            if (filter.getNumberOfGuests() != null) {
                restrictions.add(builder.equal(rootRoom.get("numberOfGuests"), filter.getNumberOfGuests()));
            }
            if (filter.getMaxPrice() != null) {
                restrictions.add(builder.lessThanOrEqualTo(rootRoom.get("price"), filter.getMaxPrice()));
            }
            if (filter.getMinPrice() != null) {
                restrictions.add(builder.greaterThanOrEqualTo(rootRoom.get("price"), filter.getMinPrice()));
            }
            if (filter.getName() != null) {
                restrictions.add(builder.equal(hotelJoin.get("name"), filter.getName()));
            }
            if (filter.getCountry() != null) {
                restrictions.add(builder.equal(hotelJoin.get("country"), filter.getCountry()));
            }
            if (filter.getCity() != null) {
                restrictions.add(builder.equal(hotelJoin.get("city"), filter.getCity()));
            }

            if (!restrictions.isEmpty()) {
                Predicate[] predicates = new Predicate[restrictions.size()];
                restrictions.toArray(predicates);
                criteriaQuery.where(predicates);
                return session.createQuery(criteriaQuery).list();
            }
        }
        return null;
    }

}
