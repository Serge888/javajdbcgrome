package lesson4.hw.service;

import lesson4.hw.exception.BadRequestException;
import lesson4.hw.model.File;
import lesson4.hw.model.Storage;
import lesson4.hw.repository.FileDao;

import java.util.ArrayList;
import java.util.List;

public class Service {
    FileDao fileDao = new FileDao();

    // - добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    public File put(Storage storage, File file) throws BadRequestException {
        file.setStorage(storage);
        if(file.getStorage() != storage) {
            throw new BadRequestException("The file id " + file.getId()
                    + " is not in the storage id " + storage.getId());
        }
        List<File> files = new ArrayList<>();
        files.add(file);
        fileDao.update(files);
        return file;
    }


    public void delete(Storage storage, File file) throws BadRequestException {
        if(file.getStorage() == storage) {
            file.setStorage(null);
        } else {
            throw new BadRequestException("The file id " + file.getId()
                    + " is not in the storage id " + storage.getId());
        }
        List<File> files = new ArrayList<>();
        files.add(file);
        fileDao.update(files);

    }


    // - трансфер всех файлов
    public void transferAll(Storage storageFrom, Storage storageTo) throws BadRequestException {
        allFilesTransferValidation(storageFrom, storageTo);
        List<File> files = fileDao.findAllInStorage(storageFrom.getId());
        for (File file : files) {
            file.setStorage(storageTo);
        }
        fileDao.update(files);
    }

    //    - трансфер файла с хранилища storageFrom по его айди Если операцию выполнить
// невозможно, пишите ошибку в консоль и выбрасывайте исключение (подумайте какое)
// с текстом ошибки. В тексте пишите ее причину и обязательно включайте id файла,
// который не удалось доставить и id хранилища, куда доставка не удалась
    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws BadRequestException {
        File file = fileDao.findById(id);
        if (file.getStorage() == null || !file.getStorage().equals(storageFrom)) {
            throw new BadRequestException("File with id " + file.getId() + " doesn't exist in storage id "
                    + storageFrom.getId());
        }
        oneFileValidation(storageTo, file);
        file.setStorage(storageTo);
        List<File> files = new ArrayList<>();
        files.add(file);
        fileDao.update(files);
    }

    private void oneFileValidation(Storage storageTo, File file) throws BadRequestException {
        long availableSpaceTo = storageTo.getStorageMaxSize() - takenPlaceInStorage(storageTo);
        if (file.getSize() > availableSpaceTo) {
            throw new BadRequestException("Space in storage with id " + storageTo.getId() + " is not enough " +
                    " for file id " + file.getId());
        }
        isFormatSupported(storageTo, file);
        isFileAllreadyInStorage(storageTo, file);
    }

    private void allFilesTransferValidation(Storage storageFrom, Storage storageTo) throws BadRequestException {
        long availableSpaceTo = storageTo.getStorageMaxSize() - takenPlaceInStorage(storageTo);
        if (takenPlaceInStorage(storageFrom) > availableSpaceTo) {
            throw new BadRequestException("Space in storage with id " + storageTo.getId() + " is not enough");
        }
        for (File file : fileDao.findAllInStorage(storageFrom.getId())) {
            isFormatSupported(storageTo, file);
        }
        for (File file : fileDao.findAllInStorage(storageFrom.getId())) {
            isFileAllreadyInStorage(storageTo, file);
        }
    }



    private long takenPlaceInStorage(Storage storage) {
        long takenPlace = 0;
        List<File> filesInTo = fileDao.findAllInStorage(storage.getId());
        for (File file : filesInTo) {
            takenPlace += file.getSize();
        }
        return takenPlace;
    }

    private void isFormatSupported(Storage storageTo, File file) throws BadRequestException {
        for (String format : storageTo.getFormatsSupported()) {
            if (file.getFormat().equals(format)) {
                return;
            }
        }
        throw new BadRequestException("Transfer failed. File id " + file.getId() + " has format ("
                + file.getFormat() + ") that doesn't supported by " +
                "storage id " + storageTo.getId());
    }

    private void isFileAllreadyInStorage(Storage storageTo, File file) throws BadRequestException {
        long id = file.getId();
        for (File fileEl : fileDao.findAllInStorage(storageTo.getId())) {
            if (id == fileEl.getId()) {
                throw new BadRequestException("File id " + file.getId() + " already exists in storage id "
                        + storageTo.getId());
            }
        }

    }

}
