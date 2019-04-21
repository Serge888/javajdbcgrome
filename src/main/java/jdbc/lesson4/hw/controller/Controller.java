package jdbc.lesson4.hw.controller;

import jdbc.lesson4.hw.exception.BadRequestException;
import jdbc.lesson4.hw.model.File;
import jdbc.lesson4.hw.model.Storage;
import jdbc.lesson4.hw.service.Service;

public class Controller {
    Service service = new Service();

    // - добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    public File put(Storage storage, File file) throws BadRequestException {
        return service.put(storage, file);
    }


    public void delete(Storage storage, File file) throws BadRequestException {
        service.delete(storage, file);
    }


    // - трансфер всех файлов
    public void transferAll(Storage storageFrom, Storage storageTo) throws BadRequestException {
        service.transferAll(storageFrom, storageTo);

    }

    //    - трансфер файла с хранилища storageFrom по его айди
    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws BadRequestException {
        service.transferFile(storageFrom, storageTo, id);

    }

}
