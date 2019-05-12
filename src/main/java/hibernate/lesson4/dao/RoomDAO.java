package hibernate.lesson4.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.*;
import hibernate.lesson4.model.Order;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.*;

public class RoomDAO extends GeneralDAO<Room> {
    private String hqlFindById = "from Room where id = ";

    private UserDAO userDAO = InstanceFactory.instanceUserDAO;
    private OrderDAO orderDAO = InstanceFactory.instanceOrderDAO;


    public List<Room> findRooms(Filter filter) throws Exception {
        List<Room> rooms = foundRoomsByHotelFilter(filter);
        if (rooms == null || rooms.size() == 0) {
            throw new BadRequestException("No matching rooms found.");
        }
        return rooms;
    }


    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
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


    public Room save(Room room) {
        return saveEntity(room);
    }

    public Room update(Room room) {
        return updateEntity(room);
    }

    public void delete(long id) {
        deleteEntity(findById(id));
    }

    public Room findById(long id) {
        return findEntityByHql(hqlFindById + id).get(0);
    }


    private List<Room> foundRoomsByHotelFilter(Filter filter) {
        try (Session session = InstanceFactory.sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);
            Root<Room> rootRoom = criteriaQuery.from(Room.class);
            criteriaQuery.select(rootRoom);
            Join<Room, Hotel> hotelJoin = rootRoom.join("hotel");

            if (filter.getDateAvailableFrom() != null
                    || filter.isBreakfastIncluded() != null
                    || filter.isPetsAllowed() != null
                    || filter.getNumberOfGuests() != null
                    || filter.getMaxPrice() != null
                    || filter.getMinPrice() != null) {

                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> mainMap = objectMapper.convertValue(filter, Map.class);

                Map<String, Object> equalRoom = new HashMap<>();
                Map<String, Object> equalHotel = new HashMap<>();
                Map<String, Object> lessThanRoom = new HashMap<>();
                Map<String, Object> lessThanOrEqualToRoom = new HashMap<>();
                Map<String, Object> greaterThanOrEqualToRoom = new HashMap<>();

                mainMap.forEach((k, v) -> {
                    switch (k) {
                        case "dateAvailableFrom": {
                            lessThanRoom.put(k, v);
                            break;
                        }
                        case "breakfastIncluded": {
                            equalRoom.put(k, v);
                            break;
                        }
                        case "petsAllowed": {
                            equalRoom.put(k, v);
                            break;
                        }
                        case "numberOfGuests": {
                            equalRoom.put(k, v);
                            break;
                        }
                        case "maxPrice": {
                            lessThanOrEqualToRoom.put(k, v);
                            break;
                        }
                        case "minPrice": {
                            greaterThanOrEqualToRoom.put(k, v);
                            break;
                        }
                        case "name": {
                            equalHotel.put(k, v);
                            break;
                        }
                        case "country": {
                            equalHotel.put(k, v);
                            break;
                        }
                        case "city": {
                            equalHotel.put(k, v);
                            break;
                        }

                    }
                });

                List<Predicate> restrictions = new ArrayList<>();

               equalRoom.forEach((key, v) -> {
                    if (equalRoom.get(key) != null) {
                        restrictions.add(builder.equal(rootRoom.get(key), equalRoom.get(key)));
                    }
                });

                equalHotel.forEach((key, v) -> {
                    if (equalHotel.get(key) != null) {
                        restrictions.add(builder.equal(hotelJoin.get(key), equalHotel.get(key)));
                    }
                });

                lessThanRoom.forEach((key, v) -> {
                    if (lessThanRoom.get(key) != null) {
                        restrictions.add(builder.lessThan(rootRoom.get(key), (Date) lessThanRoom.get(key)));
                    }
                });

                lessThanOrEqualToRoom.forEach((key, v) -> {
                    if (lessThanOrEqualToRoom.get(key) != null) {
                        restrictions.add(builder.lessThanOrEqualTo(rootRoom.get("price"), (Double) lessThanOrEqualToRoom.get(key)));
                    }
                });

                greaterThanOrEqualToRoom.forEach((key, v) -> {
                    if (greaterThanOrEqualToRoom.get(key) != null) {
                        restrictions.add(builder.greaterThanOrEqualTo(rootRoom.get("price"), (Double) greaterThanOrEqualToRoom.get(key)));
                    }
                });

                if (!restrictions.isEmpty()) {
                    Predicate[] predicates = new Predicate[restrictions.size()];
                    restrictions.toArray(predicates);
                    criteriaQuery.where(predicates);
                    return session.createQuery(criteriaQuery).list();
                }
            }
        }
        return null;
    }

}
