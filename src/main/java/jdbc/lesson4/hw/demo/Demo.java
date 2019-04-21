package jdbc.lesson4.hw.demo;

import jdbc.lesson4.hw.controller.Controller;
import jdbc.lesson4.hw.exception.BadRequestException;
import jdbc.lesson4.hw.repository.StorageDao;

public class Demo {
    public static void main(String[] args) throws BadRequestException {
//        FileDao fileDao = new FileDao();
        StorageDao storageDao = new StorageDao();
        Controller controller = new Controller();
//        File file1 = new File("file11", "txt", 10, storageDao.findEntityBy(24));

//        File file2 = new File(21,"file20", "jpg", 20, storageDao.findEntityBy(24));
//        fileDao.saveEntity(file2);

//        controller.put(storageDao.findEntityBy(24), file2);
//        controller.transferFile(storageDao.findEntityBy(21), storageDao.findEntityBy(2), 8);
        controller.transferAll(storageDao.findById(25), storageDao.findById(24));
//        controller.transferFile(storageDao.findEntityBy(24), storageDao.findEntityBy(25), 4);


//        fileDao.saveEntity(storageDao.findEntityBy(24), file1);
//        fileDao.saveEntity(storageDao.findEntityBy(21), file2);

//        System.out.println(fileDao.findEntityBy(4));
//        fileDao.updateEntity(file2);
//        fileDao.deleteEntity(9);






//        String[] fomats = {"txt","jpg","psd"};
//        String[] fomats1 = {"txt","jpg"};
//        Storage storage = new Storage(24,"Poland", 10000, fomats1);

//        storageDao.saveEntity(storage1);
//        System.out.println(storageDao.findEntityBy(24).toString());

//        storageDao.deleteEntity(23);
//        storageDao.updateEntity(storage);



    }
}
