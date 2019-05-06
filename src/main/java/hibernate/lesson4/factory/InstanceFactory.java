package hibernate.lesson4.factory;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.service.HotelService;
import hibernate.lesson4.service.OrderService;
import hibernate.lesson4.service.RoomService;
import hibernate.lesson4.service.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InstanceFactory {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public static HotelDAO instanceHotelDAO  = new HotelDAO();
    public static OrderDAO instanceOrderDAO = new OrderDAO();
    public static RoomDAO instanceRoomDAO = new RoomDAO();
    public static UserDAO instanceUserDAO = new UserDAO();
    public static HotelService instanceHotelService = new HotelService();
    public static OrderService instanceOrderService = new OrderService();
    public static RoomService instanceRoomService = new RoomService();
    public static UserService instanceUserService = new UserService();

}
