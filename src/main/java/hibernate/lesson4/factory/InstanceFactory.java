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
    private static SessionFactory sessionFactory;
    private static HotelDAO instanceHotelDAO;
    private static OrderDAO instanceOrderDAO;
    private static RoomDAO instanceRoomDAO;
    private static UserDAO instanceUserDAO;
    private static HotelService instanceHotelService;
    private static OrderService instanceOrderService;
    private static RoomService instanceRoomService;
    private static UserService instanceUserService;
    
    
    public static HotelDAO getInstanceHotelDAO() {
        if (instanceHotelDAO == null) {
            instanceHotelDAO = new HotelDAO();
        }
        return instanceHotelDAO;
    } 
    public static OrderDAO getInstanceOrderDAO() {
        if (instanceOrderDAO == null) {
            instanceOrderDAO = new OrderDAO();
        }
        return instanceOrderDAO;
    } 
    public static RoomDAO getInstanceRoomDAO() {
        if (instanceRoomDAO == null) {
            instanceRoomDAO = new RoomDAO();
        }
        return instanceRoomDAO;
    } 
    public static UserDAO getInstanceUserDAO() {
        if (instanceUserDAO == null) {
            instanceUserDAO = new UserDAO();
        }
        return instanceUserDAO;
    }

    public static HotelService getInstanceHotelService() {
        if (instanceHotelService == null) {
            instanceHotelService = new HotelService();
        }
        return instanceHotelService;
    }
    public static OrderService getInstanceOrderService() {
        if (instanceOrderService == null) {
            instanceOrderService = new OrderService();
        }
        return instanceOrderService;
    }
    public static RoomService getInstanceRoomService() {
        if (instanceRoomService == null) {
            instanceRoomService = new RoomService();
        }
        return instanceRoomService;
    }
    public static UserService getInstanceUserService() {
        if (instanceUserService == null) {
            instanceUserService = new UserService();
        }
        return instanceUserService;
    }

    public static SessionFactory getInstanceSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
