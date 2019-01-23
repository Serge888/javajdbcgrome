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
        List<File> filesFrom = fileDao.findAllInStorage(storageFrom.getId());
        List<File> filesTo = fileDao.findAllInStorage(storageTo.getId());
        allFilesTransferValidation(storageFrom, storageTo, filesFrom, filesTo);
        for (File file : filesFrom) {
            file.setStorage(storageTo);
        }
        fileDao.update(filesFrom);
    }

    //    - трансфер файла с хранилища storageFrom по его айди Если операцию выполнить
// невозможно, пишите ошибку в консоль и выбрасывайте исключение (подумайте какое)
// с текстом ошибки. В тексте пишите ее причину и обязательно включайте id файла,
// который не удалось доставить и id хранилища, куда доставка не удалась
    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws BadRequestException {
        List<File> filesTo = fileDao.findAllInStorage(storageTo.getId());
        File file = fileDao.findById(id);
        if (file.getStorage() == null || !file.getStorage().equals(storageFrom)) {
            throw new BadRequestException("File with id " + file.getId() + " doesn't exist in storage id "
                    + storageFrom.getId());
        }
        oneFileValidation(filesTo, file, storageTo);
        file.setStorage(storageTo);
        List<File> files = new ArrayList<>();
        files.add(file);
        fileDao.update(files);
    }

    private void oneFileValidation(List<File> filesTo, File file, Storage storageTo) throws BadRequestException {
        long availableSpaceTo = storageTo.getStorageMaxSize() - takenPlaceInStorage(filesTo);
        if (file.getSize() > availableSpaceTo) {
            throw new BadRequestException("Space in storage with id " + storageTo.getId() + " is not enough " +
                    " for file id " + file.getId());
        }
        isFormatSupported(storageTo, file);
        isFileAlreadyInStorage(filesTo, file, storageTo.getId());
    }

    private void allFilesTransferValidation(Storage storageFrom, Storage storageTo,
                                            List<File> filesFrom, List<File> filesTo) throws BadRequestException {
        long availableSpaceTo = storageTo.getStorageMaxSize() - takenPlaceInStorage(filesTo);
        if (takenPlaceInStorage(filesFrom) > availableSpaceTo) {
            throw new BadRequestException("Space in storage with id " + storageTo.getId() + " is not enough");
        }
        for (File file : filesFrom) {
            isFormatSupported(storageTo, file);
        }
        for (File file : filesFrom) {
            isFileAlreadyInStorage(filesTo, file, storageTo.getId());
        }
    }



    private long takenPlaceInStorage(List<File> files) {
        long takenPlace = 0;
        for (File file : files) {
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

    private void isFileAlreadyInStorage(List<File> filesTo, File file , long storageToId) throws BadRequestException {
        long id = file.getId();
        for (File fileEl : filesTo) {
            if (id == fileEl.getId()) {
                throw new BadRequestException("File id " + file.getId() + " already exists in storage id "
                        + storageToId);
            }
        }

    }

}
