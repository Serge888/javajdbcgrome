package lesson4.hw.demo;

import lesson4.hw.controller.Controller;
import lesson4.hw.exception.BadRequestException;
import lesson4.hw.model.File;
import lesson4.hw.model.Storage;
import lesson4.hw.repository.FileDao;
import lesson4.hw.repository.StorageDao;

public class Demo {
    public static void main(String[] args) throws BadRequestException {
//        FileDao fileDao = new FileDao();
        StorageDao storageDao = new StorageDao();
        Controller controller = new Controller();
//        File file1 = new File("file11", "txt", 10, storageDao.findById(24));

//        File file2 = new File(21,"file20", "jpg", 20, storageDao.findById(24));
//        fileDao.save(file2);

//        controller.put(storageDao.findById(24), file2);
//        controller.transferFile(storageDao.findById(21), storageDao.findById(2), 8);
//        controller.transferAll(storageDao.findById(2), storageDao.findById(24));
        controller.transferFile(storageDao.findById(24), storageDao.findById(25), 6);


//        fileDao.save(storageDao.findById(24), file1);
//        fileDao.save(storageDao.findById(21), file2);

//        System.out.println(fileDao.findById(4));
//        fileDao.update(file2);
//        fileDao.delete(9);






//        String[] fomats = {"txt","jpg","psd"};
//        String[] fomats1 = {"txt","jpg"};
//        Storage storage = new Storage(24,"Poland", 10000, fomats1);

//        storageDao.save(storage1);
//        System.out.println(storageDao.findById(24).toString());

//        storageDao.delete(23);
//        storageDao.update(storage);



    }
}
