package lesson4.hw.model;

import java.util.Objects;

public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;


    public File(String name, String format, long size) {
        try {
            if (fileNameChecker(name)) {
                this.name = name;
                this.format = format;
                this.size = size;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public File(String name, String format, long size, Storage storage) {
        try {
            if (fileNameChecker(name)) {
                this.name = name;
                this.format = format;
                this.size = size;
                this.storage = storage;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public File(long id, String name, String format, long size) {
        try {
            if (fileNameChecker(name)) {
                this.name = name;
                this.id = id;
                this.format = format;
                this.size = size;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public File(long id, String name, String format, long size, Storage storage) {
        try {
            if (fileNameChecker(name)) {
                this.name = name;
                this.id = id;
                this.format = format;
                this.size = size;
                this.storage = storage;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name.length() > 10) {
            throw new IllegalArgumentException("a file name can not be mare than 10 characters");
        }
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    // Файлы считаем одинаковыми если у них равные id и имя
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id == file.id &&
                Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                '}';
    }

    private boolean fileNameChecker(String name) throws IllegalArgumentException {
        if (name.length() <= 10) {
            return true;
        }
        throw new IllegalArgumentException("error: this file can not be created " +
                "\n because a file name can not be mare than 10 characters");

    }
}

