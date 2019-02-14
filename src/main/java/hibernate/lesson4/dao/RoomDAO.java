package hibernate.lesson4.dao;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.util.*;

public class RoomDAO extends GeneralDAO<Room> {
    private static RoomDAO instance;

    public RoomDAO() {
    }

    public static RoomDAO getInstance() {
        if (instance == null) {
            instance = new RoomDAO();
        }
        return instance;
    }

    private UserDAO userDAO = new UserDAO();
    private OrderDAO orderDAO = new OrderDAO();


    public List<Room> findRooms(Filter filter) throws Exception {
        List<Room> rooms = new ArrayList<>(findEntityBy(createRequestByFiltr(filter)));
        if (rooms.size() > 0) {
            return foundRoomsByHotelFilter(filter, rooms);
            }
        throw new BadRequestException("No matching rooms found.");
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
        String hqlFindById = "from Room where id = " + id;
        return findEntityBy(hqlFindById).get(0);
    }


    private String createRequestByFiltr(Filter filter) {
        String hqlFilterRequest = "from Room";
        if (filter.getDateAvailableFrom() != null
            || filter.isBreakfastIncluded() != null
            || filter.isPetsAllowed() != null
            || filter.getNumberOfGuests() != null
            || filter.getMaxPrice() != null
            || filter.getMinPrice() != null) {

            List<String> criteria = new ArrayList<>();
            criteria.add(" where"); // criteria(0)
            criteria.add(" and"); // criteria(1)
            // criteria(2)
            if (filter.getDateAvailableFrom() != null) {
                criteria.add(" DATE_AVAILABLE_FROM < " + filter.getDateAvailableFrom());
            } else {
                criteria.add(null);
            }
            // criteria(3)
            if (filter.isBreakfastIncluded() != null) {
                criteria.add(" BREAKFAST_INCLUDED = " + filter.isBreakfastIncluded());
            } else {
                criteria.add(null);
            }
            // criteria(4)
            if (filter.isPetsAllowed() != null) {
                criteria.add(" PETS_ALLOWED = " + filter.isPetsAllowed());
            } else {
                criteria.add(null);
            }
            // criteria(5)
            if (filter.getNumberOfGuests() != null) {
                criteria.add(" NUMBER_OF_GUESTS = " + filter.getNumberOfGuests());
            } else {
                criteria.add(null);
            }
            // criteria(6)
            if (filter.getMaxPrice() != null) {
                criteria.add(" PRICE >= " + filter.getMaxPrice());
            } else {
                criteria.add(null);
            }
            // criteria(7)
            if (filter.getMinPrice() != null) {
                criteria.add(" PRICE <= " + filter.getMaxPrice());
            } else {
                criteria.add(null);
            }
            int i = 2;
            while (criteria.get(i) == null) {
                i++;
            }
            hqlFilterRequest = hqlFilterRequest + criteria.get(0) + criteria.get(i);

            for (i = i + 1; i < 8; i++) {
                if (criteria.get(i) != null) {
                    hqlFilterRequest = hqlFilterRequest + criteria.get(1) + criteria.get(i);
                }
            }
        }
        return hqlFilterRequest;
    }

    private List<Room> foundRoomsByHotelFilter(Filter filter, List<Room> rooms) throws Exception {
        List<Room> foundRooms = new ArrayList<>();
        if (filter.getName() != null
                || filter.getCountry() != null
                || filter.getCity() != null) {
            for (Room room : rooms) {
                if (filter.getName() != null && filter.getName().equals(room.getHotel().getName())) {
                    foundRooms.add(room);
                } else if (filter.getCountry() != null && filter.getCountry().equals(room.getHotel().getCountry())) {
                    foundRooms.add(room);
                } else if (filter.getCity() != null && filter.getCity().equals(room.getHotel().getCity())) {
                    foundRooms.add(room);
                }
            }
            if (foundRooms.size() == 0) {
                throw new BadRequestException("No matching rooms found.");
            }
        } else {
            foundRooms = rooms;
        }
        return foundRooms;
    }
}
