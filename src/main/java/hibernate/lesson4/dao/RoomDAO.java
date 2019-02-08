package hibernate.lesson4.dao;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.util.*;

public class RoomDAO extends GeneralDAO<Room> {

    public List<Room> findRooms(Filter filter) throws Exception {
        List<Hotel> foundHotels = foundHotelIds(filter);
        LinkedList<Room> rooms = new LinkedList<>(getAll());
        if (foundHotels.size() > 0) {
            Set<Room> suitableRooms = new HashSet<>();
            for (Room room : rooms) {
                for (Hotel hotel : foundHotels) {
                    if (room.getHotel().equals(hotel)) {
                        suitableRooms.add(room);
                    }
                }
            }
            rooms = new LinkedList<>(suitableRooms);
        }
        return foundRoomsByFilter(filter, rooms);
    }


    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo, double moneyPaid)
            throws BadRequestException {
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
        order.setRoom(findById(roomId));
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setUserOrdered(userDAO.findById(userId));
        order.setMoneyPaid(moneyPaid);
        orderDAO.save(order);
    }


    public void cancelReservation(long orderId) {
        OrderDAO orderDAO = new OrderDAO();
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

    public List<Room> getAll() {
        String hqlFindById = "from Room";
        return findEntityBy(hqlFindById);
    }

    private List<Room> foundRoomsByFilter(Filter filter, LinkedList<Room> rooms) throws BadRequestException {
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room itrRoom = iterator.next();
            if (filter.getDateAvailableFrom() != null
                    && itrRoom.getDateAvailableFrom() != null
                    && filter.getDateAvailableFrom().before(itrRoom.getDateAvailableFrom())) {
                iterator.remove();
            } else if (filter.isBreakfastIncluded()
                    && itrRoom.getBreakfastIncluded() == 0) {
                iterator.remove();
            } else if (filter.isPetsAllowed()
                    && itrRoom.getPetsAllowed() == 0) {
                iterator.remove();
            } else if (filter.getNumberOfGuests() != null
                    && !filter.getNumberOfGuests().equals(itrRoom.getNumberOfGuests())) {
                iterator.remove();
            } else if (filter.getMaxPrice() != null
                    && filter.getMaxPrice() < itrRoom.getPrice()) {
                iterator.remove();
            } else if (filter.getMinPrice() != null
                    && filter.getMinPrice() > itrRoom.getPrice()) {
                iterator.remove();
            }
        }
        List<Room> foundRooms = new ArrayList<>(rooms);
        if (foundRooms.size() == 0) {
            throw new BadRequestException("No matching rooms found.");
        }
        return foundRooms;
    }

    private List<Hotel> foundHotelIds(Filter filter) throws Exception {
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> foundHotels = new ArrayList<>();
        if (filter.getName() != null
                || filter.getCountry() != null
                || filter.getCity() != null) {
            List<Hotel> hotels = new LinkedList<>(hotelDAO.getAll());
            for (Hotel hotel : hotels) {
                if (filter.getName() != null && filter.getName().equals(hotel.getName())) {
                    foundHotels.add(hotel);
                } else if (filter.getCountry() != null && filter.getCountry().equals(hotel.getCountry())) {
                    foundHotels.add(hotel);
                } else if (filter.getCity() != null && filter.getCity().equals(hotel.getCity())) {
                    foundHotels.add(hotel);
                }
            }
            if (foundHotels.size() == 0) {
                throw new BadRequestException("No matching rooms found.");
            }
        }
        return foundHotels;
    }
}
